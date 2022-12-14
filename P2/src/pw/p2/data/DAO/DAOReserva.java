package pw.p2.data.DAO;

import pw.p2.business.DTORInfantil;
import pw.p2.business.DTORAdulto;
import pw.p2.business.DTORFamiliar;
import pw.p2.data.Dificultad;
import pw.p2.data.common.DBConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.time.LocalDate;

/**
 * 
 * DAO de reserva
 * Encargado de interactuar con la tabla reserva de la base de datos
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DAOReserva {
	
	/**
	 * Solicita las reservas infantiles
	 * @return ArrayList<DTORInfantil> de reservas infantiles
	 **/
	
	public ArrayList<DTORInfantil> solicitarReservasInfantiles() {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaInfantil = prop.getProperty("consultaReservaInfantil");
		ArrayList<DTORInfantil> reservas = new ArrayList<DTORInfantil>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaInfantil;
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
	
	/**
	 * Solicita el identificador de una reserva
	 * @param usuario Correo del usuario de la reserva
	 * @param fecha Fecha de la reserva
	 * @param pista Nombre de la pista de la reserva
	 * @param duracion Duracion de la reserva
	 * @param tipo Tipo de la reserva
	 * @return Integer identificador de la reserva
	 **/
	
	public int solicitarIdReserva(String usuario, LocalDate fecha, String pista, Integer duracion, Dificultad tipo) {	
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaId = prop.getProperty("consultaReservaId");
		Integer idReserva = -1;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaReservaId);
			ps.setString(1, usuario);
			ps.setString(2, fecha.toString());
			ps.setString(3, pista);
			ps.setInt(4, duracion);
			ps.setString(5, tipo.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				idReserva  = rs.getInt("idReserva");
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return idReserva;
	}
	
	/**
	 * Actualiza una reserva infantil
	 * @param reserva Reserva Infantil a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaInfantilUpdate(DTORInfantil reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateReserva = prop.getProperty("updateReserva");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateReserva);
			ps.setInt(9, reserva.getIdReserva());
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setInt(3, reserva.getDesc());
			ps.setString(4, reserva.getFecha().toString());
			ps.setString(5, reserva.getPista());
			ps.setString(6, reserva.getTipo().toString());
			ps.setInt(7, reserva.getParticipantes());
			ps.setInt(8, 0);
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Añade una reserva infantil
	 * @param reserva Reserva Infantil a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaInfantilInsert(DTORInfantil reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReserva = prop.getProperty("insertReserva");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertReserva);
			ps.setString(1, reserva.getUsuario());
			ps.setInt(2, reserva.getDur());
			ps.setFloat(3, reserva.getPrecio());
			ps.setInt(4, reserva.getDesc());
			ps.setString(5, reserva.getFecha().toString());
			ps.setString(6, reserva.getPista());
			ps.setString(7, reserva.getTipo().toString());
			ps.setInt(8, reserva.getParticipantes());
			ps.setInt(9, 0);
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Solicita las reservas adultas
	 * @return ArrayList<DTORAdulto> de reservas adultos
	 **/
	
	public ArrayList<DTORAdulto> solicitarReservasAdultos() {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaAdulto = prop.getProperty("consultaReservaAdulto");
		ArrayList<DTORAdulto> reservas = new ArrayList<DTORAdulto>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaAdulto;
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
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad")); //CAMBIAR A TIPO
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
	
	/**
	 * Actualiza una reserva adulta
	 * @param reserva Reserva Adulto a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaAdultoUpdate(DTORAdulto reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateReserva = prop.getProperty("updateReserva");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateReserva);
			ps.setInt(9, reserva.getIdReserva());
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setInt(3, reserva.getDesc());
			ps.setString(4, reserva.getFecha().toString());
			ps.setString(5, reserva.getPista());
			ps.setString(6, reserva.getTipo().toString());
			ps.setInt(7, reserva.getPartipantes());
			ps.setInt(8, 0);
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Inserta la reserva adulta
	 * @param reserva Reserva Adulto a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaAdultoInsert(DTORAdulto reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReserva = prop.getProperty("insertReserva");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertReserva);
			ps.setString(1, reserva.getUsuario());
			ps.setInt(2, reserva.getDur());
			ps.setFloat(3, reserva.getPrecio());
			ps.setInt(4, reserva.getDesc());
			ps.setString(5, reserva.getFecha().toString());
			ps.setString(6, reserva.getPista());
			ps.setString(7, reserva.getTipo().toString());
			ps.setInt(8, reserva.getPartipantes());
			ps.setInt(9, 0);
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Solicita las reservas familiares
	 * @return ArrayList<DTORFamiliar> de reservas familiares
	 **/
	
	public ArrayList<DTORFamiliar> solicitarReservasFamiliares() {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaFamiliar = prop.getProperty("consultaReservaFamiliar");
		ArrayList<DTORFamiliar> reservas = new ArrayList<DTORFamiliar>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaFamiliar;
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
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad")); //CAMBIAR A TIPO
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
	
	/**
	 * Actualiza una reserva familiar
	 * @param reserva Reserva Familiar a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaFamiliarUpdate(DTORFamiliar reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateReserva = prop.getProperty("updateReserva");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateReserva);
			ps.setInt(9, reserva.getIdReserva());
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setInt(3, reserva.getDesc());
			ps.setString(4, reserva.getFecha().toString());
			ps.setString(5, reserva.getPista());
			ps.setString(6, reserva.getTipo().toString());
			ps.setInt(7, reserva.getadultos());
			ps.setInt(8, reserva.getNinos());
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Inserta una reserva familiar
	 * @param reserva Reserva Familiar a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaFamiliarInsert(DTORFamiliar reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReserva = prop.getProperty("insertReserva");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertReserva);
			ps.setString(1, reserva.getUsuario());
			ps.setInt(2, reserva.getDur());
			ps.setFloat(3, reserva.getPrecio());
			ps.setInt(4, reserva.getDesc());
			ps.setString(5, reserva.getFecha().toString());
			ps.setString(6, reserva.getPista());
			ps.setString(7, reserva.getTipo().toString());
			ps.setInt(8, reserva.getadultos());
			ps.setInt(9, reserva.getNinos());
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Elimina una reserva infantil
	 * @param reserva Reserva Infantil a eliminar
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int deleteReservaInfantil(DTORInfantil reserva){
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String deleteReserva = prop.getProperty("deleteReserva");
		int status=0;
		try{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement(deleteReserva);
		ps.setString(1, reserva.getUsuario());
		ps.setString(2, reserva.getFecha().toString());
		ps.setString(3, reserva.getPista());
		ps.setInt(4, reserva.getDur());
		status = ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Elimina una reserva adulta
	 * @param reserva Reserva Adulto a eliminar
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int deleteReservaAdulto(DTORAdulto reserva){
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String deleteReserva = prop.getProperty("deleteReserva");
		int status=0;
		try{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement(deleteReserva);
		ps.setString(1, reserva.getUsuario());
		ps.setString(2, reserva.getFecha().toString());
		ps.setString(3, reserva.getPista());
		ps.setInt(4, reserva.getDur());
		status = ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Elimina una reserva familiar
	 * @param reserva Reserva Familiar a eliminar
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int deleteReservaFamiliar(DTORFamiliar reserva){
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String deleteReserva = prop.getProperty("deleteReserva");
		int status=0;
		try{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement(deleteReserva);
		ps.setString(1, reserva.getUsuario());
		ps.setString(2, reserva.getFecha().toString());
		ps.setString(3, reserva.getPista());
		ps.setInt(4, reserva.getDur());
		status = ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
}
