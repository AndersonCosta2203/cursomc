package br.com.cursomc.services.validation;

import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.dto.ClienteNewDTO;
import br.com.cursomc.resources.exception.FieldMessage;
import br.com.cursomc.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    /*
    * Tem que retornar true se o objeto for válido e false para objeto inválido
    */
    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) &&
                !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDIA.getCod()) &&
                !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
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
