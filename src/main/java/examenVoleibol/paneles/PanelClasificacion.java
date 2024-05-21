package examenVoleibol.paneles;

import javax.swing.JFrame;
import javax.swing.JPanel;


import examenVoleibol.controllers.ControladorEquipo;
import examenVoleibol.model.Equipo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PanelClasificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<Equipo> listModelEquipos = null;
	private JList jlistEquipos;
	private List<Equipo> equipos = (List<Equipo>) ControladorEquipo.getInstance().findAll();

	public static void main(String[] args) {
		JFrame frame = new JFrame("Panel Clasificacion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.getContentPane().add(new PanelClasificacion());
		frame.setVisible(true);
	}

	public PanelClasificacion() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Clasificación");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		jlistEquipos = new JList(this.getDefaultListModel());
		GridBagConstraints gbc_jlistEquipos = new GridBagConstraints();
		gbc_jlistEquipos.gridheight = 7;
		gbc_jlistEquipos.insets = new Insets(0, 0, 0, 5);
		gbc_jlistEquipos.fill = GridBagConstraints.BOTH;
		gbc_jlistEquipos.gridx = 0;
		gbc_jlistEquipos.gridy = 1;
		add(jlistEquipos, gbc_jlistEquipos);

		JButton btnNewButton_2 = new JButton("Reset");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 3;
		add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton_1 = new JButton("Arriba");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moverArriba();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 4;
		add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton = new JButton("Abajo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moverAbajo();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_3 = new JButton("Eliminar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarEquipoSeleccionado();
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 6;
		add(btnNewButton_3, gbc_btnNewButton_3);

	}

	private DefaultListModel getDefaultListModel() {
		this.listModelEquipos = new DefaultListModel<Equipo>();
		this.listModelEquipos.addAll(equipos);
		return this.listModelEquipos;
	}

	private void eliminarEquipoSeleccionado() {
		for (int i = this.jlistEquipos.getSelectedIndices().length - 1; i >= 0; i--) {
			this.listModelEquipos.removeElementAt(this.jlistEquipos.getSelectedIndices()[i]);
		}
	}

	private void reset() {
		this.listModelEquipos.removeAllElements();
		this.listModelEquipos.addAll(equipos);
	}
	private void moverArriba() {
	    int selectedIndex = jlistEquipos.getSelectedIndex();
	    if (selectedIndex > 0) {
	        Equipo equipo = listModelEquipos.getElementAt(selectedIndex);
	        listModelEquipos.remove(selectedIndex);
	        listModelEquipos.add(selectedIndex - 1, equipo);
	        jlistEquipos.setSelectedIndex(selectedIndex - 1);
	    }
	}

	private void moverAbajo() {
	    int selectedIndex = jlistEquipos.getSelectedIndex();
	    if (selectedIndex < listModelEquipos.getSize() - 1) {
	        Equipo equipo = listModelEquipos.getElementAt(selectedIndex);
	        listModelEquipos.remove(selectedIndex);
	        listModelEquipos.add(selectedIndex + 1, equipo);
	        jlistEquipos.setSelectedIndex(selectedIndex + 1);
	    }
	}

}
