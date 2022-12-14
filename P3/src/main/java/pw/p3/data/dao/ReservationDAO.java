package pw.p3.data.dao;

import pw.p3.business.reservation.*;
import pw.p3.data.Dificultad;
import pw.p3.data.common.DBConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import com.mysql.jdbc.ResultSet;

/**
 * 
 * DAO Reserva
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/


public class ReservationDAO {
	
	/**
	 * Solicita las reservas infantiles
	 * @return ArrayList<RInfantileDTO> de las reservas
	 **/
	
	public ArrayList<RInfantileDTO> solicitarReservasInfantiles() {
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
		ArrayList<RInfantileDTO> reservas = new ArrayList<RInfantileDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaInfantil;
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery("select * from reserva where dificultad = 'INFANTIL'");

			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				String correo = rs.getString("correoUsuario");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reservas.add(new RInfantileDTO(idReserva, correo, fecha, duracion, pista, precio, descuento, ninos, tipo));
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
	 * Devuelve una reserva buscada por id
	 * @param id
	 * @return
	 */
	
	public RInfantileDTO solicitarReservaInfantil(Integer id) {
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
		RInfantileDTO reserva= new RInfantileDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where idReserva=?");
			ps.setInt(1, id);
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {			
				reserva.setDesc(rs.getInt("descuento")); 
				reserva.setDur(rs.getInt("duracion"));
				reserva.setFecha(LocalDate.parse(rs.getString("fecha")));
				reserva.setIdReserva(id);
				reserva.setParticipantes(rs.getInt("ninos"));
				reserva.setPista(rs.getString("nombrePista"));
				reserva.setPrecio(rs.getInt("precio"));
				reserva.setTipo(Dificultad.INFANTIL);
				reserva.setUsuario(rs.getString("correoUsuario"));
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
	
	/**
	 * Solicita las reservas de adultos
	 * @return ArrayList<RAdultDTO> de las reservas
	 **/
	
	public ArrayList<RAdultDTO> solicitarReservasAdultos() {
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
		ArrayList<RAdultDTO> reservas = new ArrayList<RAdultDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaAdulto;
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery("select * from reserva where dificultad = 'ADULTO'");

			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				String correo = rs.getString("correoUsuario");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad")); //CAMBIAR A TIPO
				reservas.add(new RAdultDTO(idReserva, correo, fecha, duracion, pista, precio, descuento, adultos, tipo));
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
	 * Devuelve una reserva buscada por id
	 * @param id
	 * @return
	 */
	
	public RAdultDTO solicitarReservaAdulto(Integer id) {
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
		RAdultDTO reserva= new RAdultDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where idReserva=?");
			ps.setInt(1, id);
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {			
				reserva.setDesc(rs.getInt("descuento")); 
				reserva.setDur(rs.getInt("duracion"));
				reserva.setFecha(LocalDate.parse(rs.getString("fecha")));
				reserva.setIdReserva(id);
				reserva.setParticipantes(rs.getInt("adultos"));
				reserva.setPista(rs.getString("nombrePista"));
				reserva.setPrecio(rs.getInt("precio"));
				reserva.setTipo(Dificultad.ADULTO);
				reserva.setUsuario(rs.getString("correoUsuario"));
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
	
	/**
	 * Solicita las reservas familiares
	 * @return ArrayList<RFamiliarDTO> de los reservas
	 **/
	
	public ArrayList<RFamiliarDTO> solicitarReservasFamiliares() {
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
		ArrayList<RFamiliarDTO> reservas = new ArrayList<RFamiliarDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaReservaFamiliar;
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery("select * from reserva where dificultad = 'FAMILIAR'");

			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				String correo = rs.getString("correoUsuario");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad")); //CAMBIAR A TIPO
				reservas.add(new RFamiliarDTO(idReserva, correo, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo));
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
	 * Devuelve una reserva buscada por id
	 * @param id
	 * @return
	 */
	
	public RFamiliarDTO solicitarReservaFamiliar(Integer id) {
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
		RFamiliarDTO reserva= new RFamiliarDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where idReserva=?");
			ps.setInt(1, id);
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {			
				reserva.setDesc(rs.getInt("descuento")); 
				reserva.setDur(rs.getInt("duracion"));
				reserva.setFecha(LocalDate.parse(rs.getString("fecha")));
				reserva.setIdReserva(id);
				reserva.setninos(rs.getInt("ninos"));
				reserva.setadultos(rs.getInt("adultos"));
				reserva.setPista(rs.getString("nombrePista"));
				reserva.setPrecio(rs.getInt("precio"));
				reserva.setTipo(Dificultad.INFANTIL);
				reserva.setUsuario(rs.getString("correoUsuario"));
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
	
	/**
	 * Solicita la proxima reserva infantil
	 * @param correoUsuario String Correo del usuario que solicita
	 * @param dificultad Dificultad Dificultad de la reserva que se solicita
	 * @return RInfantileDTO de la proxima reserva infantil
	 **/
	
	public RInfantileDTO solicitarProximaReservaInfantil(String correoUsuario, Dificultad dificultad) {
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
		RInfantileDTO reserva = new RInfantileDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where fecha = (select MIN(fecha) from reserva where correoUsuario=? and dificultad=? and fecha >= now())");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reserva = new RInfantileDTO(idReserva, correoUsuario, fecha, duracion, pista, precio, descuento, ninos, tipo);
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
	
	/**
	 * Solicita la proxima reserva adulta
	 * @param correoUsuario String Correo del usuario que solicita
	 * @param dificultad Dificultad Dificultad de la reserva que se solicita
	 * @return RAdultDTO de la proxima reserva adulta
	 **/
	
	public RAdultDTO solicitarProximaReservaAdulto(String correoUsuario, Dificultad dificultad) {
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
		RAdultDTO reserva = new RAdultDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where fecha = (select MIN(fecha) from reserva where correoUsuario=? and dificultad=? and fecha >= now())");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reserva = new RAdultDTO(idReserva, correoUsuario, fecha, duracion, pista, precio, descuento, adultos, tipo);
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
	
	/**
	 * Solicita la proxima reserva familiar 
	 * @param correoUsuario String Correo del usuario que solicita
	 * @param dificultad Dificultad Dificultad de la reserva que se solicita
	 * @return RFamiliarDTO de la proxima reserva
	 **/
	
	public RFamiliarDTO solicitarProximaReservaFamiliar(String correoUsuario, Dificultad dificultad) {
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
		RFamiliarDTO reserva = new RFamiliarDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where fecha = (select MIN(fecha) from reserva where correoUsuario=? and dificultad=? and fecha >= now())");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reserva = new RFamiliarDTO(idReserva, correoUsuario, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo);
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
	
	/**
	 * Solicita las reservas infantiles completadas  
	 * @param correoUsuario String Correo del usuario que solicita
	 * @param dificultad Dificultad Dificultad de la reserva que se solicita
	 * @return ArrayList<RInfantileDTO> de las reservas completadas 
	 **/
	
	public ArrayList<RInfantileDTO> solicitarReservasInfantilCompletada(String correoUsuario, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservasInfantil = prop.getProperty("consultaReservasInfantil");
		ArrayList<RInfantileDTO> reservas = new ArrayList<RInfantileDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where correoUsuario=? and dificultad=? and fecha < now()");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reservas.add(new RInfantileDTO(idReserva, correoUsuario, fecha, duracion, pista, precio, descuento, ninos, tipo));
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
	
	/**
	 * Solicita las reservas adultas completadas  
	 * @param correoUsuario String Correo del usuario que solicita
	 * @param dificultad Dificultad Dificultad de la reserva que se solicita
	 * @return ArrayList<RAdultDTO> de las reservas completadas 
	 **/
	
	public ArrayList<RAdultDTO> solicitarReservasAdultoCompletada(String correoUsuario, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservasAdulto = prop.getProperty("consultaReservasAdulto");
		ArrayList<RAdultDTO> reservas = new ArrayList<RAdultDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where correoUsuario=? and dificultad=? and fecha < now()");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reservas.add(new RAdultDTO(idReserva, correoUsuario, fecha, duracion, pista, precio, descuento, adultos, tipo));
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
	
	/**
	 * Solicita las reservas familiares completadas  
	 * @param correoUsuario String Correo del usuario que solicita
	 * @param dificultad Dificultad Dificultad de la reserva que se solicita
	 * @return ArrayList<RFamiliarDTO> de las reservas completadas 
	 **/
	
	public ArrayList<RFamiliarDTO> solicitarReservasFamiliarCompletada(String correoUsuario, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservasFamiliar = prop.getProperty("consultaReservasFamiliar");
		ArrayList<RFamiliarDTO> reservas = new ArrayList<RFamiliarDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where correoUsuario=? and dificultad=? and fecha < now()");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				Integer idReserva = rs.getInt("idReserva");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reservas.add(new RFamiliarDTO(idReserva, correoUsuario, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo));
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
	
	/**
	 * Borra una reserva pendiente  
	 * @param idReserva Identificador de la reserva a eliminar
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public Integer deleteReservaPendiente(Integer idReserva){
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
		PreparedStatement ps = connection.prepareStatement("delete from reserva where idReserva=? and fecha > now()");
		ps.setInt(1, idReserva);
		status = ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Añade una reserva infantil
	 * @param reserva Reserva Infantil
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaInfantilInsert(RInfantileDTO reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReserva = "insert into reserva (correoUsuario,duracion,precio,descuento,fecha,nombrePista,dificultad,ninos,adultos) values(?,?,?,?,?,?,?,?,?)";
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
	 * Inserta una reserva adulta
	 * @param reserva Reserva Adulto
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaAdultoInsert(RAdultDTO reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReserva = "insert into reserva (correoUsuario,duracion,precio,descuento,fecha,nombrePista,dificultad,ninos,adultos) values(?,?,?,?,?,?,?,?,?)";
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
	 * Inserta una reserva familiar
	 * @param reserva Reserva Familiar
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirReservaFamiliarInsert(RFamiliarDTO reserva) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertReserva = "insert into reserva (correoUsuario,duracion,precio,descuento,fecha,nombrePista,dificultad,ninos,adultos) values(?,?,?,?,?,?,?,?,?)";
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
	 * Cancela una reserva con 24 horas de antelacion
	 * @param idReserva Identificador de la reserva a eliminar
	 * @return Integer que informa sobre el status de la devolucion
	 **/

	public Integer cancelReservaCliente(Integer idReserva){
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		LocalDate fecha = LocalDate.now().plusDays(1);
		
		String deleteReserva = prop.getProperty("deleteReserva");
		int status=0;
		try{
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("delete from reserva where idReserva=? and fecha > ?");
		ps.setInt(1, idReserva);
		ps.setString(2, fecha.toString());
		status = ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Solicita la dificultad de una pista dada por id
	 * @param id
	 * @return
	 */
	
	public String solicitarDificultad(Integer id) {
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
		String dificultad="";
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where idReserva=?");
			ps.setInt(1, id);
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {			
				dificultad = rs.getString("dificultad"); 
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return dificultad;
	}
	
	/**
	 * Actualiza una reserva infantil
	 * @param reserva Reserva de tipo infantil a escribir
	 * @param idBono Identificador del bono al que pertenece la reserva
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int actualizarInfantil(RInfantileDTO reserva) {
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
			PreparedStatement ps = connection.prepareStatement("update reserva set duracion=?,precio=?,fecha=?,nombrePista=?,ninos=?, where correoUsuario=?");
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setString(3, reserva.getFecha().toString());
			ps.setString(4, reserva.getPista());
			ps.setInt(5, reserva.getParticipantes());
			ps.setString(6, reserva.getUsuario());
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
	
	public int actualizarAdulto(RAdultDTO reserva) {
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
			PreparedStatement ps = connection.prepareStatement("update reserva set duracion=?,precio=?,fecha=?,nombrePista=?,adultos=?, where correoUsuario=?");
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setString(3, reserva.getFecha().toString());
			ps.setString(4, reserva.getPista());
			ps.setInt(5, reserva.getPartipantes());
			ps.setString(6, reserva.getUsuario());
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
	
	public int actualizarFamiliar(RFamiliarDTO reserva) {
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
			PreparedStatement ps = connection.prepareStatement("update reserva set duracion=?,precio=?,fecha=?,nombrePista=?,ninos=?,adultos=?, where correoUsuario=?");
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setString(3, reserva.getFecha().toString());
			ps.setString(4, reserva.getPista());
			ps.setInt(6, reserva.getadultos());
			ps.setInt(5, reserva.getNinos());
			ps.setString(7, reserva.getUsuario());
			status = ps.executeUpdate();
	
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
}
	

