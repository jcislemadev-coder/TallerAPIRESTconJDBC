package com.krakedev.jdbc.videojuegos.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.krakedev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakedev.videojuegos.entidades.Videojuego;

@Service
public class VideojuegoJdbcServicio {

	public Videojuego crear(Videojuego videojuego) {
		Videojuego game = VideojuegoJdbc.insertar(videojuego.getCodigo(), videojuego.getNombre(),
				videojuego.getPlataforma(), videojuego.getPrecio(), videojuego.isDisponible(), videojuego.getGenero());
		return game;
	}
	
	public List<Videojuego> listar(){
		List<Videojuego> listadeJuegos;
		listadeJuegos = VideojuegoJdbc.listar();
		return listadeJuegos;
	}

	public Videojuego buscar(String codigo) {
		Videojuego gameFound = VideojuegoJdbc.buscar(codigo);
		return gameFound;
	}
	
	
	public Videojuego actualizar(String codigo, Videojuego videojuegoActualizado) {
		Videojuego juegoActualizado = VideojuegoJdbc.actualizar(codigo,
				videojuegoActualizado.getNombre(),
				videojuegoActualizado.getPlataforma(),
				videojuegoActualizado.getPrecio(),
				videojuegoActualizado.isDisponible(),
				videojuegoActualizado.getGenero());
		return juegoActualizado;
	}
		
	public boolean eliminar(String codigo) {
		boolean eliminado;
		eliminado = VideojuegoJdbc.eliminar(codigo);
		return eliminado;
	}
}	