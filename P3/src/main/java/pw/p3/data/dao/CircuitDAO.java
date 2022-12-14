package pw.p3.data.dao;

import pw.p3.business.kart.KartDTO;
import pw.p3.business.circuit.CircuitDTO;
import pw.p3.data.Dificultad;
import pw.p3.data.common.DBConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * DAO Pista
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class CircuitDAO {
	
	/**
	 * Solicita todas las pistas
	 * @return ArrayList<CircuitDTO> de pistas
	 **/
	
	public ArrayList<CircuitDTO> solicitarPistas(String path) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaPista = prop.getProperty("consultaPista");
		ArrayList<CircuitDTO> pistas = new ArrayList<CircuitDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaPista;
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String nombre = rs.getString("nombrePista");
				Boolean estado = rs.getBoolean("estado");
				Dificultad dificultad = Dificultad.valueOf(rs.getString("dificultad"));
				Integer maxkarts = rs.getInt("maxKarts");
				pistas.add(new CircuitDTO(nombre, estado, dificultad, maxkarts));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return pistas;
	}
	
	/**
	 * Solicita las pistas libres
	 * @param estado Estado de la pista
	 * @param participantes Numero de participantes
	 * @param dificultad Dificultad de la pista
	 * @return ArrayList<CircuitDTO> de pistas libres
	 **/
	
	public ArrayList<CircuitDTO> solicitarPistasLibres(String path, Boolean estado, Integer participantes, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaPistasLibres = prop.getProperty("consultaPistasLibres");
		KartDAO kartTabla = new KartDAO();
		ArrayList <KartDTO> kartsPista = new ArrayList<KartDTO>();
		ArrayList<CircuitDTO> pistas = new ArrayList<CircuitDTO>();
		int iestado = estado ? 1 : 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaPistasLibres);
			ps.setInt(1, iestado);
			ps.setString(2,dificultad.toString());
			ps.setInt(3,participantes);
			
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombrePista");
				Integer maxkarts = rs.getInt("maxKarts");
				kartsPista = kartTabla.solicitarKartsPista(path, nombre);
				if(maxkarts >= participantes && participantes <= kartsPista.size()) {
						pistas.add(new CircuitDTO(nombre, estado, dificultad, maxkarts));
				}
			}

			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return pistas;
	}
	
	/**
	 * Solicita las pistas disponibles por dificultad
	 * @param dificultad Dificultad de la pista
	 * @return ArrayList<CircuitDTO> de pistas disponibles
	 **/
	
	public ArrayList<CircuitDTO> solicitarPistasDisponiblesDificultad(String path, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaPistasLibres = prop.getProperty("solicitarPistasDisponiblesDificultad");
		ArrayList<CircuitDTO> pistas = new ArrayList<CircuitDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaPistasLibres);
			ps.setString(1,dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombrePista");
				Integer maxkarts = rs.getInt("maxKarts");
				pistas.add(new CircuitDTO(nombre, false, dificultad, maxkarts));
			}

			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return pistas;
	}
	
	/**
	 * Solicita las pistas disponibles por nombre
	 * @param nombre Nombre de la pista
	 * @return ArrayList<CircuitDTO> de pistas disponibles
	 **/
	
	public ArrayList<CircuitDTO> solicitarPistasDisponiblesNombre(String path, String nombre) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaPistasLibres = prop.getProperty("solicitarPistasDisponiblesNombre");
		ArrayList<CircuitDTO> pistas = new ArrayList<CircuitDTO>();;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaPistasLibres);
			ps.setString(1,nombre);
			
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				Integer maxkarts = rs.getInt("maxKarts");
				Dificultad dificultad = Dificultad.valueOf(rs.getString("dificultad"));
				pistas.add(new CircuitDTO(nombre, false, dificultad, maxkarts));
			}

			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return pistas;
	}
	
	/**
	 * Solicita las pistas disponibles por numero de karts
	 * @param nkarts Numero de karts
	 * @return ArrayList<CircuitDTO> de pistas disponibles
	 **/
	
	public ArrayList<CircuitDTO> solicitarPistasDisponiblesNkarts(String path, Integer nkarts) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaPistasLibres = prop.getProperty("solicitarPistasDisponiblesNkarts");
		KartDAO kartTabla = new KartDAO();
		ArrayList <KartDTO> kartsPista = new ArrayList<KartDTO>();
		ArrayList<CircuitDTO> pistas = new ArrayList<CircuitDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaPistasLibres);
			ps.setInt(1,nkarts);
			
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombrePista");
				Dificultad dificultad = Dificultad.valueOf(rs.getString("dificultad"));
				Integer maxkarts = rs.getInt("maxKarts");
				kartsPista = kartTabla.solicitarKartsPista(path, nombre);
				if(maxkarts >= nkarts && nkarts <= kartsPista.size()) {
						pistas.add(new CircuitDTO(nombre, false, dificultad, maxkarts));
				}
			}

			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return pistas;
	}
	
	/**
	 * Solicita una pista específica por nombre
	 * @param nombre Nombre de la pista
	 * @return CircuitDTO de la pista
	 **/
	
	public CircuitDTO solicitarPista(String path, String nombre) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaPistaNombre = prop.getProperty("consultaPistaNombre");
		CircuitDTO pista = null;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaPistaNombre);
			ps.setString(1, nombre);
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				Boolean estado = rs.getBoolean("estado");
				Dificultad dificultad = Dificultad.valueOf(rs.getString("dificultad"));
				Integer maxkarts = rs.getInt("maxKarts");
				pista = new CircuitDTO(nombre, estado, dificultad, maxkarts);
			}

			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return pista;
	}
	
	/**
	 * Actualiza una pista
	 * @param pista Pista
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirPistaUpdate(String path, CircuitDTO pista) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updatePista = prop.getProperty("updatePista");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updatePista);
			ps.setString(4, pista.getNombre());
			ps.setBoolean(1, pista.isEstado());
			ps.setString(2, pista.getDificultad().toString());
			ps.setInt(3, pista.getMaxkarts());		
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Inserta una nueva pista
	 * @param pista Pista
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirPistaInsert(String path, CircuitDTO pista) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertPista = prop.getProperty("insertPista");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertPista);
			ps.setString(1, pista.getNombre());
			ps.setBoolean(2, pista.isEstado());
			ps.setString(3, pista.getDificultad().toString());
			ps.setInt(4, pista.getMaxkarts());				
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}	
}
