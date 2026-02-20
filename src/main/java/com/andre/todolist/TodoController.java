package com.andre.todolist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TarefaRepository tarefaRepository;
    private final TarefaService tarefaService;

    public TodoController(TarefaRepository tarefaRepository, TarefaService tarefaService) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaService = tarefaService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> ResponseEntity.ok(tarefa))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id,@RequestBody Tarefa tarefa) {
        Tarefa tarefaAtualizada = tarefaService.atualizar(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @GetMapping()
    public List<Tarefa> listarTodos () {
        return tarefaRepository.findAll();
    }

    @PostMapping
public Tarefa criar (@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id){
        Tarefa tarefaAtualizada = tarefaService.marcarComoConcluido(id);
        return ResponseEntity.ok(tarefaAtualizada);
    }
}
