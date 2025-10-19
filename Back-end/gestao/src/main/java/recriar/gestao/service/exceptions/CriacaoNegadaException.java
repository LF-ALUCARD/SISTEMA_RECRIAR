package recriar.gestao.service.exceptions;

public class CriacaoNegadaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CriacaoNegadaException(String msg) {
		super(msg);
	}
}
