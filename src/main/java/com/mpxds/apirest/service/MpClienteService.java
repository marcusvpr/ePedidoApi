package com.mpxds.apirest.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mpxds.apirest.model.MpCidade;
import com.mpxds.apirest.model.MpCliente;
import com.mpxds.apirest.model.MpEndereco;
import com.mpxds.apirest.model.enums.MpPerfil;
import com.mpxds.apirest.model.enums.MpTipoCliente;
import com.mpxds.apirest.model.dto.MpClienteDTO;
import com.mpxds.apirest.model.dto.MpClienteNewDTO;
import com.mpxds.apirest.repository.MpClienteRepository;
import com.mpxds.apirest.repository.MpEnderecoRepository;
import com.mpxds.apirest.security.MpUserSS;
import com.mpxds.apirest.service.exceptions.MpAuthorizationException;
import com.mpxds.apirest.service.exceptions.MpDataIntegrityException;
import com.mpxds.apirest.service.exceptions.MpObjectNotFoundException;

@Service
public class MpClienteService {
	//
	@Autowired
	private MpClienteRepository repo;
	
	@Autowired
	private MpEnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private MpS3Service s3Service;
	
	@Autowired
	private MpImageService imageService;
		
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public MpCliente find(Integer id) {
		//
		MpUserSS user = MpUserService.authenticated();
		if (user==null || !user.hasRole(MpPerfil.ADMIN) && !id.equals(user.getId())) {
			throw new MpAuthorizationException("Acesso negado");
		}
		//
		Optional<MpCliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new MpObjectNotFoundException(
									"Objeto não encontrado! Id: " + id + ", Tipo: " + MpCliente.class.getName()));
	}
	
	@Transactional
	public MpCliente insert(MpCliente obj) {
		//
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getMpEnderecos());
		
		return obj;
	}
	
	public MpCliente update(MpCliente obj) {
		//
		MpCliente newObj = find(obj.getId());
		updateData(newObj, obj);
		
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		//
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new MpDataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<MpCliente> findAll() {
		//
		return repo.findAll();
	}
	
	public MpCliente findByEmail(String email) {
		//
		MpUserSS user = MpUserService.authenticated();
		if (user == null || !user.hasRole(MpPerfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new MpAuthorizationException("Acesso negado");
		}
	
		MpCliente obj = repo.findByEmail(email);
		if (obj == null) {
			throw new MpObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + MpCliente.class.getName());
		}
		return obj;
	}
	
	public Page<MpCliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		//
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public MpCliente fromDTO(MpClienteDTO objDto) {
		//
		return new MpCliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public MpCliente fromDTO(MpClienteNewDTO objDto) {
		//
		MpCliente cli = new MpCliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), MpTipoCliente.
																	toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		MpCidade cid = new MpCidade(objDto.getCidadeId(), null, null);
		MpEndereco end = new MpEndereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), 
																			objDto.getBairro(),	objDto.getCep(), cli, cid);
		cli.getMpEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		//
		return cli;
	}
	
	private void updateData(MpCliente newObj, MpCliente obj) {
		//
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		//
		MpUserSS user = MpUserService.authenticated();
		if (user == null) {
			throw new MpAuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		//
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	
}
