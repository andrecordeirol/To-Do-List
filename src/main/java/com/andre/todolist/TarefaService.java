package com.andre.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;


    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public void deletar(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
        } else {
            throw new TarefaNotFoundException(id);
        }
    }

    public Tarefa atualizar(Long id, Tarefa atualizarTarefa) {
        return tarefaRepository.findById(id).map(tarefaExistemte -> {
                    tarefaExistemte.setDescricao(atualizarTarefa.getDescricao());
                    tarefaExistemte.setStatus(atualizarTarefa.isStatus());
                    return tarefaRepository.save(tarefaExistemte);
                }
        ).orElseThrow(() -> new TarefaNotFoundException(id));
    }
}
