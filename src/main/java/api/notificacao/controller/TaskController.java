package api.notificacao.controller;

import api.notificacao.dto.TaskRequestDTO;
import api.notificacao.dto.TaskResponseDTO;
import api.notificacao.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<TaskResponseDTO> tasks() {
        return taskService.findAll();
    }

    @PostMapping("")
    public TaskResponseDTO createTask(@RequestBody @Valid TaskRequestDTO dto) {
        return taskService.create(dto);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO findById(@PathVariable UUID id) {
        return taskService.findById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO update(@PathVariable UUID id, @RequestBody @Valid TaskRequestDTO dto) {
        return taskService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        taskService.delete(id);
    }
}
