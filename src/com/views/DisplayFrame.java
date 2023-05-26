package com.views;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.TooManyListenersException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.file.nativ.chooser.DemoJavaFxStage;
import com.main.JVideoPlayer;
import com.play_list.PlayListFrame;
import com.slider.tipo2.JsliderCustom;
import com.spinner.decimal.DecimalSpinner;
import com.spinner.simple.Spinner;
import com.util.Metodos;

import checkbox.JCheckBoxCustom;
import drag_and_drop.DragAndDrop;
import drag_and_drop.UtilDragAndDrop;
import main.JFfmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import progressbar.ProgressBarCustom;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import util.Mthos;

@SuppressWarnings("serial")

public class DisplayFrame extends JFrame {

	private JPanel contentPane;

	EmbeddedMediaPlayerComponent playerComponent;

	public static String nombreVideo;

	private JPanel panel;

	private JButton stopButton;

	private static JButton playButton;

	private JPanel controlPanel;

	private ProgressBarCustom progressBar;

	private JsliderCustom volumControlerSlider;

	private JMenuBar menuBar;

	private JMenu mnFile;

	private JMenuItem mntmOpenVideo;

	private JMenuItem mntmOpenSubtitle;

	private JButton forwardButton;

	private JButton backwordButton;

	private JButton fullScreenButton;

	private int flag = 0;

	private KeyBordListenerEvent kble;

	private JPanel progressTimePanel;

	private JLabel currentLabel;

	private JLabel totalLabel;

	private static PlayListFrame playListFrame;

	private JButton listButton;

	private JButton btnNewButton;

	private JButton prev;

	private JButton prox;

	private JSeparator separator;

	private JSeparator separator_1;

	private JMenuItem mntmNewMenuItem;

	private JButton btnNewButton_1;

	private JMenuItem mntmNewMenuItem_1;

	public static String[] config;

	static long contador;

	String path;

	private JSeparator separator_2;

	private JSeparator separator_3;

	private JSeparator separator_4;

	private JButton btnNewButton_2;

	private DecimalSpinner time1;

	private JButton btnNewButton_3;

	private DecimalSpinner time2;

	private JButton btnNewButton_4;

	private JCheckBoxCustom hq;

	JFfmpeg utilidad;

	private Spinner fps;

	private JSeparator separator_5;

	private JSeparator separator_6;

	private static Spinner ancho;
	private JButton btnNewButton_5;
	private JSeparator separator_7;
	private JButton btnNewButton_6;
	private DragAndDrop panel_1;

	private void ponerTiempo(boolean primerTiempo) {

		if (!JVideoPlayer.getListView().getList().isEmpty()) {

			float tiempo = JVideoPlayer.getFrame().getMediaPlayer().getTime() / 1000f;

			if (primerTiempo) {

				time1.setValor(tiempo);

			}

			else {

				if (time2.getValor() == 0f) {

					time2.setValor(1f);

				}

				else if (tiempo > time1.getValor()) {

					time2.setValor(tiempo - time1.getValor());

				}

				else {

					time1.setValor(0f);

				}

			}

		}

	}

	private void addVideo() {

		playListFrame.setList(JVideoPlayer.getListView().getList());

		playListFrame.getScrollPane().setViewportView(playListFrame.getList());

	}

	private static void parar() {

		JVideoPlayer.stop();

		playButton.setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/play.png")));

	}

	private static void iniciar() {

		try {

			playButton.setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/pause.png")));

			ControlFrame.playButton.setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/pause.png")));

			ponerAncho(true);

		}

		catch (Exception e) {

		}

		JVideoPlayer.play();

	}

	public static void ponerAncho(boolean filtro) {

		try {

			if (!filtro || ancho.getValor() == 0) {

				FFprobe ffprobe = new FFprobe();

				FFmpegProbeResult probeResult = ffprobe.probe(JVideoPlayer.getListView().getMap()
						.get(JVideoPlayer.getFrame().getMediaPlayer().getMediaMetaData().getTitle()));

				FFmpegStream stream = probeResult.getStreams().get(0);

				ancho.setValor(stream.width);

			}

		}

		catch (Exception e) {
		}

	}

