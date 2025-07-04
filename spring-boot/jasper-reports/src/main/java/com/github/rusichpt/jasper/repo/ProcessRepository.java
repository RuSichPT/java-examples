package com.github.rusichpt.jasper.repo;

import com.github.rusichpt.jasper.entity.ProcessEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ProcessRepository {
    private final List<ProcessEntity> entityList;

    public ProcessRepository() {
        ProcessEntity e1 = new ProcessEntity(1L,"Первый процесс", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 1, "Успех");
        ProcessEntity e2 = new ProcessEntity(2L,"Второй процесс", LocalDateTime.now(), LocalDateTime.now().plusHours(2), 2, null);
        ProcessEntity e3 = new ProcessEntity(3L,"Третий процесс",LocalDateTime.now(), LocalDateTime.now().plusHours(3), 3, "Успех");

        entityList = List.of(e1, e2, e3);
    }

    public Optional<ProcessEntity> findById(Long id) {
        return entityList.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }
}
