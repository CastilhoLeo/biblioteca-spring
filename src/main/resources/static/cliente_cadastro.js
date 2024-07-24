
document.getElementById('formularioCliente').addEventListener('submit', function(event) {
    event.preventDefault();

    const cliente = {
        nome: document.getElementById('nome').value,
        sobrenome: document.getElementById('sobrenome').value,
        dataNascimento: document.getElementById('dataNascimento').value,
        cpf: document.getElementById('cpf').value,
        telefone: document.getElementById('telefone').value,
        genero: document.getElementById('genero').value,
        endereco: []
    };

    const enderecoo = {
        rua: document.getElementById('rua').value,
        numero: document.getElementById('numero').value,
        cep: document.getElementById('cep').value,
        complemento: document.getElementById('complemento').value,
        bairro: document.getElementById('bairro').value
    };

    cliente.endereco.push(enderecoo);

    if(!document.getElementById('id').value){
    fetch('api/clientes/cadastro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cliente)
    })
 .then(response => {
            if (!response.ok) {
                // Se a resposta não for ok (status não 2xx), lança um erro.
                return response.json().then(data => {
                    alert(data.message)
                    throw data;
                });
            }
            // Se a resposta for ok, extrai os dados JSON do corpo da resposta.
            return response.json();
        })
    .then(data => {
        alert('Dados enviados com sucesso:', data);
    })
    .catch(error => {
            console.error('Erro ao enviar dados:', error);
        });
    }
    else{
    alert('cliente ja cadastrado');
    }


    document.getElementById('formularioCliente').reset();
});

document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const clienteId = urlParams.get('id');

    if (clienteId) {
        fetch(`http://localhost:8080/api/clientes/${clienteId}`)
            .then(response => response.json())
            .then(data => {
                console.log(data); // Log para depuração
                preencherFormulario(data);
            })
            .catch(error => {
                console.error('Erro ao buscar cliente:', error);
            });
    }
});

function preencherFormulario(cliente) {
    document.getElementById('id').value = cliente.id;
    document.getElementById('nome').value = cliente.nome;
    document.getElementById('sobrenome').value = cliente.sobrenome;
    document.getElementById('cpf').value = cliente.cpf;
    document.getElementById('dataNascimento').value = cliente.dataNascimento;
    document.getElementById('telefone').value = cliente.telefone;
    document.getElementById('genero').value = cliente.genero;
    document.getElementById('rua').value = cliente.endereco[0].rua;
    document.getElementById('numero').value = cliente.endereco[0].numero;
    document.getElementById('cep').value = cliente.endereco[0].cep;
    document.getElementById('complemento').value = cliente.endereco[0].complemento;
    document.getElementById('bairro').value = cliente.endereco[0].bairro;


}
