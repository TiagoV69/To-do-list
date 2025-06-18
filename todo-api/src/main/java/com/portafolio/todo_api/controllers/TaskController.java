package com.portafolio.todo_api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portafolio.todo_api.models.Task;
import com.portafolio.todo_api.services.TaskService;

@RestController // Indica que esta clase es un controlador REST.
@RequestMapping("/api/tasks") // Establece la URL base para todos los endpoints de este controlador.
public class TaskController {

    @Autowired
    private TaskService taskService;

    // ENDPOINT PARA OBTENER TODAS LAS TAREAS
    @GetMapping // Mapea peticiones HTTP GET a /api/tasks
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // ENDPOINT PARA OBTENER UNA TAREA POR ID
    @GetMapping("/{id}") // Mapea peticiones GET a /api/tasks/{id}
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) { // @PathVariable extrae el {id} de la URL
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok(task)) // Si la tarea existe, devuelve 200 OK con la tarea.
                .orElse(ResponseEntity.notFound().build()); // Si no, devuelve un 404 Not Found.
    }

    // ENDPOINT PARA CREAR UNA NUEVA TAREA
    @PostMapping // Mapea peticiones POST a /api/tasks
    public ResponseEntity<Task> createTask(@RequestBody Task task) { // @RequestBody convierte el JSON de la petición en un objeto Task.
        Task newTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask); // Devuelve un estado 201 Created.
    }

    // ENDPOINT PARA ACTUALIZAR UNA TAREA
    @PutMapping("/{id}") // Mapea peticiones PUT a /api/tasks/{id}
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.getTaskById(id)
                .map(task -> {
                    task.setTitle(taskDetails.getTitle());
                    task.setDescription(taskDetails.getDescription());
                    task.setCompleted(taskDetails.isCompleted());
                    Task updatedTask = taskService.saveTask(task);
                    return ResponseEntity.ok(updatedTask);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ENDPOINT PARA ELIMINAR UNA TAREA
    @DeleteMapping("/{id}") // Mapea peticiones DELETE a /api/tasks/{id}
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    // Primero, buscamos la tarea usando el servicio
    Optional<Task> taskOptional = taskService.getTaskById(id);

     // Verificamos si la tarea existe
    if (taskOptional.isPresent()) {
    // Si existe, la eliminamos
    taskService.deleteTask(id);
    // Y devolvemos una respuesta 204 No Content, que indica éxito sin contenido.
    return ResponseEntity.noContent().build();
    } else {
        // Si no existe, devolvemos un 404 Not Found.
        return ResponseEntity.notFound().build();
    }
}   
}
