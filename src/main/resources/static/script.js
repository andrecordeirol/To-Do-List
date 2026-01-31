fetch('http://localhost:8080/todos')
.then(response => response.json())
.then(dados => {
    dados.forEach(tarefa => {
        // Selecionar a lista pelo ID
        const lista = document.getElementById('lista-tarefas');

        // Crio o texto que representa a linha e usamos o descrição do Java
        const itemHTML = '<li>${tarefa.descricao}</li>';

        // adicionando o item ao final da lista
        lista.insertAdjacentElement('beforeend', itemHTML);
    })
})

async function carregarTarefas() {
    try {
        const response = await fetch('/todos');
        const tarefas = await response.json();

        const lista = document.getElementById('lista-tarefas');
        lista.innerHTML = '';

        tarefas.forEach(tarefa => {
            const li = document.createElement('li');
            li.textContent = tarefa.descricao;
            lista.appendChild(li);
        });
    } catch (error) {
        console.error("Erro ao buscar tarefas: ", error);
    }
}

carregarTarefas();