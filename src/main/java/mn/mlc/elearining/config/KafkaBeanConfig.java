package mn.mlc.elearining.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaBeanConfig {
//    @Bean
//    public NewTopic topic1(){
//        return TopicBuilder.name("testKafka")
//                .partitions(10)
//                .replicas(1)
//                .compact()
//                .build();
//    }
}
