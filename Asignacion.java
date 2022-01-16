import java.sql.Timestamp;

/**
 * 
 */

/**
 * @author <em><strong>Andr�s P�rez Dom�nguez</strong></em>
 *
 */
public class Asignacion {
	private int codPersona;
	private int codCuenta;
	private Timestamp alta;
	private Timestamp baja;
	private String observacion;
	
	/**
	 * Constructor que nos pide el c�digo de persona a asignar, la cuenta a ser asignada y la observaci�n.
	 * 
	 * @param <em>codPersona</em> -> el c�digo de la persona
	 * @param <em>codCuenta</em> -> c�digo de la cuenta
	 * @param <em>observacion</em> -> la observaci�n
	 */
	
	public Asignacion(int codPersona, int codCuenta, String observacion) {
	
		this.codPersona = codPersona;
		this.codCuenta = codCuenta;
		this.observacion = observacion;
	}
	
	
	
}
