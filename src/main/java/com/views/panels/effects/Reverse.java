package com.views.panels.effects;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.checkbox.CheckBoxCustom;

public class Reverse extends JPanel {

	private CheckBoxCustom chckbxNewCheckBox;

	public CheckBoxCustom getChckbxNewCheckBox() {

		return chckbxNewCheckBox;

	}

	public Reverse() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(1, 0, 0, 0));

		JLabel label = new JLabel();

		label.setIcon(new ImageIcon(getClass().getResource("/images/reverse.png")));

		label.setHorizontalAlignment(SwingConstants.CENTER);

		chckbxNewCheckBox = new CheckBoxCustom("Reverse");

		chckbxNewCheckBox.sumarAlto(-13);

		add(label);

		add(chckbxNewCheckBox);

	}

}
