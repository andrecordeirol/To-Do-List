async function carregarTarefas() {
    try {
        const resposta = await fetch('/todos');
        const tarefas = await resposta.json();
        const lista = document.getElementById('lista-tarefas');
        lista.innerHTML = '';

        tarefas.forEach(tarefa => {
            const li = document.createElement('li');

            // Verificamos o status para aplicar a classe CSS
            if (tarefa.status) {
                li.classList.add('concluida');
            }

            li.innerHTML = `
                <span>${tarefa.descricao}</span>
                ${!tarefa.status ? `<button onclick="concluirTarefa(${tarefa.id})">✅</button>` : ''}
            `;
            lista.appendChild(li);
        });
    } catch (erro) {
        console.error("Erro ao carregar:", erro);
    }
} carregarTarefas();


const botao = document.getElementById('btn-adicionar');
botao.addEventListener('click', () => {
    console.log("O botão foi clicado com sucesso!");
    // capturo o elemento da caixa de texto
    // Verifique se o 'input' está definido corretamente antes do fetch
    const input = document.getElementById('nova-tarefa-input');
    const textoDaTarefa = input.value;

    fetch('/todos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ descricao: textoDaTarefa })
    })
        .then(response => {
            if (response.ok) {
                // Se o Java salvou, chamamos a função de listar
                carregarTarefas();
                // E limpamos o campo de texto
                input.value = '';
            } else {
                // 👇 Esta é a linha nova para investigar!
                console.error("O servidor rejeitou o pedido. Status:", response.status);
            }
            input.value = '';

        })
        .catch(erro => console.log("Erro ao salvar", erro));



});

async function concluirTarefa(id) {
    try {
        const resposta = await fetch(`/todos/${id}/concluir`, {
            method: 'PUT'
        });

        if (resposta.ok) {
            // Se o servidor respondeu OK, atualizamos a lista na tela
            carregarTarefas();
        }
    } catch (erro) {
        console.error("Erro ao concluir tarefa:", erro);
    }
}