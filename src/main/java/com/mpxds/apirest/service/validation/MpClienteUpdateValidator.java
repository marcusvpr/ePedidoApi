package com.mpxds.apirest.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.mpxds.apirest.model.MpCliente;
import com.mpxds.apirest.model.dto.MpClienteDTO;
import com.mpxds.apirest.repository.MpClienteRepository;
import com.mpxds.apirest.controller.exception.MpFieldMessage;

public class MpClienteUpdateValidator implements ConstraintValidator<MpClienteUpdate, MpClienteDTO> {
	//
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private MpClienteRepository repo;
	
	@Override
	public void initialize(MpClienteUpdate ann) { }

	@Override
	public boolean isValid(MpClienteDTO objDto, ConstraintValidatorContext context) {
		//
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<MpFieldMessage> list = new ArrayList<>();
		
		MpCliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new MpFieldMessage("email", "Email já existente"));
		}
		//
		for (MpFieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		//
		return list.isEmpty();
	}
	
}

