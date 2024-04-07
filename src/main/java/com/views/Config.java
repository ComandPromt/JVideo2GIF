package com.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;

import com.buttons.round.NButton;
import com.checkbox.CheckBoxCustom;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.fontPicker.FontPicker;
import com.layout.MaterialPanelLayout;
import com.main.VideoViewer;
import com.materialfilechooser.ChooserWithInput;
import com.spinner.simple.Spinner;
import com.textField.text.NTextField;
import com.textField.text.NewTextField;
import com.views.panels.Blur;
import com.views.panels.Watermark;

@SuppressWarnings("all")

public class Config extends javax.swing.JFrame {

	public VideoViewer ventanaCrop;

	public DisplayFrame frame;

	public Config(VideoViewer ventanaCrop, DisplayFrame frame) throws IOException {

		this.ventanaCrop = ventanaCrop;

		this.frame = frame;

		setIconImage(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/images/settings.png")));

		getContentPane().setBackground(Color.WHITE);

		setTitle("JVideoPlayer  - Config");

		initComponents();

		setVisible(true);

		try {

			File archivo = new File("config.dat");

			if (archivo.exists()) {

				ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream("config.dat"));

				DisplayFrame.config = leyendoFichero.readObject().toString().split(",");

				leyendoFichero.close();

				if (DisplayFrame.config[0] != null) {

					// volumen.setValor(Integer.parseInt(DisplayFrame.config[0]));

				}

				if (DisplayFrame.config[1] != null) {

					// ruta.setText(DisplayFrame.config[1]);

				}

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void abrirCarpeta(String ruta) throws IOException {

		if (ruta != null && !ruta.equals("") && !ruta.isEmpty()) {

			try {

				if (System.getProperty("os.name").contains("Linux")) {

					Runtime.getRuntime().exec("xdg-open " + ruta);

				}

				else if (System.getProperty("os.name").contains("Mac")) {

					Runtime.getRuntime().exec("open " + ruta);

				}

				else {

					Runtime.getRuntime().exec("cmd /c C:\\Windows\\explorer.exe " + "\"" + ruta + "\"");

				}

			}

			catch (IOException e) {

			}
		}

	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();

		ComboBoxSuggestion comboBox = new ComboBoxSuggestion();

		comboBox.addItem("Nuevo");

		comboBox.addItem("a");

		comboBox.addItem("bbb");

		NButton agrega = new NButton("");

		agrega.setBackground(Color.WHITE);

		agrega.setIcon(new ImageIcon(Config.class.getResource("/images/add.png")));

		NButton edita = new NButton("");

		edita.setIcon(new ImageIcon(Config.class.getResource("/images/edit.png")));

		NButton elimina = new NButton("");

		elimina.setIcon(new ImageIcon(Config.class.getResource("/images/delete.png")));

		edita.setBackground(Color.WHITE);

		elimina.setBackground(Color.WHITE);

		JComponent[] componentes = new JComponent[4];

		componentes[0] = comboBox;

		componentes[1] = agrega;

		componentes[2] = edita;

		componentes[3] = elimina;

		List<Integer> porcentajes = new LinkedList<>();

		porcentajes.add(85);

		porcentajes.add(5);

		porcentajes.add(5);

		porcentajes.add(5);

		JPanel panel = new MaterialPanelLayout(componentes, porcentajes, false);

		panel.setBackground(Color.WHITE);

		panel.setBounds(10, 10, 459, 62);

		panel.add(agrega);

		panel.add(edita);

		panel.add(elimina);

		getContentPane().add(panel);

		Blur textoBlur = new Blur();

		textoBlur.setBounds(10, 148, 373, 196);

		getContentPane().add(textoBlur);

		CheckBoxCustom panel_3 = new CheckBoxCustom();

		panel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));

		panel_3.setText("BN");

		panel_3.setBounds(389, 177, 43, 56);

		getContentPane().add(panel_3);

		ComboBoxSuggestion<String> panel_4 = new ComboBoxSuggestion();

		panel_4.addItem("Todo el gif");

		panel_4.addItem("Solo el texto");

		panel_4.addItem("Solo la marca de agua");

		panel_4.setBounds(438, 178, 269, 55);

		getContentPane().add(panel_4);

		JPanel panel_2 = new JPanel();

		panel_2.setBorder(null);

		panel_2.setBackground(Color.WHITE);

		panel_2.setBounds(10, 354, 373, 61);

		getContentPane().add(panel_2);

		panel_2.setLayout(null);

		JPanel panel_5 = new JPanel();

		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_5.setBackground(Color.WHITE);

		panel_5.setBounds(0, 0, 373, 61);

		panel_2.add(panel_5);

		panel_5.setLayout(null);

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setIcon(new ImageIcon(Config.class.getResource("/images/crop.png")));

		lblNewLabel.setBounds(1, 1, 60, 60);

		panel_5.add(lblNewLabel);

		CheckBoxCustom chckbxNewCheckBox = new CheckBoxCustom("Crop");

		chckbxNewCheckBox.setBounds(67, 1, 91, 60);

		panel_5.add(chckbxNewCheckBox);

		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 25));

