package com.paulinogreis.selecao2019.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.paulinogreis.selecao2019.dominio.enums.TipoPessoa;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String email;
	private Integer tipo;
	private String cpfCnpj;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();

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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public TipoPessoa getTipo() {
		return TipoPessoa.toEnum(tipo);
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo.getCod();
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Pessoa(Integer id, String nome, String email, TipoPessoa tipo, String cpfCnpj, List<Endereco> endereco,
			Set<String> telefones) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipo = (tipo == null ? null : tipo.getCod());
		this.cpfCnpj = cpfCnpj;
		this.setEnderecos(endereco);
		this.setTelefones(telefones);
	}

	public Pessoa(Integer id, String nome, String email, TipoPessoa tipo, String cpfCnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipo = (tipo == null ? null : tipo.getCod());
		this.cpfCnpj = cpfCnpj;
	}

	public Pessoa() {
	}

}
