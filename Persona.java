import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * 
 */

/**
 * @author <em><strong>Andrés Pérez Domínguez</strong></em>
 *
 */
public class Persona {
	
	private int codPersona;
	private String apelNom;
	private String nif;
	private String email;
	private String direccion;
	private String tlf;
	private String fnaci;
	
	/**
	 * Constructor que nos pide los datos del usuario.
	 * 
	 * @param <em>apelNom</em> -> apellidos y nombre pedidos.
	 * @param <em>nif</em> -> NIF del usuario.
	 * @param <em>email</em> -> Correo electrónico del usuario.
	 * @param <em>direccion</em> -> Dirección del usuario.
	 * @param <em>tlf</em> -> Teléfono de contacto del usuario.
	 * @param <em>fnaci</em> -> Fecha de nacimiento del usuario.
	 */
	public Persona(String apelNom, String nif, String email, String direccion, String tlf, String fnaci) {
		this.apelNom = apelNom;
		this.nif = nif;
		this.email = email;
		this.direccion = direccion;
		this.tlf = tlf;
		this.fnaci = fnaci;
	}

	/**
	 * @return the codPersona
	 */
	private int getCodPersona() {
		return codPersona;
	}

	/**
	 * @param codPersona the codPersona to set
	 */
	private void setCodPersona(int codPersona) {
		this.codPersona = codPersona;
	}

	/**
	 * @return the apelNom
	 */
	private String getApelNom() {
		return apelNom;
	}

	/**
	 * @param apelNom the apelNom to set
	 */
	private void setApelNom(String apelNom) {
		this.apelNom = apelNom;
	}

	/**
	 * @return the nif
	 */
	private String getNif() {
		return nif;
	}

	/**
	 * @param nif the nif to set
	 */
	private void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * @return the email
	 */
	private String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	private void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the direccion
	 */
	private String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	private void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the tlf
	 */
	private String getTlf() {
		return tlf;
	}

	/**
	 * @param tlf the tlf to set
	 */
	private void setTlf(String tlf) {
		this.tlf = tlf;
	}

	/**
	 * @return the fnaci
	 */
	private String getFnaci() {
		return fnaci;
	}

	/**
	 * @param fnaci the fnaci to set
	 */
	private void setFnaci(String fnaci) {
		this.fnaci = fnaci;
	}
	
}
