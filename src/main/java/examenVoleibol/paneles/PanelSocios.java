package examenVoleibol.paneles;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.event.ChangeEvent;

import examenVoleibol.controllers.ControladorEquipo;
import examenVoleibol.controllers.ControladorSocio;
import examenVoleibol.model.Equipo;
import examenVoleibol.model.Socio;


import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelSocios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfNombre;
	private JTextField jtfPApellido;
	private JTextField jtfSApellido;
	private JFormattedTextField jtfFecha;
	private Socio currentEntity = null;
	private JLabel lblantiguedad;
	private JSlider slider;
	private JComboBox<Equipo> jcbEquipo;
	private JCheckBox ckActivo;

	/**
	 * Create the panel.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Panel Socios");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.getContentPane().add(new PanelSocios());
		frame.setVisible(true);
	}

	public PanelSocios() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridwidth = 7;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		add(toolBar, gbc_toolBar);

		JButton btnPrimero = new JButton("");
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEntityOnPanel((Socio) ControladorSocio.getInstance().findFirst());
			}
		});
		btnPrimero.setIcon(new ImageIcon(PanelSocios.class.getResource("/examenVoleibol/paneles/res/gotostart.png")));
		toolBar.add(btnPrimero);

		JButton btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEntityOnPanel((Socio) ControladorSocio.getInstance().findPrevious(currentEntity.getId()));
			}
		});
		btnAnterior.setIcon(new ImageIcon(PanelSocios.class.getResource("/examenVoleibol/paneles/res/previous.png")));
		toolBar.add(btnAnterior);

		JButton btnSiguiente = new JButton("");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEntityOnPanel((Socio) ControladorSocio.getInstance().findNext(currentEntity.getId()));
			}
		});
		btnSiguiente.setIcon(new ImageIcon(PanelSocios.class.getResource("/examenVoleibol/paneles/res/next.png")));
		toolBar.add(btnSiguiente);

		JButton btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEntityOnPanel((Socio) ControladorSocio.getInstance().findLast());
			}
		});
		btnUltimo.setIcon(new ImageIcon(PanelSocios.class.getResource("/examenVoleibol/paneles/res/gotoend.png")));
		toolBar.add(btnUltimo);

		JButton btnNuevo = new JButton("");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newEntity();
			}
		});
		btnNuevo.setIcon(new ImageIcon(PanelSocios.class.getResource("/examenVoleibol/paneles/res/nuevo.png")));
		toolBar.add(btnNuevo);

		JButton btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveEntity();
			}
		});
		btnGuardar.setIcon(new ImageIcon(PanelSocios.class.getResource("/examenVoleibol/paneles/res/guardar.png")));
		toolBar.add(btnGuardar);

		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteEntity();
			}
		});
		btnEliminar.setIcon(new ImageIcon(PanelSocios.class.getResource("/examenVoleibol/paneles/res/eliminar.png")));
		toolBar.add(btnEliminar);

		JLabel lblNewLabel = new JLabel("Gestión de socios");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		jtfNombre = new JTextField();
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.gridwidth = 6;
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 1;
		gbc_jtfNombre.gridy = 2;
		add(jtfNombre, gbc_jtfNombre);
		jtfNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Primer Apellido:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		jtfPApellido = new JTextField();
		GridBagConstraints gbc_jtfPApellido = new GridBagConstraints();
		gbc_jtfPApellido.gridwidth = 6;
		gbc_jtfPApellido.insets = new Insets(0, 0, 5, 0);
		gbc_jtfPApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfPApellido.gridx = 1;
		gbc_jtfPApellido.gridy = 3;
		add(jtfPApellido, gbc_jtfPApellido);
		jtfPApellido.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Segundo Apellido:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		add(lblNewLabel_3, gbc_lblNewLabel_3);

		jtfSApellido = new JTextField();
		GridBagConstraints gbc_jtfSApellido = new GridBagConstraints();
		gbc_jtfSApellido.gridwidth = 6;
		gbc_jtfSApellido.insets = new Insets(0, 0, 5, 0);
		gbc_jtfSApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfSApellido.gridx = 1;
		gbc_jtfSApellido.gridy = 4;
		add(jtfSApellido, gbc_jtfSApellido);
		jtfSApellido.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 5;
		add(lblNewLabel_4, gbc_lblNewLabel_4);

		jtfFecha = new JFormattedTextField();
		GridBagConstraints gbc_jtfFecha = new GridBagConstraints();
		gbc_jtfFecha.gridwidth = 6;
		gbc_jtfFecha.insets = new Insets(0, 0, 5, 0);
		gbc_jtfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFecha.gridx = 1;
		gbc_jtfFecha.gridy = 5;
		add(jtfFecha, gbc_jtfFecha);

		JLabel lblNewLabel_5 = new JLabel("Antigüedad(años)");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		add(lblNewLabel_5, gbc_lblNewLabel_5);

		slider = new JSlider();
		slider.setMaximum(200);
		GridBagConstraints gbc_4 = new GridBagConstraints();
		gbc_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_4.gridwidth = 5;
		gbc_4.insets = new Insets(0, 0, 5, 5);
		gbc_4.gridx = 1;
		gbc_4.gridy = 6;
		add(slider, gbc_4);

		lblantiguedad = new JLabel("HOla");
		GridBagConstraints gbc_lblantiguedad = new GridBagConstraints();
		gbc_lblantiguedad.insets = new Insets(0, 0, 5, 0);
		gbc_lblantiguedad.gridx = 6;
		gbc_lblantiguedad.gridy = 6;
		add(lblantiguedad, gbc_lblantiguedad);

		ckActivo = new JCheckBox("");
		GridBagConstraints gbc_ckActivo = new GridBagConstraints();
		gbc_ckActivo.insets = new Insets(0, 0, 5, 5);
		gbc_ckActivo.gridx = 1;
		gbc_ckActivo.gridy = 7;
		add(ckActivo, gbc_ckActivo);

		JLabel lblNewLabel_7 = new JLabel("Socio Activo");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 7;
		add(lblNewLabel_7, gbc_lblNewLabel_7);

		JLabel lblNewLabel_6 = new JLabel("Equipo");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 8;
		add(lblNewLabel_6, gbc_lblNewLabel_6);

		jcbEquipo = new JComboBox();
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.gridwidth = 6;
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 1;
		gbc_jcbEquipo.gridy = 8;
		add(jcbEquipo, gbc_jcbEquipo);
		
		//addControlCustomizableBehaviours();
		showEntityOnPanel((Socio) ControladorSocio.getInstance().findFirst());
		cargaTodosEquipos();

	}

	private void addControlCustomizableBehaviours() {

		// JFormattedTextfield para la fecha de firma, si el valor no es correcto se
		// pone fondo en rojo
		this.jtfFecha.setFormatterFactory(new AbstractFormatterFactory() {
			@Override
			public AbstractFormatter getFormatter(JFormattedTextField tf) {
				return new JFormattedTextField.AbstractFormatter() {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					@Override
					public String valueToString(Object value) throws ParseException {
						if (value != null && value instanceof Date) {
							jtfFecha.setBackground(Color.WHITE);
							return sdf.format(((Date) value));
						}
						return "";
					}

					@Override
					public Object stringToValue(String text) throws ParseException {
						try {
						return sdf.parse(text);
						} catch (Exception e) {
							jtfFecha.setBackground(Color.RED);
							JOptionPane.showMessageDialog(null, "Error en la fecha");
							return null;
						}
					}
				};
			}
		});
	}
	private void showEntityOnPanel (Socio socio) {
		
		if (socio != null) {
			this.currentEntity = socio;			
			this.jtfNombre.setText(this.currentEntity.getNombre());
			this.jtfPApellido.setText(this.currentEntity.getApellido1());
			this.jtfSApellido.setText(this.currentEntity.getApellido2());
			this.jtfFecha.setText(GuiUtils.getFormattedStringFromDate("dd/MM/yyyy", this.currentEntity.getFechaNacimiento()));
			
			
			this.slider.setMaximum(200);
			this.slider.setMinimum(0);
			this.slider.setValue((int) this.currentEntity.getAntiguedadAnios());
			this.showSliderBalanceDescriptor();
			
			this.ckActivo.setSelected(this.currentEntity.getActivo());
			
			for(int i = 0; i < this.jcbEquipo.getItemCount(); i++) {
				if (socio.getIdEquipo() == this.jcbEquipo.getItemAt(i).getId()) {
					this.jcbEquipo.setSelectedIndex(i);
				}
			}

		}
		
	}
	private void showSliderBalanceDescriptor () {
		this.lblantiguedad.setText(this.slider.getValue() + " de " + this.slider.getMaximum());
	}
	private void cargaTodosEquipos() {
		List<Equipo> equipos = (List<Equipo>) ControladorEquipo
				.getInstance().findAll();
		for (Equipo u : equipos) {
			this.jcbEquipo.addItem(u);
		}
	}
	private void newEntity () {
		this.currentEntity = new Socio();
		this.currentEntity.setId(0);

		
		showEntityOnPanel(currentEntity);
	}
	private void saveEntity() {
		this.currentEntity.setNombre(this.jtfNombre.getText());
		this.currentEntity.setApellido1(this.jtfPApellido.getText());
		this.currentEntity.setApellido2(this.jtfSApellido.getText());
		this.currentEntity.setFechaNacimiento(GuiUtils.getDateFromFormattedString("dd/MM/yyyy", this.jtfFecha.getText()));
		this.currentEntity.setAntiguedadAnios(this.slider.getValue());
		
		try {
			ControladorSocio.getInstance().save(currentEntity);
			JOptionPane.showMessageDialog(null, "Socio guardado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "NO se ha guardado el socio. ERROR");
		}
	}
	private void deleteEntity () {
		try {
			String respuestas[] = new String[] {"Sí", "No"};
			int opcionElegida = JOptionPane.showOptionDialog(
					null, 
					"¿Realmente desea eliminar el registro?", 
					"Eliminación de fabricante", 
			        JOptionPane.DEFAULT_OPTION, 
			        JOptionPane.WARNING_MESSAGE, 
			        null, respuestas, 
			        respuestas[1]);
		    
			if(opcionElegida == 0) {
				ControladorSocio.getInstance().remove(currentEntity);
		    	  
		    	  // Decido qué registro voy a mostrar en pantalla.
		    	  // Voy a comprobar si existe un anterior, si existe lo muestro
		    	  // Si no existe anterior compruebo si existe siguiente, 
		    	  // si existe lo muestro. En caso contrario, no quedan registros
		    	  // así que muestro en blanco la pantalla
		    	  this.currentEntity = ControladorSocio.getInstance().findPrevious(this.currentEntity.getId());
		    	  if (this.currentEntity != null) { // Existe un anterior, lo muestro
		    		  showEntityOnPanel(currentEntity);
		    	  }
		    	  else {
		    		  this.currentEntity = ControladorSocio.getInstance().findNext(this.currentEntity.getId());
			    	  if (this.currentEntity != null) { // Existe el siguiente, lo muestro
			    		  showEntityOnPanel(currentEntity);
			    	  }
		    		  else { // No quedan registros en la tabla
		    			  newEntity();
		    		  }
		    	  }
		      }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}


}
