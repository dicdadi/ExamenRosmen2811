package aed.examen;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
public class Conexiones {



		private Connection conexion=null;

		public Connection conexionMYSQL() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/direcciondetrafico", "root", "");

			} catch (ClassNotFoundException e) {
			System.out.println("ERROR DE CONEXIÓN A MYSQL");
			} catch (SQLException e) {
				System.out.println("ERROR DE CONEXIÓN A MYSQL");
			}
			return conexion;
		}

		public Connection conexionSQL() {

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conexion = DriverManager.getConnection(
						"jdbc:sqlserver://LAPTOP-OFULADPK\\SQLEXPRESS;DataBaseName=Direcciondetrafico", "sa",
						"sa");

			} catch (ClassNotFoundException e) {
				System.out.println("ERROR DE CONEXIÓN A SQL");
			} catch (SQLException e) {
				System.out.println("ERROR DE CONEXIÓN A SQL");
			}
			return conexion;
		}



	}


