package api.notificacao.repository;

import api.notificacao.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {

    @Query("SELECT t FROM TaskModel t WHERE t.notified = false AND t.dueDate BETWEEN :now AND :tomorrow")
    List<TaskModel> findTasksDueWithin24Hours(@Param("now") LocalDateTime now, @Param("tomorrow") LocalDateTime tomorrow);
}
