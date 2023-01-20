package com.mpxds.apirest.service;

import org.springframework.mail.SimpleMailMessage;

import com.mpxds.apirest.model.MpCliente;
import com.mpxds.apirest.model.MpPedido;

public interface MpEmailService {
	//
	void sendOrderConfirmationEmail(MpPedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(MpCliente cliente, String newPass);
}
