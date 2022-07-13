package efub.team4.backend_eweather.domain.hello.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
@Api(tags = {"테스트 API"})
@RequiredArgsConstructor
public class HelloController {

    @GetMapping
    @ApiOperation(value = "테스트 조회", notes = "객체를 조회한다.")
    public String hello() {
        return "hello world!";
    }

}