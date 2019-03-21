package com.paulinogreis.selecao2019.excessoes;

public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoException(String msg) {
		super(msg);
	}

	public ObjetoNaoEncontradoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

