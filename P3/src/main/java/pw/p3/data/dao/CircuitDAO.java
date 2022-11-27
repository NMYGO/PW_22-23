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
 * DAO de pista
 * Encargado de interactuar con la tabla pista de la base de datos
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class CircuitDAO {
	
	/**
	 * Solicita la tabla de pistas
	 * @return ArrayList<DTOPista> de pistas
	 **/
	
	public ArrayList<CircuitDTO> solicitarPistas() {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
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
	 * @return ArrayList<DTOPista> de pistas libres
	 **/
	
	public ArrayList<CircuitDTO> solicitarPistasLibres(Boolean estado, Integer participantes, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
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
			
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombrePista");
				Integer maxkarts = rs.getInt("maxKarts");
				kartsPista = kartTabla.solicitarKartsPista(nombre);
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
	 * Solicita una pista específica
	 * @param nombre Nombre de la pista
	 * @return DTOPista
	 **/
	
	public CircuitDTO solicitarPista(String nombre) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaPistaNombre = prop.getProperty("consultaPistaNombre");
		CircuitDTO pista = new CircuitDTO();
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
	 * @param pista Nombre de la pista a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirPistaUpdate(CircuitDTO pista) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
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
	 * @param pista Nombre de la pista a escribir
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirPistaInsert(CircuitDTO pista) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
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
