package excepciones;

public class ExcepcionImporte extends Exception{
	String mensaje;
	
	public ExcepcionImporte(String causa) {
		mensaje = causa;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
}
