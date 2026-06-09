package api.notificacao.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import api.notificacao.dto.TaskRequestDTO;
import api.notificacao.dto.TaskResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.notificacao.entity.TaskModel;
import api.notificacao.repository.TaskRepository;
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    private TaskResponseDTO toResponse(TaskModel task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.isNotified(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getUserEmail()
        );
    }

    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponseDTO create(TaskRequestDTO dto) {
        TaskModel task = new TaskModel();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());
        task.setUserEmail(dto.userEmail());

        TaskResponseDTO response = toResponse(taskRepository.save(task));
        emailService.sendTaskCreatedEmail(dto.userEmail(), dto.title());
        return response;
    }

    public TaskResponseDTO findById(UUID id) {
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com id: " + id));
        return toResponse(task);
    }

    public TaskResponseDTO update(UUID id, TaskRequestDTO dto) {
        TaskModel existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com id: " + id));

        existing.setTitle(dto.title());
        existing.setDescription(dto.description());
        existing.setStatus(dto.status());
        existing.setPriority(dto.priority());
        existing.setDueDate(dto.dueDate());

        return toResponse(taskRepository.save(existing));
    }

    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }
}
