package IGJ.imgeokjeong.office.service;

import IGJ.imgeokjeong.errors.exception.NotExistOfficeException;
import IGJ.imgeokjeong.errors.response.CommonResponse;
import IGJ.imgeokjeong.errors.response.ResponseService;
import IGJ.imgeokjeong.office.domain.Office;
import IGJ.imgeokjeong.office.dto.DetailResponse;
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

    //
    // private method
    //

    private void setData() {
        //data 를 DB 에 저장하기 위해 최초 1회 실행
        DataUtil dataUtil = new DataUtil();
        officeRepository.saveAll(dataUtil.getOffices());
    }
}
