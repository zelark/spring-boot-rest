package sun.rock.server.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import sun.rock.server.dto.ProcessDTO;
import sun.rock.server.entity.Process;
import sun.rock.server.repository.TaskRepository;

import java.util.List;

@org.mapstruct.Mapper(uses = TaskMapper.class)
public abstract class ProcessMapper implements Mapper<Process, ProcessDTO> {

  @Autowired
  private TaskRepository taskRepository;

  public abstract ProcessDTO toDTO(Process process);

  public abstract List<ProcessDTO> toDTOs(List<Process> processes);

  public abstract void update(@MappingTarget Process process, ProcessDTO dto);

  @BeforeMapping
  protected void wipeTasks(@MappingTarget Process process) {
    if (process.getTasks() != null) {
      taskRepository.delete(process.getTasks());
    }
  }

  @AfterMapping
  protected void saveTasks(@MappingTarget Process process) {
    if (process.getTasks() != null) {
      process.getTasks().forEach(t -> t.setParentID(process.getId()));
      taskRepository.save(process.getTasks());
    }
  }
}
