package ml.socshared.service.support.controller.v1;

import ml.socshared.service.support.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import ml.socshared.service.support.api.v1.rest.TechSupportApi;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/v1")
public class TechSupportController implements TechSupportApi {

    private TestService service;

    public TechSupportController(TestService service) {
        this.service = service;
    }

    @Override
    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> printHelloWorld() {

        return new HashMap<>() {
            {
                put("text", "Hello, World");
            }
        };
    }

    @GetMapping(value = "/feign", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testFeign() {
        return service.test();
    }

}
