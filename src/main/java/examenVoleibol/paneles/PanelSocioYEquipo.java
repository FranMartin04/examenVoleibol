package examenVoleibol.paneles;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import examenVoleibol.controllers.ControladorEquipo;
import examenVoleibol.model.Equipo;
import examenVoleibol.model.Socio;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class PanelSocioYEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtm = null;
	private JComboBox<Equipo> jcbEquipo;

	/**
	 * Create the panel.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Panel Socio y Equipo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.getContentPane().add(new PanelSocioYEquipo());
		frame.setVisible(true);
	}

	public PanelSocioYEquipo() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Socios Equipos");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Equipo:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		jcbEquipo = new JComboBox();
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.insets = new Insets(0, 0, 5, 0);
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 1;
		gbc_jcbEquipo.gridy = 1;
		add(jcbEquipo, gbc_jcbEquipo);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 20, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JRadioButton rdNombre = new JRadioButton("Ordenar por nombre");
		GridBagConstraints gbc_rdNombre = new GridBagConstraints();
		gbc_rdNombre.insets = new Insets(0, 0, 5, 5);
		gbc_rdNombre.gridx = 2;
		gbc_rdNombre.gridy = 0;
		panel.add(rdNombre, gbc_rdNombre);

		JRadioButton rdPrimerApellido = new JRadioButton("Ordenar por primer apellido");
		GridBagConstraints gbc_rdPrimerApellido = new GridBagConstraints();
		gbc_rdPrimerApellido.insets = new Insets(0, 0, 5, 0);
		gbc_rdPrimerApellido.gridx = 3;
		gbc_rdPrimerApellido.gridy = 0;
		panel.add(rdPrimerApellido, gbc_rdPrimerApellido);

		JRadioButton rdSegundoApellido = new JRadioButton("Ordenar por segundo apellido");
		GridBagConstraints gbc_rdSegundoApellido = new GridBagConstraints();
		gbc_rdSegundoApellido.insets = new Insets(0, 0, 0, 5);
		gbc_rdSegundoApellido.gridx = 2;
		gbc_rdSegundoApellido.gridy = 1;
		panel.add(rdSegundoApellido, gbc_rdSegundoApellido);

		JRadioButton rdFechaNacimiento = new JRadioButton("Ordenar por fecha de nacimiento");
		GridBagConstraints gbc_rdFechaNacimiento = new GridBagConstraints();
		gbc_rdFechaNacimiento.gridx = 3;
		gbc_rdFechaNacimiento.gridy = 1;
		panel.add(rdFechaNacimiento, gbc_rdFechaNacimiento);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		cargaTodosEquipos();

		dtm = getDefaultTableModelNoEditable();
		JTable table = new JTable(dtm);
		scrollPane.setViewportView(table);

	}

	private DefaultTableModel getDefaultTableModelNoEditable() {
		String titulosEnTabla[] = DatosEnTabla.getTitulosColumnas();
		Object datosEnTabla[][] = DatosEnTabla.getDatosDeTabla(sacarEquipo());
		DefaultTableModel dtm = new DefaultTableModel(datosEnTabla, titulosEnTabla) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1;
			}
		};
		return dtm;
	}

	private void cargaTodosEquipos() {
		List<Equipo> equipos = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		for (Equipo u : equipos) {
			this.jcbEquipo.addItem(u);
		}
	}

	private int sacarEquipo() {
		int idEquipo = 0;
		for (int i = 0; i < this.jcbEquipo.getItemCount(); i++) {
			idEquipo = this.jcbEquipo.getItemAt(i).getId();

		}
		return idEquipo;
	}

}