	public static void reiniciarVideo() {

		parar();

		iniciar();

	}

	public static void playPause() {

		if (JVideoPlayer.getFrame().getMediaPlayer().isPlaying()) {

			try {

				playButton.setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/play.png")));

				ControlFrame.playButton.setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/play.png")));

			}

			catch (Exception e) {

			}

			JVideoPlayer.pause();

		}

		else {

			iniciar();

		}

	}

	private void abrirVideo(boolean filter) {

		JVideoPlayer.openVideo(filter);

		addVideo();

	}

	public DisplayFrame() throws TooManyListenersException {

		setBackground(Color.WHITE);

		config = new String[2];

		nombreVideo = "";

		try {

			File dir = new File("screenshots");

			if (!dir.exists()) {

				dir.mkdir();

			}

			File archivo = new File("config.dat");

			if (archivo.exists()) {

				ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream("config.dat"));

				config = leyendoFichero.readObject().toString().split(",");

				leyendoFichero.close();

			}

			else {

				config[0] = null;

				config[1] = null;

			}

			path = new File(".").getCanonicalPath() + Mthos.saberSeparador() + "screenshots";

			if (config[1] != null) {

				path = config[1];

				contador = Mthos.listarFicherosPorCarpeta(path, "jpg") + 1;

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		setTitle("JVideo2GIF");

		setIconImage(Toolkit.getDefaultToolkit().getImage(DisplayFrame.class.getResource("/images/video_gif.png")));

		playListFrame = new PlayListFrame();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 907, 394);

		kble = new KeyBordListenerEvent();

		kble.keyBordListner();

		menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(Color.WHITE);

		setJMenuBar(menuBar);

		mnFile = new JMenu("Open");
		mnFile.setFont(new Font("SansSerif", Font.PLAIN, 16));

		mnFile.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/abrir.png")));

		mnFile.setBackground(Color.WHITE);

		menuBar.add(mnFile);

		mntmOpenVideo = new JMenuItem("Video");

		mntmOpenVideo.setFont(new Font("SansSerif", Font.PLAIN, 16));

		mntmOpenVideo.setBackground(Color.WHITE);

		mntmOpenVideo.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/video_2_frame.png")));

		mntmOpenVideo.setSelected(true);

		mnFile.add(mntmOpenVideo);

		separator = new JSeparator();
		separator.setBackground(Color.WHITE);

		mnFile.add(separator);

		mntmNewMenuItem = new JMenuItem("Folder");
		mntmNewMenuItem.setBackground(Color.WHITE);
		mntmNewMenuItem.setFont(new Font("SansSerif", Font.PLAIN, 16));

		mntmNewMenuItem.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				abrirVideo(true);

			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/if_open-file_85334.png")));
		mnFile.add(mntmNewMenuItem);

		separator_1 = new JSeparator();
		separator_1.setBackground(Color.WHITE);
		mnFile.add(separator_1);

		mntmOpenSubtitle = new JMenuItem("Open Subtitle");
		mntmOpenSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
		mntmOpenSubtitle.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/srt.png")));
		mntmOpenSubtitle.setBackground(Color.WHITE);

		mnFile.add(mntmOpenSubtitle);

		mntmNewMenuItem_1 = new JMenuItem("Config");
		mntmNewMenuItem_1.setBackground(Color.WHITE);
		mntmNewMenuItem_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				try {

					new Config().setVisible(true);

				}

				catch (IOException e1) {

				}

			}

		});

		separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setBackground(Color.WHITE);
		menuBar.add(separator_4);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ponerTiempo(true);

			}
		});
		btnNewButton_2.setContentAreaFilled(false);

		btnNewButton_2.setBorder(null);
		btnNewButton_2.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/time_1.png")));

		btnNewButton_2.setBackground(Color.WHITE);
		menuBar.add(btnNewButton_2);

		time1 = new DecimalSpinner();
		time1.setForeground(Color.WHITE);
		time1.setBackground(Color.WHITE);
		menuBar.add(time1);

		separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBackground(Color.WHITE);
		menuBar.add(separator_2);

		btnNewButton_3 = new JButton("");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ponerTiempo(false);

			}
		});
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/time_2.png")));

		menuBar.add(btnNewButton_3);

		time2 = new DecimalSpinner();

		time2.setLabelText("Seconds  ");

		time2.setToolTipText("Seconds");

		time2.setIncremento(0.25f);

		time2.setMinValor(0f);

		time2.setForeground(Color.WHITE);

		time2.setBackground(Color.WHITE);

		menuBar.add(time2);

		separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBackground(Color.WHITE);
		menuBar.add(separator_3);

		btnNewButton_4 = new JButton("Convert");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/video_2_frame.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (time2.getValor() > 0f) {

					try {

						String archivo = JVideoPlayer.getListView().getMap()
								.get(JVideoPlayer.getFrame().getMediaPlayer().getMediaMetaData().getTitle());

						String[] comandos = new String[13];

						comandos[0] = "-i";

						comandos[1] = archivo;

						comandos[2] = "-ss";

						comandos[3] = Float.toString(time1.getValor());

						comandos[4] = "-t";

						comandos[5] = Float.toString(time2.getValor());

						comandos[6] = "-fps";

						comandos[7] = Integer.toString(fps.getValor());

						comandos[8] = "-width";

						comandos[9] = Integer.toString(ancho.getValor());

						if (hq.isSelected()) {

							comandos[10] = "-good";
						}

						else {

							comandos[10] = "-bad";

						}

						comandos[11] = "-y";

						comandos[12] = archivo.substring(0, archivo.lastIndexOf(".")) + "_" + Metodos.saberFechaActual()
								+ ".gif";

						utilidad = new JFfmpeg();

						utilidad.jffmpeg(comandos);

					}

					catch (Exception e1) {
					}

				}
			}
		});

		hq = new JCheckBoxCustom("jCheckBoxCustom1", SwingConstants.LEFT);
		hq.setSelected(true);

		hq.setFont(new Font("Tahoma", Font.PLAIN, 16));

		hq.setText("HQ");

		fps = new Spinner();
		fps.setLabelText("FPS");

		fps.setMinValor(1);

		fps.setValor(25);

		fps.setToolTipText("FPS");

		menuBar.add(fps);

		btnNewButton_6 = new JButton("");

		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fps.setValor(25);

			}

		});

		btnNewButton_6.setContentAreaFilled(false);

		btnNewButton_6.setBorder(null);

		btnNewButton_6.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/reload.png")));

		menuBar.add(btnNewButton_6);

		separator_5 = new JSeparator();
		separator_5.setForeground(Color.WHITE);
		menuBar.add(separator_5);

		ancho = new Spinner();

		ancho.setToolTipText("Width");

		ancho.setLabelText("Width");

		menuBar.add(ancho);

		btnNewButton_5 = new JButton("");

		btnNewButton_5.setContentAreaFilled(false);

		btnNewButton_5.setBorder(null);

		btnNewButton_5.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/reload.png")));

		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ponerAncho(false);

			}

		});

		menuBar.add(btnNewButton_5);

		separator_7 = new JSeparator();
		separator_7.setForeground(Color.WHITE);
		menuBar.add(separator_7);

		menuBar.add(hq);

		separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		menuBar.add(separator_6);

		menuBar.add(btnNewButton_4);

		mntmNewMenuItem_1.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/settings.png")));

		menuBar.add(mntmNewMenuItem_1);

		mntmOpenVideo.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				abrirVideo(false);

			}

		});

		mntmOpenSubtitle.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				try {

					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					DemoJavaFxStage test = new DemoJavaFxStage();

					LinkedList<File> lista = new LinkedList<File>();

					lista = test.showOpenFileDialog(false, "srt");

					if (!lista.isEmpty()) {

						JVideoPlayer.frame.getMediaPlayer().setSubTitleFile(lista.get(0).getAbsolutePath());

					}

				}

				catch (Exception e1) {

					e1.printStackTrace();

				}

			}

		});

		contentPane = new JPanel();

		contentPane.setBackground(Color.WHITE);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		JPanel videoPanel = new JPanel();

		contentPane.add(videoPanel, BorderLayout.CENTER);

		videoPanel.setLayout(new BorderLayout(0, 0));

		playerComponent = new EmbeddedMediaPlayerComponent();

		final Canvas videoSurface = playerComponent.getVideoSurface();

		videoSurface.addMouseListener(new MouseAdapter() {

			Timer mouseTime;

			@Override

			public void mouseClicked(MouseEvent e) {

				int i = e.getButton();

				if (i == MouseEvent.BUTTON1) {

					if (e.getClickCount() == 1) {

						mouseTime = new Timer(350, new ActionListener() {

							@Override

							public void actionPerformed(ActionEvent e) {

								playPause();

								mouseTime.stop();

							}

						});

						mouseTime.restart();

					}

					else if (e.getClickCount() == 2 && mouseTime.isRunning()) {

						mouseTime.stop();

						if (flag == 0) {

							JVideoPlayer.fullScreen();

						}

						else if (flag == 1) {

							JVideoPlayer.originalScreen();

						}

					}

				}

			}

		});

		videoPanel.add(playerComponent, BorderLayout.CENTER);

		panel = new JPanel();

		videoPanel.add(panel, BorderLayout.SOUTH);

		panel.setLayout(new BorderLayout(0, 0));

		controlPanel = new JPanel();

		controlPanel.setBackground(Color.WHITE);

		controlPanel.getLayout();

		panel.add(controlPanel);

		playButton = new JButton("");

		playButton.setBackground(Color.WHITE);

		playButton.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/play.png")));

		playButton.setContentAreaFilled(false);

		playButton.setBorder(null);

		playButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				playPause();

			}

		});

		try {

		}

		catch (Exception e1) {

			e1.printStackTrace();

		}

		controlPanel.add(playButton);

		volumControlerSlider = new JsliderCustom();

		volumControlerSlider.setForeground(new Color(115, 118, 120));

		volumControlerSlider.setBackground(Color.BLUE);

		volumControlerSlider.setValue(100);

		volumControlerSlider.setMaximum(120);

		volumControlerSlider.addChangeListener(new ChangeListener() {

			@Override

			public void stateChanged(ChangeEvent e) {

				JVideoPlayer.setVolum(volumControlerSlider.getValue());

			}

		});

		forwardButton = new JButton("");

		forwardButton.setContentAreaFilled(false);

		forwardButton.setBorder(null);

		forwardButton.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/rew.png")));

		forwardButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				JVideoPlayer.jumpTo((float) (((progressBar.getPercentComplete() * progressBar.getWidth() + 10))
						/ progressBar.getWidth()));
			}

		});

		stopButton = new JButton("");

		stopButton.setContentAreaFilled(false);

		stopButton.setBorder(null);

		stopButton.setBackground(Color.WHITE);

		stopButton.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/stop.png")));

		stopButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				parar();

			}

		});

		controlPanel.add(stopButton);

		backwordButton = new JButton("");

		backwordButton.setContentAreaFilled(false);

		backwordButton.setBorder(null);

		backwordButton.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/prev.png")));

		backwordButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				JVideoPlayer.jumpTo((float) ((progressBar.getPercentComplete() * progressBar.getWidth() - 5)
						/ progressBar.getWidth()));

			}

		});

		btnNewButton = new JButton("");

		btnNewButton.setContentAreaFilled(false);

		btnNewButton.setBorder(null);

		btnNewButton.setBackground(Color.WHITE);

		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				reiniciarVideo();

			}

		});

		btnNewButton.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/replay.png")));

		controlPanel.add(btnNewButton);

		controlPanel.add(backwordButton);

		controlPanel.add(forwardButton);

		fullScreenButton = new JButton("");

		fullScreenButton.setContentAreaFilled(false);

		fullScreenButton.setBorder(null);

		fullScreenButton.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/full_screen.png")));

		fullScreenButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				JVideoPlayer.fullScreen();

			}

		});

		controlPanel.add(fullScreenButton);

		btnNewButton_1 = new JButton("");

		btnNewButton_1.setContentAreaFilled(false);

		btnNewButton_1.setBorder(null);

		btnNewButton_1.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/photo.png")));

		btnNewButton_1.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				if (!nombreVideo.isEmpty()) {

					try {

						Thread.sleep(120);

						Robot r = new Robot();

						contador++;

						Rectangle capture = new Rectangle(JVideoPlayer.frame.getX() + 13,
								JVideoPlayer.frame.getY() + 75, playerComponent.getWidth(),
								playerComponent.getHeight());

						BufferedImage Image = r.createScreenCapture(capture);

						String carpeta = path;

						if (!config[1].isEmpty()) {

							carpeta = config[1];

						}

						ImageIO.write(Image, "jpg",
								new File(carpeta + Mthos.saberSeparador() + nombreVideo + "_" + contador + ".jpg"));

					}

					catch (Exception ex) {
						ex.printStackTrace();
					}

				}

			}

		});

		controlPanel.add(btnNewButton_1);

		controlPanel.add(volumControlerSlider);

		prev = new JButton("");

		prev.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				if (config[0] != null) {

					volumControlerSlider.setValue(Integer.parseInt(config[0]));

				}

				else {

					volumControlerSlider.setValue(100);

				}
			}

		});

		prev.setContentAreaFilled(false);

		prev.setBorder(null);

		prev.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/sound.png")));

		controlPanel.add(prev);

		prox = new JButton("");

		prox.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				volumControlerSlider.setValue(0);

			}

		});

		prox.setContentAreaFilled(false);

		prox.setBorder(null);

		prox.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/mute.png")));

		controlPanel.add(prox);

		listButton = new JButton();

		listButton.setContentAreaFilled(false);

		listButton.setBorder(null);

		listButton.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/nota.png")));

		listButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				playListFrame.setVisible(true);

			}

		});

		panel_1 = new DragAndDrop("", "");

		panel_1.setText("Drag And Drop Here");

		panel_1.setBackground(Color.WHITE);

		new UtilDragAndDrop(panel_1, panel_1.dragBorder, true, new UtilDragAndDrop.Listener() {

			@Override

			public void filesDropped(java.io.File[] archivos) {

				PlayListFrame.ponerEnCola(archivos);

			}

		});

		controlPanel.add(panel_1);

		controlPanel.add(listButton);

		progressTimePanel = new JPanel();

		panel.add(progressTimePanel, BorderLayout.NORTH);

		progressTimePanel.setLayout(new BorderLayout(0, 0));

		progressBar = new ProgressBarCustom(false);

		progressBar.setBackground(Color.WHITE);

		progressTimePanel.add(progressBar, BorderLayout.CENTER);

		progressBar.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				int x = e.getX();

				JVideoPlayer.jumpTo(((float) x / progressBar.getWidth()));

			}

		});

		currentLabel = new JLabel("00：00");
		currentLabel.setBackground(Color.WHITE);
		currentLabel.setForeground(Color.BLACK);

		progressTimePanel.add(currentLabel, BorderLayout.WEST);

		totalLabel = new JLabel("02：13");
		totalLabel.setForeground(Color.BLACK);
		totalLabel.setBackground(Color.WHITE);

		progressTimePanel.add(totalLabel, BorderLayout.EAST);

	}

	public EmbeddedMediaPlayer getMediaPlayer() {

		return playerComponent.getMediaPlayer();

	}

	public JProgressBar getProgressBar() {

		return progressBar;

	}

	public EmbeddedMediaPlayerComponent getPlayComponent() {

		return playerComponent;

	}

	public JButton getPlayButton() {

		return playButton;

	}

	public JPanel getControlPanel() {

		return controlPanel;

	}

	public void setFlag(int flag) {

		this.flag = flag;

	}

	public int getFlag() {

		return flag;

	}

	public JSlider getVolumControlerSlider() {

		return volumControlerSlider;

	}

	public JLabel getCurrentLabel() {

		return currentLabel;

	}

	public JLabel getTotalLabel() {

		return totalLabel;

	}

	public JPanel getProgressTimePanel() {

		return progressTimePanel;

	}

	public JButton getListButton() {

		return listButton;

	}

	public static PlayListFrame getPlayListFrame() {

		return playListFrame;

	}

}
