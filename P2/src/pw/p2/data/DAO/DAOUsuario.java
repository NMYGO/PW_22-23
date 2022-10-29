package pw.p2.data.DAO;

import pw.p2.business.DTOUsuario;
import pw.p2.data.common.DBConnection;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import java.time.LocalDate;


/**
 * 
 * DAO Kart
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class DAOUsuario {
	
	public ArrayList<DTOUsuario> solicitarUsuarios() {
		ArrayList<DTOUsuario> usuarios = new ArrayList<DTOUsuario>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from usuario";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String correo = rs.getString("correoUsuario");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				LocalDate nacimiento = LocalDate.parse(rs.getString("fechaNacimiento"));
				usuarios.add(new DTOUsuario(nombre, apellidos, nacimiento, correo));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public ArrayList<DTOUsuario> solicitarUsuariosPorCorreo(String correo) {
		ArrayList<DTOUsuario> listOfUsuarios = new ArrayList<DTOUsuario>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select last, first from Usuario where correo = " + correo;
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apelllidos");
				LocalDate nacimiento = LocalDate.parse(rs.getString("fechaNacimiento"));
				listOfUsuarios.add(new DTOUsuario(nombre, apellidos, nacimiento, correo));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listOfUsuarios;
	}
	
}
