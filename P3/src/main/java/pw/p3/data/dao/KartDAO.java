package pw.p3.data.dao;

import pw.p3.business.kart.KartDTO;
import pw.p3.data.Estado;
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
 * DAO de Kart
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class KartDAO {
	
	/**
	 * Solicita todos los karts
	 * @return ArrayList<KartDTO> de los karts
	 **/
	
	public ArrayList<KartDTO> solicitarKarts(String path) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaKart = prop.getProperty("consultaKart");
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = consultaKart;
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				Integer id = rs.getInt("idKart");
				Boolean tipo = rs.getBoolean("tipo");
				Estado estado = Estado.valueOf(rs.getString("estado"));
				String pista = rs.getString("nombrePista");
				karts.add(new KartDTO(id, tipo, estado, pista));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return karts;
	}
	
	/**
	 * Solicita un kart especifico por id
	 * @param id Identificador del kart
	 * @return KartDTO
	 **/
	
	public KartDTO solicitarKart(String path, Integer id) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaKartID = prop.getProperty("consultaKartID");
		KartDTO kart = null;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaKartID);
			ps.setInt(1, id);
			ResultSet rs = (ResultSet) ps.executeQuery();
			
			
			while (rs.next()) {
				Boolean tipo = rs.getBoolean("tipo");
				Estado estado = Estado.valueOf(rs.getString("estado"));
				String pista = rs.getString("nombrePista");
				kart = new KartDTO(id, tipo, estado, pista);
			}

			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return kart;
	}
	
	/**
	 * Solicita los karts de una pista concreta por nombre de pista
	 * @param nombrePista Nombre de la pista a la que pertenece el kart
	 * @return ArrayList<KartDTO> de los karts que pertenecen a la pista
	 **/
	
	public ArrayList<KartDTO> solicitarKartsPista(String path, String nombrePista) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaKartPista = prop.getProperty("consultaKartPista");
		ArrayList<KartDTO> kartsPista = new ArrayList<KartDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(consultaKartPista);
			ps.setString(1, nombrePista);
			ResultSet rs = (ResultSet) ps.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("idKart");
				Boolean tipo = rs.getBoolean("tipo");
				Estado estado = Estado.valueOf(rs.getString("estado"));
				String pista = rs.getString("nombrePista");
				kartsPista.add(new KartDTO(id, tipo, estado, pista));
			}

			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return kartsPista;
	}
	
	/**
	 * Actualiza un kart
	 * @param kart Kart
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirKartUpdate(String path, KartDTO kart) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String updateKart = prop.getProperty("updateKart");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateKart);
			ps.setInt(4, kart.getId());
			ps.setBoolean(1, kart.isTipo());
			ps.setString(2, kart.getEstado().toString());
			ps.setString(3, kart.getNombrePista());			
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Inserta un nuevo kart
	 * @param kart Kart
	 * @return Integer que informa sobre el status de la devolucion
	 **/
	
	public int escribirKartInsert (String path, KartDTO kart) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File(path)));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String insertKart = prop.getProperty("insertKart");
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(insertKart);
			ps.setInt(1, kart.getId());
			ps.setBoolean(2, kart.isTipo());
			ps.setString(3, kart.getEstado().toString());
			ps.setString(4, kart.getNombrePista());			
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	public void asociarKartAPista() {
		
	}	
}
