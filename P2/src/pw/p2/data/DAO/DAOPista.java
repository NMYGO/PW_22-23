package pw.p2.data.DAO;

import pw.p2.business.DTOKart;
import pw.p2.business.DTOPista;
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

public class DAOPista {
	
	public ArrayList<DTOPista> solicitarPistas() {
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
		ArrayList<DTOPista> pistas = new ArrayList<DTOPista>();
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
				pistas.add(new DTOPista(nombre, estado, dificultad, maxkarts));
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
	
	public ArrayList<DTOPista> solicitarPistasLibres(Boolean estado, Integer participantes, Dificultad dificultad) {
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
		DAOKart kartTabla = new DAOKart();
		ArrayList <DTOKart> kartsPista = new ArrayList<DTOKart>();
		ArrayList<DTOPista> pistas = new ArrayList<DTOPista>();
		int iestado = estado ? 1 : 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			//String query = consultaPistasLibres;
			//Statement stmt = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(consultaPistasLibres);
			ps.setInt(1, iestado);
			ps.setString(2,dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombrePista");
				Integer maxkarts = rs.getInt("maxKarts");
				kartsPista = kartTabla.solicitarKartsPista(nombre);
				if(maxkarts >= participantes && participantes <= kartsPista.size()) {
						pistas.add(new DTOPista(nombre, estado, dificultad, maxkarts));
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
	
	public DTOPista solicitarPista(String nombre) {
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
		DTOPista pista = new DTOPista();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			//String query = consultaPistaNombre;
			//Statement stmt = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(consultaPistaNombre);
			ps.setString(1, nombre);
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				Boolean estado = rs.getBoolean("estado");
				Dificultad dificultad = Dificultad.valueOf(rs.getString("dificultad"));
				Integer maxkarts = rs.getInt("maxKarts");
				pista = new DTOPista(nombre, estado, dificultad, maxkarts);
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
	
	public int escribirPistaUpdate(DTOPista pista) {
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
	
	public int escribirPistaInsert(DTOPista pista) {
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
