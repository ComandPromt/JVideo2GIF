package com.views.panels.effects;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.checkbox.CheckBoxCustom;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.layout.MaterialPanelLayout;

public class Bn extends JPanel {

	private CheckBoxCustom chckbxNewCheckBox;

	private ComboBoxSuggestion<String> comboBox;

	public CheckBoxCustom getChckbxNewCheckBox() {

		return chckbxNewCheckBox;

	}

	public ComboBoxSuggestion<String> getComboBox() {

		return comboBox;

	}

	public Bn() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(0, 1, 0, 0));

		chckbxNewCheckBox = new CheckBoxCustom("BN");

		chckbxNewCheckBox.setLeft(50);

		chckbxNewCheckBox.sumarAlto(2);

		comboBox = new ComboBoxSuggestion<>();

		comboBox.addItem("Todo el gif");

		comboBox.addItem("Solo el texo");

		comboBox.addItem("Solo la marca de agua");

		ArrayList<JComponent> lista = new ArrayList<>();

		lista.add(chckbxNewCheckBox);

		lista.add(comboBox);

		ArrayList<Integer> porcentajes = new ArrayList();

		porcentajes.add(20);

		porcentajes.add(80);

		MaterialPanelLayout panel = new MaterialPanelLayout(lista, porcentajes, false);

		panel.setBackground(Color.WHITE);

		add(panel);

	}

}
