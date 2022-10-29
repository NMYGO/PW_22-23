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
}
