package sun.rock.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sun.rock.server.entity.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> {
}
