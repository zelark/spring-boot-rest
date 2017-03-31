package sun.rock.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sun.rock.server.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
