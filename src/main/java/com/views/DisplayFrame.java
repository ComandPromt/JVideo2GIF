package com.views;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TooManyListenersException;
import java.util.stream.Collectors;

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

import com.buttons.round.NButton;
import com.checkbox.CheckBoxCustom;
import com.draganddrop.DragAndDrop;
import com.draganddrop.UtilDragAndDrop;
import com.file.nativ.chooser.DemoJavaFxStage;
import com.history.ListHistory;
import com.main.JVideoPlayer;
import com.main.VideoPanel;
import com.main.VideoViewer;
import com.message.alerts.PopupAlerts;
import com.message.alerts.PopupAlerts.AlertType;
import com.play_list.PlayListFrame;
import com.progressBar.ProgressBarCustom;
import com.slider.JSliderCustom;
import com.util.Metodos;

import main.JFfmpeg;
import mthos.JMthos;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

@SuppressWarnings("serial")

public class DisplayFrame extends JFrame {

	private JPanel contentPane;

	private EmbeddedMediaPlayerComponent playerComponent;

	public static String nombreVideo;

	private JPanel panel;

	private JButton stopButton;

	private static JButton playButton;

	private JPanel controlPanel;

	private ProgressBarCustom progressBar;

	private JSliderCustom volumControlerSlider;

	private JMenuBar menuBar;

	private JMenu mnFile;

	private JMenuItem mntmOpenVideo;

	private JMenuItem mntmOpenSubtitle;

	private JButton forwardButton;

	private JButton backwordButton;

	private JButton fullScreenButton;

	private int flag;

	private KeyBordListenerEvent kble;

	private JPanel progressTimePanel;

	private JLabel currentLabel;

	private JLabel totalLabel;

	private PlayListFrame playListFrame;

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

	private static long contador;

	private String path;

	private NButton btnNewButton_4;

	private LinkedList<String> comandos;

	private DisplayFrame frame;

	private JPanel panel_8;

	private DragAndDrop panel_1;

	public VideoViewer ventanaCrop;

	private Point crop;

	private Time panel_2;

	public static Config configuracion;

	public static VideoPanel videoPanel;

	private CheckBoxCustom btnNewButton_2;

	private CheckBoxCustom chckbxNewCheckBox;

	private PopupAlerts alerta;

	private ArrayList<String> listaComprobacion;

