package IGJ.imgeokjeong.office.controller;

import IGJ.imgeokjeong.errors.reponse.CommonResponse;
import IGJ.imgeokjeong.office.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/office")
@RestController
public class OfficeController {

    private final OfficeService officeService;

    @GetMapping("/list")
    public CommonResponse officeList() {
        return officeService.list();
    }
}
