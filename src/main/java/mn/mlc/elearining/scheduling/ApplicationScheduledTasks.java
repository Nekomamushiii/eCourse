package mn.mlc.elearining.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApplicationScheduledTasks {
    @Scheduled(cron = "0 0 2 * * ?")
    public void runDailyJob(){
        System.out.println("Running daily job at 2 am");
    }
}
