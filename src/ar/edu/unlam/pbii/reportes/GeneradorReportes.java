package ar.edu.unlam.pbii.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.unlam.pbii.criaturas.AfinidadElemental;
import ar.edu.unlam.pbii.criaturas.Criatura;
import ar.edu.unlam.pbii.maestros.MaestroElemental;

public class GeneradorReportes {
	
	public List<Criatura> listarTodasLasCriaturas(List<MaestroElemental> maestros) {
		List<Criatura> todasLasCriaturas = new ArrayList<>();
		
		if (maestros == null) {
			return todasLasCriaturas;
		}
		
		for (MaestroElemental maestro : maestros) {
			if (maestro != null && maestro.getCriaturas() != null) {
				todasLasCriaturas.addAll(maestro.getCriaturas().values());
			}
		}
		
		return todasLasCriaturas;
	}
	
	public Criatura obtenerCriaturaMayorEnergia(List<MaestroElemental> maestros) {
		List<Criatura> todasLasCriaturas = listarTodasLasCriaturas(maestros);
		
		if (todasLasCriaturas.isEmpty()) {
			return null;
		}
		
		Criatura criaturaMayorEnergia = null;
		int mayorEnergia = Integer.MIN_VALUE;
		
		for (Criatura criatura : todasLasCriaturas) {
			if (criatura != null) {
				int energia = criatura.getEnergiaTotal();
				if (energia > mayorEnergia) {
					mayorEnergia = energia;
					criaturaMayorEnergia = criatura;
				}
			}
		}
		
		return criaturaMayorEnergia;
	}
	
	public MaestroElemental obtenerMaestroConMasTransformadas(List<MaestroElemental> maestros) {
		if (maestros == null || maestros.isEmpty()) {
			return null;
		}
		
		MaestroElemental maestroConMas = null;
		int maxTransformadas = -1;
		
		for (MaestroElemental maestro : maestros) {
			if (maestro != null) {
				int cantidadTransformadas = maestro.contarCriaturasTransformadas();
				if (cantidadTransformadas > maxTransformadas) {
					maxTransformadas = cantidadTransformadas;
					maestroConMas = maestro;
				}
			}
		}
		
		return maestroConMas;
	}
	
	public HashMap<AfinidadElemental, Integer> obtenerCantidadPorAfinidad(List<MaestroElemental> maestros) {
		HashMap<AfinidadElemental, Integer> cantidadPorAfinidad = new HashMap<>();
		
		// Inicializar todas las afinidades con 0
		for (AfinidadElemental afinidad : AfinidadElemental.values()) {
			cantidadPorAfinidad.put(afinidad, 0);
		}
		
		List<Criatura> todasLasCriaturas = listarTodasLasCriaturas(maestros);
		
		for (Criatura criatura : todasLasCriaturas) {
			if (criatura != null) {
				AfinidadElemental afinidad = criatura.getAfinidad();
				if (afinidad != null) {
					int cantidadActual = cantidadPorAfinidad.get(afinidad);
					cantidadPorAfinidad.put(afinidad, cantidadActual + 1);
				}
			}
		}
		
		return cantidadPorAfinidad;
	}
}

