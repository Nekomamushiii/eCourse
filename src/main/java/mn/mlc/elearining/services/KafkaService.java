package mn.mlc.elearining.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String,String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String,String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String message){
        kafkaTemplate.send("testTopic",message);
    }
}
