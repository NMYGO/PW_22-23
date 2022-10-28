package pw.p2.data.DAO;

import pw.p2.business.DTOKart;
import pw.p2.data.Estado;
import pw.p2.data.common.DBConnection;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;


/**
 * 
 * DAO Kart
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class DAOKart {
	public ArrayList<DTOKart> requestKartsById(int id) {
		ArrayList<DTOKart> listOfUsers = new ArrayList<DTOKart>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = "select last, first from User where age = " + id;
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				Boolean tipo = rs.getBoolean(0);
				Estado estado = Estado.valueOf(rs.getString("VALOR"));
				listOfUsers.add(new DTOKart());
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listOfUsers;
	}
}
