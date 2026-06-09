package api.notificacao.dto;

import api.notificacao.enums.Priority;
import api.notificacao.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        Priority priority,
        LocalDateTime dueDate,
        boolean notified,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String userEmail
) {}
