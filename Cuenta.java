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
public class Cuenta {
	
	private int codCuenta;
	private String iban;
	private String banco;
	private String dirsuc;
	
	/**
	 * Constructor que nos pide que introduzcamos el IBAN, BANCO y la DIRECCION DE LA SUCURSAL
	 * 
	 * @param <em>dirsuc</em> -> parámetro que hace referencia a la direccion de la sucursal
	 * @param <em>iban</em> -> el IBAN de cada cuenta
	 * @param <em>banco</em> -> el banco al cual pertenece la cuenta
	 */
	
	public Cuenta(String iban, String banco, String dirsuc) {

		this.iban = iban;
		this.banco = banco;
		this.dirsuc = dirsuc;
	}
	

	/**
	 * @return codCuenta
	 */
	private int getCodCuenta() {
		return codCuenta;
	}

	/**
	 * @param codCuenta the codCuenta to set
	 */
	private void setCodCuenta(int codCuenta) {
		this.codCuenta = codCuenta;
	}

	/**
	 * @return the iban
	 */
	private String getIban() {
		return iban;
	}

	/**
	 * @param iban the iban to set
	 */
	private void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * @return the banco
	 */
	private String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	private void setBanco(String banco) {
		this.banco = banco;
	}
	
	/**
	 * @return the dirsuc
	 */
	private String getDirsuc() {
		return dirsuc;
	}
	
	/**
	 * @param dirsuc the dirsuc to set
	 */
	private void setDirsuc(String dirsuc) {
		this.dirsuc = dirsuc;
	}
	
}
	
	


