package pw.p2.data.DAO;

import pw.p2.business.DTOBono;
import pw.p2.business.DTORInfantil;
import pw.p2.data.Dificultad;
import pw.p2.data.common.DBConnection;
import java.sql.*;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import java.time.LocalDate;

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
				int sesion = rs.getInt("sesion");
				LocalDate fcaducidad = LocalDate.parse(rs.getString("fechaCaducidad"));
				String bUsuario = rs.getString("correoUsuario");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				bonos.add(new DTOBono(sesion,bUsuario,tipo,fcaducidad));
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
	
	public Integer solicitarBono(String usuario, Dificultad tipo) {		
		int idBono = -1;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "select * from bono where correoUsuario = '" + usuario + "' and dificultad = '" + tipo.toString() + "'";
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				idBono = rs.getInt("idBono");
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return idBono;
	}
	
	public int escribirBonoUpdate(DTOBono bono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update bono set sesion=?,fechaCaducidad=?,correoUsuario=?,tipo=? where idBono=?");
			ps.setInt(5, bono.getId());
			ps.setInt(1, bono.getSesion());
			ps.setString(2, bono.getFcaducidad().toString());
			ps.setString(3, bono.getbUsuario());
			ps.setString(4, bono.getTipo().toString());
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	public int escribirReservaBonoInsert(DTOBono bono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into bono (idBono,sesion,fechaCaducidad,correoUsuario,tipo) values(?,?,?,?,?)");
			ps.setInt(5, bono.getId());
			ps.setInt(2, bono.getSesion());
			ps.setString(3, bono.getFcaducidad().toString());
			ps.setString(4, bono.getbUsuario());
			ps.setString(5, bono.getTipo().toString());
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	public int escribirReservaInfantilUpdate(DTORInfantil reserva, Integer idBono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("update reserva set duracion=?,precio=?,descuento=?,fecha=?,nombrePista=?,dificultad=?,ninos=?,adultos=?,idBono=? where correoUsuario=?");
			ps.setString(10, reserva.getUsuario());
			ps.setInt(1, reserva.getDur());
			ps.setFloat(2, reserva.getPrecio());
			ps.setInt(3, reserva.getDesc());
			ps.setString(4, reserva.getFecha().toString());
			ps.setString(5, reserva.getPista());
			ps.setString(6, reserva.getTipo().toString());
			ps.setInt(7, reserva.getParticipantes());
			ps.setInt(8, 0);
			ps.setInt(9, idBono);
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
	public int escribirReservaInfantilInsert(DTORInfantil reserva, Integer idBono) {
		int status = 0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into reserva (correoUsuario,duracion,precio,descuento,fecha,nombrePista,dificultad,ninos,adultos,idBono) values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, reserva.getUsuario());
			ps.setInt(2, reserva.getDur());
			ps.setFloat(3, reserva.getPrecio());
			ps.setInt(4, reserva.getDesc());
			ps.setString(5, reserva.getFecha().toString());
			ps.setString(6, reserva.getPista());
			ps.setString(7, reserva.getTipo().toString());
			ps.setInt(8, reserva.getParticipantes());
			ps.setInt(9, 0);
			ps.setInt(10, idBono);
			status = ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return status;
	}
	
}
