package mn.mlc.elearining.controllers.restController;

import mn.mlc.elearining.services.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class KafkaTestController {
    private final KafkaService kafkaService;

    public KafkaTestController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }
    @GetMapping("/kafka")
    public ResponseEntity<Void> kafkaTest(@RequestParam("message") String message){
        kafkaService.sendMessage(message);
        return ResponseEntity.ok().build();
    }
}
