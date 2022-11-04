package pw.p2.data.DAO;

import pw.p2.business.DTOBono;
import pw.p2.business.DTORAdulto;
import pw.p2.business.DTORFamiliar;
import pw.p2.business.DTORInfantil;
import pw.p2.data.Dificultad;
import pw.p2.data.common.DBConnection;
import java.io.*;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.time.LocalDate;

/**
 * 
 * DAO de bonos
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DAOBono {
	
	public ArrayList<DTOBono> solicitarBonos() {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaBono = prop.getProperty("consultaBono");
		ArrayList<DTOBono> bonos = new ArrayList<DTOBono>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaBono;
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
	
			while (rs.next()) {
				int sesion = rs.getInt("sesion");
				LocalDate fcaducidad = LocalDate.parse(rs.getString("fechaCaducidad"));
				String bUsuario = rs.getString("correoUsuario");
				Dificultad tipo = Dificultad.valueOf(rs.getString("tipo"));
				bonos.add(new DTOBono(sesion,bUsuario,tipo,fcaducidad));
			}
	
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return bonos;
	}

	public DTOBono solicitarBono(String usuario, Dificultad tipo) {		
		DTOBono bono = new DTOBono();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from bono where correoUsuario = '" + usuario + "' and tipo = '" + tipo.toString() + "'";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
	
			while (rs.next()) {
				Integer idBono  = rs.getInt("idBono");
				Integer sesion = rs.getInt("sesion");
				LocalDate fechaCaducidad = LocalDate.parse(rs.getString("fechaCaducidad"));
				bono = new DTOBono(sesion, usuario, tipo, fechaCaducidad);
				bono.setId(idBono);
			}
	
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return bono;
	}

	public int escribirBonoUpdate(DTOBono bono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update bono set sesion=?,fechaCaducidad=?,correoUsuario=?,tipo=? where idBono=?");
			ps.setInt(5, bono.getId());
			ps.setInt(1, bono.getSesion());
			ps.setString(2, bono.getFcaducidad().toString());
			ps.setString(3, bono.getbUsuario());
			ps.setString(4, bono.getTipo().toString());
			status = ps.executeUpdate();
	
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}

	public int escribirBonoInsert(DTOBono bono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into bono (sesion,fechaCaducidad,correoUsuario,tipo) values(?,?,?,?)");
			ps.setInt(1, bono.getSesion());
			ps.setString(2, bono.getFcaducidad().toString());
			ps.setString(3, bono.getbUsuario());
			ps.setString(4, bono.getTipo().toString());
			status = ps.executeUpdate();
	
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	public int deleteBono(DTOBono bono){
		int status=0;
		try{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("delete from bono where idBono=?");
		ps.setInt(1, bono.getId());
		status = ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<DTORInfantil> solicitarReservasInfantiles(DTOBono bono) {
		ArrayList<DTORInfantil> reservas = new ArrayList<DTORInfantil>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from reserva where dificultad = 'INFANTIL' and idBono = " + bono.getId();
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {
				String correo = rs.getString("correoUsuario");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reservas.add(new DTORInfantil(correo, fecha, duracion, pista, precio, descuento, ninos, tipo));
			}
			
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
	
	public ArrayList<DTORAdulto> solicitarReservasAdultos(DTOBono bono) {
		ArrayList<DTORAdulto> reservas = new ArrayList<DTORAdulto>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from reserva where dificultad = 'ADULTO' and idBono = " + bono.getId();
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String correo = rs.getString("correoUsuario");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reservas.add(new DTORAdulto(correo, fecha, duracion, pista, precio, descuento, adultos, tipo));
			}
			
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
	
	public ArrayList<DTORFamiliar> solicitarReservasFamiliares(DTOBono bono) {
		ArrayList<DTORFamiliar> reservas = new ArrayList<DTORFamiliar>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from reserva where dificultad = 'FAMILIAR' and idBono = " + bono.getId();
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String correo = rs.getString("correoUsuario");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reservas.add(new DTORFamiliar(correo, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo));
			}
			
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
	
	public int escribirReservaInfantilUpdate(DTORInfantil reserva, Integer idBono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update reserva set duracion=?,precio=?,descuento=?,fecha=?,nombrePista=?,dificultad=?,ninos=?,adultos=?,idBono=? where correoUsuario=?");
			ps.setString(10, reserva.getUsuario());
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setInt(3, reserva.getDesc());
			ps.setString(4, reserva.getFecha().toString());
			ps.setString(5, reserva.getPista());
			ps.setString(6, reserva.getTipo().toString());
			ps.setInt(7, reserva.getParticipantes());
			ps.setInt(8, 0);
			ps.setInt(9, idBono);
			status = ps.executeUpdate();
	
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}

	public int escribirReservaInfantilInsert(DTORInfantil reserva, Integer idBono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into reserva (correoUsuario,duracion,precio,descuento,fecha,nombrePista,dificultad,ninos,adultos,idBono) values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, reserva.getUsuario());
			ps.setInt(2, reserva.getDur());
			ps.setFloat(3, reserva.getPrecio());
			ps.setInt(4, reserva.getDesc());
			ps.setString(5, reserva.getFecha().toString());
			ps.setString(6, reserva.getPista());
			ps.setString(7, reserva.getTipo().toString());
			ps.setInt(8, reserva.getParticipantes());
			ps.setInt(9, 0);
			ps.setInt(10, idBono);
			status = ps.executeUpdate();
	
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}

	public int escribirReservaAdultoUpdate(DTORAdulto reserva, Integer idBono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update reserva set duracion=?,precio=?,descuento=?,fecha=?,nombrePista=?,dificultad=?,adultos=?,ninos=?,idBono=? where correoUsuario=?");
			ps.setString(10, reserva.getUsuario());
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setInt(3, reserva.getDesc());
			ps.setString(4, reserva.getFecha().toString());
			ps.setString(5, reserva.getPista());
			ps.setString(6, reserva.getTipo().toString());
			ps.setInt(7, reserva.getPartipantes());
			ps.setInt(8, 0);
			ps.setInt(9, idBono);
			status = ps.executeUpdate();
	
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}

	public int escribirReservaAdultoInsert(DTORAdulto reserva, Integer idBono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into reserva (correoUsuario,duracion,precio,descuento,fecha,nombrePista,dificultad,adultos,ninos,idBono) values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, reserva.getUsuario());
			ps.setInt(2, reserva.getDur());
			ps.setFloat(3, reserva.getPrecio());
			ps.setInt(4, reserva.getDesc());
			ps.setString(5, reserva.getFecha().toString());
			ps.setString(6, reserva.getPista());
			ps.setString(7, reserva.getTipo().toString());
			ps.setInt(8, reserva.getPartipantes());
			ps.setInt(9, 0);
			ps.setInt(10, idBono);
			status = ps.executeUpdate();
	
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}

	public int escribirReservaFamiliarUpdate(DTORFamiliar reserva, Integer idBono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update reserva set duracion=?,precio=?,descuento=?,fecha=?,nombrePista=?,dificultad=?,adultos=?,ninos=?,idBono=? where correoUsuario=?");
			ps.setString(10, reserva.getUsuario());
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setInt(3, reserva.getDesc());
			ps.setString(4, reserva.getFecha().toString());
			ps.setString(5, reserva.getPista());
			ps.setString(6, reserva.getTipo().toString());
			ps.setInt(7, reserva.getadultos());
			ps.setInt(8, reserva.getNinos());
			ps.setInt(9, idBono);
			status = ps.executeUpdate();
	
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}

	public int escribirReservaFamiliarInsert(DTORFamiliar reserva, Integer idBono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into reserva (correoUsuario,duracion,precio,descuento,fecha,nombrePista,dificultad,adultos,ninos,idBono) values(?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, reserva.getUsuario());
				ps.setInt(2, reserva.getDur());
				ps.setFloat(3, reserva.getPrecio());
				ps.setInt(4, reserva.getDesc());
				ps.setString(5, reserva.getFecha().toString());
				ps.setString(6, reserva.getPista());
				ps.setString(7, reserva.getTipo().toString());
				ps.setInt(8, reserva.getadultos());
				ps.setInt(9, reserva.getNinos());
				ps.setInt(10, idBono);
				status = ps.executeUpdate();
	
				dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
}
