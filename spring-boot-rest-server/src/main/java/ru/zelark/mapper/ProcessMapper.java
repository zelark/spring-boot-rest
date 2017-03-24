package ru.zelark.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zelark.dto.ProcessDTO;
import ru.zelark.entity.Process;
import ru.zelark.repository.TaskRepository;

import java.util.List;

@org.mapstruct.Mapper(uses = TaskMapper.class)
public abstract class ProcessMapper implements Mapper<Process, ProcessDTO> {

    @Autowired private TaskRepository taskRepository;

    public abstract ProcessDTO toDTO(Process process);
    public abstract List<ProcessDTO> toDTOs(List<Process> processes);

    public abstract void update(@MappingTarget Process process, ProcessDTO dto);

    @BeforeMapping
    protected void wipeTasks(@MappingTarget Process process) {
        taskRepository.delete(process.getTasks());
    }

    @AfterMapping
    protected void saveTasks(@MappingTarget Process process) {
        process.getTasks().forEach(t -> t.setParentID(process.getId()));
        taskRepository.save(process.getTasks());
    }
}
