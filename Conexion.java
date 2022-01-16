import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 */

/**
 * @author <em><strong>Andrés Pérez Domínguez</strong></em>
 *
 */
public class Conexion {
	private String nombreDB = "banco"; // Nombre de la base de datos
	private String usuario = "root"; // Usuario de la base de datos
	private String password = ""; // Contraseña de la base de datos
	private String url = "jdbc:mysql://localhost:3306/"+ nombreDB; // URL de la base de datos

	Connection conexion = null; // Variable de conexion a la base de datos
	
	/**
	 * Constructor vacío ya que no es necesario introducir ningún dato para que funcione la conexión. 
	 */
	
	public Conexion () {}
	
	/**
	 * Metodo que conecta con la base de datos
	 */
	public void conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar clase Driver
			try {
				//Damos valor a la variable conexion realizando la conexion a la base de datos
				conexion = DriverManager.getConnection(url, usuario, password); 
				
				if(conexion != null) {
					System.out.println("CONEXION ESTABLECIDA");
				}
				
			} catch (SQLException e) {
				System.out.println("Tabla o base de datos no encontrada");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver mal cargado");
		}
	}
	
	/**
	 * Metodo para retornar la conexion de la base de datos
	 * 
	 * @return conexion - estado de la conexion a la base de datos
	 */
	public Connection getConexion() {
		return conexion;
	}
	
	/**
	 * Metodo para desconectar de la base de datos
	 */
	public void desconectar () {
		conexion = null;
	}
	
	
}
