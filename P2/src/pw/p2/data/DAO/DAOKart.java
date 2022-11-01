package pw.p2.data.DAO;

import pw.p2.business.DTOKart;
import pw.p2.data.Estado;
import pw.p2.data.common.DBConnection;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;

public class DAOKart {
	
	public ArrayList<DTOKart> solicitarKarts() {
		ArrayList<DTOKart> karts = new ArrayList<DTOKart>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from kart";
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
	
	public ArrayList<DTOKart> solicitarKartsPista(String nombrePista) {
		ArrayList<DTOKart> kartsPista = new ArrayList<DTOKart>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from kart where nombrePista like " + "'" + nombrePista + "'";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				Integer id = rs.getInt("idKart");
				Boolean tipo = rs.getBoolean("tipo");
				Estado estado = Estado.valueOf(rs.getString("estado"));
				String pista = rs.getString("nombrePista");
				kartsPista.add(new DTOKart(id, tipo, estado, pista));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return kartsPista;
	}
	
	public void insertarKart(DTOKart kart) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into usuario (idKart,tipo,estado,nombrePista) values(?,?,?,?,?)");
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
	}
	
	public DTOKart seleccionarKart(int id) {
		DTOKart kart;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from kart where idKart="+id+";";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			
			Integer id2 = rs.getInt("idKart");
			Boolean tipo = rs.getBoolean("tipo");
			Estado estado = Estado.valueOf(rs.getString("estado"));
			String pista = rs.getString("nombrePista");
			kart = new DTOKart(id2, tipo, estado, pista);
			
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return kart;
	}
	
	public void asociarKartAPista() {
		
	}
	
	public ArrayList<DTOKart> seleccionarKartsDisponibles() {
		ArrayList<DTOKart> kartsDisponibles = new ArrayList<DTOKart>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from kart where estado like 'disponible';";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				Integer id = rs.getInt("idKart");
				Boolean tipo = rs.getBoolean("tipo");
				Estado estado = Estado.valueOf(rs.getString("estado"));
				String pista = rs.getString("nombrePista");
				kartsDisponibles.add(new DTOKart(id, tipo, estado, pista));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return kartsDisponibles;
	}
	
}
