package pw.p2.data.DAO;

import pw.p2.business.DTOReserva;
import pw.p2.data.Tipo;
import pw.p2.data.common.DBConnection;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import java.time.LocalDate;

public class DAOReserva {
	public ArrayList<DTOReserva> solicitarReservas() {
		ArrayList<DTOReserva> reservas = new ArrayList<DTOReserva>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from reserva";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String correo = rs.getString("correoUsuario");
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer idReserva = rs.getInt("idReserva");
				Integer idBono = rs.getInt("idBono");
				Integer adultos = rs.getInt("adultos");
				Integer ninos = rs.getInt("ninos");
				Tipo tipo = Tipo.valueOf(rs.getString("dificultad")); //CAMBIAR A TIPO
				reservas.add(new DTOReserva(correo, fecha, duracion, pista, precio, descuento, idReserva, idBono, adultos, ninos, tipo));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reservas;
	}
}
