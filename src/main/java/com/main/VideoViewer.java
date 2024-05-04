package com.main;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.comboBox.comboSuggestion.ComboBoxSuggestion;
import com.history.ListHistory;
import com.views.DisplayFrame;

@SuppressWarnings("serial")
public class VideoViewer extends JFrame {

	private VideoPanel videoPanel;

	public static ComboBoxSuggestion<String> openButton;

	private DisplayFrame frame;

	public String saberCrop() {

		try {

			return videoPanel.getCrop();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return "";

	}

	public VideoViewer(DisplayFrame frame) {

		addWindowListener(new WindowAdapter() {

			@Override

			public void windowClosing(WindowEvent e) {

				DisplayFrame.videoPanel = videoPanel;

			}

		});

		this.frame = frame;

		setTitle("Video Viewer");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setSize(800, 600);

		setLocationRelativeTo(null);

		videoPanel = new VideoPanel();

		getContentPane().add(videoPanel);

		JPanel controlPanel = new JPanel();

		openButton = new ComboBoxSuggestion<>();

		openButton.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				try {

					if (JVideoPlayer.getListView().getMap().get(openButton.getSelectedItem().toString()) != null
							&& new File(
									JVideoPlayer.getListView().getMap().get(openButton.getSelectedItem().toString()))
									.exists()) {

						openVideo(JVideoPlayer.getListView().getMap().get(openButton.getSelectedItem().toString()));

					}

				}

				catch (Exception e1) {

				}

			}

		});

		ponerVideos();

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

	public void ponerVideos() {

		try {

			if (JVideoPlayer.getListView().getList().size() == 0) {

				JVideoPlayer.getListView().setList(ListHistory.readHistory());

				JVideoPlayer.getListView().setMap(ListHistory.readHistoryMap());

			}

			ArrayList<String> historial = JVideoPlayer.getListView().getList();

			openButton.removeAllItems();

			if (!historial.isEmpty()) {

				for (int i = 0; i < historial.size(); i++) {

					openButton.addItem(historial.get(i).toString());

				}

			}

		}

		catch (Exception e) {

		}

	}

	private void openVideo(String path) {

		try {

			if (new File(path).exists()) {

				videoPanel.loadVideo(new File(path));

				videoPanel.showThreeSeconds();

				videoPanel.resetTimer();

			}

		}

		catch (IOException ex) {

			JOptionPane.showMessageDialog(VideoViewer.this, "Error loading video file", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

}
