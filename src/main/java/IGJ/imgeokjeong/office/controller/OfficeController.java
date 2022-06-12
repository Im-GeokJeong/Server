package IGJ.imgeokjeong.office.controller;

import IGJ.imgeokjeong.errors.response.CommonResponse;
import IGJ.imgeokjeong.office.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/office")
@RestController
public class OfficeController {

    private final OfficeService officeService;

    @GetMapping("/list")
    public CommonResponse officeList() {
        return officeService.list();
    }

    @GetMapping("/{officeId}")
    public CommonResponse officeDetails(@PathVariable("officeId") Long id) {
        return officeService.detail(id);
    }
}
