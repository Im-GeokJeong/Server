package IGJ.imgeokjeong.office.service;

import IGJ.imgeokjeong.errors.exception.NotExistOfficeException;
import IGJ.imgeokjeong.errors.response.CommonResponse;
import IGJ.imgeokjeong.errors.response.ResponseService;
import IGJ.imgeokjeong.office.domain.Machine;
import IGJ.imgeokjeong.office.domain.Office;
import IGJ.imgeokjeong.office.dto.DetailResponse;
import IGJ.imgeokjeong.office.dto.NearestRequest;
import IGJ.imgeokjeong.office.repository.OfficeRepository;
import IGJ.imgeokjeong.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OfficeService {

    private final OfficeRepository officeRepository;
    private final ResponseService responseService;

    private final DataUtil dataUtil = new DataUtil();

    @Transactional
    public CommonResponse list() {
        return responseService.getListResponse(officeRepository.findAll());
    }

    @Transactional
    public CommonResponse detail(Long id) {
        Office office = officeRepository.findById(id).orElseThrow(NotExistOfficeException::new);
        return responseService.getSingleResponse(new DetailResponse(office));
    }

    @Transactional
    public CommonResponse neededForCropList(String cropName) {
        dataUtil.setCropMap();
        Map<String, List<String>> cropMap = dataUtil.getCropMap();

        List<String> machineList = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : cropMap.entrySet())
            if (entry.getValue().contains(cropName)) machineList.add(entry.getKey());

        return responseService.getListResponse(machineList);
    }

    @Transactional
    public CommonResponse nearestList(NearestRequest nearestRequest) {
        String machine = nearestRequest.getMachine();
        double nowLat = Double.parseDouble(nearestRequest.getLatitude());
        double nowLong = Double.parseDouble(nearestRequest.getLongitude());

        // 해당 농기계를 보유한 사업소 목록 생성
        List<Office> findOffice = new ArrayList<>();
        List<Office> offices = officeRepository.findAll();

        for (Office office : offices)
            for (Machine findMachine : office.getMachines())
                if (findMachine.getName().equals(machine))
                    findOffice.add(office);

        // 사업소 목록을 현위치를 기준으로 가까운 순 정렬
        findOffice.sort(((o1, o2) -> {
            double o1Dist = getDistance(nowLat, nowLong, o1);

            double o2Dist = getDistance(nowLat, nowLat, o2);

            return (int) (o1Dist - o2Dist);
        }));

        return responseService.getListResponse(findOffice.subList(0, 5));
    }

    //
    // private method
    //

    private void setData() {
        //data 를 DB 에 저장하기 위해 최초 1회 실행
        DataUtil dataUtil = new DataUtil();
        officeRepository.saveAll(dataUtil.getOffices());
    }

    private double getDistance(double nowLat, double nowLong, Office o) {
        double oLat = Double.parseDouble(o.getLatitude());
        double oLong = Double.parseDouble(o.getLongitude());
        double oTheta = Math.abs(nowLong - oLong);
        return Math.sin(degToRad(oLat)) * Math.sin(degToRad(nowLat)) + Math.cos(degToRad(oLat)) * Math.cos(degToRad(nowLat)) * Math.cos(degToRad(oTheta));
    }

    private double degToRad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double radToDeg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
