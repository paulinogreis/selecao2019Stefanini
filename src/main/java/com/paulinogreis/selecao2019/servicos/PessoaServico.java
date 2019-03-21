package com.paulinogreis.selecao2019.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulinogreis.selecao2019.dominio.Cidade;
import com.paulinogreis.selecao2019.dominio.Endereco;
import com.paulinogreis.selecao2019.dominio.Pessoa;
import com.paulinogreis.selecao2019.dominio.enums.TipoPessoa;
import com.paulinogreis.selecao2019.dto.PessoaDTO;
import com.paulinogreis.selecao2019.dto.PessoaNewDTO;
import com.paulinogreis.selecao2019.repositorios.PessoaRepositorio;

@Service
public class PessoaServico {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;

	public Pessoa find(Integer id) {
		Pessoa obj = pessoaRepositorio.findOne(id);
		return obj;
	}

	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		obj = pessoaRepositorio.save(obj);
		return obj;
	}

	public Pessoa update(Pessoa obj) {
		Pessoa newObj = find(obj.getId());
		atualizarDados(newObj, obj);
		obj = pessoaRepositorio.save(newObj);
		return obj;
	}

	public void delete(Integer id) {
		pessoaRepositorio.delete(id);
	}

	public List<Pessoa> findAll() {
		return pessoaRepositorio.findAll();
	}

	public Pessoa fromDTO(PessoaDTO objDto) {
		return new Pessoa(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getTipo(), objDto.getCpfCnpj());
	}

	public Pessoa fromDTO(PessoaNewDTO objDto) {
		Pessoa p = new Pessoa(null, objDto.getNome(), objDto.getEmail(), TipoPessoa.toEnum(objDto.getTipo()),
				objDto.getCpfCnpj());
		Cidade c = new Cidade(objDto.getCidadeId(), null, null);
		Endereco e = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), p, c);
		p.getEnderecos().add(e);
		p.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			p.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			p.getTelefones().add(objDto.getTelefone3());
		}
		return p;
	}

	public void atualizarDados(Pessoa objAatualizar, Pessoa objOrigem) {
		objAatualizar.setId(objOrigem.getId());
		objAatualizar.setNome(objOrigem.getNome());
		objAatualizar.setEmail(objOrigem.getEmail());
		objAatualizar.setTipo(objOrigem.getTipo());
	}
}
