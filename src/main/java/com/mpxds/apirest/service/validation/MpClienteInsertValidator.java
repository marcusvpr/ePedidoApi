package com.mpxds.apirest.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mpxds.apirest.model.MpCliente;
import com.mpxds.apirest.model.enums.MpTipoCliente;
import com.mpxds.apirest.model.dto.MpClienteNewDTO;
import com.mpxds.apirest.repository.MpClienteRepository;
import com.mpxds.apirest.controller.exception.MpFieldMessage;
import com.mpxds.apirest.service.validation.utils.MpBR;

public class MpClienteInsertValidator implements ConstraintValidator<MpClienteInsert, MpClienteNewDTO> {
	//
	@Autowired
	private MpClienteRepository repo;
	
	@Override
	public void initialize(MpClienteInsert ann) {
		//
	}

	@Override
	public boolean isValid(MpClienteNewDTO objDto, ConstraintValidatorContext context) {
		//
		List<MpFieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(MpTipoCliente.PESSOAFISICA.getCod()) && !MpBR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new MpFieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(MpTipoCliente.PESSOAJURIDICA.getCod()) && !MpBR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new MpFieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		MpCliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new MpFieldMessage("email", "Email já existente"));
		}

		for (MpFieldMessage e : list) {
			//
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		//
		return list.isEmpty();
	}
}

