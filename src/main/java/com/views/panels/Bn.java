package com.views.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.layout.MaterialPanelLayout;

public class Bn extends JPanel {

	public Bn() {
		setBackground(Color.WHITE);

		setLayout(new GridLayout(0, 1, 0, 0));

		JCheckBox chckbxNewCheckBox = new JCheckBox("BN");

		chckbxNewCheckBox.setBackground(Color.WHITE);

		ComboBoxSuggestion<String> comboBox = new ComboBoxSuggestion();

		comboBox.addItem("Todo el gif");

		comboBox.addItem("Solo el texo");

		comboBox.addItem("Solo la marca de agua");

		ArrayList<JComponent> lista = new ArrayList<>();

		lista.add(chckbxNewCheckBox);

		lista.add(comboBox);

		ArrayList<Integer> porcentajes = new ArrayList();

		porcentajes.add(35);

		porcentajes.add(65);

		MaterialPanelLayout panel = new MaterialPanelLayout(lista, porcentajes, true);

		panel.setBackground(Color.WHITE);

		add(panel);

	}
}
