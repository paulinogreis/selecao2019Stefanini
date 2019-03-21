package com.paulinogreis.selecao2019.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paulinogreis.selecao2019.dominio.Endereco;
import com.paulinogreis.selecao2019.dto.EnderecoDTO;
import com.paulinogreis.selecao2019.servicos.EnderecoServico;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoRecurso {

	@Autowired
	private EnderecoServico servico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable Integer id) {
		Endereco obj = servico.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EnderecoDTO objDto) {
		Endereco obj = servico.fromDTO(objDto);
		obj = servico.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EnderecoDTO>> findAll() {
		List<Endereco> list = servico.findAll();
		List<EnderecoDTO> listDto = list.stream().map(obj -> new EnderecoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EnderecoDTO objDto, @PathVariable Integer id) {
		Endereco obj = servico.fromDTO(objDto);
		obj = servico.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		servico.delete(id);
		return ResponseEntity.noContent().build();
	}

}
