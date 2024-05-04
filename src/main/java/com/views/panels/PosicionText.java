package com.views.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.radiobutton.RadioButtonCustom;

public class PosicionText extends JPanel {

	private int pos;

	public PosicionText() {

		RadioButtonCustom rdbtnNewRadioButton = new RadioButtonCustom("1");

		RadioButtonCustom rdbtnNewRadioButton_1 = new RadioButtonCustom("2");

		RadioButtonCustom rdbtnNewRadioButton_6 = new RadioButtonCustom("3");

		RadioButtonCustom rdbtnNewRadioButton_2 = new RadioButtonCustom("4");

		RadioButtonCustom rdbtnNewRadioButton_3 = new RadioButtonCustom("5");

		RadioButtonCustom rdbtnNewRadioButton_4 = new RadioButtonCustom("6");

		RadioButtonCustom rdbtnNewRadioButton_8 = new RadioButtonCustom("7");

		RadioButtonCustom rdbtnNewRadioButton_7 = new RadioButtonCustom("8");

		RadioButtonCustom rdbtnNewRadioButton_5 = new RadioButtonCustom("9");

		rdbtnNewRadioButton_5.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton_5.isSelected()) {

					pos = 9;

					rdbtnNewRadioButton.setSelected(false);

					rdbtnNewRadioButton_1.setSelected(false);

					rdbtnNewRadioButton_6.setSelected(false);

					rdbtnNewRadioButton_2.setSelected(false);

					rdbtnNewRadioButton_3.setSelected(false);

					rdbtnNewRadioButton_4.setSelected(false);

					rdbtnNewRadioButton_8.setSelected(false);

					rdbtnNewRadioButton_7.setSelected(false);

				}

			}

		});

		rdbtnNewRadioButton_7.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton_7.isSelected()) {

					pos = 8;

					rdbtnNewRadioButton.setSelected(false);

					rdbtnNewRadioButton_1.setSelected(false);

					rdbtnNewRadioButton_6.setSelected(false);

					rdbtnNewRadioButton_2.setSelected(false);

					rdbtnNewRadioButton_3.setSelected(false);

					rdbtnNewRadioButton_4.setSelected(false);

					rdbtnNewRadioButton_8.setSelected(false);

					rdbtnNewRadioButton_5.setSelected(false);

				}

			}

		});

		rdbtnNewRadioButton_8.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton_8.isSelected()) {

					pos = 7;

					rdbtnNewRadioButton.setSelected(false);

					rdbtnNewRadioButton_1.setSelected(false);

					rdbtnNewRadioButton_6.setSelected(false);

					rdbtnNewRadioButton_2.setSelected(false);

					rdbtnNewRadioButton_3.setSelected(false);

					rdbtnNewRadioButton_4.setSelected(false);

					rdbtnNewRadioButton_7.setSelected(false);

					rdbtnNewRadioButton_5.setSelected(false);

				}

			}

		});

		rdbtnNewRadioButton_4.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton_4.isSelected()) {

					pos = 6;

					rdbtnNewRadioButton.setSelected(false);

					rdbtnNewRadioButton_1.setSelected(false);

					rdbtnNewRadioButton_6.setSelected(false);

					rdbtnNewRadioButton_2.setSelected(false);

					rdbtnNewRadioButton_3.setSelected(false);

					rdbtnNewRadioButton_8.setSelected(false);

					rdbtnNewRadioButton_7.setSelected(false);

					rdbtnNewRadioButton_5.setSelected(false);

				}

			}

		});

		rdbtnNewRadioButton_3.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton_3.isSelected()) {

					pos = 5;

					rdbtnNewRadioButton.setSelected(false);

					rdbtnNewRadioButton_1.setSelected(false);

					rdbtnNewRadioButton_6.setSelected(false);

					rdbtnNewRadioButton_2.setSelected(false);

					rdbtnNewRadioButton_4.setSelected(false);

					rdbtnNewRadioButton_8.setSelected(false);

					rdbtnNewRadioButton_7.setSelected(false);

					rdbtnNewRadioButton_5.setSelected(false);

				}

			}

		});

		setBackground(Color.WHITE);

		setLayout(new GridLayout(3, 3));

		rdbtnNewRadioButton_2.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton_2.isSelected()) {

					pos = 4;

					rdbtnNewRadioButton.setSelected(false);

					rdbtnNewRadioButton_1.setSelected(false);

					rdbtnNewRadioButton_6.setSelected(false);

					rdbtnNewRadioButton_3.setSelected(false);

					rdbtnNewRadioButton_4.setSelected(false);

					rdbtnNewRadioButton_8.setSelected(false);

					rdbtnNewRadioButton_7.setSelected(false);

					rdbtnNewRadioButton_5.setSelected(false);

				}

			}

		});

		rdbtnNewRadioButton_6.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton_6.isSelected()) {

					pos = 3;

					rdbtnNewRadioButton.setSelected(false);

					rdbtnNewRadioButton_1.setSelected(false);

					rdbtnNewRadioButton_2.setSelected(false);

					rdbtnNewRadioButton_3.setSelected(false);

					rdbtnNewRadioButton_4.setSelected(false);

					rdbtnNewRadioButton_8.setSelected(false);

					rdbtnNewRadioButton_7.setSelected(false);

					rdbtnNewRadioButton_5.setSelected(false);

				}

			}

		});

		rdbtnNewRadioButton_1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton_1.isSelected()) {

					pos = 2;

					rdbtnNewRadioButton.setSelected(false);

					rdbtnNewRadioButton_6.setSelected(false);

					rdbtnNewRadioButton_2.setSelected(false);

					rdbtnNewRadioButton_3.setSelected(false);

					rdbtnNewRadioButton_4.setSelected(false);

					rdbtnNewRadioButton_8.setSelected(false);

					rdbtnNewRadioButton_7.setSelected(false);

					rdbtnNewRadioButton_5.setSelected(false);

				}
			}

		});

		rdbtnNewRadioButton.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (rdbtnNewRadioButton.isSelected()) {

					pos = 1;

					rdbtnNewRadioButton_1.setSelected(false);

					rdbtnNewRadioButton_6.setSelected(false);

					rdbtnNewRadioButton_2.setSelected(false);

					rdbtnNewRadioButton_3.setSelected(false);

					rdbtnNewRadioButton_4.setSelected(false);

					rdbtnNewRadioButton_8.setSelected(false);

					rdbtnNewRadioButton_7.setSelected(false);

					rdbtnNewRadioButton_5.setSelected(false);

				}

			}

		});

		rdbtnNewRadioButton.setBackground(Color.WHITE);

		rdbtnNewRadioButton.setLeft(100);

		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);

		add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1.setBackground(Color.WHITE);

		rdbtnNewRadioButton_1.setLeft(100);

		add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton_6.setBackground(Color.WHITE);

		rdbtnNewRadioButton_6.setLeft(100);

		add(rdbtnNewRadioButton_6);

		rdbtnNewRadioButton_2.setBackground(Color.WHITE);

		rdbtnNewRadioButton_2.setLeft(100);

		add(rdbtnNewRadioButton_2);

		rdbtnNewRadioButton_3.setBackground(Color.WHITE);

		rdbtnNewRadioButton_3.setLeft(100);

		add(rdbtnNewRadioButton_3);

		rdbtnNewRadioButton_4.setBackground(Color.WHITE);

		rdbtnNewRadioButton_4.setLeft(100);

		add(rdbtnNewRadioButton_4);

		rdbtnNewRadioButton_8.setBackground(Color.WHITE);

		rdbtnNewRadioButton_8.setLeft(100);

		add(rdbtnNewRadioButton_8);

		rdbtnNewRadioButton_7.setBackground(Color.WHITE);

		rdbtnNewRadioButton_7.setLeft(100);

		add(rdbtnNewRadioButton_7);

		rdbtnNewRadioButton_5.setBackground(Color.WHITE);

		rdbtnNewRadioButton_5.setLeft(100);

		add(rdbtnNewRadioButton_5);

		rdbtnNewRadioButton.setSelected(true);

		pos = 1;

	}

	public int getPos() {

		return pos;

	}

}
