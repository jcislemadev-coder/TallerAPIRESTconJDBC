package com.krakedev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {

	private static final Logger log = LogManager.getLogger(VideojuegoJdbc.class);

	// -------METODO PARA INSERTAR VIDEOJUEGOS//
	public static Videojuego insertar(String codigo, String nombre, String plataforma, float precio, boolean disponible,
			String genero) {
		Connection con = null;
		PreparedStatement ps = null;
		Videojuego juego = null;

		try {
			con = Conexion.getConnection();
			String sql = """
					INSERT INTO videojuegos (codigo,nombre,plataforma,precio,disponible,genero)
					VALUES(?,?,?,?,?,?);

					""";
			juego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);
			ps = con.prepareStatement(sql);
			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setFloat(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);

			int filas = ps.executeUpdate();
			log.info("SE HAN INSERTADO CORRECTAMENTE, LAS ", filas, "FILAS");
		} catch (Exception e) {
			log.error("ERROR AL INSERTAR FILAS EN VIDEOJUEGOS", e);
			throw new RuntimeException("Error al insertar: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return juego;
	}

	// -------METODO PARA LISTAR VIDEOJUEGOS//
	public static List<Videojuego> listar() {
		List<Videojuego> videojuegos = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs;
		String sql = """
				SELECT *FROM videojuegos;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Videojuego juego = new Videojuego(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("plataforma"), rs.getFloat("precio"), rs.getBoolean("disponible"),
						rs.getString("genero"));
				videojuegos.add(juego);
			}

		} catch (Exception e) {
			log.error("Error al listar videojuegos", e);
			throw new RuntimeException("Error al listar: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return videojuegos;
	}

	// -------METODO PARA BUSCAR//

	public static Videojuego buscar(String codigo) {

		Videojuego juego = null;
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs = null;
		String sql = """

				SELECT *FROM videojuegos
				WHERE codigo = ?;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, codigo);

			rs = ps.executeQuery();
			if (rs.next()) {
				juego = new Videojuego(rs.getString("codigo"), rs.getString("nombre"), rs.getString("plataforma"),
						rs.getFloat("precio"), rs.getBoolean("disponible"), rs.getString("genero"));
			}

		} catch (Exception e) {
			log.error("ERROR AL BUSCAR VIDEOJUEGO POR CODIGO: ", e);
			throw new RuntimeException("Error al buscar videojuego: " + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return juego;
	}

	// -------METODO PARA ACTUALIZAR//

	public static Videojuego actualizar(String codigo, String nombreNuevo, String plataformaNuevo, float precioNuevo,
			boolean disponibleNuevo, String generoNuevo) {

		Videojuego juego = null;
		Connection con = null;
		PreparedStatement ps;

		String sql = """
				UPDATE videojuegos
				SET nombre= ?,
				plataforma= ?,
				precio=?,
				disponible= ?,
				genero= ?
				WHERE codigo =?
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, nombreNuevo);
			ps.setString(2, plataformaNuevo);
			ps.setFloat(3, precioNuevo);
			ps.setBoolean(4, disponibleNuevo);
			ps.setString(5, generoNuevo);
			ps.setString(6, codigo);

			int fila = ps.executeUpdate();
			juego = new Videojuego(codigo, nombreNuevo, plataformaNuevo, precioNuevo, disponibleNuevo, generoNuevo);
			log.info("Filas actualizadas con exito: " + fila);
		} catch (Exception e) {
			log.error("ERROR AL ACTUALIZAR VIDEOJUEGO", e);
			throw new RuntimeException("Error al actualizar videojuego" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return juego;

	}

	public static boolean eliminar(String codigo) {
		Connection con = null;
		PreparedStatement ps;
		String sql = """
				DELETE FROM videojuegos
				WHERE codigo =?;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, codigo);

			int filas = ps.executeUpdate();
			log.info("FILAS ELIMINADAS" + filas);
			return true;
		} catch (Exception e) {
			log.error("ERROR AL ELIMINAR VIDEOJUEGO", e.getMessage());
			throw new RuntimeException("Error al eliminar: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}