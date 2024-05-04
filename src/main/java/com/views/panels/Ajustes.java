package com.views.panels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.checkbox.CheckBoxCustom;
import com.materialfilechooser.ChooserWithInput;
import com.textField.text.NTextField;

public class Ajustes extends JPanel {

	private CheckBoxCustom playFile;

	private CheckBoxCustom openFolder;

	private NTextField extension;

	private ChooserWithInput output;

	public CheckBoxCustom getPlayFile() {

		return playFile;

	}

	public CheckBoxCustom getOpenFolder() {

		return openFolder;

	}

	public NTextField getExtension() {

		return extension;

	}

	public ChooserWithInput getOutput() {

		return output;

	}

	public Ajustes() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(0, 1, 0, 0));

		playFile = new CheckBoxCustom("Play file at the end of the conversion");

		playFile.setLeft(200);

		playFile.sumarAlto(-33);

		add(playFile);

		openFolder = new CheckBoxCustom("Open folder at the end of the conversion");

		openFolder.sumarAlto(-33);

		openFolder.setLeft(200);

		add(openFolder);

		extension = new NTextField("Output extension", "");

		extension.setText("gif");

		extension.setHorizontalAlignment(SwingConstants.CENTER);

		extension.setLeft(300);

		add(extension);

		output = new ChooserWithInput("Select A Image", "", true, null, false, true);

		output.setHorizontalAlignment(SwingConstants.CENTER);

		output.setLeft(300);

		output.setHeader("Output Folder");

		add(output);

	}

}
