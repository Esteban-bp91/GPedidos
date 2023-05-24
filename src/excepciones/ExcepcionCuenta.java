package excepciones;

public class ExcepcionCuenta extends Exception{
	String mensaje;
	
	public ExcepcionCuenta (String causa) {
		mensaje = causa;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
}
