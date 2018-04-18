package co.com.pruebas.saber.app.ecuaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * implementacion de las ecuaciones y sus operaciones
 * 
 * @author Michael
 *
 */
public class Ecuaciones {
	private static double primeraEcuacion;
	private static double segundaEcuacion;
	private static double terceraEcuacion;
	private static double cuartaEuacion;
	private static int evaluados;
	private static int evaluadosDepartamento;
	private static int evaluadosPeriodo;
	private static String departamento = "CUNDINAMARCA";
	private static String area = "PUNT_FISICA";
	public static int añoInicial = 2005;
	public static int añoSiguiente = 2005;
	private static ArrayList<Double> puntajes = new ArrayList<Double>();
	private static ArrayList<Integer> frecuenciaMatematicas = new ArrayList<Integer>();
	private static ArrayList<Double> puntajeDepartamento = new ArrayList<Double>();
	private static ArrayList<Double> PrimeraOperacionE4 = new ArrayList<Double>();
	private static ArrayList<Double> segundaOperacionE4 = new ArrayList<Double>();

	/**
	 * constructor utiliza la conexion establecida en la clase conexionMySql y
	 * realiza los select nesesarios a la base de datos para el funcionamiento
	 * de los metodos implementados.
	 * 
	 * @throws SQLException
	 */
	public Ecuaciones() throws SQLException {
		new PuntajesAreas();
		Connection auxConexion = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "PRUEBASSABER11",
				"millonarios10");
		Statement sentencia = (Statement) auxConexion.createStatement();
		PreparedStatement prepararSentencia = null;
		String consulta = "SELECT * FROM PRUEBA3";
		ResultSet resultadoConsulta = sentencia.executeQuery(consulta);

