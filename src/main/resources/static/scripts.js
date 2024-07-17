
document.getElementById('formularioCliente').addEventListener('submit', function(event) {
    event.preventDefault();

    const cliente = {
        nome: document.getElementById('nome').value,
        sobrenome: document.getElementById('sobrenome').value,
        dataNascimento: document.getElementById('dataNascimento').value,
        cpf: document.getElementById('cpf').value,
        telefone: document.getElementById('telefone').value,
        genero: document.getElementById('genero').value,
        enderecos: []
    };

    const endereco = {
        rua: document.getElementById('rua').value,
        numero: document.getElementById('numero').value,
        cep: document.getElementById('cep').value,
        complemento: document.getElementById('complemento').value,
        bairro: document.getElementById('bairro').value
    };

    cliente.enderecos.push(endereco);

    fetch('api/clientes/cadastro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cliente)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Dados enviados com sucesso:', data);
    })
    .catch(error => {
        console.error('Erro ao enviar dados:', error);
    });
});

document.getElementById('searchForm').addEventListener('click', () => {

    let cpf = document.getElementById('cpf').value;
    let nome = document.getElementById('nome').value;

    fetch('http://localhost:8080/api/clientes?nome=${nome}&cpf=${cpf}')
        .then(response => {
            return response.json();
        })
        .then(data => {
            displayTable(data);
        })
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
        `;
        tbody.appendChild(row);
    });
}
