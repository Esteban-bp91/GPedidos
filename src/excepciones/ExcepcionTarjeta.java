package excepciones;

public class ExcepcionTarjeta extends Exception{
	String mensaje;
	
	public ExcepcionTarjeta (String causa) {
		mensaje = causa;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
}
