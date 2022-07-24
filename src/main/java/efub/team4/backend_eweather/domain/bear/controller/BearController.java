package efub.team4.backend_eweather.domain.bear.controller;

import efub.team4.backend_eweather.domain.bear.dto.BearImageResponseDto;
import efub.team4.backend_eweather.domain.bear.service.BearService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bear")
public class BearController {

    @Autowired
    private final BearService bearService;

    @GetMapping
    public BearImageResponseDto loadBearImage() throws IOException, ParseException {
        return bearService.findBearImage();
    }

}

