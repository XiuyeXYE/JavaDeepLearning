package com.xiuye.javafx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import sun.awt.image.ByteComponentRaster;
import sun.awt.image.IntegerComponentRaster;
import java.awt.image.DataBufferByte;

public class EighthDemo extends Application {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JavaFX Canvas");
		FlowPane root = new FlowPane();
		root.setHgap(10);
		root.setVgap(10);
		Canvas canvas = new Canvas(300,250);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawShapes(gc);

		root.getChildren().add(canvas);
		Image img = new Image("file:///D:/programming/DevelopingProjects/JavaAPILearning/background.jpg");

		ImageView iv = new ImageView(img);

		BufferedImage bf = ImageIO.read(new File("p1.jpg"));
		Mat mat = Highgui.imread("p1.jpg");
		byte[] data = new byte[mat.rows() * mat.cols() * (int) (mat.elemSize())];
		mat.get(0, 0, data);

		byte[] tartgetData = ((DataBufferByte)bf.getRaster().getDataBuffer()).getData();
		System.arraycopy(data, 0, tartgetData, 0, data.length);

//		bf.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
		img = SwingFXUtils.toFXImage(bf, null);
		iv.setImage(img);

//		System.out.println("==============");
//		int []data1 = new int[bf.getWidth()*bf.getHeight()];
//		bf.getRGB(0, 0, bf.getWidth(), bf.getHeight(), data1, 0, 2);


//		ByteComponentRaster icr = (ByteComponentRaster) bf.getRaster();
//		byte []data = icr.getDataStorage();

//		WritableImage wimg = new WritableImage(bf.getWidth(), bf.getHeight());
////		SwingFXUtils.toFXImage(bf, wimg);
//		PixelFormat<ByteBuffer> pf = PixelFormat.getByteBgraPreInstance();
//		PixelWriter pw = wimg.getPixelWriter();
////		System.out.println(pw);
//		System.out.println(icr.getScanlineStride());
////		System.out.println(data.length);
////		System.out.println(bf.getWidth()*bf.getHeight()*3);
////		System.out.println(bf.getType());
//		//icr.getScanlineStride()始终说数组越界,不知道咋回事,也许是bug吧
//		pw.setPixels(0, 0, bf.getWidth(), bf.getHeight(), pf, data, icr.getDataOffset(0), icr.getScanlineStride());
//		iv.setImage(wimg);

		root.getChildren().add(iv);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();


	}

	private void drawShapes(GraphicsContext gc) {
		PixelWriter pw = gc.getPixelWriter();
		for(int i=0;i<30;i++){
			for(int j=0;j<30;j++){
				pw.setColor(i, j, Color.RED);
			}
		}

		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5);


		gc.strokeLine(40, 10, 10, 40);
		gc.fillOval(10, 60, 30, 30);
		gc.strokeOval(60, 60, 30, 30);
		gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        //多边形(面)
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        //多边形(线)
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        //折线
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
        Image img = new Image("file:///D:/programming/DevelopingProjects/JavaAPILearning/background.jpg");
        gc.drawImage(img, 100, 100);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