	private void saberComandos(String archivo) {

		boolean reverse = false;

		boolean bn = false;

		int tpyeBn = 0;

		float brillo = 0;

		int boxBlur = 0;

		float contraste = 0;

		int colores = 0;

		int rotate = 0;

		int speed = 0;

		String videoCodec = "libx264";

		String audioCodec = "acc";

		String crf = "";

		String qv = "";

		String blur = "";

		if (configuracion != null) {

			reverse = configuracion.getEfectos().getReverse().getChckbxNewCheckBox().isSelected();

			bn = configuracion.getEfectos().getBn().getChckbxNewCheckBox().isSelected();

			tpyeBn = configuracion.getEfectos().getBn().getComboBox().getSelectedIndex();

			brillo = configuracion.getEfectos().getBrillo().getChckbxNewCheckBox().getValor();

			contraste = configuracion.getEfectos().getContraste().getChckbxNewCheckBox().getValor();
			colores = configuracion.getEfectos().getColores().getChckbxNewCheckBox().getValor();

			rotate = configuracion.getEfectos().getRotate().getChckbxNewCheckBox().getValor();

			speed = configuracion.getEfectos().getSpeed().getChckbxNewCheckBox().getValor();

			videoCodec = configuracion.getCodec().getVideoCodec().getText();

			audioCodec = configuracion.getCodec().getAudioCodec().getText();

			boxBlur = configuracion.getBlur().getBlur().getValor();

			blur = configuracion.getBlur().getBrillo().getValor() + "x" + boxBlur + "x"
					+ configuracion.getBlur().getDatoX().getValor() + "x"
					+ configuracion.getBlur().getDatoY().getValor() + "x"
					+ configuracion.getBlur().getAncho().getValor() + "x"
					+ configuracion.getBlur().getAlto().getValor();

			if (configuracion.getCrop().isCrop() && (!videoPanel.getCrop().startsWith("0")
					&& !videoPanel.getCrop().substring(videoPanel.getCrop().indexOf("x")).startsWith("0"))) {

				comandos.add("-crop");

				comandos.add(videoPanel.getCrop());

			}

			if (reverse) {

				comandos.add("-reverse");

			}

			if (brillo > 0f) {

				comandos.add("-brillo");

				comandos.add(Float.toString(brillo));

			}

			if (contraste > 0f) {

				comandos.add("-contrate");

				comandos.add(Float.toString(contraste));

			}

			if (colores > 0) {

				comandos.add("-colors");

				comandos.add(Integer.toString(colores));

			}

			if (rotate > 0) {

				comandos.add("-rotate");

				comandos.add("#" + rotate);

			}

			if (speed > 0) {

				comandos.add("-speed");

				comandos.add(Integer.toString(speed));

			}

			if (bn) {

				comandos.add("-bn");

				comandos.add(Integer.toString(tpyeBn));

			}

			if (boxBlur > 0) {

				comandos.add("-blur");

				comandos.add(blur);

				comandos.add("-blurParams");

				comandos.add(configuracion.getBlur().getTransparencia() + "-"
						+ JMthos.colorToHex(configuracion.getBlur().getColor()) + "x"
						+ configuracion.getBlur().getTransparenciaColor());

			}

			if (configuracion.getQuality().getHq().getChckbxNewCheckBox().isSelected()) {

				comandos.add("-good");

			}

			else {

				comandos.add("-bad");

			}

			comandos.add("-fps");

			comandos.add(Integer.toString(configuracion.getEfectos().getFps().getChckbxNewCheckBox().getValor()));

			comandos.add("-loop");

			comandos.add(Integer.toString(configuracion.getEfectos().getLoop().getChckbxNewCheckBox().getValor()));

			comandos.add("-codec");

			comandos.add(videoCodec + " " + audioCodec);

			if (configuracion.getWatermark().getWatermark()) {

				comandos.add("-watermark");

				comandos.add(configuracion.getWatermark().getArchivo());

				comandos.add("-text-watermark");

				comandos.add(configuracion.getWatermark().getText());

				comandos.add("--pos-watermark");

				comandos.add(Integer.toString(configuracion.getWatermark().getPosWatermark()));

				comandos.add("--pos-text-watermark");

				comandos.add(Integer.toString(configuracion.getWatermark().getPos()));

				comandos.add("--color-watermark-text");

				comandos.add(JMthos.colorToHex(configuracion.getWatermark().getColor().getColor()));

				comandos.add("-font-size-text-watermark");

				comandos.add(Integer.toString(configuracion.getWatermark().getFuente().getFontSize()));

			}

		}

		else {

			comandos.add("-fps");

			comandos.add("25");

			comandos.add("-good");

		}

		// Leo de la bd

//		// comandos[9] = "\"crop=" + cropSize.x + ":" + cropSize.y + ":" + crop.x + ":"
//		// + crop.y + "\"";
//

	}

	public Point getCrop() {

		return crop;

	}

	public void setCrop(Point crop) {

		this.crop = crop;

	}

