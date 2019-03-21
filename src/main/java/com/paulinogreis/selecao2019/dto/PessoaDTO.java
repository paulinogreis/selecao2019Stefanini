package com.paulinogreis.selecao2019.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.paulinogreis.selecao2019.dominio.Pessoa;
import com.paulinogreis.selecao2019.dominio.enums.TipoPessoa;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@org.hibernate.validator.constraints.NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	private String cpfCnpj;

	private Integer tipo;

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoPessoa getTipo() {
		return TipoPessoa.toEnum(tipo);
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo.getCod();
	}

	public PessoaDTO() {
	}

	public PessoaDTO(Pessoa obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		cpfCnpj = obj.getCpfCnpj();
		tipo = (obj.getTipo() == null ? null : obj.getTipo().getCod());
	}
}
