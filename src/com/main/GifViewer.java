package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GifViewer extends JFrame {
	private ImagePanel imagePanel;
	private JLabel originLabel;
	private JTextField startXField;
	private JTextField startYField;
	private JTextField widthField;
	private JTextField heightField;
	private JButton updateButton;

	public GifViewer() {
		setTitle("GIF Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);

		imagePanel = new ImagePanel();
		add(imagePanel);

		originLabel = new JLabel("Origin: ");
		startXField = new JTextField(5);
		startYField = new JTextField(5);
		widthField = new JTextField(5);
		heightField = new JTextField(5);
		updateButton = new JButton("Update");

		JPanel infoPanel = new JPanel();
		infoPanel.add(originLabel);
		infoPanel.add(new JLabel("X: "));
		infoPanel.add(startXField);
		infoPanel.add(new JLabel("Y: "));
		infoPanel.add(startYField);
		infoPanel.add(new JLabel("Width: "));
		infoPanel.add(widthField);
		infoPanel.add(new JLabel("Height: "));
		infoPanel.add(heightField);
		infoPanel.add(updateButton);
		add(infoPanel, BorderLayout.SOUTH);

		JFileChooser fileChooser = new JFileChooser();
		JButton openButton = new JButton("Open GIF");
		openButton.addActionListener(e -> {
			int returnVal = fileChooser.showOpenDialog(GifViewer.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					imagePanel.loadImage(file);
					resetLabels();
				} catch (IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(GifViewer.this, "Error loading GIF file", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(openButton);
		add(buttonPanel, BorderLayout.NORTH);

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateSelectionWithNewDimensions();
			}
		});

		imagePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				imagePanel.startSelection(e.getPoint());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				imagePanel.finishSelection(e.getPoint());
				updateFields();
			}
		});

		imagePanel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				imagePanel.updateSelection(e.getPoint());
				updateFields();
			}
		});
	}

	private void updateFields() {
		Rectangle selection = imagePanel.getSelection();
		if (selection != null) {
			originLabel.setText("Origin: (" + selection.x + ", " + selection.y + ")");
			startXField.setText(String.valueOf(selection.x));
			startYField.setText(String.valueOf(selection.y));
			widthField.setText(String.valueOf(selection.width));
			heightField.setText(String.valueOf(selection.height));
		}
	}

	private void updateSelectionWithNewDimensions() {
		try {
			int newX = Integer.parseInt(startXField.getText());
			int newY = Integer.parseInt(startYField.getText());
			int newWidth = Integer.parseInt(widthField.getText());
			int newHeight = Integer.parseInt(heightField.getText());

			Rectangle selection = new Rectangle(newX, newY, newWidth, newHeight);
			imagePanel.setRectangle(selection);
			updateFields();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(GifViewer.this,
					"Please enter valid numeric values for X, Y, width, and height", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resetLabels() {
		originLabel.setText("Origin: ");
		startXField.setText("");
		startYField.setText("");
		widthField.setText("");
		heightField.setText("");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			GifViewer gifViewer = new GifViewer();
			gifViewer.setVisible(true);
		});
	}
}

class ImagePanel extends JPanel {
	private Image gifImage;
	private Rectangle selection;

	public ImagePanel() {
	}

	public void loadImage(File file) throws IOException {
		gifImage = new ImageIcon(file.toURI().toURL()).getImage();
		selection = null;
		repaint();
	}

	public Rectangle getSelection() {
		return selection;
	}

	public void setRectangle(Rectangle selection) {
		this.selection = selection;
		repaint();
	}

	public void startSelection(Point startPoint) {
		selection = new Rectangle(startPoint);
		repaint();
	}

	public void updateSelection(Point endPoint) {
		if (selection != null) {
			int width = endPoint.x - selection.x;
			int height = endPoint.y - selection.y;
			selection.setSize(width, height);
			repaint();
		}
	}

	public void finishSelection(Point endPoint) {
		updateSelection(endPoint);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (gifImage != null) {
			g.drawImage(gifImage, 0, 0, this);

			if (selection != null) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.RED);
				g2d.draw(selection);
			}
		}
	}
}
