package IGJ.imgeokjeong.farm.controller;

import IGJ.imgeokjeong.farm.service.FarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farmMachine")
public class FarmController {

    private final FarmService farmService;

    @PostMapping("/all")
    public JSONArray farmMachineAllApi() throws Exception {
        log.info("전체 농기계임대정보 반환");
        return farmService.farmMachineRentAll();
    }
}