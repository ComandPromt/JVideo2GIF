package com.views.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.views.panels.effects.Hq;

@SuppressWarnings("serial")
public class Quality extends JPanel {

	private ComboBoxSuggestion<String> comboBox;

	private Hq panel_3;

	public Hq getHq() {

		return panel_3;

	}

	public String getStatsMode() {

		return comboBox.getSelectedItem().toString().toLowerCase();

	}

	public Quality() {

		comboBox = new ComboBoxSuggestion<>();

		comboBox.setBounds(40, 244, 240, 67);

		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));

		setBackground(Color.WHITE);

		setLayout(new GridLayout());

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		add(panel);

		panel.setLayout(new GridLayout());

		panel_3 = new Hq();

		panel_3.getChckbxNewCheckBox().setPosition(new Point(25, 285));

		panel_3.setBackground(Color.WHITE);

		panel.add(panel_3);

		panel_3.setLayout(new GridLayout());

		JPanel panel_1 = new JPanel();

		panel_1.setBackground(Color.WHITE);

		add(panel_1);

		panel_1.setLayout(new GridLayout());

		JPanel panel_5 = new JPanel();

		panel_5.setBackground(Color.WHITE);
		panel_1.add(panel_5);

		comboBox.addItem("Full");

		comboBox.addItem("Diff");

		comboBox.addItem("None");
		panel_5.setLayout(null);

		panel_5.add(comboBox);

		JLabel lblNewLabel = new JLabel("Stats Mode");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(40, 115, 204, 67);
		panel_5.add(lblNewLabel);

	}
}
