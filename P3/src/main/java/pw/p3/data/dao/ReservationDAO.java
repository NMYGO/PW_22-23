package pw.p3.data.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.ResultSet;

import pw.p3.business.reservation.*;
import pw.p3.data.Dificultad;
import pw.p3.data.common.DBConnection;

public class ReservationDAO {
	
	public RInfantileDTO solicitarProximaReservaInfantil(String correoUsuario, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaInfantil = prop.getProperty("consultaReservaInfantil");
		RInfantileDTO reserva = new RInfantileDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where fecha = (select MIN(fecha) from reserva where correoUsuario=? and dificultad=?)");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reserva = new RInfantileDTO(correoUsuario, fecha, duracion, pista, precio, descuento, ninos, tipo);
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
	
	public RAdultDTO solicitarProximaReservaAdulto(String correoUsuario, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaAdulto = prop.getProperty("consultaReservaAdulto");
		RAdultDTO reserva = new RAdultDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where fecha = (select MIN(fecha) from reserva where correoUsuario=? and dificultad=?)");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reserva = new RAdultDTO(correoUsuario, fecha, duracion, pista, precio, descuento, adultos, tipo);
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
	
	public RFamiliarDTO solicitarProximaReservaFamiliar(String correoUsuario, Dificultad dificultad) {
		Properties prop = new Properties();
		try{
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		String consultaReservaFamiliar = prop.getProperty("consultaReservaFamiliar");
		RFamiliarDTO reserva = new RFamiliarDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from reserva where fecha = (select MIN(fecha) from reserva where correoUsuario=? and dificultad=?)");
			ps.setString(1, correoUsuario);
			ps.setString(2, dificultad.toString());
			
			ResultSet rs = (ResultSet) ps.executeQuery();
	
			while (rs.next()) {
				LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
				Integer duracion = rs.getInt("duracion");
				String pista = rs.getString("nombrePista");
				Float precio = rs.getFloat("precio");
				Integer descuento = rs.getInt("descuento");
				Integer adultos = rs.getInt("adultos");
				Integer ninos = rs.getInt("ninos");
				Dificultad tipo = Dificultad.valueOf(rs.getString("dificultad"));
				reserva = new RFamiliarDTO(correoUsuario, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo);
			}
	
			if (ps != null){ 
				ps.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return reserva;
	}
}
