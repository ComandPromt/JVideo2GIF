package com.main;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.layout.MaterialPanelLayout;

public class Nuevo extends JPanel {

	public Nuevo() {
		setBackground(Color.PINK);

		setLayout(new GridLayout(0, 1, 0, 0));

		List<JComponent> componentes = new ArrayList<>();

		componentes.add(new JLabel("X:"));
		componentes.add(new JLabel("Y:"));
		componentes.add(new JLabel("Width:"));
		componentes.add(new JLabel("Height:"));

		MaterialPanelLayout panel = new MaterialPanelLayout(componentes.toArray(new JComponent[0]), null, false);

		componentes.clear();
		JLabel test = new JLabel("aa");
		test.setBackground(Color.BLUE);
		test.setSize(400, 400);
		componentes.add(test);

		componentes.add(panel);

		JPanel combinedPanel = new JPanel();

		List<Integer> list = new ArrayList<>();
		list.add(90);
		list.add(10);
		combinedPanel = new MaterialPanelLayout(componentes, list, true);

		add(combinedPanel);

	}
}
