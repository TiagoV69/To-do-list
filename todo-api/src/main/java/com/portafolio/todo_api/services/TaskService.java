package com.portafolio.todo_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portafolio.todo_api.models.Task;
import com.portafolio.todo_api.repositories.TaskRepository;

@Service // Marca esta clase como un componente de Servicio en Spring.
public class TaskService {

     @Autowired // Inyección de dependencias: Spring nos dará una instancia de TaskRepository.
    private TaskRepository taskRepository;

    // Método para obtener TODAS las tareas
    public List<Task> getAllTasks() {
        return taskRepository.findAll(); // Usa el método heredado de JpaRepository.
    }

    // Método para obtener una tarea por su ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id); // Retorna un 'Optional' para manejar el caso de que no se encuentre.
    }

    // Método para guardar una nueva tarea o actualizar una existente
    public Task saveTask(Task task) {
        return taskRepository.save(task); // El método save() sirve tanto para crear como para actualizar.
    }

    // Método para eliminar una tarea por su ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
