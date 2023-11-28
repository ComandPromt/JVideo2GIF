package com.main;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jcodec.api.JCodecException;

import com.views.DisplayFrame;

public class VideoViewer extends JFrame {
	private VideoPanel videoPanel;

	public VideoViewer(DisplayFrame frame) {
		setTitle("Video Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);

		videoPanel = new VideoPanel();
		getContentPane().add(videoPanel);

		JPanel controlPanel = new JPanel();
		JButton openButton = new JButton("Open Video");
		openButton.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(VideoViewer.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					videoPanel.loadVideo(file);
				} catch (IOException | JCodecException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(VideoViewer.this, "Error loading video file", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		controlPanel.add(openButton);

		getContentPane().add(controlPanel, BorderLayout.NORTH);

		JButton btnCrop = new JButton("Crop");

		btnCrop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.setCrop(new Point(videoPanel.getxField().getValor(), videoPanel.getyField().getValor()));

				frame.setCropSize(
						new Point(videoPanel.getWidthField().getValor(), videoPanel.getHeightField().getValor()));

			}

		});

		controlPanel.add(btnCrop);

		JPanel infoPanel = new JPanel();

		JLabel timeLabel = new JLabel("Time: 0 seconds");

		infoPanel.add(timeLabel);

		getContentPane().add(infoPanel, BorderLayout.SOUTH);

		videoPanel.setTimeLabel(timeLabel);

		setVisible(true);

	}

}
