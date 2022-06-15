package IGJ.imgeokjeong.office.controller;

import IGJ.imgeokjeong.errors.response.CommonResponse;
import IGJ.imgeokjeong.office.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/office")
@RestController
public class OfficeController {

    private final OfficeService officeService;

    @Operation(summary = "get office list", description = "경북 지역의 임대 사업소 리스트")
    @GetMapping("/list")
    public CommonResponse officeList() {
        return officeService.list();
    }

    @Operation(summary = "get office info", description = "임대 사업소 ID로 상세 정보")
    @GetMapping("/{officeId}")
    public CommonResponse officeDetails(@PathVariable("officeId") Long id) {
        return officeService.detail(id);
    }
}
