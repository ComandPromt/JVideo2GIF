package com.views.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.layout.MaterialPanelLayout;
import com.spinner.simple.Spinner;

public class Blur extends JPanel {

	public Blur() {

		setBorder(new LineBorder(new Color(0, 0, 0)));

		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		add(panel);

		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel brillo = new JLabel("Brillo");

		brillo.setIcon(new ImageIcon(getClass().getResource("/images/brillo.png")));

		brillo.setFont(new Font("Dialog", Font.PLAIN, 17));

		JLabel desenfoque = new JLabel("Desenfoque");

		desenfoque.setIcon(new ImageIcon(getClass().getResource("/images/blur.png")));

		desenfoque.setFont(new Font("Dialog", Font.PLAIN, 17));

		Spinner textArea = new Spinner();

		textArea.setValorMaximo(false);

		textArea.setFont(new Font("Dialog", Font.PLAIN, 15));

		textArea.setLabelText("Brillo");

		textArea.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		textArea.sumarAlto(17);

		textArea.setCenterText(true);

		Spinner btnNewButton = new Spinner();

		btnNewButton.setLabelText("Desenfoque");

		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 14));

		btnNewButton.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		btnNewButton.setCenterText(true);

		btnNewButton.sumarAlto(17);

		btnNewButton.setValorMaximo(false);

		ArrayList<JComponent> lista = new ArrayList<>();

		lista.add(brillo);

		lista.add(textArea);

		lista.add(desenfoque);

		lista.add(btnNewButton);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(25);

		porcentajes.add(25);

		porcentajes.add(25);

		porcentajes.add(25);

		MaterialPanelLayout panel_1 = new MaterialPanelLayout(lista, porcentajes, true);

		panel_1.setBackground(Color.WHITE);

		panel.add(panel_1);

		JLabel horizontal = new JLabel("Horizontal");

		horizontal.setIcon(new ImageIcon(getClass().getResource("/images/horizontal.png")));

		horizontal.setFont(new Font("Dialog", Font.PLAIN, 17));

		JLabel vertical = new JLabel("Vertical");

		vertical.setIcon(new ImageIcon(getClass().getResource("/images/vertical.png")));

		vertical.setFont(new Font("Dialog", Font.PLAIN, 17));

		Spinner shorizontal = new Spinner();

		shorizontal.setValorMaximo(false);

		shorizontal.setCenterText(true);

		shorizontal.setLabelText("Horizontal");

		shorizontal.setFont(new Font("Dialog", Font.PLAIN, 15));

		shorizontal.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		shorizontal.sumarAlto(17);

		Spinner svertical = new Spinner("Vertical");

		svertical.setValorMaximo(false);

		svertical.setCenterText(true);

		svertical.setFont(new Font("Dialog", Font.PLAIN, 15));

		svertical.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		svertical.sumarAlto(17);

		ArrayList<JComponent> lista2 = new ArrayList<>();

		lista2.add(horizontal);

		lista2.add(shorizontal);

		lista2.add(vertical);

		lista2.add(svertical);

		MaterialPanelLayout panel_2 = new MaterialPanelLayout(lista2, porcentajes, true);

		panel_2.setBackground(Color.WHITE);

		panel.add(panel_2);

	}

}
