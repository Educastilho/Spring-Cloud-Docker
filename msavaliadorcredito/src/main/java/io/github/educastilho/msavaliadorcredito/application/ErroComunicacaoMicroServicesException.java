package io.github.educastilho.msavaliadorcredito.application;

public class ErroComunicacaoMicroServicesException extends Exception {

	private Integer status;
	
	public ErroComunicacaoMicroServicesException(String msg, Integer status) {
		super(msg);
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
