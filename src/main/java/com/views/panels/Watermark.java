package com.views.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.fontPicker.FontPicker;
import com.materialfilechooser.ChooserWithInput;
import com.textField.text.NTextField;

import net.java.dev.colorchooser.demo.CopyColor;

public class Watermark extends JPanel {

	private PostText watermark;

	private FontPicker fuente;

	private CopyColor color;

	private NTextField text;

	private ChooserWithInput archivo;

	public boolean getWatermark() {

		return watermark.watermark();

	}

//	public void setWatermark(CheckBoxCustom watermark) {
//
//		this.watermark = watermark;
//
//	}

	public FontPicker getFuente() {

		return fuente;

	}

	public void setFuente(FontPicker fuente) {

		this.fuente = fuente;

	}

	public CopyColor getColor() {

		return color;

	}

	public void setColor(CopyColor color) {

		this.color = color;

	}

	public String getText() {

		try {

			return text.getText();

		}

		catch (Exception e) {

			return "";

		}

	}

	public void setText(NTextField text) {

		this.text = text;

	}

	public String getArchivo() {

		return archivo.getFile();

	}

	public void setArchivo(ChooserWithInput archivo) {

		this.archivo = archivo;

	}

	public int getPos() {

		try {

			return watermark.getPos();

		} catch (Exception e) {

		}

		return 0;

	}

	public Watermark() throws IOException {

		setLayout(new GridLayout(5, 0, 0, 0));

		watermark = new PostText();

		JPanel panel1 = new JPanel();

		panel1.setLayout(new GridLayout(0, 1, 0, 0));

		panel1.add(watermark);

		add(panel1);

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(0, 1, 0, 0));

		fuente = new FontPicker();

		fuente.setBackground(Color.WHITE);

		panel.add(fuente);

		add(panel);

		JPanel panel_1 = new JPanel();

		add(panel_1);

		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		color = new CopyColor(Color.BLACK, false);

		panel_1.add(color);

		color.setLayout(new GridLayout(1, 0, 0, 0));

		text = new NTextField();

		text.setLeft(340);

		text.setHeaderText("Text");

		text.setHorizontalAlignment(SwingConstants.CENTER);

		add(text);

		archivo = new ChooserWithInput("Select A Image", "", false, new String[] { "images" }, false, true);

		String[] filtro = new String[3];

		filtro[0] = ".jpg";

		filtro[1] = ".png";

		filtro[2] = ".gif";

		archivo.setHeader("Select a image");

		archivo.setHorizontalAlignment(SwingConstants.CENTER);

		add(archivo);

	}

}