	private void ponerTiempo(boolean primerTiempo) {

		if (!JVideoPlayer.getListView().getList().isEmpty()) {

			float tiempo = JVideoPlayer.getFrame().getMediaPlayer().getTime() / 1000f;

			if (primerTiempo) {

				panel_2.getTime1().setValor(tiempo);

			}

			else {

				if (panel_2.getTime2().getValor() == 0f) {

					panel_2.getTime2().setValor(1f);

				}

				else if (tiempo > panel_2.getTime1().getValor()) {

					panel_2.getTime2().setValor(tiempo - panel_2.getTime1().getValor());

				}

				else {

					panel_2.getTime1().setValor(0f);

				}

			}

		}

		else {

			if (primerTiempo) {

				panel_2.getTime1().setValor(0f);

			}

			else {

				panel_2.getTime2().setValor(0f);

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

		}

		catch (Exception e) {

		}

		JVideoPlayer.play();

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

	public void conversion() {

		if (btnNewButton_2.isSelected()) {

			for (int i = 0; i < JVideoPlayer.getListView().getList().size(); i++) {

				PlayListFrame.indice = i;

				convertir();

			}

		}

		else {

			convertir();

		}

	}

	public void convertir() {

		try {

			alerta.setSize(600, 350);

			alerta.setFuente(getFont().deriveFont(40f));

			listaComprobacion = JVideoPlayer.getListView().getList();

			if (listaComprobacion.isEmpty()) {

				alerta.mensaje("Por favor, reproduzca un vídeo", AlertType.WARNING, 0, "");

			}

			else {

				btnNewButton_4.setEnabled(false);

				// mirar indice

				String archivo = JVideoPlayer.getListView().getMap().get(listaComprobacion.get(PlayListFrame.indice));

				comandos.clear();

				comandos.add("-i");

				comandos.add(archivo);

				if (chckbxNewCheckBox.isSelected()) {

					comandos.add("-ss");

					comandos.add("0");

					comandos.add("-t");

					comandos.add("");

				}

				else {

					comandos.add("-ss");

					comandos.add(Float.toString(panel_2.getTime1().getValor()));

					comandos.add("-t");

					float valor = panel_2.getTime2().getValor();

					if (valor == 0f) {

						comandos.add("");

					}

					else {

						comandos.add(Float.toString(valor));

					}

				}

				saberComandos(archivo);

				comandos.add("-y");

				String extension = "gif";

				if (DisplayFrame.configuracion != null && configuracion.getAjustes() != null
						&& !configuracion.getAjustes().getExtension().getText().trim().isEmpty()) {

					extension = configuracion.getAjustes().getExtension().getText().trim();

					extension = extension.replace(".", "");

				}

				if (configuracion == null) {

					comandos.add(archivo.substring(0, archivo.lastIndexOf(".")) + "_" + Metodos.saberFechaActual() + "."
							+ extension);

				}

				else {

					if (configuracion.getAjustes().getOutput().getFile().isEmpty()) {

						comandos.add(archivo.substring(0, archivo.lastIndexOf(".")) + "_" + Metodos.saberFechaActual()
								+ "." + extension);

					}

					else {

						comandos.add(
								configuracion.getAjustes().getOutput().getFile()
										+ archivo.substring(archivo.lastIndexOf(JMthos.saberSeparador()) + 1,
												archivo.lastIndexOf("."))
										+ "_" + Metodos.saberFechaActual() + "." + extension);

					}

				}

				JFfmpeg conversion = null;

				if (configuracion != null) {
					System.out.println("AAAAA-------------------------------------------------");
					System.out.println(comandos);
					System.out.println("AAAAA-------------------------------------------------");

					if (btnNewButton_2.isSelected()) {

					}

					else {

						conversion = new JFfmpeg(comandos.toArray(new String[0]),
								configuracion.getAjustes().getPlayFile().isSelected());

						if (configuracion.getAjustes().getOpenFolder().isSelected()) {

							JMthos.abrir(comandos.getLast().substring(0,
									comandos.getLast().lastIndexOf(JMthos.saberSeparador())));

						}

					}

				}

				else {

					System.out.println("BBBB-------------------------------------------------");

					System.out.println(comandos);

					System.out.println("BBBB-------------------------------------------------");

					if (btnNewButton_2.isSelected()) {

						ArrayList<String> historialReproductor = ListHistory.readHistory();

						Map<String, String> mapa = JVideoPlayer.getListView().getMap();

						for (int i = 0; i < historialReproductor.size(); i++) {

							System.out.println("aaa " + comandos.getLast());

							if (i > 0) {

								comandos.set(1, mapa.get(historialReproductor.get(i)));

							}

							conversion = new JFfmpeg(comandos.toArray(new String[0]), true);

						}

					}

					else {

						conversion = new JFfmpeg(comandos.toArray(new String[0]), true);

					}

				}

				btnNewButton_4.setEnabled(true);

				String dbName = JMthos.directorioActual() + "db" + JMthos.saberSeparador() + "video_gif.db";

				boolean perfil = false;

				int idPerfil = 0;

				if (configuracion != null) {

					perfil = configuracion.getPerfiles().getUsarPerfil().isSelected();

				}

				if (perfil) {

				}

				ArrayList<String> columnas = new ArrayList<>();

				ArrayList<String> valores = new ArrayList<>();

				columnas.add("PERFIL");

				columnas.add("CONVERSION");

				columnas.add("ORIGEN");

				columnas.add("COMANDO");

				valores.add(Integer.toString(idPerfil));

				valores.add(comandos.getLast());

				valores.add(archivo);

				valores.add(conversion.getOutput());

				insertSQLite(dbName, "CONVERSIONES", columnas, valores);

				alerta.mensaje("Conversion realizada", AlertType.INFO, 0, "");

			}

		}

		catch (Exception e1) {

			e1.printStackTrace();

		}

	}

	public static ArrayList<String> selectSQlite(String dbName, String query, List<String> columns) {

		String url = "jdbc:sqlite:" + dbName;

		ArrayList<String> resultados = new ArrayList<>();

		try {

			Class.forName("org.sqlite.JDBC");

			Connection connection = DriverManager.getConnection(url);

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				for (int i = 0; i < columns.size(); i++) {

					resultados.add(resultSet.getString(columns.get(i)));

				}

			}

			resultSet.close();

			statement.close();

			connection.close();

		}

		catch (Exception e) {

		}

		return resultados;

	}

	public static void insertSQLite(String db, String table, List<String> columns, List<String> values) {

		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db);

				PreparedStatement pstmt = conn
						.prepareStatement("INSERT INTO " + table + " (" + String.join(",", columns) + ") VALUES ("
								+ values.stream().map(v -> "?").collect(Collectors.joining(",")) + ")")) {

			for (int i = 0; i < values.size(); i++) {

				pstmt.setString(i + 1, values.get(i));

			}

			pstmt.executeUpdate();

		}

