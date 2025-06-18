package com.portafolio.todo_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //modelo de una tabla de la base de datos
@Table(name = "tasks") //opcional, se le dice a JPA se debe llamar en plural y no en singular
public class Task {

    @Id //Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //el id de la tarea se genera automaticamente sin necesidad de asignarlo uno mismo. la BD lo hara por nosotros
    private Long id;

    @Column(nullable = false) //este campo es obligatorio
    private String title;

    private String description;

    private boolean completed = false;

    // Constructor vacío requerido por JPA
    public Task() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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