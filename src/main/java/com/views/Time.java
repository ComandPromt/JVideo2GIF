package com.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.buttons.round.NButton;
import com.spinner.decimal.DecimalSpinner;

public class Time extends JPanel {

	private NButton btn1;

	private NButton btn2;

	private DecimalSpinner time1;

	private DecimalSpinner time2;

	public NButton getBtn1() {

		return btn1;

	}

	public NButton getBtn2() {

		return btn2;

	}

	public DecimalSpinner getTime1() {

		return time1;

	}

	public void setTime1(DecimalSpinner time1) {

		this.time1 = time1;

	}

	public DecimalSpinner getTime2() {

		return time2;

	}

	public void setTime2(DecimalSpinner time2) {

		this.time2 = time2;

	}

	public Time() {

		setBackground(Color.WHITE);

		setLayout(new GridLayout(1, 0, 0, 0));

		btn1 = new NButton("");

		btn1.setForeground(Color.WHITE);

		btn1.setContentAreaFilled(false);

		btn1.setBorderColor(Color.WHITE);

		btn1.setBorder(null);

		btn1.setBackground(new Color(224, 224, 224));

		btn1.setIcon(new ImageIcon(getClass().getResource("/images/time_1.png")));

		time1 = new DecimalSpinner("  Start");

		time1.setHeaderFont(new Font("Dialog", Font.PLAIN, 16));

		time1.setFont(new Font("Dialog", Font.PLAIN, 16));

		time1.setForeground(Color.WHITE);

		time1.setBackground(Color.WHITE);

		btn2 = new NButton("");

		btn2.setIcon(new ImageIcon(getClass().getResource("/images/time_2.png")));

		btn2.setContentAreaFilled(false);

		btn2.setBorderColor(Color.WHITE);

		btn2.setBorder(null);

		btn2.setBackground(new Color(224, 224, 224));

		time2 = new DecimalSpinner("  Time");

		time2.setHeaderFont(new Font("Dialog", Font.PLAIN, 16));

		time2.setFont(new Font("Dialog", Font.PLAIN, 16));

		time2.setMinValor(0.0f);

		time2.setIncremento(0.25f);

		time2.setForeground(Color.WHITE);

		time2.setBackground(Color.WHITE);

		time2.sumarAlto(15);

		time1.sumarAlto(15);

		add(btn1);

		add(time1);

		add(btn2);

		add(time2);

	}

}
