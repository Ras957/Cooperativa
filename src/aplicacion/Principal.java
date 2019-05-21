package aplicacion;

import GUI.UI;
import basededatos.Conexion;
import basededatos.Socios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {

	/**
	 * @param args
	 * @throws Exception 
	 */
	//Objeto de tipo Conexion para gestionar la conexion a la base de datos
	public static Conexion miConexion;
        public static UI vista=new UI();
	
	public static void main(String[] args) throws Exception {
				
		
		//Realizamos la conexión
		miConexion=new Conexion("127.0.0.1","COOPERATIVA","root","");
		if (miConexion.conectar()==false)
		{
			System.out.println("No se ha podido conectar a la base de datos");
			
		}
		else
		{
			System.out.println("Conexión satisfactoria a la base de datos");
			if(generarEstructura())
			{
				System.out.println("La estructura de la base de datos se ha generado correctamente");
				if (insertarSociosDefault())
				{
					System.out.println("Socios insertados correctamente");
				}
				else
					System.out.println("No se han podido insertar los socios. Posiblemente ya existen");
				
				//mostramos los socios que hay en la tabla socios
				mostrarSocios();
				
				//actualizamos el nombre del primer Socido
				if (actualizarSocio(0,"MIGUEL")>=1)
				{
					System.out.println("Se ha actualizado el socio correctamente");
					System.out.println("Volvemos a mostrar los socios disponibles");
					mostrarSocios();
					
				}
				else
					System.out.println("Error al actualizar el socio");
				
				//Eliminamos un socio por código
				if (eliminarSocioPorCodigo(0)>=1)
				{
					System.out.println("Se ha eliminado el socio correctamente");
					System.out.println("Volvemos a mostrar los socios disponibles");
					mostrarSocios();
					
				}
				else
					System.out.println("Error al eliminar el socio");
				
				//Eliminar todos los socios
				int nSocios;
				if ((nSocios=eliminarSocios())>0)
				{
					System.out.println("Se han elminado "+nSocios+" socios");
					System.out.println("Mostramos que no hay ningún socio en la tabla");
					mostrarSocios();
				}
				else
					System.out.println("No hay socios para eliminar, la tabal está vacia");
				
			}
			else
			{
				System.out.println("Error al generar la estructura de la base de datos");
				
			}
			vista.setVisible(true);
		}
			
		

	}//fin de main()
	
	public static boolean generarEstructura()
	{
		boolean generada=true;
		//Cadena donde irán las sentencias sql de creación de tablas
		String lineaSQL;
		//Objeto de tipo Statement
		Statement sentencia;
		try
		{
			lineaSQL = "CREATE TABLE IF NOT EXISTS SOCIOS"
            + "  (codigoSocio           INTEGER PRIMARY KEY,"
            + "   nombreSocio            VARCHAR(50),"
            + "   apellidosSocio          VARCHAR(50),"
            + "   direccionSocio           VARCHAR(50),"
            + "   emailSocio           VARCHAR(50),"
            + "   telefonoSocio     VARCHAR(50))";
          

			//conectamos la sentencia a la base de datos
			sentencia = miConexion.getConexion().createStatement();
			//ejecutamos la sentencia;
			sentencia.executeUpdate(lineaSQL);
			
			lineaSQL = "CREATE TABLE IF NOT EXISTS TIPOS_ACEITUNA"
            + "  (codigoAceituna           INTEGER PRIMARY KEY,"
            + "   nombreAceituna            VARCHAR(50))";
         
			sentencia = miConexion.getConexion().createStatement();
			sentencia.executeUpdate(lineaSQL);
			
                        lineaSQL = "CREATE TABLE IF NOT EXISTS CAMPANYA"
            + "  (anyo           char(4) PRIMARY KEY,"
            + "   fechaInicio            date,"
            + "   fechaFinal            date,"
            + "   observaciones            varchar(200))";
			sentencia = miConexion.getConexion().createStatement();
			sentencia.executeUpdate(lineaSQL);
                        
                        lineaSQL = "CREATE TABLE IF NOT EXISTS ACEITE"
            + "  (codigoProduccion          INTEGER PRIMARY KEY,"
            + "   litros            integer,"
            + "   precioLitro            float(6,2),"
            + "   anyo                   char(4),"
            + "  foreign key (anyo) references CAMPANYA(anyo))";
			sentencia = miConexion.getConexion().createStatement();
			sentencia.executeUpdate(lineaSQL);
                        
                        lineaSQL = "CREATE TABLE IF NOT EXISTS RECOLECCION"
            + "  (codigoRecoleccion          INTEGER,"
            + "   anyo                   char(4),"
            + "   codigoSocio           INTEGER,"
            + "   codigoAceituna           INTEGER,"
            + "   kilos                     INTEGER,"
            + "   precioKilo                float(6,2),"
            + "   fecha                      date,"
            + "   primary key (codigoRecoleccion, anyo, codigoSocio, codigoAceituna),"
            + "  foreign key (anyo) references CAMPANYA(anyo),"
            + "  foreign key (codigoSocio) references SOCIOS(codigoSocio),"
            + "  foreign key (codigoAceituna) references TIPOS_ACEITUNA(codigoAceituna))";
            
			sentencia = miConexion.getConexion().createStatement();
			sentencia.executeUpdate(lineaSQL);
                        
            lineaSQL = "CREATE TABLE IF NOT EXISTS TRABAJADORES"
            + "  (codigoTrabajador           INTEGER PRIMARY KEY,"
            + "   nombreTrabajador            VARCHAR(50),"
            + "   apellidosTrabajador          VARCHAR(50),"
            + "   direccionTrabajador           VARCHAR(50),"
            + "   emailTrabajador          VARCHAR(50),"
            + "   telefonoTrabajador    integer,"
            + "   sueldoTrabajador          float(6,2),"
            + "   cargoTrabajador           varchar(20))";
          

			sentencia = miConexion.getConexion().createStatement();
			sentencia.executeUpdate(lineaSQL);
			
			
		}catch(SQLException se)
		{
			generada=false;
			se.printStackTrace();
		}
		
		return generada;
		
	}
	
	//Método que inserta de manera automática 5 registros en la tabla SOCIOS
	public static boolean insertarSociosDefault() throws SQLException
	{
		boolean insertados=true;
		int i;
		Socios nuevoSocio;
		//Cadena donde irán las sentencias sql de creación de tablas
		String lineaSQL;
		//Objeto de tipo Statement
		Statement sentencia;
		
		
		
		//comando sql generico para la inserción
		lineaSQL="INSERT INTO SOCIOS (codigoSocio, nombreSocio, apellidosSocio, "
                + "direccionSocio, emailSocio, telefonoSocio) values (?, ?, ?, ?, ?, ?)";
	try
	{
		
		
		//conectamos el objeto preparedStmt a la base de datos
		 PreparedStatement preparedStmt = miConexion.getConexion().prepareStatement(lineaSQL);
		
		 //creamos un nuevo socio
		 nuevoSocio=new Socios(0,"JUAN","GOMEZ","CORDOBA","CORREO@CORREO","0");
		 
		 for(i=1; i<5; i++)
		 {
			 preparedStmt.setInt(1, nuevoSocio.getCodigoSocio());
			 preparedStmt.setString(2, nuevoSocio.getNombreSocio());
			 preparedStmt.setString(3, nuevoSocio.getApellidosSocio());
			 preparedStmt.setString(4, nuevoSocio.getDireccionSocio());
			 preparedStmt.setString(5, nuevoSocio.getEmailSocio());
			 preparedStmt.setString(6, nuevoSocio.getTelefonoSocio());

			// la ejecutamos
			preparedStmt.execute();
			
			nuevoSocio.setCodigoSocio(i);
			nuevoSocio.setNombreSocio(nuevoSocio.getNombreSocio()+i);
			nuevoSocio.setApellidosSocio(nuevoSocio.getApellidosSocio()+i);
			nuevoSocio.setDireccionSocio(nuevoSocio.getDireccionSocio()+i);
			nuevoSocio.setEmailSocio(nuevoSocio.getEmailSocio()+i);
			nuevoSocio.setTelefonoSocio(nuevoSocio.getTelefonoSocio()+i);
			
			

         }
         // habría que cerrar la conexion
	}catch(SQLException se)
	{
		insertados=false;
		se.printStackTrace();
	}
		
		return insertados;
		
		
		
		
	}
	
	//Método que nos muestra todos los socios que hay insertados actualmente
	public static void mostrarSocios() throws SQLException
	{
		//Número de filas afectadas
		int nFilas=0;
		String lineaSQL="Select * from SOCIOS";
		PreparedStatement preparedStmt = miConexion.getConexion().prepareStatement(lineaSQL);
		ResultSet resultado = preparedStmt.executeQuery();
		System.out.println("LISTADO DE SOCIOS");
		System.out.println("Código   Nombre   Apellidos   Dirección   Teléfono   Email");
		
		
		while(resultado.next())
		{
			nFilas ++;
			System.out.println(resultado.getInt("codigoSocio")+"   "+resultado.getString("nombreSocio")+"   "+resultado.getString("apellidosSocio")+"   "+resultado.getString("direccionSocio")+"   "+resultado.getString("telefonoSocio")+"   "+resultado.getString("emailSocio"));
			
		}
		System.out.println("Se han mostrado "+nFilas+" de la tabla Socios");
		
	}
	
	//Método que actualiza el nombre de un socio dado por su código
	//Devuelve el número de filas actualizadas
	public static int actualizarSocio(int codigo, String nuevoNombre) throws SQLException
	{
				
		//Cadena donde irán las sentencias sql
		String lineaSQL;
		//Objeto de tipo Statement
		PreparedStatement preparedStmt;
		//Número de filas actualizadas
		int nFilas=0;
		
		
		//Cadena update
		lineaSQL="update SOCIOS set nombreSocio = ? where codigoSocio = ?";
		try
		{		
		
			//conectamos el objeto preparedStmt a la base de datos
			preparedStmt = miConexion.getConexion().prepareStatement(lineaSQL);
			
			//agregamos los valores que faltan a la linea SQL
		    preparedStmt.setString   (1, nuevoNombre);
		    preparedStmt.setInt (2, codigo);
		
			// la ejecutamos
			nFilas=preparedStmt.executeUpdate();
		       
			// habría que cerrar la conexion
		}catch(SQLException se)
		{
		
			se.printStackTrace();
		}
		
		return nFilas;
		
	}
	
	//Método qu elimina un socio por su código
	//Devuelve el número de filas afectadas
	public static int eliminarSocioPorCodigo(int codigo) throws SQLException
	{
				
		//Cadena donde irán las sentencias sql
		String lineaSQL;
		//Objeto de tipo Statement
		PreparedStatement preparedStmt;
		//Número de filas actualizadas
		int nFilas=0;
		
		
		//Cadena update
		lineaSQL="delete from SOCIOS where codigoSocio = ?";
		try
		{		
		
			//conectamos el objeto preparedStmt a la base de datos
			preparedStmt = miConexion.getConexion().prepareStatement(lineaSQL);
			
			//agregamos los valores que faltan a la linea SQL
		    preparedStmt.setInt (1, codigo);
		
			// la ejecutamos
			nFilas=preparedStmt.executeUpdate();
		       
			// habría que cerrar la conexion
		}catch(SQLException se)
		{
		
			se.printStackTrace();
		}
		
		return nFilas;
		
	}
	
	//Método qu elimina todos los socios
	//Devuelve el número de filas afectadas
	public static int eliminarSocios() throws SQLException
	{
				
		//Cadena donde irán las sentencias sql
		String lineaSQL;
		//Objeto de tipo Statement
		PreparedStatement preparedStmt;
		//Número de filas actualizadas
		int nFilas=0;
		
		
		//Cadena update
		lineaSQL="delete from SOCIOS";
		try
		{		
		
			//conectamos el objeto preparedStmt a la base de datos
			preparedStmt = miConexion.getConexion().prepareStatement(lineaSQL);
			
					
			// la ejecutamos
			nFilas=preparedStmt.executeUpdate();
		       
			// habría que cerrar la conexion
		}catch(SQLException se)
		{
		
			se.printStackTrace();
		}
		
		return nFilas;
		
	}
	
	

}//fin de Principal









