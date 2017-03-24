package ru.zelark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zelark.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
