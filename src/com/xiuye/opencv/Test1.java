package com.xiuye.opencv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import javafx.embed.swing.SwingFXUtils;

public class Test1 {

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {

		Mat mat = Highgui.imread("D:/programming/DevelopingProjects/JavaAPILearning/houzi.jpg");
		byte[] data = new byte[mat.rows() * mat.cols() * (int) (mat.elemSize())];
		mat.get(0, 0, data);
		System.out.println(data.length);
		System.out.println(mat.rows() * mat.cols() * mat.channels());
		ByteArrayInputStream bais = new ByteArrayInputStream(data);

		DataInputStream dis = new DataInputStream(bais);
		System.out.println(bais);
		System.out.println(dis);
		System.out.println(mat.channels());
		System.out.println(mat.depth());
		BufferedImage img = new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_3BYTE_BGR);

		System.out.println(SwingFXUtils.toFXImage(img, null));

		img.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
		// BufferedImage src = ImageIO.read();
		Mat dest = Mat.eye(mat.rows(), mat.cols(), CvType.CV_16UC3);
		Imgproc.cvtColor(mat, dest, Imgproc.COLOR_RGB2BGR);
		Highgui.imwrite("dest.jpg", dest);
	}

}
