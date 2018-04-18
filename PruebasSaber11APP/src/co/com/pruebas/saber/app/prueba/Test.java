package co.com.pruebas.saber.app.prueba;

import co.com.pruebas.saber.app.ecuaciones.Ecuaciones;

import java.sql.SQLException;

public class Test {
	public static void main(String[] args) throws SQLException {

		Ecuaciones ecuaciones = new Ecuaciones();

		System.out
				.println("...............................................................");

		System.out.println("primera ecuacion  "
				+ Ecuaciones.getPrimeraEcuacion());
		System.out.println("segunda ecuacion   "
				+ Ecuaciones.getSegundaEcuacion());

		System.out.println("tercera ecuacion   "
				+ Ecuaciones.getTerceraEcuacion());

		System.out
				.println("cuarta ecuacion   " + Ecuaciones.getCuartaEuacion());

		System.out.println("evaluados " + Ecuaciones.getEvaluados());

		System.out
				.println("..................................................................");
		System.out.print("puntaje PuntajeDepartamento"
				+ Ecuaciones.getPuntajeDepartamento());

		System.out.println();
		System.out.println("Frecuencia: " + Ecuaciones.getFrecuencia());

	}
}