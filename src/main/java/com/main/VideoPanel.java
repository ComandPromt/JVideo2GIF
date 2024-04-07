package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
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
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IContainerFormat;
import com.xuggle.xuggler.IStreamCoder;

@SuppressWarnings("serial")

public class VideoPanel extends JPanel {

	private Picture lastFrame;

	private SeekableByteChannel videoChannel;

	private Timer timer;

	private double currentTimeSec;

	private double totalDurationSec;

	private File video;

	private Point startPoint;

	private Point endPoint;

	private JLabel timeLabel;

	private JPanel videoDisplay;
	private JPanel controlPanel;
	private Spinner xField;

	private Spinner yField;

	private Spinner widthField;

	private Spinner heightField;

	private static double duracion;

	private boolean pinto;

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

	private void updateFields() {

		int x;

		int y;

		int width;

		int height;

		if (pinto) {

			x = startPoint.x;

			y = startPoint.y;

			width = endPoint.x - startPoint.x;

			height = endPoint.y - startPoint.y;

			x = Math.max(0, x);

			y = Math.max(0, y);

			if (width <= 0) {

				width = Math.abs(width);

				x = endPoint.x;

			}

			if (height <= 0) {

				height = Math.abs(height);

				y = endPoint.y;

			}

		}

		else {

			x = xField.getValor();

			y = yField.getValor();

			width = widthField.getValor();

			height = heightField.getValor();

		}

		xField.setValor(x);

		yField.setValor(y);

		widthField.setValor(width);

		heightField.setValor(height);

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (lastFrame != null) {

			drawVideoFrame((Graphics2D) g, lastFrame);

		}

	}

	@Override
	public Dimension getPreferredSize() {

		if (lastFrame != null) {

			return new Dimension(lastFrame.getWidth(), lastFrame.getHeight());

		}

		else {

			return super.getPreferredSize();

		}

	}

	private void resizeRectangle() {

		try {

			int x = xField.getValor();

			int y = yField.getValor();

			int width = widthField.getValor();

			int height = heightField.getValor();

			Dimension videoSize = getVideoSize(video);

			int videoWidth = videoSize.width;

			int videoHeight = videoSize.height;

			double scaleX = (double) videoWidth / getWidth();

			double scaleY = (double) videoHeight / getHeight();

			int adjustedX = (int) (x * scaleX);

			int adjustedY = (int) (y * scaleY);

			int adjustedWidth = (int) (width * scaleX);

			int adjustedHeight = (int) (height * scaleY);

			startPoint = new Point(adjustedX, adjustedY);

			endPoint = new Point(adjustedX + adjustedWidth, adjustedY + adjustedHeight);

			repaint();

		}

		catch (Exception ex) {

		}

	}

	private void repaintLastFrame() {

		try {

			SwingUtilities.invokeLater(() -> {

				Graphics2D g2d = (Graphics2D) videoDisplay.getGraphics();

				Picture lastFrame = getLastFrame();

				if (lastFrame != null) {

					drawVideoFrame(g2d, lastFrame);

					drawRectangle(g2d);

					updateFields();

				}

			});

		}

		catch (Exception e) {

		}

	}

	private Picture getLastFrame() {

		try {

			if (video == null) {

				return null;

			}

			else {

				double frameRate = 0.25;

				int frameNumber = (int) (currentTimeSec * frameRate);

				return FrameGrab.getFrameFromFile(video, frameNumber);

			}

		}

		catch (IOException | JCodecException ex) {

			ex.printStackTrace();

			return null;

		}

	}

