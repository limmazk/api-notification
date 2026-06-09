package api.notificacao.job;

import api.notificacao.entity.TaskModel;
import api.notificacao.repository.TaskRepository;
import api.notificacao.service.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationJob implements Job {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusHours(24);

        List<TaskModel> tasks = taskRepository.findTasksDueWithin24Hours(now, tomorrow);

        for (TaskModel task : tasks) {
            emailService.sendTaskDueSoonEmail(task.getUserEmail(), task.getTitle(), task.getDueDate());
            task.setNotified(true);
            taskRepository.save(task);
        }
    }
}
