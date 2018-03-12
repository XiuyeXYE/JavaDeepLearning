package com.xiuye.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class ImageOp1 {

	public static void main(String[] args) {
		try {

			BufferedImage bf = ImageIO.read(new File("./p1.jpg"));
			log("width:");
			log(bf.getWidth());
			log("height:");
			log(bf.getHeight());
			Graphics2D g2d = (Graphics2D) bf.getGraphics();
			g2d.setColor(Color.RED);
			g2d.setFont(new Font("Default", Font.BOLD, 100));
			g2d.drawRect(100, 100, 100, 100);
			ImageIO.write(bf, "png", new File("out.png"));
			log("over");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void log(String s) {
		System.out.println(s);
	}

	private static void log(int s) {
		System.out.println(s);
	}
}
