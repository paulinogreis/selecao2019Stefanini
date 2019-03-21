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

import com.paulinogreis.selecao2019.dominio.Pessoa;
import com.paulinogreis.selecao2019.dto.PessoaDTO;
import com.paulinogreis.selecao2019.servicos.PessoaServico;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaRecurso {

	@Autowired
	private PessoaServico servico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> find(@PathVariable Integer id) {
		Pessoa obj = servico.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PessoaDTO objDto) {
		Pessoa obj = servico.fromDTO(objDto);
		obj = servico.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PessoaDTO>> findAll() {
		List<Pessoa> list = servico.findAll();
		List<PessoaDTO> listDto = list.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PessoaDTO objDto, @PathVariable Integer id) {
		Pessoa obj = servico.fromDTO(objDto);
		obj = servico.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		servico.delete(id);
		return ResponseEntity.noContent().build();
	}

}
