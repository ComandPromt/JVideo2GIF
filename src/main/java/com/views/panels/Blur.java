package com.views.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.layout.MaterialPanelLayout;
import com.spinner.simple.Spinner;

public class Blur extends JPanel {

	private Spinner textArea;

	private Spinner btnNewButton;

	private Spinner datoX;

	private Spinner datoY;

	private Spinner width;

	private Spinner height;

	public Spinner getAncho() {

		return width;

	}

	public Spinner getAlto() {

		return height;

	}

	public Spinner getBrillo() {

		return textArea;

	}

	public Spinner getBlur() {

		return btnNewButton;

	}

	public Spinner getDatoX() {

		return datoX;

	}

	public Spinner getDatoY() {

		return datoY;

	}

	public Blur() {

		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		add(panel);

		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel brillo = new JLabel("Brightness");

		brillo.setIcon(new ImageIcon(getClass().getResource("/images/brillo.png")));

		brillo.setFont(new Font("Dialog", Font.PLAIN, 17));

		JLabel desenfoque = new JLabel("Blur");

		desenfoque.setIcon(new ImageIcon(getClass().getResource("/images/blur.png")));

		desenfoque.setFont(new Font("Dialog", Font.PLAIN, 17));

		textArea = new Spinner();

		textArea.setValorMaximo(false);

		textArea.setFont(new Font("Dialog", Font.PLAIN, 15));

		textArea.setLabelText("Brightness");

		textArea.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		textArea.sumarAlto(17);

		textArea.setCenterText(true);

		btnNewButton = new Spinner();

		btnNewButton.setLabelText("Blur");

		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 14));

		btnNewButton.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		btnNewButton.setCenterText(true);

		btnNewButton.sumarAlto(17);

		btnNewButton.setValorMaximo(false);

		width = new Spinner("Width");

		width.setFont(new Font("Dialog", Font.PLAIN, 17));

		JLabel horizontal = new JLabel("Width");

		horizontal.setIcon(new ImageIcon(getClass().getResource("/images/horizontal.png")));

		horizontal.setFont(new Font("Dialog", Font.PLAIN, 17));

		ArrayList<JComponent> lista = new ArrayList<>();

		lista.add(brillo);

		lista.add(textArea);

		lista.add(desenfoque);

		lista.add(btnNewButton);

		lista.add(horizontal);

		lista.add(width);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(17);

		porcentajes.add(16);

		porcentajes.add(16);

		porcentajes.add(16);

		porcentajes.add(16);

		porcentajes.add(16);

		MaterialPanelLayout panel_1 = new MaterialPanelLayout(lista, porcentajes, true);

		panel_1.setBackground(Color.WHITE);

		panel.add(panel_1);

		JLabel vertical = new JLabel("Height");

		vertical.setIcon(new ImageIcon(getClass().getResource("/images/vertical.png")));

		vertical.setFont(new Font("Dialog", Font.PLAIN, 17));

		JLabel equis = new JLabel("X");

		equis.setIcon(new ImageIcon(getClass().getResource("/images/vertical.png")));

		equis.setFont(new Font("Dialog", Font.PLAIN, 17));

		JLabel ye = new JLabel("Y");

		ye.setIcon(new ImageIcon(getClass().getResource("/images/vertical.png")));

		ye.setFont(new Font("Dialog", Font.PLAIN, 17));

		datoX = new Spinner();

		datoX.setValorMaximo(false);

		datoX.setCenterText(true);

		datoX.setLabelText("X");

		datoX.setFont(new Font("Dialog", Font.PLAIN, 15));

		datoX.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		datoX.sumarAlto(17);

		datoY = new Spinner("Y");

		datoY.setValorMaximo(false);

		datoY.setCenterText(true);

		datoY.setFont(new Font("Dialog", Font.PLAIN, 15));

		datoY.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		datoY.sumarAlto(17);

		ArrayList<JComponent> lista2 = new ArrayList<>();

		lista2.add(equis);

		lista2.add(datoX);

		lista2.add(ye);

		lista2.add(datoY);

		height = new Spinner("Height");

		height.setFont(new Font("Dialog", Font.PLAIN, 17));

		lista2.add(vertical);

		lista2.add(height);

		MaterialPanelLayout panel_2 = new MaterialPanelLayout(lista2, porcentajes, true);

		panel_2.setBackground(Color.WHITE);

		panel.add(panel_2);

	}

}
