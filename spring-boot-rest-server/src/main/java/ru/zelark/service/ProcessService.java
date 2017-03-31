package ru.zelark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zelark.dto.ProcessDTO;
import ru.zelark.entity.Process;
import ru.zelark.mapper.Mapper;
import ru.zelark.repository.ProcessRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProcessService {

  private ProcessRepository repository;
  private Mapper<Process, ProcessDTO> mapper;

  @Autowired
  public ProcessService(ProcessRepository repository, Mapper<Process, ProcessDTO> mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public List<ProcessDTO> findAll() {
    return mapper.toDTOs(repository.findAll());
  }

  public ProcessDTO findOne(Long id) {
    return mapper.toDTO(findOneSafe(id));
  }

  @Transactional
  public ProcessDTO create(ProcessDTO dto) {
    Process newProcess = new Process();
    mapper.update(newProcess, dto);
    Process savedProcess = repository.saveAndFlush(newProcess);
    return mapper.toDTO(savedProcess);
  }

  @Transactional
  public ProcessDTO update(Long id, ProcessDTO dto) {
    Process process = findOneSafe(id);
    mapper.update(process, dto);
    return mapper.toDTO(process);
  }

  @Transactional
  public void delete(Long id) {
    Process process = findOneSafe(id);
    repository.delete(process);
  }

  private Process findOneSafe(Long id) {
    Process process = repository.findOne(id);
    if (process == null) {
      throw new ProcessNotFoundException();
    }
    else {
      return process;
    }
  }
}
