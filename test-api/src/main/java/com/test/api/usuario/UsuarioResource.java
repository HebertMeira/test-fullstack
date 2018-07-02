package com.test.api.usuario;

import java.util.Collection;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.service.UsuarioService;
import com.test.model.api.response.UsuarioResponse;

@RestController
@RequestMapping("/api")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method=RequestMethod.POST,value="/user",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioResponse> cadastrarCliente(@RequestBody UsuarioResponse usuario) {
		UsuarioResponse usuarioCadastrado = usuarioService.cadastrar(usuario);
		return new ResponseEntity<>(usuarioCadastrado,HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/user",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UsuarioResponse>> buscarTodosClientes() {
		Collection<UsuarioResponse> usuariosBuscados = usuarioService.buscarTodos();
		return new ResponseEntity<>(usuariosBuscados,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/user/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioResponse> buscarClientesPorId(@PathVariable Long id) {
		UsuarioResponse usuario = usuarioService.buscaPorId(id);
		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/user/{id}")
	public ResponseEntity<UsuarioResponse> excluirCliente(@PathVariable Long id) {
		UsuarioResponse usuarioEncontrado = usuarioService.buscaPorId(id);
		
		if(usuarioEncontrado==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		usuarioService.excluir(usuarioEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/user",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioResponse> alterarCliente(@RequestBody UsuarioResponse usuario) throws ServletException {
		if(usuario.getId()== null) {
			throw new ServletException("Para editar o id é obrigatorio.");
		}
		UsuarioResponse usuarioAlterado = usuarioService.alterar(usuario);
		return new ResponseEntity<>(usuarioAlterado,HttpStatus.OK);
	}
}
