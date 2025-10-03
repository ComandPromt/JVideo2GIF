package com.views.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.textField.text.NTextField;

public class Codec extends JPanel {

	private NTextField videoCodec;

	private NTextField audioCodec;

	public NTextField getVideoCodec() {

		return videoCodec;

	}

	public NTextField getAudioCodec() {

		return audioCodec;

	}

	public Codec() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(3, 1));

		videoCodec = new NTextField("libx264");

		videoCodec.setBounds(0, 0, 450, 59);

		videoCodec.setHorizontalAlignment(SwingConstants.CENTER);

		videoCodec.setFont(new Font("Dialog", Font.PLAIN, 15));

		videoCodec.setHeaderText("Video Codec");

		videoCodec.setLeft(90);

		add(videoCodec);

		audioCodec = new NTextField("aac");

		audioCodec.setBounds(0, 82, 450, 67);

		audioCodec.setHorizontalAlignment(SwingConstants.CENTER);

		audioCodec.setHeaderText("Audio Codec");

		add(audioCodec);

		ComboBoxSuggestion<String> panel = new ComboBoxSuggestion<>();

		panel.setBounds(0, 181, 450, 44);

		panel.addItem("pal8");

		panel.addItem("gray");

		panel.addItem("yuv420p");

		panel.addItem("rgb24");

		panel.addItem("rgb8");

		panel.setCenteredText();

		panel.setCenteredMenuText();

		add(panel);

	}

}
