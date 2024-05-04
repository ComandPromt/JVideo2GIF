package com.views.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.checkbox.CheckBoxCustom;
import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.views.Config;

public class Historial extends JPanel {

	public Historial() {

		ComboBoxSuggestion comboBox = new ComboBoxSuggestion();

		comboBox.addItem("Nuevo 11111111");

		comboBox.addItem("a");

		comboBox.addItem("bbb");

		add(comboBox);

		JPanel items = new JPanel();

		NButton agrega = new NButton("");

		agrega.setBackground(Color.WHITE);

		agrega.setIcon(new ImageIcon(Config.class.getResource("/images/add.png")));

		NButton edita = new NButton("");

		edita.setIcon(new ImageIcon(Config.class.getResource("/images/edit.png")));

		NButton elimina = new NButton("");

		elimina.setIcon(new ImageIcon(Config.class.getResource("/images/delete.png")));

		edita.setBackground(Color.WHITE);

		elimina.setBackground(Color.WHITE);
		CheckBoxCustom usarPerfil = new CheckBoxCustom("Activate");

		usarPerfil.setFont(new Font("SansSerif", Font.PLAIN, 15));

		items.add(usarPerfil);

		items.add(agrega);

		items.add(edita);

		items.add(elimina);

		add(items);

	}

}
