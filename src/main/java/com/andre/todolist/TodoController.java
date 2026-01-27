package com.andre.todolist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TarefaRepository tarefaRepository;

    public TodoController(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> ResponseEntity.ok(tarefa))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if(tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id,@RequestBody Tarefa tarefa) {
        return tarefaRepository.findById(id).map(tarefaExistente -> {
                tarefaExistente.setDescricao(tarefa.getDescricao());
                tarefaExistente.setStatus(tarefa.isStatus());
                Tarefa tarefaSalva = tarefaRepository.save(tarefaExistente);
                return ResponseEntity.ok(tarefaSalva);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public List<Tarefa> listarTodos () {
        return tarefaRepository.findAll();
    }

    @PostMapping
public Tarefa criar (@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
}
