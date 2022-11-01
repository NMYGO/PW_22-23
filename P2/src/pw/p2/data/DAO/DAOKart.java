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
	
	public DTOKart solicitarKart(Integer id) {
		DTOKart kart = new DTOKart();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from kart where idKart = " + id;
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				Boolean tipo = rs.getBoolean("tipo");
				Estado estado = Estado.valueOf(rs.getString("estado"));
				String pista = rs.getString("nombrePista");
				kart = new DTOKart(id, tipo, estado, pista);
			}

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
	
	public int escribirKartUpdate(DTOKart kart) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update kart set tipo=?,estado=?,nombrePista=? where idKart=?");
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
	
	public int escribirKartInsert (DTOKart kart) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into kart (idKart,tipo,estado,nombrePista) values(?,?,?,?)");
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
