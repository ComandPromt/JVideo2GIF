package com.views.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.dialog.confirm.MessageDialog;
import com.dialog.confirm.MessageDialog.MessageType;
import com.material.table.MaterialTable;

import mthos.JMthos;

@SuppressWarnings("serial")
public class Historial extends JPanel {

	public Historial() {

		setLayout(null);

		JPanel accionesBd = new JPanel();

		String db = JMthos.directorioActual() + "db" + JMthos.saberSeparador() + "video_gif.db";

		ArrayList<String> cabecera = new ArrayList<>();

		cabecera.add("ID");

		cabecera.add("COMANDO");

		cabecera.add("ORIGEN");

		cabecera.add("CONVERSION");

		MaterialTable panel = new MaterialTable(cabecera,
				JMthos.selectSqlite(db, "SELECT ID,COMANDO,ORIGEN,CONVERSION FROM CONVERSIONES ORDER BY ID DESC"), 2,
				2);

		addComponentListener(new ComponentAdapter() {

			@Override

			public void componentResized(ComponentEvent e) {

				accionesBd.setBounds(0, 0, getWidth(), Math.round(getHeight() * 0.05f));

				panel.setBounds(0, Math.round(getHeight() * 0.05f), getWidth(), Math.round(getHeight() * 0.95f));

			}

		});

		accionesBd.setBackground(Color.WHITE);

		accionesBd.setLayout(new GridLayout());

		NButton borrarTodo = new NButton("Clear");

		borrarTodo.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				MessageDialog dialogo = new MessageDialog(Color.RED, Color.WHITE, "", "Quieres vaciar la tabla?", null,
						new Font("Dialog", Font.PLAIN, 30));

				if (dialogo.getMessageType().equals(MessageType.OK)) {

					JMthos.deleteAllFromTableSqlite(db, "CONVERSIONES");

					panel.vaciarTabla();

				}

			}

		});

		accionesBd.add(borrarTodo);

		add(accionesBd);

		add(panel);

	}

}
