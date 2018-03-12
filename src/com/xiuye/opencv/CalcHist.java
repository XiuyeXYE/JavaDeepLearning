package com.xiuye.opencv;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.xiuye.imshow.IV;

public class CalcHist {

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		log("load libary");
	}
	{// new T()的时候调用,构造函数之前在{}中初始化
		log("TEST");
	}

	public static void main(String[] args) {

		Mat src = Highgui.imread("p1.jpg");
		IV.imshow("原图", src);

		Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);
		IV.imshow("灰度图", src);

		List<Mat> images = new ArrayList<>();
		images.add(src);

		MatOfInt channels = new MatOfInt(0);
		log("channels := " + channels.dump());
		MatOfInt histSize = new MatOfInt(256);
		log("histSize := " + histSize.dump());
		MatOfFloat ranges = new MatOfFloat(0, 255);
		log("ranges := " + ranges.dump());

		// hist仅相当于一个矩阵(数组)
		Mat hist = Mat.eye(src.rows(), src.cols(), CvType.CV_8UC1);
		Imgproc.calcHist(images, channels, new Mat(), hist, histSize, ranges);
		log("hist := " + hist.dump());

		MinMaxLocResult r = Core.minMaxLoc(hist);
		double maxVal = r.maxVal;
		double minVal = r.minVal;
		log("maxValue := " + maxVal);
		log("minValue := " + minVal);


		int size = 256;
		int scale = 3;//直方图条形宽度

//		Mat histImg = Mat.zeros(size, size * scale+10/*加大图像宽度*/, CvType.CV_8UC1);
		Mat histImg = new Mat(size, size * scale+10/*加大图像宽度*/, CvType.CV_8UC3/*,Scalar.all(255)*/);
		int hpt = (int) (0.9*size);
//		Random  randomColor = new Random();//设置随机颜色
		// log(hist.get(254, 0)[0]);
		for (int i = 0; i < hist.rows(); i++) {

			double binValue = hist.get(i, 0)[0];

			//因为值太大了,需要缩小,否则图形显示有问题
			int val = (int) (binValue/maxVal*hpt);

			Point p1 = new Point(i*scale,size);
			Point p2 = new Point((i+1)*scale-1/*下标不越界,-1*/,size - val);

//			Core.rectangle(histImg, p1, p2,
//					new Scalar(randomColor.nextInt(size),
//					randomColor.nextInt(size),randomColor.nextInt(size)),
//					Core.FILLED/*设置填充整个矩形*/);
			Core.rectangle(histImg, p1, p2,
					new Scalar(0xd6,0x9a,0x00),/*B,G,R*/
					Core.FILLED/*设置填充整个矩形*/);

		}
		IV.imshow("直方图", histImg);
		IV.waitKey(0);// 必须写上,否则不显示,按"ESC"退出
	}

	private static <T> void log(T t) {
		System.out.println(t);
	}

}
