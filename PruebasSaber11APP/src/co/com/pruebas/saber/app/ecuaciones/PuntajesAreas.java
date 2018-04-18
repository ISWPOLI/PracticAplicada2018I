package co.com.pruebas.saber.app.ecuaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import co.com.pruebas.saber.app.connection.DatabaseResultados;

public class PuntajesAreas {
	private static ArrayList<Double> puntajeMatematicas = new ArrayList<Double>();
	private static ArrayList<Double> puntajeFisica = new ArrayList<Double>();
	private static ArrayList<Double> puntajeLenguaje = new ArrayList<Double>();
	private static ArrayList<Double> puntajeBiologia = new ArrayList<Double>();

	/**
	 * Este constructor realiza el select a la base de datos principal y para
	 * los parametros de conexion y statement,preparedStatement a cada metodo
	 * 
	 * @throws SQLException
	 */
	public PuntajesAreas() throws SQLException {

		new DatabaseResultados();
		Connection auxConexion = DatabaseResultados.getConnection();
		Statement sentencia = (Statement) auxConexion.createStatement(); // sentencias
																			// SQL
		PreparedStatement prepararSentencia = null;
		String consulta = "SELECT * FROM PRUEBA3";
		ResultSet resultadoConsulta = sentencia.executeQuery(consulta);

		puntajeMatematicas(resultadoConsulta, auxConexion, prepararSentencia);
		puntajeFisica(resultadoConsulta, auxConexion, prepararSentencia);
		puntajeLenguaje(resultadoConsulta, auxConexion, prepararSentencia);
		puntajeBiologia(resultadoConsulta, auxConexion, prepararSentencia);
	}

	/**
	 * Este metodo obtiene el puntaje de la area de biologia
	 * 
	 * @param resultadoConsulta
	 *            - select a la base de datos
	 * @param auxConexion
	 *            - conexion establecida con la base de datos
	 * @param prepararSentencia
	 *            - resultado obtenido del select a la base de datos
	 * @return - retorna el puntaje de biologia
	 */
	public static ArrayList<Double> puntajeBiologia(
			ResultSet resultadoConsulta, Connection auxConexion,
			PreparedStatement prepararSentencia) {
		try {
			int contador = 0;
			String registros = "select * from PRUEBA3";
			prepararSentencia = (PreparedStatement) auxConexion
					.prepareStatement(registros);
			resultadoConsulta = prepararSentencia.executeQuery();

			while (resultadoConsulta.next()) {
				puntajeBiologia.add(contador,
						resultadoConsulta.getDouble("PUNT_BIOLOGIA"));
				contador++;
			}
		} catch (Exception e) {
			System.out.println("OPERACION INVALIDA O DATOS ERRONEOS");
		}

		return puntajeBiologia;
	}

	/**
	 * Este metodo obtiene el puntaje de la area de lenguaje
	 * 
	 * @param resultadoConsulta
	 *            - select a la base de datos
	 * @param auxConexion
	 *            - conexion establecida con la base de datos
	 * @param prepararSentencia
	 *            - resultado obtenido del select a la base de datos
	 * @return - retorna el puntaje de lenguaje
	 */

	public static ArrayList<Double> puntajeLenguaje(
			ResultSet resultadoConsulta, Connection auxConexion,
			PreparedStatement prepararSentencia) {
		try {
			int contador = 0;
			String registros = "select * from PRUEBA3";
			prepararSentencia = (PreparedStatement) auxConexion
					.prepareStatement(registros);
			resultadoConsulta = prepararSentencia.executeQuery();

			while (resultadoConsulta.next()) {
				puntajeLenguaje.add(contador,
						resultadoConsulta.getDouble("PUNT_LENGUAJE"));
				contador++;
			}
		} catch (Exception e) {
			System.out.println("OPERACION INVALIDA O DATOS ERRONEOS");
		}

		return puntajeLenguaje;
	}

	/**
	 * Este metodo obtiene el puntaje de la area de fisica
	 * 
	 * @param resultadoConsulta
	 *            - select a la base de datos
	 * @param auxConexion
	 *            - conexion establecida con la base de datos
	 * @param prepararSentencia
	 *            - resultado obtenido del select a la base de datos
	 * @return - retorna el puntaje de fisica
	 */

	public static ArrayList<Double> puntajeFisica(ResultSet resultadoConsulta,
			Connection auxConexion, PreparedStatement prepararSentencia) {
		try {
			int contador = 0;
			String registros = "select * from PRUEBA3";
			prepararSentencia = (PreparedStatement) auxConexion
					.prepareStatement(registros);
			resultadoConsulta = prepararSentencia.executeQuery();

			while (resultadoConsulta.next()) {
				puntajeFisica.add(contador,
						resultadoConsulta.getDouble("PUNT_FISICA"));
				contador++;
			}
		} catch (Exception e) {
			System.out.println("OPERACION INVALIDA O DATOS ERRONEOS");
		}

		return puntajeFisica;
	}

	/**
	 * Este metodo obtiene el puntaje de la area de matematicas
	 * 
	 * @param resultadoConsulta
	 *            - select a la base de datos
	 * @param auxConexion
	 *            - conexion establecida con la base de datos
	 * @param prepararSentencia
	 *            - resultado obtenido del select a la base de datos
	 * @return - retorna el puntaje de matematicas
	 */
	public static ArrayList<Double> puntajeMatematicas(
			ResultSet resultadoConsulta, Connection auxConexion,
			PreparedStatement prepararSentencia) {

		try {
			int contador = 0;
			String registros = "select * from PRUEBA3 where ESTU_RESIDE_DEPTO = 'CUNDINAMARCA'";
			prepararSentencia = (PreparedStatement) auxConexion
					.prepareStatement(registros);
			resultadoConsulta = prepararSentencia.executeQuery();

			while (resultadoConsulta.next()) {
				puntajeMatematicas.add(contador,
						resultadoConsulta.getDouble("PUNT_MATEMATICAS"));
				contador++;
			}
		} catch (Exception e) {
			System.out.println("OPERACION INVALIDA O DATOS ERRONEOS");
		}
		return puntajeMatematicas;
	}

	public static ArrayList<Double> getPuntajeBiologia() {
		return puntajeBiologia;
	}

	public static ArrayList<Double> getPuntajeFisica() {
		return puntajeFisica;
	}

	public static ArrayList<Double> getPuntajeLenguaje() {
		return puntajeLenguaje;
	}

	public static ArrayList<Double> getPuntajeMatematicas() {
		return puntajeMatematicas;
	}

}
