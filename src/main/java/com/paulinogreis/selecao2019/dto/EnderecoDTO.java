package com.paulinogreis.selecao2019.dto;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.paulinogreis.selecao2019.dominio.Cidade;
import com.paulinogreis.selecao2019.dominio.Endereco;
import com.paulinogreis.selecao2019.dominio.Pessoa;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	private Integer pessoaId;

	private Integer cidadeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public EnderecoDTO(Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
			Integer pessoaId, Integer cidadeId) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.pessoaId = pessoaId;
		this.cidadeId = cidadeId;
	}

	public EnderecoDTO(Endereco obj) {
		this.id = obj.getId();
		this.bairro = obj.getBairro();
		this.cep = obj.getCep();
		this.complemento = obj.getComplemento();
		this.logradouro = obj.getLogradouro();
		this.numero = obj.getNumero();
		this.pessoaId = obj.getPessoa().getId();
		this.cidadeId = obj.getCidade().getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoDTO other = (EnderecoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public EnderecoDTO() {
	}
}
