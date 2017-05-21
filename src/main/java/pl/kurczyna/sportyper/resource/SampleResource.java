package pl.kurczyna.sportyper.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kurczyna.sportyper.service.HelloWorldService;

@Controller
@AllArgsConstructor
public class SampleResource {

    private HelloWorldService helloWorldService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(200).body(helloWorldService.run());
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