		catch (SQLException e) {

		}

	}

	public DisplayFrame() throws TooManyListenersException {

		alerta = new PopupAlerts(600, 200);

		comandos = new LinkedList<>();

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

			path = new File(".").getCanonicalPath() + JMthos.saberSeparador() + "screenshots";

			if (config[1] != null) {

				path = config[1];

				contador = JMthos.listarFicherosPorCarpeta(path, "jpg") + 1;

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

					Config config = new Config(ventanaCrop, frame);

					config.setVisible(true);

				}

				catch (IOException e1) {

				}

			}

		});

		mntmNewMenuItem_1.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/settings.png")));

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

		controlPanel.add(playButton);

		volumControlerSlider = new JSliderCustom();

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
								new File(carpeta + JMthos.saberSeparador() + nombreVideo + "_" + contador + ".jpg"));

					}

					catch (Exception ex) {

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

		panel_1 = new DragAndDrop("");

		panel_1.setText("Drag And Drop Here");

		panel_1.setBackground(Color.WHITE);

		new UtilDragAndDrop(panel_1, panel_1.dragBorder, true, new UtilDragAndDrop.Listener() {

			@Override

			public void filesDropped(java.io.File[] archivos) {

				playListFrame.ponerEnCola(archivos);

			}

		});

		controlPanel.add(panel_1);

		controlPanel.add(listButton);

		progressTimePanel = new JPanel();

		panel.add(progressTimePanel, BorderLayout.NORTH);

		progressTimePanel.setLayout(new BorderLayout(0, 0));

		progressBar = new ProgressBarCustom();

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

		frame = this;

		panel_8 = new JPanel();

		panel_8.setBackground(Color.WHITE);

		panel_8.setLayout(new GridLayout());

		panel_2 = new Time();

		panel_2.setBackground(Color.WHITE);

		panel_2.getBtn1().addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				ponerTiempo(true);

			}

		});

		panel_2.getBtn2().addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				ponerTiempo(false);

				ponerTiempo(false);

			}

		});

		menuBar.add(panel_2);

		chckbxNewCheckBox = new CheckBoxCustom("ALL");

		chckbxNewCheckBox.setLeft(40);

		chckbxNewCheckBox.setPosition(new Point(65, 35));

		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));

		chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);

		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);

		panel_8.add(chckbxNewCheckBox);

		btnNewButton_2 = new CheckBoxCustom("Convert All");

		btnNewButton_2.setPosition(new Point(65, 36));

		btnNewButton_2.setLeft(40);

		btnNewButton_2.setFocusPainted(false);

		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnNewButton_2.setText("Bulk");

		btnNewButton_2.setBackground(Color.LIGHT_GRAY);

		panel_8.add(btnNewButton_2);

		btnNewButton_4 = new NButton("Convert");

		btnNewButton_4.setBackground(Color.WHITE);

		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnNewButton_4.setContentAreaFilled(false);

		btnNewButton_4.setBorder(null);

		btnNewButton_4.setIcon(new ImageIcon(DisplayFrame.class.getResource("/images/video_2_frame.png")));

		btnNewButton_4.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				convertir();

			}

		});

		panel_8.add(btnNewButton_4);

		panel_8.add(mntmNewMenuItem_1);

		menuBar.add(panel_8);

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

	public PlayListFrame getPlayListFrame() {

		return playListFrame;

	}

}
