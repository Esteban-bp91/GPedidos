package excepciones;

public class ExcepcionTelefono extends Exception{
	String mensaje;
	
	public ExcepcionTelefono (String causa) {
		mensaje = causa;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
}
