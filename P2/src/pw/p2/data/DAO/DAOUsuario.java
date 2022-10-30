package pw.p2.data.DAO;

import pw.p2.business.DTOUsuario;
import pw.p2.data.Usuario;
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
				LocalDate inscripcion = LocalDate.parse(rs.getString("fechaInscripcion"));
				LocalDate nacimiento = LocalDate.parse(rs.getString("fechaNacimiento"));
				usuarios.add(new DTOUsuario(nombre, apellidos, nacimiento, inscripcion, correo));
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
	
	public int escribirUsuariosUpdate(Usuario usuario) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update usuario set nombre=?,apellidos=?,fechaInscripcion=?,fechaNacimiento=? where correoUsuario=?");
			ps.setString(5, usuario.getCorreo());
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellidos());
			ps.setString(3, usuario.getInscripcion().toString());
			ps.setString(4, usuario.getNacimiento().toString());			
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	public int escribirUsuariosInsert(Usuario usuario) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into usuario (correoUsuario,nombre,apellidos,fechaInscripcion,fechaNacimiento) values(?,?,?,?,?)");
			ps.setString(1, usuario.getCorreo());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellidos());
			ps.setString(4, usuario.getInscripcion().toString());
			ps.setString(5, usuario.getNacimiento().toString());			
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
}
