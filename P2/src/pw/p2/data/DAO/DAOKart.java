package pw.p2.data.DAO;

import pw.p2.business.DTOKart;
import pw.p2.data.Estado;
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

/**
 * 
 * DAO de kart
 * Encargado de interactuar con la tabla kart de la base de datos
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DAOKart {
	/**
	 * Solicita la tabla de los karts
	 * @return
	 */
	public ArrayList<DTOKart> solicitarKarts() {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaKart = prop.getProperty("consultaKart");
		ArrayList<DTOKart> karts = new ArrayList<DTOKart>();
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
				karts.add(new DTOKart(id, tipo, estado, pista));
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
	 * Solicita un kart específico
	 * @param id - identificador del kart
	 * @return
	 */
	public DTOKart solicitarKart(Integer id) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaKartID = prop.getProperty("consultaKartID");
		DTOKart kart = new DTOKart();
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
				kart = new DTOKart(id, tipo, estado, pista);
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
	 * Solicita los karts de una pista concreta
	 * @param nombrePista
	 * @return
	 */
	public ArrayList<DTOKart> solicitarKartsPista(String nombrePista) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaKartPista = prop.getProperty("consultaKartPista");
		ArrayList<DTOKart> kartsPista = new ArrayList<DTOKart>();
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
				kartsPista.add(new DTOKart(id, tipo, estado, pista));
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
	 */
	public int escribirKartUpdate(DTOKart kart) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
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
	 * @param kart
	 * @return
	 */
	public int escribirKartInsert (DTOKart kart) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
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
			ps.setString(4, kart.getNombrePista().toString());			
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
