package com.github.rusichpt.jasper.repo;

import com.github.rusichpt.jasper.entity.TaskEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<TaskEntity> entityList;

    public TaskRepository() {
        TaskEntity e1 = new TaskEntity("Первая", "В работе", "Иванов", LocalDateTime.now(), null, "low");
        TaskEntity e2 = new TaskEntity("Вторая", "Новая", "Петров", LocalDateTime.now(), null, "high");
        TaskEntity e3 = new TaskEntity("Третья", "Завершена", "Сидоров", LocalDateTime.now(), LocalDateTime.now().plusDays(1), "very high");

        entityList = List.of(e1, e2, e3);
    }

    public List<TaskEntity> findAll() {
        return entityList;
    }
}
