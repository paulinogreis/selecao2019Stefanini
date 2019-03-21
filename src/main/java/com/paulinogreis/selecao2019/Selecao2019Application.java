package com.paulinogreis.selecao2019;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paulinogreis.selecao2019.dominio.Cidade;
import com.paulinogreis.selecao2019.dominio.Endereco;
import com.paulinogreis.selecao2019.dominio.Estado;
import com.paulinogreis.selecao2019.dominio.Pessoa;
import com.paulinogreis.selecao2019.dominio.enums.TipoPessoa;
import com.paulinogreis.selecao2019.repositorios.CidadeRepositorio;
import com.paulinogreis.selecao2019.repositorios.EnderecoRepositorio;
import com.paulinogreis.selecao2019.repositorios.EstadoRepositorio;
import com.paulinogreis.selecao2019.repositorios.PessoaRepositorio;

@SpringBootApplication
public class Selecao2019Application implements CommandLineRunner {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	@Autowired
	private EstadoRepositorio estadoRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(Selecao2019Application.class, args);
	}

	public void run(String... args) throws Exception {

		Estado est1 = new Estado(null, "Distrito Federal");
		Estado est2 = new Estado(null, "Goiás");
		Estado est3 = new Estado(null, "Minas Gerais");

		Cidade c1 = new Cidade(null, "Brasilia", est1);
		Cidade c2 = new Cidade(null, "Goiânia", est2);
		Cidade c3 = new Cidade(null, "Anápolis", est2);
		Cidade c4 = new Cidade(null, "Patos de Minas", est3);

		estadoRepositorio.save(Arrays.asList(est1, est2, est3));
		cidadeRepositorio.save(Arrays.asList(c1, c2, c3, c4));

		Pessoa p1 = new Pessoa(null, "Paulino Reis", "paulinogreis@gmail.com", TipoPessoa.PESSOAFISICA, "58534504787");
		Pessoa p2 = new Pessoa(null, "Sandra Reis", "sandra@gmail.com", TipoPessoa.PESSOAFISICA, "76077788899");
		Pessoa p3 = new Pessoa(null, "Tecnologia da informática", "tiinfo@tiinfo.com.br", TipoPessoa.PESSOAJURIDICA,
				"03445567000176");

		p1.getTelefones().addAll(Arrays.asList("30300284", "999689050"));
		p2.getTelefones().addAll(Arrays.asList("32330487", "986755442"));
		p3.getTelefones().addAll(Arrays.asList("34335875"));

		Endereco e1 = new Endereco(null, "Condominio RK", "25", "Conjunto Antares", "Sobradinho", "73252200", p1, c1);
		Endereco e2 = new Endereco(null, "SQS 512", "512", "101", "Apto", "70646774", p2, c2);
		Endereco e3 = new Endereco(null, "SCLN 705", "705", "1", "Subsolo sala", "70646532", p3, c1);

		p1.getEnderecos().addAll(Arrays.asList(e1));
		p2.getEnderecos().addAll(Arrays.asList(e2));
		p3.getEnderecos().addAll(Arrays.asList(e3));

		pessoaRepositorio.save(Arrays.asList(p1, p2, p3));
		enderecoRepositorio.save(Arrays.asList(e1, e2, e3));

	}
}
