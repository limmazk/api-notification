package api.notificacao.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail notificationJobDetail() {
        return JobBuilder.newJob(NotificationJob.class)
                .withIdentity("notificationJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger notificationTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(notificationJobDetail())
                .withIdentity("notificationTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
                .build();
    }
}
