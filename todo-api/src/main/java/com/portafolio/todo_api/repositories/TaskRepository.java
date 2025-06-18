package com.portafolio.todo_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portafolio.todo_api.models.Task;

@Repository  // Indica a Spring que esta es una interfaz de repositorio (un Bean).
public interface TaskRepository extends JpaRepository<Task, Long> {

}
