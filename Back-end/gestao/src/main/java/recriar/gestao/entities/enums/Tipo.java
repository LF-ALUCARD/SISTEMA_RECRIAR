package recriar.gestao.entities.enums;

public enum Tipo {
	
	ADMIN(0),
	PROFESSOR(1);
	
	private int code;
	
	private Tipo (int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public static Tipo valueof(int code) {
		for(Tipo x : Tipo.values()) {
			if(x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Códico invalido");
	}
}
