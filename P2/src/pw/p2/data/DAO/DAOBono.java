package pw.p2.data.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import pw.p2.business.DTOBono;
import pw.p2.data.Tipo;
import pw.p2.data.common.DBConnection;

public class DAOBono {
	public ArrayList<DTOBono> solicitarBonos() {
		ArrayList<DTOBono> bonos = new ArrayList<DTOBono>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from bono";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("idBono");
				int sesion = rs.getInt("sesion");
				LocalDate fcaducidad = LocalDate.parse(rs.getString("fechaCaducidad"));
				String bUsuario = rs.getString("correoUsuario");
				Tipo tipo = Tipo.valueOf(rs.getString("tipo"));
				bonos.add(new DTOBono(id,sesion,bUsuario,tipo,fcaducidad));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return bonos;
	}
}
