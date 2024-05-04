package com.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import com.jmenu.horizontal.HorizontalMenu;
import com.views.panels.effects.Brillo;
import com.views.panels.effects.Contraste;

@SuppressWarnings("all")

public class Configuracion extends javax.swing.JFrame {

	public Configuracion() throws IOException {

		setTitle("");

		initComponents();

		setVisible(true);

	}

	public static void main(String[] args) {

		try {

			new Configuracion().setVisible(true);

		}

		catch (Exception e) {

		}

	}

	public void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		ArrayList<String> lista = new ArrayList();

		lista.add("B/C");

		lista.add("Blur");

		lista.add("Watermark");

		lista.add("Efects");

		lista.add("Codec");

		ArrayList<JComponent> componentes = new ArrayList<>();

		componentes.add(new Brillo());

		componentes.add(new Contraste());

		HorizontalMenu panel = new HorizontalMenu(lista, componentes, 20);

		getContentPane().add(panel);

		setSize(new Dimension(532, 433));

		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public void stateChanged(ChangeEvent e) {

	}
}
