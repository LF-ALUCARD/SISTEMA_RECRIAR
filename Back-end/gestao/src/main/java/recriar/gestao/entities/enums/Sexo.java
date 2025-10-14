package recriar.gestao.entities.enums;

public enum Sexo {

	M(1),
	F(2);
	
	private int code;
	
	private Sexo(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public static Sexo valueof(int code) { 
		for(Sexo x : Sexo.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Códico invalido");
	}
}