	public VideoPanel() {

		pinto = true;

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

		setLayout(new BorderLayout());

		videoDisplay = new JPanel();

		add(videoDisplay, BorderLayout.CENTER);

		xField = new Spinner();

		xField.getEditor().addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				pinto = false;
			}

		});

		xField.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				pinto = false;

			}

		});

		xField.setValorMaximo(false);

		yField = new Spinner();

		yField.getEditor().addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				pinto = false;
			}

		});

		yField.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				pinto = false;

			}

		});

		yField.setValorMaximo(false);

		widthField = new Spinner();

		widthField.getEditor().addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				pinto = false;
			}

		});

		widthField.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				pinto = false;

			}

		});

		widthField.setValorMaximo(false);

		heightField = new Spinner();

		heightField.getEditor().addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				pinto = false;
			}

		});

		heightField.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				pinto = false;

			}

		});

		heightField.setValorMaximo(false);

		JButton resizeButton = new JButton("Resize Rectangle");

		resizeButton.addActionListener(e -> resizeRectangle());

		controlPanel = new JPanel();

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

		timer = new Timer(100, e -> {

			if (currentTimeSec > totalDurationSec) {

				currentTimeSec = 1;

				restartVideo();

			}

		});

		timer.setInitialDelay(3000);

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

				pinto = true;

				endPoint = e.getPoint();

				SwingUtilities.invokeLater(() -> {

					repaint();

					restartVideo();

					updateFields();

				});

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

	void loadVideo(File file) throws FileNotFoundException {
		video = file;
		videoChannel = NIOUtils.readableChannel(file);
		totalDurationSec = getDuration(file);

		// Ajustar el tamaño del panel al tamaño del video
		Dimension videoSize = getVideoSize(file);
		setPreferredSize(videoSize);
		revalidate();

		startPlayback();
	}

	private Dimension getVideoSize(File videoFile) {
		// Obtener el tamaño del video
		int width = 0;
		int height = 0;
		try {
			Picture picture = FrameGrab.getFrameFromFile(videoFile, 1);
			if (picture != null) {
				width = picture.getWidth();
				height = picture.getHeight();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Dimension(width, height);
	}

	public void showThreeSeconds() {

		currentTimeSec = 1;

		restartVideo();

	}

	public void resetTimer() {

		timer.restart();

	}

	public void resetPlayback() {

		currentTimeSec = 1;

		restartVideo();

	}

	public static double getDuration(File videoFile) {

		IContainer container = IContainer.make();

		IContainerFormat format = IContainerFormat.make();

		if (container.open(videoFile.getAbsolutePath(), IContainer.Type.READ, format) >= 0) {

			duracion = container.getDuration() / 1000000.0;

		}

		else {

			duracion = 0;

		}

		return duracion;

	}

	private double getFrameRate() throws IOException, JCodecException {

		IContainer container = IContainer.make();

		if (container.open(video.getAbsolutePath(), IContainer.Type.READ, null) < 0) {

			throw new IOException("Could not open video file.");

		}

		int streamIndex = -1;

		int numStreams = container.getNumStreams();

		for (int i = 0; i < numStreams; i++) {

			if (container.getStream(i).getStreamCoder().getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {

				streamIndex = i;

				break;

			}

		}

		if (streamIndex == -1) {

			throw new IOException("No video stream found in the file.");

		}

		IStreamCoder coder = container.getStream(streamIndex).getStreamCoder();

		return coder.getFrameRate().getDouble();

	}

	private void startPlayback() {

		if (videoChannel == null) {

			JOptionPane.showMessageDialog(this, "No video loaded", "Error", JOptionPane.ERROR_MESSAGE);

			return;

		}

		if (timer != null && timer.isRunning()) {

			timer.stop();

		}

		currentTimeSec = 1;

		totalDurationSec = currentTimeSec;

		totalDurationSec += 3;

		timer = new Timer(100, e -> {

			try {

				double frameRate = getFrameRate() / 15;

				double playbackSpeed = 1;

				int frameNumber = (int) (currentTimeSec * getFrameRate() * playbackSpeed);

				Picture frame = FrameGrab.getFrameFromFile(video, frameNumber);

				currentTimeSec += 1.0 / (frameRate * playbackSpeed);

				if (frame != null) {

					SwingUtilities.invokeLater(() -> {

						Graphics2D g2d = (Graphics2D) videoDisplay.getGraphics();

						drawVideoFrame(g2d, frame);

						drawRectangle(g2d);

						updateLabel();

						lastFrame = frame;

						if (currentTimeSec >= totalDurationSec) {

							currentTimeSec = 1;

							repaintLastFrame();

						}

					});

				}

				else {

					timer.stop();

					startPlayback();

				}

			}

			catch (IOException | JCodecException ex) {

				timer.stop();

			}

		});

		timer.setInitialDelay(0);

		timer.start();

	}

	private void drawVideoFrame(Graphics2D g2d, Picture frame) {
		try {
			int panelWidth = getWidth();
			int panelHeight = getHeight();
			int videoWidth = frame.getWidth();
			int videoHeight = frame.getHeight();

			// Calcular el factor de escala
			double scaleX = (double) panelWidth / videoWidth;
			double scaleY = (double) panelHeight / videoHeight;

			// Crear una transformación de escala
			AffineTransform transform = AffineTransform.getScaleInstance(scaleX, scaleY);

			// Aplicar la transformación al contexto de gráficos
			g2d.setTransform(transform);

			// Dibujar el video en el panel
			g2d.drawImage(AWTUtil.toBufferedImage(frame), 0, 0, null);

			// Restaurar la transformación
			g2d.setTransform(new AffineTransform());
		} catch (Exception e) {

		}
	}

	private void drawRectangle(Graphics2D g2d) {

		try {

			Rectangle rectangle = createRectangle(startPoint, endPoint);

			g2d.setColor(Color.RED);

			g2d.draw(rectangle);

			controlPanel.repaint();

		}

		catch (Exception e) {

		}

	}

	private Rectangle createRectangle(Point start, Point end) {

		int x = Math.min(start.x, end.x);

		int y = Math.min(start.y, end.y);

		int width = Math.abs(start.x - end.x);

		int height = Math.abs(start.y - end.y);

		return new Rectangle(x, y, width, height);

	}

	private void restartVideo() {

		if (timer != null && timer.isRunning()) {

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