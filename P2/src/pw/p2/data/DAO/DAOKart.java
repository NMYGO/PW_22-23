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
				karts.add(new DTOKart(id, tipo, estado));
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
				kartsPista.add(new DTOKart(id, tipo, estado));
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
	
}
