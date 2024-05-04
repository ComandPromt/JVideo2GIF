package com.views.panels.effects;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.buttons.round.NButton;
import com.checkbox.CheckBoxCustom;
import com.main.VideoViewer;
import com.views.DisplayFrame;

public class CropPanel extends JPanel {

	private CheckBoxCustom crop;

	private VideoViewer ventana;

	public String getCrop() {

		try {

			return ventana.saberCrop();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return "";

	}

	public boolean isCrop() {

		try {

			return crop.isSelected();

		}

		catch (Exception e) {

		}

		return false;

	}

	public VideoViewer getVentana() {

		return ventana;

	}

	public CropPanel(VideoViewer ventanaCrop, DisplayFrame frame) {

		ventana = ventanaCrop;

		setBackground(Color.WHITE);

		setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/crop.png")));

		add(lblNewLabel);

		crop = new CheckBoxCustom("Crop");

		crop.sumarAlto(-157);

		crop.setFont(new Font("Tahoma", Font.PLAIN, 25));

		add(crop);

		NButton btnNewButton = new NButton("New button");

		btnNewButton.setRound(90);

		btnNewButton.setBorderPaint(true);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				click(ventanaCrop, frame);

			}

			private void click(VideoViewer ventanaCrop, DisplayFrame frame) {

				try {

					if (!(ventanaCrop instanceof VideoViewer)) {

						ventanaCrop = new VideoViewer(frame);

					}

					ventanaCrop.setVisible(true);

				}

				catch (Exception e1) {

				}

			}

		});

		add(btnNewButton);

		btnNewButton.setThickness(1);

		btnNewButton.setBorderColor(Color.WHITE);

		btnNewButton.setBackground(Color.decode("#dcd8d7"));

		btnNewButton.setText("Crop");

	}

}