		NButton btnNewButton = new NButton("New button");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					if (!(ventanaCrop instanceof VideoViewer)) {

						ventanaCrop = new VideoViewer(frame);

					}

					ventanaCrop.setVisible(true);

				}

				catch (Exception e1) {

				}

			}

		});

		btnNewButton.setBounds(151, 1, 222, 60);

		panel_5.add(btnNewButton);

		btnNewButton.setThickness(1);

		btnNewButton.setBorderPaint(true);

		btnNewButton.setBorderColor(new Color(255, 0, 255));

		btnNewButton.setBackground(Color.WHITE);

		btnNewButton.setText("Crop");

		JPanel panel_6 = new JPanel();

		panel_6.setBackground(Color.WHITE);

		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_6.setBounds(10, 425, 373, 50);

		getContentPane().add(panel_6);

		panel_6.setLayout(new GridLayout(0, 3, 0, 0));

		NewTextField codec;

		codec = new NewTextField();

		codec.setHorizontalAlignment(SwingConstants.CENTER);

		codec.setText("libx264");

		codec.setFont(new Font("Dialog", Font.PLAIN, 15));

		codec.setLabel("Codec");

		codec.setColumns(10);

		CheckBoxCustom chckbxNewCheckBox_1 = new CheckBoxCustom("Gifsicle");

		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		Spinner textArea = new Spinner("CRF");

		textArea.setFont(new Font("Dialog", Font.PLAIN, 15));

		textArea.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		textArea.setMaxValor(51);

		textArea.setCenterText(true);

		textArea.sumarAlto(17);

		panel_6.add(codec);

		panel_6.add(chckbxNewCheckBox_1);

		panel_6.add(textArea);

		JLabel panel_7 = new JLabel();

		panel_7.setHorizontalAlignment(SwingConstants.CENTER);

		panel_7.setIcon(new ImageIcon(Config.class.getResource("/images/rotacion.png")));

		panel_7.setBounds(393, 283, 57, 61);

		getContentPane().add(panel_7);

		Spinner panel_8 = new Spinner("Rotate");

		panel_8.setCenterText(true);

		panel_8.sumarAlto(15);

		panel_8.setMaxValor(360);

		panel_8.setFont(new Font("Dialog", Font.PLAIN, 15));

		panel_8.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		panel_8.setBounds(438, 289, 269, 60);

		getContentPane().add(panel_8);

		CheckBoxCustom chckbxNewCheckBox_2 = new CheckBoxCustom("Reverse");

		chckbxNewCheckBox_2.setFont(new Font("Dialog", Font.PLAIN, 15));

		chckbxNewCheckBox_2.setBounds(438, 234, 269, 55);

		getContentPane().add(chckbxNewCheckBox_2);

		Spinner panel_9 = new Spinner("Velocidad");

		panel_9.setLabelText("Speed");

		panel_9.setFont(new Font("Dialog", Font.PLAIN, 15));

		panel_9.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		panel_9.setValorMaximo(false);

		panel_9.setCenterText(true);

		panel_9.sumarAlto(15);

		panel_9.setBounds(438, 358, 269, 56);

		getContentPane().add(panel_9);

		JLabel lblNewLabel_1 = new JLabel("");

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_1.setIcon(new ImageIcon(Config.class.getResource("/images/velocidad.png")));

		lblNewLabel_1.setBounds(393, 359, 53, 56);

		getContentPane().add(lblNewLabel_1);

		Spinner panel_10 = new Spinner("Colors");

		panel_10.setFont(new Font("Dialog", Font.PLAIN, 15));

		panel_10.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		panel_10.setValorMaximo(false);

		panel_10.setLabelText("Nº Colors");

		panel_10.setCenterText(true);

		panel_10.sumarAlto(17);

		panel_10.setBounds(438, 420, 269, 55);

		getContentPane().add(panel_10);

		Watermark panel_12 = new Watermark();

		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_12.setBounds(10, 487, 697, 50);

		getContentPane().add(panel_12);

		JPanel panel_11 = new JPanel();

		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_11.setBounds(10, 547, 697, 135);

		getContentPane().add(panel_11);

		panel_11.setLayout(new GridLayout(0, 1, 0, 0));

		FontPicker btnNewButton_2 = new FontPicker();

		btnNewButton_2.setTextSize(23);

		btnNewButton_2.setPhrase("ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789");

		btnNewButton_2.setBackground(Color.WHITE);

		panel_11.add(btnNewButton_2);

		ChooserWithInput lblNewLabel_2 = new ChooserWithInput("New label", "", false, true);

		lblNewLabel_2.setText("");

		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));

		componentes = new JComponent[2];

		JLabel iconoCubo = new JLabel("");

		iconoCubo.setIcon(new ImageIcon(Config.class.getResource("/images/colores.png")));

		iconoCubo.setHorizontalAlignment(SwingConstants.CENTER);

		iconoCubo.setBackground(Color.WHITE);

		componentes[0] = iconoCubo;

		componentes[1] = lblNewLabel_2;

		porcentajes.clear();

		porcentajes.add(5);

		porcentajes.add(95);

		MaterialPanelLayout imagen = new MaterialPanelLayout(componentes, porcentajes, false);

		imagen.setBorder(new LineBorder(new Color(0, 0, 0)));

		imagen.setBackground(Color.WHITE);

		panel_11.add(imagen);

		JLabel iconoSalida = new JLabel("");

		iconoSalida.setIcon(new ImageIcon(Config.class.getResource("/images/save.png")));

		iconoSalida.setBackground(Color.WHITE);

		componentes = new JComponent[2];

		componentes[0] = iconoSalida;

		componentes[1] = new ChooserWithInput("A", "", true, true);

		porcentajes.clear();

		porcentajes.add(12);

		porcentajes.add(88);

		JPanel salida = new MaterialPanelLayout(componentes, porcentajes, false);

		salida.setBorder(new LineBorder(new Color(0, 0, 0)));

		salida.setBackground(Color.WHITE);

		JPanel panel_13 = new JPanel();

		panel_13.setBounds(438, 82, 269, 84);

		getContentPane().add(panel_13);

		panel_13.setLayout(new GridLayout(0, 1, 0, 0));

		panel_13.add(salida);

		NTextField panel_14 = new NTextField("Output extension", "");

		panel_14.setHorizontalAlignment(SwingConstants.CENTER);

		panel_14.setSumarAlto(15);

		panel_14.setText("gif");

		panel_14.setBounds(474, 10, 233, 62);

		getContentPane().add(panel_14);

		JPanel panel_15 = new JPanel();

		panel_15.setBackground(Color.WHITE);

		panel_15.setBounds(10, 82, 422, 62);

		getContentPane().add(panel_15);

		panel_15.setLayout(new GridLayout(0, 1, 0, 0));

		CheckBoxCustom chckbxNewCheckBox_3 = new CheckBoxCustom("Play file at the end of the conversion");

		panel_15.add(chckbxNewCheckBox_3);

		CheckBoxCustom chckbxNewCheckBox_4 = new CheckBoxCustom("Open folder at the end of the conversion");

		panel_15.add(chckbxNewCheckBox_4);

		JLabel lblNewLabel_1_1 = new JLabel("");

		lblNewLabel_1_1.setIcon(new ImageIcon(Config.class.getResource("/images/colores.png")));

		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel_1_1.setBounds(393, 419, 53, 56);

		getContentPane().add(lblNewLabel_1_1);

		setSize(new Dimension(731, 729));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}
