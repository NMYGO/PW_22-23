package pw.p2.data.DAO;

import pw.p2.business.DTOPista;
import pw.p2.data.Dificultad;
import pw.p2.data.common.DBConnection;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;

public class DAOPista {
	
	public ArrayList<DTOPista> solicitarPistas() {
		ArrayList<DTOPista> pistas = new ArrayList<DTOPista>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from pista";
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
	
	public ArrayList<DTOPista> solicitarPistasLibres(Boolean estado, Dificultad dificultad) {
		ArrayList<DTOPista> pistas = new ArrayList<DTOPista>();
		int iestado = estado ? 1 : 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from pista where estado = " + iestado + " and dificultad = " + "'" + dificultad.toString() + "'";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String nombre = rs.getString("nombrePista");
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
	
	public DTOPista solicitarPista(String nombre) {
		DTOPista pista = new DTOPista();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from pista where nombrePista = " + "'" + nombre + "'";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				Boolean estado = rs.getBoolean("estado");
				Dificultad dificultad = Dificultad.valueOf(rs.getString("dificultad"));
				Integer maxkarts = rs.getInt("maxKarts");
				pista = new DTOPista(nombre, estado, dificultad, maxkarts);
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return pista;
	}
	
	public int escribirPistaUpdate(DTOPista pista) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update pista set estado=?,dificultad=?,maxKarts=? where nombrePista=?");
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
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into pista (nombrePista,estado,dificultad,maxKarts) values(?,?,?,?)");
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
