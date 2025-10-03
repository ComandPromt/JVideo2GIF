package com.views.panels.effects;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.checkbox.CheckBoxCustom;

public class Hq extends JPanel {

	private CheckBoxCustom chckbxNewCheckBox;

	public CheckBoxCustom getChckbxNewCheckBox() {

		return chckbxNewCheckBox;

	}

	public Hq() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(1, 0, 0, 0));

		JLabel label = new JLabel();

		label.setIcon(new ImageIcon(getClass().getResource("/images/hd.png")));

		label.setHorizontalAlignment(SwingConstants.CENTER);

		chckbxNewCheckBox = new CheckBoxCustom("HQ");

		chckbxNewCheckBox.sumarAlto(-13);

		add(label);

		add(chckbxNewCheckBox);

	}

}
