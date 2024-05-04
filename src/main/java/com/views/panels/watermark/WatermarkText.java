package com.views.panels.watermark;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.layout.MaterialPanelLayout;
import com.textField.text.NTextField;

import net.java.dev.colorchooser.demo.CopyColor;

public class WatermarkText extends JPanel {

	public WatermarkText() throws IOException {

		setLayout(new GridLayout(1, 0, 0, 0));

		ArrayList<JComponent> lista = new ArrayList<>();

		NTextField text = new NTextField("Text");

		lista.add(text);

		CopyColor color = new CopyColor(Color.BLACK, false);

		MaterialPanelLayout panel = new MaterialPanelLayout(lista, null, false);

		add(panel);

	}

}
