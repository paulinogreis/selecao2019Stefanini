package com.paulinogreis.selecao2019.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulinogreis.selecao2019.dominio.Cidade;
import com.paulinogreis.selecao2019.dominio.Endereco;
import com.paulinogreis.selecao2019.dominio.Pessoa;
import com.paulinogreis.selecao2019.dto.EnderecoDTO;
import com.paulinogreis.selecao2019.repositorios.CidadeRepositorio;
import com.paulinogreis.selecao2019.repositorios.EnderecoRepositorio;
import com.paulinogreis.selecao2019.repositorios.PessoaRepositorio;

@Service
public class EnderecoServico {

	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	@Autowired
	private CidadeRepositorio cidadeRepositorio;

	public Endereco find(Integer id) {
		return enderecoRepositorio.findOne(id);
	}

	@Transactional
	public Endereco insert(Endereco obj) {
		obj.setId(null);
		return enderecoRepositorio.save(obj);
	}

	public Endereco update(Endereco obj) {
		return enderecoRepositorio.save(obj);

	}

	public void delete(Integer id) {
		enderecoRepositorio.delete(id);
	}

	public List<Endereco> findAll() {
		return enderecoRepositorio.findAll();
	}

	public Endereco fromDTO(EnderecoDTO objDto) {
		Pessoa pessoa = pessoaRepositorio.findOne(objDto.getPessoaId());
		Cidade cidade = cidadeRepositorio.findOne(objDto.getCidadeId());
		return new Endereco(objDto.getId(), objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), pessoa, cidade);
	}
}
