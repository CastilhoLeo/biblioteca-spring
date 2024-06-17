package br.com.leonardo.bibliotecaspring.converter;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import br.com.leonardo.bibliotecaspring.dto.LivroDTO;
import br.com.leonardo.bibliotecaspring.dto.LocacaoDTO;
import br.com.leonardo.bibliotecaspring.entity.Cliente;
import br.com.leonardo.bibliotecaspring.entity.Livro;
import br.com.leonardo.bibliotecaspring.entity.Locacao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocacaoConverter {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    ClienteConverter clienteConverter;

    @Autowired
    LivroConverter livroConverter;

    public LocacaoDTO toDto(Locacao locacao){
        LocacaoDTO locacaoDto =  mapper.map(locacao, LocacaoDTO.class);

        ClienteDTO clienteDTO = clienteConverter.toDto(locacao.getCliente());
        locacaoDto.setClienteDto(clienteDTO);

        LivroDTO livroDTO = livroConverter.toDto(locacao.getLivro());
        locacaoDto.setLivroDto(livroDTO);

        return locacaoDto;
    }

    public Locacao toEntity(LocacaoDTO locacaoDto){
        Locacao locacao =  mapper.map(locacaoDto, Locacao.class);

        Cliente cliente = clienteConverter.toEntity(locacaoDto.getClienteDto());
        locacao.setCliente(cliente);

        Livro livro = livroConverter.toEntity(locacaoDto.getLivroDto());
        locacao.setLivro(livro);

        return locacao;
    }
}
