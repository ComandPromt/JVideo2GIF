package com.views.panels.effects;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.layout.MaterialPanelLayout;
import com.spinner.decimal.DecimalSpinner;

public class Brillo extends JPanel {

	private DecimalSpinner chckbxNewCheckBox;

	public DecimalSpinner getChckbxNewCheckBox() {

		return chckbxNewCheckBox;

	}

	public Brillo() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label = new JLabel();

		label.setIcon(new ImageIcon(getClass().getResource("/images/brillo.png")));

		label.setHorizontalAlignment(SwingConstants.CENTER);

		chckbxNewCheckBox = new DecimalSpinner("Brightness");

		chckbxNewCheckBox.sumarAlto(20);

		ArrayList<JComponent> lista = new ArrayList<>();

		lista.add(label);

		lista.add(chckbxNewCheckBox);

		ArrayList<Integer> porcentajes = new ArrayList();

		porcentajes.add(20);

		porcentajes.add(80);

		MaterialPanelLayout panel = new MaterialPanelLayout(lista, porcentajes, false);

		panel.setBackground(Color.WHITE);

		add(panel);

	}

}
