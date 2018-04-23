package br.com.cursomc.services.validation;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    ClienteRepository clienteRepository;

   /*
    * Tem que retornar true se o objeto for válido e false para objeto inválido
    */
    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        /*
        * Para pegar o valor do parametro passado na URI quando o update do cliente foi chamado
        * */
        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
        if (cliente != null && !cliente.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        /* Para acrescentar no framework os erros encontrados, para que possam ser retornados para a origem */
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
