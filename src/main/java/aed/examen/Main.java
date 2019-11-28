/**
 * ROSMEN RAMOS DÍAZ 28/11/2019
 */
package aed.examen;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
	private static int numeroMenu;
	public static void main(String[] args) {
		System.out.println("------------------------------------- ROSMEN RAMOS DÍAZ 2ºDAM A ---------------------------------------------");
		 Funciones funciones= new Funciones();
		 Scanner entrada = new Scanner(System.in);
		 System.out.print("1.-Conectarse a una BD (Mysql o Sql).\n2.-Visualizar tabla\n3.-Modificar registro\n4.-Función almacenada\n5.-Salir");
		 System.out.println();
		 numeroMenu=entrada.nextInt();
		 boolean conectadoABd=false;
		 Connection conexion=null;
		 while(numeroMenu !=0) {
				
				switch (numeroMenu) {
				case 1:
					if(!conectadoABd) {
						System.out.println("Introduce 1 para Mysql o 2 para Sql:");
							
							conexion=funciones.conectar(entrada.nextInt());
							if(conexion!=null) {
								System.out.println("Se ha conectado con la BD.");
								conectadoABd=true;
							}

						}else {
							System.out.println("Ya estas conectado a una BD, si deseas desconectarte introduce 1 para SÍ o 2 para NO.");
							if(entrada.nextInt()==1) {
								try {
									conexion.close();
									conectadoABd=false;
									System.out.println("Desconectando de la bd.... vuelve a seleccionar en el menu la opcion 1 para conectarte.");
								} catch (SQLException e) {
								System.out.println("No se ha podido pasar la conexion.");
								}
							}else {
								System.out.println("Cancelando desconexion");
							}
							
						}
					
					break;
					

					
				
				case 2:
					int importe;
					entrada.nextLine();
					System.out.print("Introduce un importe:");
					importe=entrada.nextInt();
					
					funciones.visualizar(conexion,importe);
					break;
				case 3:
					int idRegistro,importeModificar;
					entrada.nextLine();
					System.out.println("Introduce un id:");
					idRegistro=entrada.nextInt();
					System.out.println("Introduce el nuevo importe:");
					importeModificar=entrada.nextInt();
					funciones.modificar(conexion, idRegistro,importeModificar);
					break;
				case 4:
					entrada.nextLine();
					String matricula;
					System.out.println("Introduce matrícula:");
					matricula=entrada.nextLine();
					funciones.funcion(conexion, matricula);
					
					break;
				case 5:
					numeroMenu=0;
					if(conexion!=null) {
						try {
							conexion.close();
						} catch (SQLException e) {
	
							System.out.println("No se ha podido cerrar la conexión");
						}
					}
					break;
				default:
					break;
				}
				
				if(numeroMenu!=0) {
				 System.out.print("1.-Conectarse a una BD (Mysql o Sql).\n2.-Visualizar tabla\n3.-Modificar registro\n4.-Función almacenada\n5.-Salir");
				 System.out.println();
				 numeroMenu=entrada.nextInt();
				}
		 		}
		 
		 entrada.close();
		}

	}


