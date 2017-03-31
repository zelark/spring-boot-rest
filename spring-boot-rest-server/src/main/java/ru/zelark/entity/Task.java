package ru.zelark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "parent_id")
  private Long parentID;

  @Column(name = "description")
  private String description;

  @Column(name = "completed")
  private boolean completed;

  public Task(Long id, Long parentID, String description, boolean completed) {
    this.id = id;
    this.parentID = parentID;
    this.description = description;
    this.completed = completed;
  }

  public Task() {
  }

  public Long getId() {
    return id;
  }

  public Long getParentID() {
    return parentID;
  }

  public void setParentID(Long parentID) {
    this.parentID = parentID;
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
}
