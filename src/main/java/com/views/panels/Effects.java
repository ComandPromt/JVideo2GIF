package com.views.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.views.panels.effects.Bn;
import com.views.panels.effects.Brillo;
import com.views.panels.effects.Colors;
import com.views.panels.effects.Contraste;
import com.views.panels.effects.Fps;
import com.views.panels.effects.Hq;
import com.views.panels.effects.Loop;
import com.views.panels.effects.Reverse;
import com.views.panels.effects.Rotate;
import com.views.panels.effects.Speed;

public class Effects extends JPanel {

	private Reverse reverse;

	private Bn bn;

	private Brillo brillo;

	private Contraste contraste;

	private Colors colores;

	private Rotate rotate;

	private Speed speed;

	private Codec codec;

	private Loop loop;

	private Fps fps;

	private Hq hq;

	public Hq getHq() {

		return hq;

	}

	public Fps getFps() {

		return fps;

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

	public Codec getCodec() {

		return codec;

	}

	public Reverse getReverse() {

		return reverse;

	}

	public Bn getBn() {

		return bn;

	}

	public Loop getLoop() {

		return loop;

	}

	public Effects() {

		setLayout(new GridLayout(0, 1, 0, 0));

		fps = new Fps();

		loop = new Loop();

		reverse = new Reverse();

		bn = new Bn();

		brillo = new Brillo();

		contraste = new Contraste();

		colores = new Colors();

		rotate = new Rotate();

		speed = new Speed();

		codec = new Codec();

		hq = new Hq();

		add(hq);

		add(reverse);

		add(bn);

		add(brillo);

		add(contraste);

		add(colores);

		add(rotate);

		add(fps);

		add(speed);

		add(loop);

		add(codec);

	}

	public Brillo getBrillo() {

		return brillo;

	}

	public Contraste getConstraste() {

		return contraste;

	}

}
