package hello;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class HomeController {

    @PatchMapping("/{name}")
    public Map<String, Object> greeting(@PathVariable String name, @RequestParam String greeting) {
        return Collections.singletonMap("message", greeting + " " + name);
    }

}
