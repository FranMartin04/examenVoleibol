package examenVoleibol.paneles;

import java.util.List;

import examenVoleibol.controllers.ControladorSocio;
import examenVoleibol.model.Socio;




public class DatosEnTabla {
private static List<Socio> socios = null;
	
	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Nombre", "Primer Apellido", "Segundo Apellido","Fecha de Nacimiento"};
	}
	public static List<Socio> getSocios (int idEquipo) {
		if (socios == null) {
			socios =  (List<Socio>) ControladorSocio
					.getInstance().findById(idEquipo);
		}
		return socios;

	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla(int idEquipo) {
		// Obtengo todas las personas
		List<Socio> Socios = getSocios(idEquipo);
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[Socios.size()][7];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < Socios.size(); i++) {
			Socio c = Socios.get(i);
			datos[i][0] = c.getNombre();
			datos[i][1] = c.getApellido1();
			datos[i][2] = c.getApellido2();
			datos[i][3] = c.getFechaNacimiento();

		}
		
		return datos;
	}
}
