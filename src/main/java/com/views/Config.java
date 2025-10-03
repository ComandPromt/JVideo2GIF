package com.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import com.jmenu.horizontal.HorizontalMenu;
import com.main.VideoViewer;
import com.views.panels.Ajustes;
import com.views.panels.Blur;
import com.views.panels.Codec;
import com.views.panels.Effects;
import com.views.panels.Historial;
import com.views.panels.Perfiles;
import com.views.panels.Quality;
import com.views.panels.Watermark;
import com.views.panels.effects.CropPanel;

@SuppressWarnings("all")

public class Config extends javax.swing.JFrame {

	public VideoViewer ventanaCrop;

	public DisplayFrame frame;

	private Effects efectos;

	private Config configuracion;

	private Blur blur;

	private Ajustes ajustes;

	private Watermark watermark;

	private CropPanel crop;

	private Perfiles perfiles;

	private Historial historial;

	private Quality quality;

	private Codec codec;

	public Codec getCodec() {
		return codec;
	}

	public Quality getQuality() {

		return quality;

	}

	public Perfiles getPerfiles() {

		return perfiles;

	}

	public Historial getHistorial() {

		return historial;

	}

	public CropPanel getCrop() {

		return crop;

	}

	public Ajustes getAjustes() {

		return ajustes;

	}

	public Watermark getWatermark() {

		return watermark;

	}

	public Config(VideoViewer ventanaCrop, DisplayFrame frame) throws IOException {

		addWindowListener(new WindowAdapter() {

			@Override

			public void windowClosing(WindowEvent e) {

				DisplayFrame.configuracion = configuracion;

			}

		});

		this.ventanaCrop = ventanaCrop;

		this.frame = frame;

		setIconImage(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/images/settings.png")));

		getContentPane().setBackground(Color.WHITE);

		setTitle("JVideoPlayer  - Config");

		initComponents();

		setResizable(true);

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

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		ArrayList<String> lista = new ArrayList<>();

		lista.add("Blur");

		lista.add("Efecto");

		lista.add("Crop");

		lista.add("Watermark");

		lista.add("Codecs");

		lista.add("HQ");

		lista.add("Historial");

		lista.add("Perfiles");

		lista.add("Output");

		ArrayList<JComponent> component = new ArrayList<>();

		blur = new Blur();

		component.add(blur);

		efectos = new Effects();

		ajustes = new Ajustes();

		watermark = new Watermark();

		crop = new CropPanel(ventanaCrop, frame);

		perfiles = new Perfiles();

		historial = new Historial();

		quality = new Quality();

		codec = new Codec();

		component.add(efectos);

		component.add(crop);

		component.add(watermark);

		component.add(codec);

		component.add(quality);

		component.add(historial);

		component.add(perfiles);

		component.add(ajustes);

		getContentPane().setLayout(null);

		HorizontalMenu panel = new HorizontalMenu(lista, component, 20);

		panel.setBounds(0, 0, 717, 642);

		panel.setFont(new Font("Dialog", Font.PLAIN, 20));

		setLayout(new GridLayout());

		getContentPane().add(panel);

		setSize(new Dimension(850, 730));

		setLocationRelativeTo(null);

		configuracion = this;

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}

	public Blur getBlur() {

		return blur;

	}

	public Effects getEfectos() {

		return efectos;

	}

}
