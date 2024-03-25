package br.com.leonardo.bibliotecaspring.service;

import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

   public Cliente localizarPeloId(Long id){
       return clienteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Cliente não localizado!"));
    }
}
