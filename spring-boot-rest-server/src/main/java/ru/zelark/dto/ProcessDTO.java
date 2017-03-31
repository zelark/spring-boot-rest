package ru.zelark.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ProcessDTO {

  @JsonInclude
  private Long id;

  @NotNull(message = "NotNull.processDTO.description")
  @Size(min = 1, max = 64, message = "Size.processDTO.description")
  private String description;

  private boolean completed;

  private List<TaskDTO> tasks;

  public ProcessDTO(Long id, String description, boolean completed) {
    this.id = id;
    this.description = description;
    this.completed = completed;
  }

  public ProcessDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public List<TaskDTO> getTasks() {
    return tasks;
  }

  public void setTasks(List<TaskDTO> tasks) {
    this.tasks = tasks;
  }
}
