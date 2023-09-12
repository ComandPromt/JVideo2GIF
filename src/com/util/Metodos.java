package com.util;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import util.Mthos;

public class Metodos {

	public static String saberFechaActual() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");

		return dtf.format(LocalDateTime.now());

	}

	public static boolean esVideo(String name) {

		boolean resultado = false;

		try {

			name = name.toLowerCase();

			if (name.endsWith(".mp4") || name.endsWith(".avi") || name.endsWith(".mpg") || name.endsWith(".mkv")
					|| name.endsWith(".webm")) {

				resultado = true;

			}

		}

		catch (Exception e) {

		}

		return resultado;

	}

	public static void crearCarpeta() {

		try {

			String ruta = new File(".").getCanonicalPath() + Mthos.saberSeparador() + "log";

			File dir = new File(ruta);

			if (!dir.exists()) {

				dir.mkdir();

			}

			File file = new File(ruta + Mthos.saberSeparador() + "output.txt");

			if (!file.exists()) {

				file.createNewFile();

			}

			file = new File(ruta + Mthos.saberSeparador() + "output1.txt");

			if (!file.exists()) {

				file.createNewFile();

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
