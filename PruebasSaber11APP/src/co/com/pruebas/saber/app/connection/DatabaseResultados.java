package co.com.pruebas.saber.app.connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 
 * @author Michael
 *
 */
public class DatabaseResultados {
	private static Connection auxConectar;
	private static final String driver= "oracle.jdbc.driver.OracleDriver";
	private static final String usuario="PRUEBASSABER11";
	private static final String password="millonarios10";
	private static final String  url="jdbc:oracle:thin:@localhost:1521:xe";
	
	/**
	 * Establecer la conexion con la base de datos
	 */
	public DatabaseResultados(){

		auxConectar = null;

		try {
			Class.forName(driver);
			auxConectar = (Connection) DriverManager.getConnection(url, usuario, password);

			if (auxConectar!= null) {
				System.out.println("Conexion establecida");
			}

		}catch (SQLException e) {
			System.out.println("Error al establecer la conexion");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static  Connection getConnection() {
		return auxConectar;
	}

	public void desconectar() {
		auxConectar = null;
		if (auxConectar==null) {
			System.out.println("Conexion terminada");
		}
	}
}
