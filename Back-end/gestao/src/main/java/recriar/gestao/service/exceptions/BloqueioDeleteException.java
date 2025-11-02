package recriar.gestao.service.exceptions;

public class BloqueioDeleteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BloqueioDeleteException(String msg) {
		super(msg);
	}
}
