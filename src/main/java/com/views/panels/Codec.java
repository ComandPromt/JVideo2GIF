package com.views.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

		setLayout(new GridLayout());

		videoCodec = new NTextField("libx264");

		videoCodec.setHorizontalAlignment(SwingConstants.CENTER);

		videoCodec.setFont(new Font("Dialog", Font.PLAIN, 15));

		videoCodec.setHeaderText("Video Codec");

		videoCodec.setLeft(90);

		add(videoCodec);

		audioCodec = new NTextField("aac");
		audioCodec.setHorizontalAlignment(SwingConstants.CENTER);

		audioCodec.setHeaderText("Audio Codec");

		add(audioCodec);

	}

}
