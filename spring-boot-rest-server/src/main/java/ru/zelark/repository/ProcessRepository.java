package ru.zelark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zelark.entity.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> {
}
