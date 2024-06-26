package com.play_list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.dialog.confirm.MessageDialog;
import com.draganddrop.DragAndDrop;
import com.draganddrop.UtilDragAndDrop;
import com.history.ListHistory;
import com.main.JVideoPlayer;
import com.util.Metodos;

public class PlayListFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private int flag;

	public static JList<String> list;

	private JScrollPane scrollPane;

	private JPanel panel;

	private JButton historyClearButton;

	public static DefaultListModel<String> dlm;

	private JButton searchButton;

	private JTextField searchField;

	private String search;

	private JPanel panel_1;

	static boolean subtitle;

	public static int indice;

	public void ponerEnCola(java.io.File[] archivos) {

		String video = "";

		String subtitulo = "";

		if (archivos.length == 0) {

			JVideoPlayer.clearHistory();

		}

		else {

			for (File f : archivos) {

				subtitle = false;

				if (Metodos.esVideo(f.getName())) {

					video = f.getAbsolutePath();

					JVideoPlayer.addHistory(f.getName(), f.getAbsolutePath());

				}

				else if (f.getName().trim().endsWith(".srt") && subtitulo.isEmpty()) {

					subtitulo = f.getAbsolutePath();

					JVideoPlayer.frame.getMediaPlayer().setSubTitleFile(subtitulo);

					subtitle = true;

				}

			}

			if (!subtitle && !dlm.isEmpty()) {

				JVideoPlayer.frame.getMediaPlayer().playMedia(video);

				JVideoPlayer.frame.getPlayButton()
						.setIcon(new ImageIcon(JVideoPlayer.class.getResource("/images/pause.png")));

			}

		}

	}

	private void buscar() {

		try {

			search = searchField.getText();

			long contador = Collections.list(dlm.elements()).stream().filter(nom -> nom.contains(search.toLowerCase()))
					.count();

			if (contador == 0) {

				contador = Collections.list(dlm.elements()).stream().filter(nom -> nom.contains(search.toUpperCase()))
						.count();

				if (contador > 0) {

					search = search.toUpperCase();

				}

				else {

					contador = Collections.list(dlm.elements()).stream().filter(nom -> nom.contains(search)).count();

				}

			}

			else {

				search = search.toLowerCase();

			}

			if (contador > 0) {

				DefaultListModel<String> busqueda = new DefaultListModel<String>();

				Stream<String> nombres5 = Collections.list(dlm.elements()).stream().filter(nom -> nom.contains(search));

				nombres5.forEach(n -> busqueda.addElement(n));

				nombres5.close();

				list.setModel(busqueda);

			}

			else {

				list.setModel(dlm);

			}

		}

		catch (Exception e1) {

		}

	}

	private void limpiarCache() {

		try {

			dlm.clear();

			JVideoPlayer.getListHistory();

			ListHistory.cleanHistory();

			list.setModel(dlm);

			scrollPane.setViewportView(getList());

			JVideoPlayer.clearHistory();

		}

		catch (Exception e1) {

			e1.printStackTrace();

		}

	}

	public PlayListFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(PlayListFrame.class.getResource("/images/name.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		subtitle = false;

		setBackground(Color.WHITE);

		flag = 0;

		list = new JList<String>();

		list.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_DELETE) {

					JVideoPlayer.deleteFromHistory(list.getSelectedIndex());

					getScrollPane().setViewportView(getList());

					ArrayList<String> videos = new ArrayList<String>();

					for (int i = 0; i < list.getModel().getSize(); i++) {

						videos.add(list.getModel().getElementAt(i));

					}

					JVideoPlayer.getListView().setList(videos);

					JVideoPlayer.escribir();

				}

			}

		});

		dlm = new DefaultListModel<String>();

		addWindowListener(new WindowAdapter() {

			@Override

			public void windowClosed(WindowEvent e) {

				flag = 1;

				JVideoPlayer.getControlFrame().getListButton()
						.setText(JVideoPlayer.getFrame().getListButton().getText());

			}

		});

		setMaximizedBounds(new Rectangle((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 400, 0, 400,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()));

		try {

			JVideoPlayer.getListHistory();

			if (!ListHistory.readHistory().isEmpty()) {

				setList(ListHistory.readHistory());

			}

		}

		catch (Exception e1) {

		}

		contentPane = new JPanel();

		contentPane.setBackground(Color.WHITE);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		scrollPane = new JScrollPane();

		scrollPane.setEnabled(false);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		list.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				indice = list.getSelectedIndex();

				if (JVideoPlayer.getListView().getList().isEmpty()) {

					JVideoPlayer.setearLista();

				}

				JVideoPlayer.openVideoFromList(list.getSelectedValue());

			}

		});

		contentPane.add(scrollPane, BorderLayout.CENTER);

		scrollPane.setViewportView(getList());

		panel = new JPanel();
		panel.setBackground(Color.WHITE);

		contentPane.add(panel, BorderLayout.NORTH);

		panel.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);

		panel.add(panel_1, BorderLayout.WEST);

		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		searchField = new JTextField();
		searchField.setHorizontalAlignment(SwingConstants.CENTER);

		searchField.addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					buscar();

				}

			}
		});

		searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));

		panel_1.add(searchField);

		searchField.setColumns(10);

		searchButton = new JButton("");

		searchButton.setContentAreaFilled(false);

		searchButton.setBorder(null);

		searchButton.setIcon(new ImageIcon(PlayListFrame.class.getResource("/images/search.png")));

		searchButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {

				buscar();

			}

		});

		panel_1.add(searchButton);

		historyClearButton = new JButton("");
		historyClearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1ActionPerformed(e);

			}
		});
		historyClearButton.setContentAreaFilled(false);

		historyClearButton.setBorder(null);

		historyClearButton.setIcon(new ImageIcon(PlayListFrame.class.getResource("/images/clean.png")));

		panel_1.add(historyClearButton);

		DragAndDrop btnNewButton = new DragAndDrop("");

		btnNewButton.setText("Drag And Drop Here");

		btnNewButton.setBackground(Color.WHITE);

		try {

			new UtilDragAndDrop(btnNewButton, btnNewButton.dragBorder, true, new UtilDragAndDrop.Listener() {

				@Override

				public void filesDropped(java.io.File[] archivos) {

					ponerEnCola(archivos);

				}

			});

		}

		catch (Exception e1) {

			e1.printStackTrace();

		}

		panel_1.add(btnNewButton);

		setSize(507, 243);

		setLocationRelativeTo(null);

	}

	protected void jButton1ActionPerformed(ActionEvent e) {

		MessageDialog obj = new MessageDialog(null, null, "Clear History", "Are You Sure To Clear History?", null,
				null);

		if (obj.getMessageType() == MessageDialog.MessageType.OK) {

			limpiarCache();

		}

	}

	public int getFlag() {

		return flag;

	}

	public void setFlag(int flag) {

		this.flag = flag;

	}

	public JList<String> getList() {

		return list;

	}

	public void setList(ArrayList<String> arrayList) {

		dlm = new DefaultListModel<String>();

		for (int i = 0; i < arrayList.size(); i++) {

			dlm.addElement(arrayList.get(i));

		}

		list.setModel(dlm);

	}

	public JScrollPane getScrollPane() {

		return scrollPane;

	}

}
