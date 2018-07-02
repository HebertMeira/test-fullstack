package com.test.api.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.api.response.UsuarioResponse;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Collection<UsuarioResponse> buscarTodos(){
		return usuarioRepository.findAll();
	}
	
	public UsuarioResponse cadastrar(UsuarioResponse usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public UsuarioResponse buscaPorId(Long id) {
		return usuarioRepository.findOne(id);
	}
	
	public void excluir(UsuarioResponse cliente) {
		usuarioRepository.delete(cliente);
	}
	
	public UsuarioResponse alterar(UsuarioResponse cliente) {
		return usuarioRepository.save(cliente);
	}
}
