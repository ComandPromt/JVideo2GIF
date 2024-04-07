package com.views.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

import com.spinner.simple.Spinner;
import com.textField.text.NTextField;

import net.java.dev.colorchooser.demo.CopyColor;

public class Watermark extends JPanel {

	public Watermark() throws IOException {

		setLayout(new GridLayout(0, 3, 0, 0));

		NTextField lblNewLabel = new NTextField("Text", "");

		lblNewLabel.setSumarAlto(15);

		add(lblNewLabel);

		CopyColor lblNewLabel_1 = new CopyColor(Color.BLACK, true);

		add(lblNewLabel_1);

		Spinner lblNewLabel_3 = new Spinner("TextSize");

		lblNewLabel_3.setHeaderFont(new Font("Dialog", Font.PLAIN, 15));

		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 15));

		lblNewLabel_3.setValorMaximo(false);

		lblNewLabel_3.setMinValor(8);

		add(lblNewLabel_3);

	}

}
