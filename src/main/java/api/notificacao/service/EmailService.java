package api.notificacao.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTaskCreatedEmail(String to, String taskTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Tarefa criada: " + taskTitle);
        message.setText("Sua tarefa \"" + taskTitle + "\" foi criada com sucesso e está aguardando execução.");
        mailSender.send(message);
    }

    public void sendTaskDueSoonEmail(String to, String taskTitle, java.time.LocalDateTime dueDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Lembrete: tarefa \"" + taskTitle + "\" vence em breve!");
        message.setText("Atenção! Sua tarefa \"" + taskTitle + "\" vence em " + dueDate + ". Não se esqueça de concluí-la.");
        mailSender.send(message);
    }
}
