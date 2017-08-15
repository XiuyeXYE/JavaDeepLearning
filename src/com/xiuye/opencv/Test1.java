package com.xiuye.opencv;


import java.awt.image.BufferedImage;



import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class Test1 {

	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {

		Mat mat = Highgui.imread("houzi.jpg");
		byte [] data = new byte[mat.rows()*mat.cols()*(int)(mat.elemSize())];
		mat.get(0, 0,data);
		BufferedImage img = new BufferedImage(mat.cols(), mat.rows(), BufferedImage.TYPE_BYTE_GRAY);
		img.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
//		BufferedImage src = ImageIO.read();
		Mat dest = Mat.eye(mat.rows(), mat.cols(), CvType.CV_16UC1);
		Imgproc.cvtColor(mat, dest, Imgproc.COLOR_GRAY2BGR);
		Highgui.imwrite("dest.jpg", mat);
	}

}
