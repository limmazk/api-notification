package api.notificacao.dto;

import api.notificacao.enums.Priority;
import api.notificacao.enums.TaskStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskRequestDTO(
        @NotBlank
        String title,

        String description,

        @NotNull
        TaskStatus status,

        @NotNull
        Priority priority,

        @NotNull
        @Future
        LocalDateTime dueDate,

        @NotBlank
        @Email
        String userEmail
) {}

