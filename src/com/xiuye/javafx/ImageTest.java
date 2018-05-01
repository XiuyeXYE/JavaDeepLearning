package com.xiuye.javafx;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTest {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("houzi.jpg");
		byte []data = new byte[fis.available()];
		fis.read(data);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		Image img = new Image(bais);
		System.out.println(img);
		ImageView iv = new ImageView(img);
		System.out.println(iv);

	}

}
