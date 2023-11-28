package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import com.spinner.simple.Spinner;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IContainerFormat;

public class VideoPanel extends JPanel {
	private Picture lastFrame;
	private SeekableByteChannel videoChannel;
	private Timer timer;
	private double currentTimeSec;
	private double totalDurationSec;
	private File video;
	private Point startPoint;
	private Point endPoint;
	private JButton restartButton;
	private JLabel timeLabel;
	private JPanel videoDisplay;
	private Spinner xField, yField, widthField, heightField;

	public Spinner getxField() {
		return xField;
	}

	public Spinner getyField() {
		return yField;
	}

	public Spinner getWidthField() {
		return widthField;
	}

	public Spinner getHeightField() {
		return heightField;
	}

	private static double duracion;

	private void updateFields() {
		xField.setValor(Integer.parseInt(String.valueOf(startPoint.x)));
		yField.setValor(Integer.parseInt(String.valueOf(startPoint.y)));
		widthField.setValor(Integer.parseInt(String.valueOf(endPoint.x - startPoint.x)));
		heightField.setValor(Integer.parseInt(String.valueOf(endPoint.y - startPoint.y)));
	}

	private void resizeRectangle() {
		try {
			int x = xField.getValor();
			int y = yField.getValor();
			int width = widthField.getValor();
			int height = heightField.getValor();

			startPoint = new Point(x, y);
			endPoint = new Point(x + width, y + height);
			repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid integers.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void repaintLastFrame() {
		SwingUtilities.invokeLater(() -> {
			Graphics2D g2d = (Graphics2D) videoDisplay.getGraphics();
			Picture lastFrame = getLastFrame();
			if (lastFrame != null) {
				drawVideoFrame(g2d, lastFrame);
				drawRectangle(g2d);
			}
		});
	}

	private Picture getLastFrame() {
		try {
			double frameRate = 0.25;
			int frameNumber = (int) (currentTimeSec * frameRate);
			return FrameGrab.getFrameFromFile(video, frameNumber);
		} catch (IOException | JCodecException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public VideoPanel() {

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				repaintLastFrame();
			}
		});

		currentTimeSec = 0;
		startPoint = new Point();
		endPoint = new Point();
		initMouseListeners();

		restartButton = new JButton("Restart Video");
		restartButton.addActionListener(e -> restartVideo());

		setLayout(new BorderLayout());
		videoDisplay = new JPanel();
		add(videoDisplay, BorderLayout.CENTER);
		add(restartButton, BorderLayout.NORTH);
		xField = new Spinner();
		yField = new Spinner();
		widthField = new Spinner();
		heightField = new Spinner();

		JButton resizeButton = new JButton("Resize Rectangle");
		resizeButton.addActionListener(e -> resizeRectangle());

		JPanel controlPanel = new JPanel();
		controlPanel.add(new JLabel("X:"));
		controlPanel.add(xField);
		controlPanel.add(new JLabel("Y:"));
		controlPanel.add(yField);
		controlPanel.add(new JLabel("Width:"));
		controlPanel.add(widthField);
		controlPanel.add(new JLabel("Height:"));
		controlPanel.add(heightField);
		controlPanel.add(resizeButton);

		add(controlPanel, BorderLayout.SOUTH);
	}

	public void setTimeLabel(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}

	private void initMouseListeners() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startPoint = e.getPoint();
				endPoint = startPoint;
				updateFields();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				endPoint = e.getPoint();
				repaint();
				restartVideo();
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				endPoint = e.getPoint();
				repaint();
				updateFields();
			}
		});
	}

	public void loadVideo(File file) throws IOException, JCodecException {
		video = file;
		videoChannel = NIOUtils.readableChannel(file);
		totalDurationSec = getDuration(file);
		startPlayback();
	}

	public static double getDuration(File videoFile) {
		IContainer container = IContainer.make();
		IContainerFormat format = IContainerFormat.make();

		if (container.open(videoFile.getAbsolutePath(), IContainer.Type.READ, format) >= 0) {
			duracion = container.getDuration() / 1000000.0; // Duración en segundos
		} else {
			duracion = 0;
		}
		return duracion;
	}

	private void startPlayback() {
		if (videoChannel == null) {
			JOptionPane.showMessageDialog(this, "No video loaded", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (timer != null && timer.isRunning()) {
			timer.stop();
		}

		currentTimeSec = 0;
		timer = new Timer(100, e -> {
			try {
				double frameRate = 0.25;
				double secondsPerFrame = 1.0 / frameRate;

				Picture frame = FrameGrab.getFrameFromFile(video, (int) currentTimeSec);
				currentTimeSec += secondsPerFrame;
				if (frame != null) {
					SwingUtilities.invokeLater(() -> {
						Graphics2D g2d = (Graphics2D) videoDisplay.getGraphics();
						drawVideoFrame(g2d, frame);
						drawRectangle(g2d);
						updateLabel();

						// Almacenar el último cuadro
						lastFrame = frame;

						double pauseTime = 0.001;
						double remainingTime = getRemainingTime();
						if (remainingTime <= pauseTime) {
							timer.stop();
						}
					});
				} else {
					timer.stop();
				}
			} catch (IOException | JCodecException ex) {
				ex.printStackTrace();
				SwingUtilities.invokeLater(() -> {
					JOptionPane.showMessageDialog(this, "Error during video playback: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				});
				timer.stop();
			}
		});
		timer.start();
	}

	private void drawVideoFrame(Graphics2D g2d, Picture frame) {
		// Dibujar el último cuadro almacenado si existe
		if (lastFrame != null) {
			g2d.drawImage(AWTUtil.toBufferedImage(lastFrame), 0, 0, this);
		} else {
			g2d.drawImage(AWTUtil.toBufferedImage(frame), 0, 0, this);
		}
	}

	private void drawRectangle(Graphics2D g2d) {
		Rectangle rectangle = createRectangle(startPoint, endPoint);
		g2d.setColor(Color.RED);
		g2d.draw(rectangle);
	}

	private double getRemainingTime() {

		double totalDuration = duracion;
		totalDuration *= 60;

		return Math.max(0, totalDuration - currentTimeSec);
	}

	private Rectangle createRectangle(Point start, Point end) {
		int x = Math.min(start.x, end.x);
		int y = Math.min(start.y, end.y);
		int width = Math.abs(start.x - end.x);
		int height = Math.abs(start.y - end.y);
		return new Rectangle(x, y, width, height);
	}

	private void restartVideo() {
		if (timer != null) {
			timer.stop();
		}
		startPlayback();
	}

	private void updateLabel() {
		if (timeLabel != null) {
			timeLabel.setText(String.format("Time: %.2f seconds / %.2f seconds", currentTimeSec, totalDurationSec));
		}
	}
}
