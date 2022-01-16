import java.sql.Timestamp;

/**
 * 
 */

/**
 * @author <em><strong>Andrés Pérez Domínguez</strong></em>
 *
 */
public class Asignacion {
	private int codPersona;
	private int codCuenta;
	private Timestamp alta;
	private Timestamp baja;
	private String observacion;
	
	/**
	 * Constructor que nos pide el código de persona a asignar, la cuenta a ser asignada y la observación.
	 * 
	 * @param <em>codPersona</em> -> el código de la persona
	 * @param <em>codCuenta</em> -> código de la cuenta
	 * @param <em>observacion</em> -> la observación
	 */
	
	public Asignacion(int codPersona, int codCuenta, String observacion) {
	
		this.codPersona = codPersona;
		this.codCuenta = codCuenta;
		this.observacion = observacion;
	}
	
	
	
}
