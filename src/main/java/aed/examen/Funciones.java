package aed.examen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Funciones {

	public Connection conectar(int n) {
		Conexiones con = new Conexiones();
		Connection conexion = null;
		if (n == 1) {
			System.out.println("Conectando con mysql...");
			conexion = con.conexionMYSQL();
		} else if (n == 2) {
			System.out.println("Conectando con sql...");
			conexion = con.conexionSQL();
		} else {
			System.out.println("Introduce un 1 o 2");
		}
		return conexion;
	}

	public void visualizar(Connection conexion, int importe) {
		System.out.println("Visualizando tabla:  ");

		try {

			PreparedStatement sentenciaPreparada = conexion
					.prepareStatement("SELECT * FROM sanciones WHERE importe > ?");
			sentenciaPreparada.setInt(1, importe);
			ResultSet rs = sentenciaPreparada.executeQuery();
			while (rs.next()) {
				System.out.println("||  " + rs.getInt(1) + "  ||  " + rs.getString(2) + "  ||  " + rs.getString(3) + "  ||  "
						+ rs.getInt(4) + "  ||  " + rs.getBoolean(5) + "  ||  " + rs.getString(6));
				System.out.println("");
			}

		} catch (SQLException e) {

			System.out.println("ERROR AL VISUALIZAR");
		}
	}

	public void modificar(Connection conexion, int idRegistro, int importe) {

		try {
			boolean existe = false;
			int importeActual = 0;
			PreparedStatement comprueba = conexion.prepareStatement("SELECT * FROM sanciones WHERE id = ?");
			comprueba.setInt(1, idRegistro);
			ResultSet rs = comprueba.executeQuery();
			// Compruebo si existe el id dado, si el result set devuelve false, entonces es
			// que no hay registro si devuelve true es que si
			if (rs.next()) {
				importeActual = rs.getInt(4);//Cojo el valor actual
				existe = true;
			} else {
				System.out.println("Sanción inexistente");
			}

			if (existe) {
				if(importe == importeActual) {
					System.out.println("Importe no actualizado");
				}else {
					PreparedStatement modificar = conexion.prepareStatement("UPDATE sanciones SET importe=? WHERE id=?");
					modificar.setInt(1, importe);
					modificar.setInt(2, idRegistro);
					modificar.execute();
					
					if (importe > importeActual) {
						System.out.println("Aumento de importe.");
					} else if (importe < importeActual) {
						System.out.println("Disminución de importe.");
					}
					
				}
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido realizar la modificación.");
		}
	}
	public void funcion(Connection conexion,String matricula) {
		try {
			CallableStatement procedure = conexion.prepareCall("{?=CALL f_sancionesporvehiculo(?)}");
			procedure.registerOutParameter(1, Types.INTEGER);
			procedure.setString(2, matricula);
			procedure.execute();
			System.out.println(procedure.getInt(1));
		} catch (SQLException e) {

			System.out.println("Error al ejecutar la función.");
		}
		
	}

}
