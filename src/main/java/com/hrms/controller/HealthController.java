@RestController
public class HealthController {

    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}
