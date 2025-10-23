package recriar.gestao.entities.enums.exceptions;

public class CodicoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CodicoInvalidoException (String msg) {
		super(msg);
	}
}
