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

@RequiredArgsConstructor
@Service
public class OfficeService {

    private final OfficeRepository officeRepository;
    private final ResponseService responseService;

    @Transactional
    public CommonResponse list() {
        return responseService.getListResponse(officeRepository.findAll());
    }

    @Transactional
    public CommonResponse detail(Long id) {
        Office office = officeRepository.findById(id).orElseThrow(NotExistOfficeException::new);
        return responseService.getSingleResponse(new DetailResponse(office));
    }

    //
    // private methode
    //

    private void setData() {
        //data 를 DB 에 저장하기 위해 최초 1회 실행
        DataUtil dataUtil = new DataUtil();
        officeRepository.saveAll(dataUtil.getOffices());
    }
}
