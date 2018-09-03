package hello;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class HomeController {

    @RequestMapping(value = "/{name}", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public Map<String, Object> greeting(@PathVariable String name, @RequestParam String greeting) {
        return Collections.singletonMap("message", greeting + " " + name);
    }

    @RequestMapping(value = "/tony", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public Map<String, Object> withoutPathVariable(@RequestParam String greeting) {
        return Collections.singletonMap("message", greeting + " Tony");
    }

}
