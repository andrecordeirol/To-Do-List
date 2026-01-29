package com.andre.todolist;

import org.springframework.stereotype.Service;


@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

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
}
