package IGJ.imgeokjeong.office.controller;

import IGJ.imgeokjeong.errors.response.CommonResponse;
import IGJ.imgeokjeong.office.dto.NearestRequest;
import IGJ.imgeokjeong.office.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Operation(summary = "get machine list needed for crop", description = "농작물에 필요한 농기계 리스트")
    @GetMapping("/machine/{cropName}")
    public CommonResponse machineForCropList(@PathVariable("cropName") String cropName) {
        return officeService.neededForCropList(cropName);
    }

    @Operation(summary = "get nearest office list has machines", description = "현 위치에서 가장 가깝고 농기계를 보유 중인 사업소 리스트")
    @PostMapping("/offices")
    public CommonResponse nearestOfficeList(@RequestBody @Valid NearestRequest nearestRequest) {
        return officeService.nearestList(nearestRequest);
    }
}
