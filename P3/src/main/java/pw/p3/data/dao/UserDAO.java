package pw.p3.data.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.ResultSet;

import pw.p3.data.common.DBConnection;
import pw.p3.business.user.UserDTO;

/**
 * 
 * DAO Usuario
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class UserDAO {
	
	/**
	 * Solicita todos los usuarios
	 * @return ArrayList<UserDTO> de los usuarios
	 **/
	
	public ArrayList<UserDTO> solicitarUsuarios() {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaUsuario = prop.getProperty("consultaUsuario");
		ArrayList<UserDTO> usuarios = new ArrayList<UserDTO>();
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
				String pass = rs.getString("contrasena");
				Boolean administrador = rs.getBoolean("administrador");
				usuarios.add(new UserDTO(nombre, apellidos, nacimiento, inscripcion, correo, pass, administrador));
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
	
	/**
	 * Solicita un usuario especifico por correo
	 * @param correo Correo del usuario
	 * @return UserDTO del usuario
	 **/
	
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
		UserDTO usuario = null;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from usuario where correoUsuario=?"); //¿METER PROPERTIES?
			ps.setString(1, correo);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				LocalDate inscripcion = LocalDate.parse(rs.getString("fechaInscripcion"));
				LocalDate nacimiento = LocalDate.parse(rs.getString("fechaNacimiento"));
				String pass = rs.getString("contrasena");
				Boolean administrador = rs.getBoolean("administrador");
				usuario = new UserDTO(nombre, apellidos, nacimiento, inscripcion, correo, pass, administrador);
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
	
	/**
	 * Inserta en la base de datos un usuario
	 * @param usuario Usuario
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirUsuarioInsert(UserDTO usuario) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertUsuario = prop.getProperty("insertUsuario");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into usuario (correoUsuario,nombre,apellidos,fechaInscripcion,fechaNacimiento,contrasena,administrador) values(?,?,?,?,?,?,0)");
			ps.setString(1, usuario.getCorreo());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellidos());
			ps.setString(4, usuario.getInscripcion().toString());
			ps.setString(5, usuario.getNacimiento().toString());
			ps.setString(6, usuario.getPassword());
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Actualiza en la base de datos un usuario
	 * @param usuario Usuario
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirUsuarioUpdate(UserDTO usuario) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateUsuario = prop.getProperty("updateUsuario");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update usuario set nombre=?,apellidos=?,fechaInscripcion=?,fechaNacimiento=?, contrasena=? where correoUsuario=?");
			ps.setString(6, usuario.getCorreo());
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellidos());
			ps.setString(3, usuario.getInscripcion().toString());
			ps.setString(4, usuario.getNacimiento().toString());
			ps.setString(5, usuario.getPassword());
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
}
