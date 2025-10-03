package com.views.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.views.panels.effects.Bn;
import com.views.panels.effects.Brillo;
import com.views.panels.effects.Colors;
import com.views.panels.effects.Contraste;
import com.views.panels.effects.Fps;
import com.views.panels.effects.Loop;
import com.views.panels.effects.Reverse;
import com.views.panels.effects.Rotate;
import com.views.panels.effects.Speed;

@SuppressWarnings("serial")
public class Effects extends JPanel {

	private Reverse reverse;

	private Bn bn;

	private Brillo brillo;

	private Contraste contraste;

	private Colors colores;

	private Rotate rotate;

	private Speed speed;

	private Loop loop;

	private Fps fps;

	public Reverse getReverse() {
		return reverse;
	}

	public Bn getBn() {
		return bn;
	}

	public Brillo getBrillo() {
		return brillo;
	}

	public Contraste getContraste() {
		return contraste;
	}

	public Colors getColores() {
		return colores;
	}

	public Rotate getRotate() {
		return rotate;
	}

	public Speed getSpeed() {
		return speed;
	}

	public Loop getLoop() {
		return loop;
	}

	public Fps getFps() {
		return fps;
	}

	public Effects() {

		setLayout(new GridLayout(0, 1, 0, 0));

		reverse = new Reverse();

		bn = new Bn();

		brillo = new Brillo();

		contraste = new Contraste();

		colores = new Colors();

		rotate = new Rotate();

		speed = new Speed();

		loop = new Loop();

		fps = new Fps();

		add(reverse);

		add(bn);

		add(brillo);

		add(contraste);

		add(colores);

		add(rotate);

		add(speed);

		add(loop);

		add(fps);

	}

}
