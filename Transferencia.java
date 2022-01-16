import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 */

/**
 * @author <em><strong>Andrés Pérez Domínguez</strong></em>
 *
 */
public class Transferencia {
	private int codope;
	private int codpemi;
	private int codce;
	private int codpr;
	private int codcre;
	private String fechaope;
	private String concepto;
	private double importe;
	
	/**
	 * Constructor de transferencias que nos pide los datos de cada usuario implicado.
	 * 
	 * @param <em>codpemi</em> -> Código de la persona emisora.
	 * @param <em>codce</em> -> Código de la cuenta emisora.
	 * @param <em>codpr</em> -> Código de la persona receptora.
	 * @param <em>codcre</em> -> Código de la cuenta receptora.
	 * @param <em>fechaope</em> -> Fecha de la operación.
	 * @param <em>concepto</em> -> Concepto de la transacción.
	 * @param <em>importe</em> -> Importe de la transacción.
	 */
	
	public Transferencia(int codpemi, int codce, int codpr, int codcre, String fechaope, String concepto,
			double importe) {
		this.codpemi = codpemi;
		this.codce = codce;
		this.codpr = codpr;
		this.codcre = codcre;
		this.fechaope = fechaope;
		this.concepto = concepto;
		this.importe = importe;
	}
	
	
	
}
