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
 * Encargado de interactuar con la tabla bono de la base de datos
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DAOBono {
	
	/**
	 * Solicita todos los bonos y los devuelve en un array
	 * @return ArrayList<DTOBono> de los bonos
	 **/
	
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
	
	/**
	 * Solicita bono en función del usuario y tipo seleccionado
	 * 
	 * @param usuario Correo del usuario del bono
	 * @param tipo Tipo de bono
	 * @return DTOBono
	 **/
	
	public DTOBono solicitarBono(String usuario, Dificultad tipo) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaBonoEspecifico = prop.getProperty("consultaBonoEspecifico");
		DTOBono bono = new DTOBono();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaBonoEspecifico);
			ps.setString(1, usuario);
			ps.setString(2, tipo.toString());

			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				Integer idBono  = rs.getInt("idBono");
				Integer sesion = rs.getInt("sesion");
				LocalDate fechaCaducidad = LocalDate.parse(rs.getString("fechaCaducidad"));
				bono = new DTOBono(sesion, usuario, tipo, fechaCaducidad);
				bono.setId(idBono);
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return bono;
	}
	
	/**
	 * Actualiza en la base de datos un bono específico
	 * @param bono Bono a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirBonoUpdate(DTOBono bono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateBono = prop.getProperty("updateBono");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateBono);
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
	
	/**
	 * Inserta en la base de datos un bono
	 * @param bono Bono a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirBonoInsert(DTOBono bono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertBono = prop.getProperty("insertBono");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertBono);
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
	
	/**
	 * Elimina un bono de la base de datos
	 * @param bono Bono a borrar
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int deleteBono(DTOBono bono){
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String deleteBono = prop.getProperty("deleteBono");
		int status=0;
		try{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement(deleteBono);
		ps.setInt(1, bono.getId());
		status = ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Solicita a la BD las reservas de un bono de tipo infantil
	 * @param bono Bono del que se solicitan las reservas
	 * @return ArrayList<DTORInfantil> de reservas que se correspondan con el bono
	 **/
	
	public ArrayList<DTORInfantil> solicitarReservasInfantiles(DTOBono bono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaInfantilBono = prop.getProperty("consultaReservaInfantilBono");
		ArrayList<DTORInfantil> reservas = new ArrayList<DTORInfantil>();
		DAOBono bonoTabla = new DAOBono();
		String correoUsuarioB = bono.getbUsuario();
		Dificultad tipoB = bono.getTipo();
		bono.setId(bonoTabla.solicitarBono(correoUsuarioB, tipoB).getId());
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaInfantilBono + bono.getId();
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
	 * Solicita a la BD las reservas de un bono de tipo adulto
	 * @param bono Bono del que se solicitan las reservas
	 * @return ArrayList<DTORAdulto> de reservas que se correspondan con el bono
	 **/
	
	public ArrayList<DTORAdulto> solicitarReservasAdultos(DTOBono bono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaAdultoBono = prop.getProperty("consultaReservaAdultoBono");
		ArrayList<DTORAdulto> reservas = new ArrayList<DTORAdulto>();
		DAOBono bonoTabla = new DAOBono();
		String correoUsuarioB = bono.getbUsuario();
		Dificultad tipoB = bono.getTipo();
		bono.setId(bonoTabla.solicitarBono(correoUsuarioB, tipoB).getId());
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaAdultoBono + bono.getId();
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
	
	/**
	 * Solicita a la BD las reservas de un bono de tipo familiar
	 * @param bono Bono del que se solicitan las reservas
	 * @return ArrayList<DTORFamiliar> de reservas que se correspondan con el bono
	 **/
	
	public ArrayList<DTORFamiliar> solicitarReservasFamiliares(DTOBono bono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaFamiliarBono = prop.getProperty("consultaReservaFamiliarBono");
		ArrayList<DTORFamiliar> reservas = new ArrayList<DTORFamiliar>();
		DAOBono bonoTabla = new DAOBono();
		String correoUsuarioB = bono.getbUsuario();
		Dificultad tipoB = bono.getTipo();
		bono.setId(bonoTabla.solicitarBono(correoUsuarioB, tipoB).getId());
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaFamiliarBono + bono.getId();
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
	
	/**
	 * Actualiza la reserva seleccionada
	 * @param reserva Reserva de tipo infantil a escribir
	 * @param idBono Identificador del bono al que pertenece la reserva
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaInfantilUpdate(DTORInfantil reserva, Integer idBono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateReservaBono = prop.getProperty("updateReservaBono");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateReservaBono);
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
	
	/**
	 * Inserta una nueva reserva en la base de datos
	 * @param reserva Reserva de tipo infantil a escribir
	 * @param idBono Identificador del bono al que pertenece la reserva
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaInfantilInsert(DTORInfantil reserva, Integer idBono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReservaBono = prop.getProperty("insertReservaBono");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertReservaBono);
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
	
	/**
	 * Actualiza una reserva adulta
	 * @param reserva Reserva de tipo Adulto a escribir
	 * @param idBono Identificador del bono al que pertenece la reserva
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaAdultoUpdate(DTORAdulto reserva, Integer idBono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateReservaBono = prop.getProperty("updateReservaBono");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateReservaBono);
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
	
	/**
	 * Inserta una nueva reserva adulta en la base de datos
	 * @param reserva Reserva de tipo Adulto a escribir
	 * @param idBono Identificador del bono al que pertenece la reserva
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaAdultoInsert(DTORAdulto reserva, Integer idBono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReservaBono = prop.getProperty("insertReservaBono");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertReservaBono);
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
	
	/**
	 * Actualiza una reserva familiar
	 * @param reserva Reserva de tipo Familiar a escribir
	 * @param idBono Identificador del bono al que pertenece la reserva
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaFamiliarUpdate(DTORFamiliar reserva, Integer idBono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateReservaBono = prop.getProperty("updateReservaBono");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateReservaBono);
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
	
	/**
	 * Inserta una nueva reserva familiar en la base de datos
	 * @param reserva Reserva de tipo Familiar a escribir
	 * @param idBono Identificador del bono al que pertenece la reserva
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaFamiliarInsert(DTORFamiliar reserva, Integer idBono) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReservaBono = prop.getProperty("insertReservaBono");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertReservaBono);
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
