package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {
	private static final Logger log = LogManager.getLogger(Conexion.class);
	private static final String URL = "jdbc:postgresql://localhost:5432/apijdbc";
	private static final String USER = "postgres";
	private static final String PASSWORD = "*****";

	public static Connection getConnection() {

		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			log.info("CONEXION EXITOSA");
			return con;

		} catch (SQLException e) {
			log.error("ERROR EN CONEXION", e);

			throw new RuntimeException("ERROR DE CONEXION", e);
		}

	}
}
