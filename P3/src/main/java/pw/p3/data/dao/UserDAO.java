// Esta clase DAO es "simulada" - no tiene acceso a la base de datos
package pw.p3.data.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Properties;

import com.mysql.jdbc.ResultSet;

import pw.p3.data.common.DBConnection;
import pw.p3.business.user.UserDTO;

public class UserDAO {
	public UserDTO solicitarUsuario(String correo) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaUsuarioEspecifico = prop.getProperty("consultaUsuarioEspecifico");
		UserDTO usuario = new UserDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaUsuarioEspecifico);
			ps.setString(1, correo);
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				LocalDate inscripcion = LocalDate.parse(rs.getString("fechaInscripcion"));
				LocalDate nacimiento = LocalDate.parse(rs.getString("fechaNacimiento"));
				if(nombre.equalsIgnoreCase("") && apellidos.equalsIgnoreCase("")) {
					return null;
				}
				usuario = new UserDTO(nombre, apellidos, nacimiento, inscripcion, correo);
			}

			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return usuario;
	}
}
