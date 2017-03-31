package sun.rock.server.mapper;

import org.mapstruct.MappingTarget;
import sun.rock.server.dto.TaskDTO;
import sun.rock.server.entity.Task;

import java.util.List;

@org.mapstruct.Mapper
public abstract class TaskMapper implements Mapper<Task, TaskDTO> {

  public abstract TaskDTO toDTO(Task task);

  public abstract List<TaskDTO> toDTOs(List<Task> tasks);

  public abstract Task toTask(TaskDTO taskDTO);

  public abstract List<Task> toTasks(List<TaskDTO> taskDTOs);

  public abstract void update(@MappingTarget Task task, TaskDTO taskDTO);
}
