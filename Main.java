import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author <em><strong>Andrés Pérez Domínguez</strong></em>
 *
 */
public class Main {

	/**
	 * @param <em>String <u>consulta</u></em> -> variable global para hacer las consultas en la base de datos.
	 * @param <em>PreparedStatement <u>statement</u></em> -> variable global para hacer los statements. 
	 * @param <em>ResultSet <u>result</u></em> -> variable global para sacar los resultados de las consultas. 
	 * @param <em>Conexion <u>conexion</u></em> -> variable global para hacer la conexion a la base de datos. 
	 */
	
	static String consulta;
	static PreparedStatement statement;
	static ResultSet result;
	static Conexion conexion = new Conexion();
	
	/**
	 * Método <u>main</u> que contiene únicamente la conexion a la base de datos y el método <em>menu()</em> el cual hablaré de él a continuación.
	 */
	
	public static void main(String[] args) {
		
		conexion.conectar();
		menu();
	}
	
	/**
	 * Método <u>menu</u> con las opciones disponibles nada más entrar al programa.
	 */

	public static void menu() {

		Object[] opciones = {"Acceder", "Nuevo Usuario"};
		int operacion = JOptionPane.showOptionDialog(null, "Bienvenido", "Banco Stormingon", 
				0, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		
		switch (operacion) {
		case 0: 
			acceso();
			break;
		case 1:
			nuevoUsuario();
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * Método <u>nuevoUsuario</u> el cual pide al usuario sus datos para registrarse en la base de datos.
	 * 
	 * @param <em>String <u>nombre</u></em> -> nombre y apellidos del usuario.
	 * @param <em>String <u>dni</u></em> -> dni del usuario.
	 * @param <em>String <u>email</u></em> -> correo del usuario.
	 * @param <em>String <u>direccion</u></em> -> direccion del usuario.
	 * @param <em>String <u>tlf</u></em> -> teléfono del usuario.
	 * @param <em>String <u>fnaci</u></em> -> fecha de nacimiento del usuario.
	 */

	public static void nuevoUsuario() {
		String nombre = JOptionPane.showInputDialog("Introduce tu nombre y tus apellidos");
		String dni = JOptionPane.showInputDialog("Introduce tu dni");
		String email = JOptionPane.showInputDialog("Introduce tu correo");
		String direccion = JOptionPane.showInputDialog("Introduce tu dirección");
		String tlf = JOptionPane.showInputDialog("Introduce tu número de teléfono");
		String fnaci = JOptionPane.showInputDialog("Introduce tu fecha de nacimiento (formato AAAA-MM-DD)");
		
		try {
			
			consulta = "insert into personas(APELLNOM, NIF, EMAIL, DIRECCION, TLF, FNACI) values (?,?,?,?,?,?)";
			statement = conexion.getConexion().prepareStatement(consulta);
			
			statement.setString(1, nombre);
			statement.setString(2, dni);
			statement.setString(3, email);
			statement.setString(4, direccion);
			statement.setString(5, tlf);
			statement.setString(6, fnaci);
			
			JOptionPane.showMessageDialog(null, "Usuario creado, diríjase a 'acceder' para crear su primera cuenta bancaria");
			
			statement.executeUpdate(); 
			
			menu();
			
		} catch (SQLException e) {
			
		}
		
		
		
	}
	
	/**
	 * Método <u>acceso</u> que nos muestra una ventana que nos pide introducir nuestro DNI para entrar a ver las cuentas de usuario, transacciones, etc...
	 * 
	 * @param <em>String <u>info</u></em> -> string que retiene la información de la consulta y lo muestra en pantalla.
	 * @param <em>String <u>usuario</u></em> -> joptionpane para introducir el dni.
	 * @param <em>int <u>operacion</u></em> -> otro joptionpane para elegir la siguiente operacion.
	 */

	public static void acceso() {
		String info = "";
		String usuario = JOptionPane.showInputDialog("Introduce tu dni");
		
		
		consulta = "select * from personas where NIF='"+usuario+"'";	
		
		try {
			
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery();
			
			while(result.next()) {
				
				info += "Código de usuario: " + result.getInt("CODP") + "\n" + "Nombre: " + result.getString("APELLNOM") +"\n"+ "DNI: " + result.getString("NIF") +"\n"+ "Correo: " +result.getString("EMAIL") +"\n"+ "Dirección: " + result.getString("DIRECCION") +"\n"+ "Teléfono: " + result.getString("TLF") +"\n"+ "Fecha nacimiento: " + result.getString("FNACI") + "\n";
			
			}
				
		} catch (SQLException e) {
			System.out.println("ERROR EN LA QUERY");
		} 
		
		
		
		Object[] opciones = {"Volver",	"Revisar cuentas", "Revisar transacciones", "Darse de baja"};
		int operacion = JOptionPane.showOptionDialog(null, info, "Banco Stormingon", 
				0, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		
		switch (operacion) {
		case 0: // Volver
			menu();
			break;
			
		case 1: // Transacciones
			revisarCuentas();
			break;
			
		case 2: // Eliminar
			revisarTransacciones();
			break;
			
		case 3:
			baja();

		default:
			break;
			
		}
		

	}
			
	/**
	 * Método <u>baja</u> que sirve para eliminar al usuario de la base de datos.
	 * 
	 * @param <em>String[] <u>opciones</u></em> -> array de strings con las diferentes opciones a elegir.
	 * @param <em>int <u>eleccion</u></em> -> int que retiene la opcion escogida y hace una cosa diferente dependiendo de ella.
	 */
	public static void baja() {
		String[] opciones = {"Sí", "No"};
		
		int eleccion = JOptionPane.showOptionDialog(null, "Estás seguro de que quieres darte de baja?", "Darse de baja", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
		
		switch (eleccion) {
		
		case 0:
			String codP = JOptionPane.showInputDialog("Introduce tu codigo de usuario para confirmar");
			
			try {
				consulta = "delete from personas where CODP = " + codP;
				statement = conexion.getConexion().prepareStatement(consulta);
				statement.executeUpdate(); 
				
				consulta = "delete from asignacion where CODP = " + codP;
				statement = conexion.getConexion().prepareStatement(consulta);
				statement.executeUpdate(); 
				
				JOptionPane.showMessageDialog(null, "Usuario eliminado");
				
			} catch (SQLException e){
				System.out.println("No se pudo eliminar tu usuario");
			}
			
		case 1:
			acceso();
		}
			
		
	}

	/**
	 * Método <u>revisarCuentas</u> que hace que aparezca en pantalla las cuentas asociadas a la persona logueada.
	 * 
	 * @param <em>int <u>codP</u></em> -> int que retiene el código del usuario para sacar sus cuentas.
	 * @param <em>String <u>info</u></em> -> string que retiene la información de la consulta y lo muestra en pantalla.
	 * @param <em>int <u>eleccion</u></em> -> int que lleva a la siguiente operacion dependiendo de lo que el usuario elija.
	 */
	public static void revisarCuentas() {
		int codP = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu código de usuario"));
		String info = "";
		
		consulta = "select IBAN, BANCO, DIRSUC FROM cuentas INNER JOIN asignacion ON asignacion.CODC = cuentas.CODC where asignacion.CODP =" + codP;

		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery();
			
			while(result.next()) {
				
				info += "IBAN: " + result.getString("IBAN") + " " + "Banco: " + result.getString("BANCO") + " " + "Dirección sucursal: " + result.getString("DIRSUC") + "\n";
			
			}
			
		} catch (SQLException e) {
			System.out.println("Error en la query");
		}
		
		Object[] opciones = {"Borrar cuenta bancaria", "Crear cuenta bancaria"};
		
		int eleccion = JOptionPane.showOptionDialog(null, info, "Banco Stormingon", 
				0, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		
		switch(eleccion) {
		
		case 0:
			eliminarCuenta();
			break;
			
		case 1:
			crearCuenta();
			break;
		}
	}
	
	/**
	 * Método <u>crearCuenta</u> que sirve para crear una cuenta bancaria para el usuario.
	 * 
	 * @param <em>String <u>banco</u></em> -> string que pide introducir el nombre del banco asociado a la cuenta.
	 * @param <em>String <u>dirsuc</u></em> -> string que pide introducir la dirección de la sucursal del banco.
	 * @param <em>String <u>iban</u></em> -> string que genera un IBAN aleatorio para la nueva cuenta.
	 */
	public static void crearCuenta() {
		
		
		
		String banco = JOptionPane.showInputDialog("Introduce el banco asociado a la cuenta nueva: ");
		String dirsuc = JOptionPane.showInputDialog("Introduce la dirección de la sucursal: ");
		String iban = "ES"+String.format("%02d", (int)(Math.random()*(99 - 1) + 1))+String.format("%04d", (int)(Math.random()*(9999 - 1) + 1))+String.format("%04d", (int)(Math.random()*(9999 - 1) + 1))+String.format("%05d", (int)(Math.random()*(99999 - 1) + 1))+String.format("%05d", (int)(Math.random()*(99999 - 1) + 1));
		int codP = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu código de usuario para confirmar"));
		int codC =  primerCodCuenta() + 1;
		
		
		try {
			
		consulta = "insert into cuentas(IBAN, BANCO, DIRSUC) values (?,?,?)";	
		statement = conexion.getConexion().prepareStatement(consulta);
		
		statement.setString(1, iban);
		statement.setString(2, banco);
		statement.setString(3, dirsuc);
		
		statement.execute();
		
		
		consulta = "insert into asignacion(CODP, CODC, OBSERVACION) values (?,?,?)";
		statement = conexion.getConexion().prepareStatement(consulta);
		
		statement.setInt(1, codP);
		statement.setInt(2, codC);
		statement.setString(3, "Nueva cuenta");
		
		statement.execute();
		
		revisarCuentas();

		} catch (SQLException e) {
			System.out.println("Error en la query");
		}
		
	}
	
	/**
	 * Método <u>eliminarCuenta</u> que sirve para deshacerse de una cuenta asociada al usuario.
	 * 
	 * @param <em>int <u>codP</u></em> -> int que se le pide al usuario para el código de la persona.
	 */
	public static void eliminarCuenta() {
		Object[] cuentas = new Object[0];
		
		Conexion conexion = new Conexion();
		conexion.conectar();
		
		int codP = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu código de usuario"));
		
		
		consulta = "select * from cuentas INNER JOIN asignacion ON asignacion.CODC = cuentas.CODC where asignacion.CODP =" + codP;

		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery(); 
			
			while(result.next()) {
				cuentas = Arrays.copyOf(cuentas, cuentas.length + 1); 
				cuentas[cuentas.length - 1] =  result.getInt("CODC") + " " + result.getString("IBAN"); 
			}
		} catch (SQLException e) {
			System.out.println("ERROR EN LA QUERY");
		} 
		
		
		Object cuentaElegida = JOptionPane.showInputDialog(null, "Elige la cuenta a eliminar", "Eliminar cuenta", 
				JOptionPane.PLAIN_MESSAGE, null, cuentas, cuentas[0]);
		
		String cuentaElegidaBuena = cuentaElegida.toString().split(" ")[0];
		
		int codcuenta = Integer.parseInt(cuentaElegidaBuena);
		
		try {
			consulta = "DELETE FROM cuentas WHERE CODC = " + codcuenta;
			statement = conexion.getConexion().prepareStatement(consulta);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cuenta eliminada", "", codcuenta, null);
			
			consulta = "DELETE FROM asignacion WHERE CODC = " + codcuenta;
			statement = conexion.getConexion().prepareStatement(consulta);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("ERROR AL HACER EL DELETE");
		}
		
		conexion.desconectar();
	}
	
	/**
	 * Método <u>revisarTransacciones</u> que muestra el historial de transacciones del usuario y realizar y eliminar transferencias.
	 * 
	 * @param <em>String <u>info</u></em> -> string que retiene la información de la consulta y lo muestra en pantalla.
	 * @param <em>String[] <u>opciones</u></em> -> array de strings con las diferentes opciones a elegir.
	 * @param <em>int <u>codP</u></em> -> int que se le pide al usuario para el código de la persona.
	 */

	public static void revisarTransacciones() {
		
		String info = "\n";
		String[] opciones = {"Realizar transferencia", "Eliminar transferencia"};
		
		Conexion conexion = new Conexion();
		conexion.conectar();
		
		int codP = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu código de usuario para continuar"));

		consulta = "select * from transferencia inner join personas on personas.CODP = transferencia.CODPEMI where personas.CODP =" + codP;

			try {
				statement = conexion.getConexion().prepareStatement(consulta);
				result = statement.executeQuery();
				
				while(result.next()) {
					info += "Código operación: " + result.getInt("CODOPE") + " " + "Código persona receptora: " + result.getInt("CODPR") + " " + "Código cuenta receptora: " + result.getInt("CODCRE") + " " + "Fecha operación: " + result.getString("FECHAOPE") + " " + "Concepto: " +  result.getString("CONCEPTO") + " " + "Importe: " + result.getDouble("IMPORTE") + "\n" ;
				}
				
			} catch (SQLException e) {
				System.out.println("ERROR AL HACER EL INSERT");
			}
			
			int eleccion = JOptionPane.showOptionDialog(null, info, "Banco Stormingon", 
					0, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
			
			switch (eleccion) {
			
			case 0:
				realizarTransferencia();
				break;
				
			case 1:
				eliminarTransferencia();
				break;

			}
		}
	
	/**
	 * Método <u>eliminarTransferencia</u> que sirve exactamente para eliminar una transferencia del usuario.
	 * 
	 * @param <em>int <u>codP</u></em> -> int que se le pide al usuario para el código de la persona.
	 */

	public static void eliminarTransferencia() {
		Object[] transferencias = new Object[0];
		int codP = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu código de usuario"));
		
		consulta = "select * FROM transferencia WHERE CODPEMI = " + codP;
		Conexion conexion = new Conexion();
		conexion.conectar();

		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery(); 
			
			while(result.next()) {
				transferencias = Arrays.copyOf(transferencias, transferencias.length + 1); 
				transferencias[transferencias.length - 1] = result.getInt("CODOPE") + " " + result.getInt("CODPR") + " " + result.getInt("CODCR") + " " + result.getString("CONCEPTO") + " " + result.getDouble("IMPORTE"); 
			}
		} catch (SQLException e) {
			System.out.println("ERROR EN LA QUERY");
		} 
		
		
		Object transferenciaElegida = JOptionPane.showInputDialog(null, "Elige la cuenta para hacer la transferencia", "Hacer transferencia", 
				JOptionPane.PLAIN_MESSAGE, null, transferencias, transferencias[0]);
		
		String transferenciaElegidaBuena = transferenciaElegida.toString().split(" ")[0];
		
		int codope = Integer.parseInt(transferenciaElegidaBuena);
		
		consulta = "DELETE FROM transferencia where CODOPE = " + codope;
		
		try {
			
			statement = conexion.getConexion().prepareStatement(consulta);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cuenta eliminada", "", codope, null);
			
		} catch (SQLException e) {
			
		}
		
	}
	
	/**
	 * Método <u>realizarTransferencia</u>
	 * 
	 * @param <em>Object[] <u>cuentas</u></em> -> array de objetos que sirve para dar a elegir al usuario la cuenta que quiere usar para hacer la transferencia.
	 * @param <em>Object[] <u>cuentas2</u></em> -> array de objetos que sirve para dar a elegir al usuario la cuenta a la que se quiere hacer la transferencia.
	 * @param <em>Object[] <u>usuarios</u></em> -> array de objetos que sirve para dar a elegir al usuario la persona a la que se le quiere hacer la transferencia.
	 * @param <em>int <u>codP</u></em> -> int que se le pide al usuario para el código de la persona.
	 * @param <em>String <u>concepto</u></em> -> string que se le pide al usuario para que introduzca un concepto de la transacción.
	 * @param <em>double <u>importe</u></em> -> double que se le pide al usuario para que introduzca el importe de la transacción.
	 */

	public static void realizarTransferencia() {
		Object[] cuentas = new Object[0];
		Object[] cuentas2 = new Object[0];
		Object[] usuarios = new Object[0];
		
		Conexion conexion = new Conexion();
		conexion.conectar();
		
		int codP = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu código de usuario"));
		String concepto = JOptionPane.showInputDialog("Introduce el concepto de la transacción");
		double importe = Double.parseDouble(JOptionPane.showInputDialog("Introduce el importe de la operación"));
		
		
		consulta = "select * from cuentas INNER JOIN asignacion ON asignacion.CODC = cuentas.CODC where asignacion.CODP =" + codP;

		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery(); 
			
			while(result.next()) {
				cuentas = Arrays.copyOf(cuentas, cuentas.length + 1); 
				cuentas[cuentas.length - 1] =  result.getInt("CODC") + " " + result.getString("IBAN"); 
			}
		} catch (SQLException e) {
			System.out.println("ERROR EN LA QUERY");
		} 
		
		
		Object cuentaElegida = JOptionPane.showInputDialog(null, "Elige la cuenta para hacer la transferencia", "Hacer transferencia", 
				JOptionPane.PLAIN_MESSAGE, null, cuentas, cuentas[0]);
		
		String cuentaElegidaBuena = cuentaElegida.toString().split(" ")[0];
		
		int codcuenta = Integer.parseInt(cuentaElegidaBuena);
		
		consulta = "select * from cuentas";

		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery(); 
			
			while(result.next()) {
				cuentas2 = Arrays.copyOf(cuentas2, cuentas2.length + 1); 
				cuentas2[cuentas2.length - 1] =  result.getInt("CODC") + " " + result.getString("IBAN"); 
			}
		} catch (SQLException e) {
			System.out.println("ERROR EN LA QUERY");
		} 
		
		
		Object cuentaElegida2 = JOptionPane.showInputDialog(null, "Elige la cuenta receptora de la transferencia", "Hacer transferencia", 
				JOptionPane.PLAIN_MESSAGE, null, cuentas2, cuentas2[0]);
		
		String cuentaElegidaBuena2 = cuentaElegida2.toString().split(" ")[0];
		
		int codcuenta2 = Integer.parseInt(cuentaElegidaBuena2);
		
		consulta = "select * from personas";

		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery(); 
			
			while(result.next()) {
				usuarios = Arrays.copyOf(usuarios, usuarios.length + 1); 
				usuarios[usuarios.length - 1] =  result.getInt("CODP") + " " + result.getString("APELLNOM"); 
			}
		} catch (SQLException e) {
			System.out.println("ERROR EN LA QUERY");
		} 
		
		
		Object usuarioElegido = JOptionPane.showInputDialog(null, "Elige la persona a la que hacer la transferencia", "Hacer transferencia", 
				JOptionPane.PLAIN_MESSAGE, null, usuarios, usuarios[0]);
		
		String usuarioElegidoBueno = usuarioElegido.toString().split(" ")[0];
		
		int codP2 = Integer.parseInt(usuarioElegidoBueno);
		
		try {
			consulta = " insert into transferencia(CODPEMI, CODCE, CODPR, CODCRE, CONCEPTO, IMPORTE) values (?,?,?,?,?,?)";
			statement = conexion.getConexion().prepareStatement(consulta);
			
			statement.setInt(1, codP);
			statement.setInt(2, codcuenta);
			statement.setInt(3, codP2);
			statement.setInt(4, codcuenta2);
			statement.setString(5, concepto);
			statement.setDouble(6, importe);
			
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Transacción realizada");
			
		} catch (SQLException e) {
			System.out.println("ERROR AL HACER EL DELETE");
		}
		
	}
	
	/**
	 * Método <u>primerCodCuenta</u> que sirve para que nos devuelva la primera posicion vacía de la tabla <em>cuentas</em> y que la siguiente cuenta se cree en esa posición.
	 * 
	 * @return codigo - el siguiente código en la tabla cuentas
	 */

	public static int primerCodCuenta (){
		Conexion conexion = new Conexion();
		conexion.conectar();
		int codigo = -1;
		
		 consulta = "select MAX(CODC) from cuentas";
		
		try {
			statement = conexion.getConexion().prepareStatement(consulta);
			result = statement.executeQuery(); 
			
			while(result.next()) {
				codigo = result.getInt("MAX(CODC)");
			}
		} catch (SQLException e) {
			System.out.println("ERROR EN LA QUERY");
		} 
		
		return codigo;
	}

}
