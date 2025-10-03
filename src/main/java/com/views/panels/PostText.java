package com.views.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.checkbox.CheckBoxCustom;
import com.layout.MaterialPanelLayout;

public class PostText extends JPanel {

	private CheckBoxCustom watermark;

	private PosicionText textPosition;

	public boolean watermark() {

		try {

			return watermark.isSelected();

		}

		catch (Exception e) {

		}

		return false;

	}

	public int getPos() {

		try {

			return textPosition.getPos();

		}

		catch (Exception e) {

		}

		return 0;

	}

	public PostText(String texto, boolean check, boolean mostrarText) {

		textPosition = new PosicionText();

		setLayout(new GridLayout(0, 1, 0, 0));

		watermark = new CheckBoxCustom();

		setBackground(Color.WHITE);

		watermark.setFont(new Font("Arial", Font.PLAIN, 20));

		watermark.setText("Watermark");

		watermark.sumarAlto(3);

		watermark.setLeft(390);

		ArrayList<JComponent> lista = new ArrayList<>();

		if (check) {

			JPanel a = new JPanel();

			a.setBackground(Color.WHITE);

			a.setLayout(new GridLayout());

			a.add(watermark);

			lista.add(a);

		}
		if (mostrarText) {
			JLabel text = new JLabel(texto);

			text.setBackground(Color.WHITE);

			text.setFont(new Font("Arial", Font.PLAIN, 20));

			lista.add(new JSeparator());

			lista.add(text);
		}
		lista.add(textPosition);

		ArrayList<Integer> porcentajes = new ArrayList<>();

		porcentajes.add(20);

		porcentajes.add(5);

		porcentajes.add(20);

		porcentajes.add(55);

		MaterialPanelLayout panel = new MaterialPanelLayout(lista, porcentajes, true);

		panel.setBackground(Color.WHITE);

		add(panel);

	}

}
