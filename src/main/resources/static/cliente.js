document.getElementById('searchForm').addEventListener('submit', (event) => {
    event.preventDefault(); // Previna o comportamento padrão do formulário

    let cpf = document.getElementById('cpf').value;
    let nome = document.getElementById('nome').value;

    fetch(`http://localhost:8080/api/clientes?nome=${nome}&cpf=${cpf}`)
        .then(response => {
            return response.json();
        })
        .then(data => {
            console.log(data); // Log para depuração
            displayTable(data.content);
        })
        .catch(error => {
            console.error('Erro ao buscar clientes:', error);
        });
});

function displayTable(data) {
    const tbody = document.getElementById('dados');
    tbody.innerHTML = '';

    data.forEach(cliente => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.sobrenome}</td>
            <td>${cliente.cpf}</td>
            <td>
                <button class="visualizar" data-id="${cliente.id}">visualizar</button>
            </td>
        `;
        tbody.appendChild(row);
    });

    document.querySelectorAll('.visualizar').forEach(button => {
        button.addEventListener('click', (event) => {
            const clienteId = event.target.getAttribute('data-id');

        window.location.href = `cliente_cadastro.html?id=${clienteId}`;


        });
    });

}

