package br.com.leonardo.bibliotecaspring.formatter;

import br.com.leonardo.bibliotecaspring.dto.ClienteDTO;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.expression.ParseException;

import javax.swing.text.MaskFormatter;
import java.util.List;

public class Formatter {

    //MaskFormatter
    public static String mask (String string, String mask){
        try {
            MaskFormatter mascara = new MaskFormatter(mask);
            mascara.setValueContainsLiteralCharacters(false);
            return mascara.valueToString(string);
        }

        catch (Exception e){
           throw new RuntimeException(e);
        }
    }

    public static String cpfMask(ClienteDTO clienteDto){
        return mask(clienteDto.getCpf(), "###.###.###-##");
    }

    public static void cpfMaskLista(List<ClienteDTO> lista){
        lista.forEach(clienteDto -> clienteDto.setCpf(Formatter.cpfMask(clienteDto)));
    }
}