		primeraEcuacion(añoInicial, añoSiguiente);
		segundaEcuacion(resultadoConsulta, auxConexion, prepararSentencia);
		// terceraEcuacion(resultadoConsulta, auxConexion, prepararSentencia);
		frecuencia(PuntajesAreas.getPuntajeMatematicas());
		PrimeraOperacionE4(frecuenciaMatematicas);
		puntajeDepartamento(resultadoConsulta, auxConexion, prepararSentencia,
				departamento, area);
		terceraEcuacion(resultadoConsulta, auxConexion, prepararSentencia);
		evaluadosPeriodo(resultadoConsulta, auxConexion, prepararSentencia);
		// CUARTA ECUACION
		cuartaEcuacion(PrimeraOperacionE4(frecuenciaMatematicas));

	}

	public static double cuartaEcuacion(ArrayList<Double> primeraOperacionE42) {
		double sumaPrimeraOPE4 = 0;
		double operacionAux;
		for (int i = 0; i < primeraOperacionE42.size(); i++) {
			Double valor;
			try {
				valor = (primeraOperacionE42.get(i));
			} catch (NumberFormatException e) {
				valor = (double) 0;
			}
			sumaPrimeraOPE4 += valor;
		}
		operacionAux = sumaPrimeraOPE4 / getEvaluadosDepartamento();
		cuartaEuacion = Math.sqrt(operacionAux);
		return cuartaEuacion;
	}

	/**
	 * Este metodo realiza la primera operacion para la cuarta ecuacion
	 * 
	 * @param frecuencia
	 *            - frecuencia de la area asignada en este caso la de
	 *            matematicas
	 * @return - retrona la frecuencia elevada al cuadrado
	 */
	public ArrayList<Double> PrimeraOperacionE4(ArrayList<Integer> frecuencia) {
		for (int j = 0; j < PuntajesAreas.getPuntajeMatematicas().size(); j++) {
			for (int i = 0; i < frecuencia.size(); i++) {
				PrimeraOperacionE4.add(j, (Math.pow(frecuencia.get(i), 2))
						* PuntajesAreas.getPuntajeMatematicas().get(j));
			}
		}
		return PrimeraOperacionE4;
	}

	/**
	 * Este metodo calcula la frecuencia
	 * 
	 * @param materia
	 *            - materia asiganada
	 * @return - retorna la frecuencia
	 */
	public static ArrayList<Integer> frecuencia(List<Double> materia) {
		Set<Double> auxiliar = new HashSet<Double>(materia);
		for (Double key : auxiliar) {
			puntajes.add(key);
			frecuenciaMatematicas.add(Collections.frequency(materia, key));
		}
		return frecuenciaMatematicas;
	}

	/**
	 * Este metodo calcula la primera ecuacion
	 * 
	 * @param añoInicial
	 * @param añoSiguiente
	 * @return - retorna longitud de los años evaluados
	 */
	public static double primeraEcuacion(int añoInicial, int añoSiguiente) {
		double longitudAños = Math.pow(añoInicial, 2)
				+ Math.pow(añoSiguiente, 2);
		double raiz = Math.sqrt(longitudAños);
		primeraEcuacion = raiz;
		return primeraEcuacion;
	}

	/**
	 * Este metodo calcula la segunda ecuacion
	 * 
	 * @param resultadoConsulta
	 *            - select a la base de datos
	 * @param auxConexion
	 *            - conexion establecida con la base de datos
	 * @param prepararSentencia
	 *            - resultado obtenido del select a la base de datos
	 * @return - retorna el resultado de la segunda ecuacion
	 */
	public static double segundaEcuacion(ResultSet resultadoConsulta,
			Connection auxConexion, PreparedStatement prepararSentencia) {
		try {
			String registros = "SELECT COUNT(*) " + "\"ESTU_CONSECUTIVO"
					+ "\" FROM PRUEBA3";
			prepararSentencia = (PreparedStatement) auxConexion
					.prepareStatement(registros);
			resultadoConsulta = prepararSentencia.executeQuery();
			if (resultadoConsulta.next()) {
				evaluados = resultadoConsulta.getInt(1);
			}
			segundaEcuacion = getPrimeraEcuacion() / evaluados;
		} catch (Exception e) {
			System.err.println("OPERACION INVALIDA O DATOS ERRONEOS");
		}

		return segundaEcuacion;
	}

	/**
	 * Este metodo obtiene el puntaje por departamento
	 * 
	 * @param resultadoConsulta
	 *            - select a la base de datos
	 * @param auxConexion
	 *            - conexion establecida con la base de datos
	 * @param prepararSentencia
	 *            - resultado obtenido del select a la base de datos
	 * @param departamento
	 *            - departamento asignado
	 * @param area
	 *            - area de la que se decea obtener el puntaje
	 * @return -retorna una lista tipo double con el puntaje de la area de ese
	 *         departamento
	 */
	public static ArrayList<Double> puntajeDepartamento(
			ResultSet resultadoConsulta, Connection auxConexion,
			PreparedStatement prepararSentencia, String departamento,
			String area) {
		String depar = "'" + departamento + "'";
		try {
			int contador = 0;
			String registros = "select * from PRUEBA3 where ESTU_RESIDE_DEPTO ="
					+ depar;
			prepararSentencia = (PreparedStatement) auxConexion
					.prepareStatement(registros);
			resultadoConsulta = prepararSentencia.executeQuery();

			while (resultadoConsulta.next()) {
				puntajeDepartamento.add(contador,
						resultadoConsulta.getDouble(area));
				contador++;
			}
		} catch (Exception e) {
			System.out.println("OPERACION INVALIDA O DATOS ERRONEOS");
		}

		return puntajeDepartamento;
	}

	public static int evaluadosPeriodo(ResultSet resultadoConsulta,
			Connection auxConexion, PreparedStatement prepararSentencia) {
		try {
			String registros = "SELECT COUNT(*) FROM PRUEBA3 where PERIODO = '20051'";
			prepararSentencia = (PreparedStatement) auxConexion
					.prepareStatement(registros);
			resultadoConsulta = prepararSentencia.executeQuery();
			if (resultadoConsulta.next()) {
				evaluadosPeriodo = resultadoConsulta.getInt(1);
				System.out.println(evaluadosPeriodo);
			}
		} catch (SQLException e) {
			System.err.println("OPERACION INVALIDA O DATOS ERRONEOSs"
					+ e.getMessage());
			e.getMessage();
		}

		return evaluadosPeriodo;
	}

	public static double terceraEcuacion(ResultSet resultadoConsulta,
			Connection auxConexion, PreparedStatement prepararSentencia) {
		try {
			String registros = "SELECT COUNT(*) "
					+ "\"ESTU_CONSECUTIVO"
					+ "\" FROM PRUEBA3 where ESTU_RESIDE_DEPTO = 'CUNDINAMARCA'";

			prepararSentencia = (PreparedStatement) auxConexion
					.prepareStatement(registros);

			resultadoConsulta = prepararSentencia.executeQuery();
			if (resultadoConsulta.next()) {
				evaluadosDepartamento = resultadoConsulta.getInt(1);
				// System.out.println(evaluadosDepartamento);
				// System.out.println(getEvaluadosPeriodo());
				terceraEcuacion = evaluadosDepartamento / getEvaluados();
			}
		} catch (SQLException e) {
			System.err.println("//OPERACION INVALIDA O DATOS ERRONEOS"
					+ e.getLocalizedMessage());
			e.getStackTrace();

		}

		return terceraEcuacion;
	}

	public static int getEvaluadosPeriodo() {
		return evaluadosPeriodo;
	}

	public static int getEvaluadosDepartamento() {
		return evaluadosDepartamento;
	}

	public static ArrayList<Double> getPuntajeDepartamento() {
		return puntajeDepartamento;
	}

	public static ArrayList<Double> getPrimeraOperacionE4() {
		return PrimeraOperacionE4;
	}

	public static ArrayList<Double> getPuntajes() {
		return puntajes;
	}

	public static ArrayList<Integer> getFrecuencia() {
		return frecuenciaMatematicas;
	}

	public static int getEvaluados() {
		return evaluados;
	}

	public static double getPrimeraEcuacion() {
		return primeraEcuacion;
	}

	public static double getSegundaEcuacion() {
		return segundaEcuacion;
	}

	public static double getTerceraEcuacion() {
		return terceraEcuacion;
	}

	public static double getCuartaEuacion() {
		return cuartaEuacion;
	}

}
