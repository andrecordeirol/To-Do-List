package com.andre.todolist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Aqui dizemos ao Spring: isto é um 404! 🏷️
public class TarefaNotFoundException extends RuntimeException {
    public TarefaNotFoundException(Long id) {
        super("Tarefa não encontrada com o ID: " + id);
    }
}