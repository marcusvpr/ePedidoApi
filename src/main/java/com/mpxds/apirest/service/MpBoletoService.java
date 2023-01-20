package com.mpxds.apirest.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mpxds.apirest.model.MpPagamentoComBoleto;

@Service
public class MpBoletoService {
	//
	public void preencherPagamentoComBoleto(MpPagamentoComBoleto pagto, Date instanteDoPedido) {
		//
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
