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
import com.spinner.simple.Spinner;

public class Fps extends JPanel {

	private Spinner chckbxNewCheckBox;

	public Spinner getChckbxNewCheckBox() {

		return chckbxNewCheckBox;

	}

	public Fps() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label = new JLabel();

		label.setIcon(new ImageIcon(getClass().getResource("/images/fps.png")));

		label.setHorizontalAlignment(SwingConstants.CENTER);

		chckbxNewCheckBox = new Spinner("FPS");

		chckbxNewCheckBox.setMinValor(1);

		chckbxNewCheckBox.setValor(25);

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
