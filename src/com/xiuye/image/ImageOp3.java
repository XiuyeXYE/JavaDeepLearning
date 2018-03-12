package com.xiuye.image;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ImageOp3 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ImageView iv = new ImageView();
		Group g = new Group(iv);

		Image i = new Image("file:///D:/programming/DevelopingProjects/JavaAPILearning/p1.jpg");
		Image i2 = new Image("file:///D:/programming/DevelopingProjects/JavaAPILearning/p2.jpg");
		Image []is = new Image[2];
		is[0] = i;
		is[1] = i2;

//		iv.setImage(i);
		primaryStage.setScene(new Scene(g));
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		t.setAutoReverse(true);
		t.getKeyFrames().add(new KeyFrame(Duration.ZERO,new KeyValue(iv.imageProperty(),i2)));
		t.getKeyFrames().add(new KeyFrame(new Duration(1000),new KeyValue(iv.imageProperty(),i)));
		t.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				log("timeline end");
			}

		});

		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				System.out.println(now);
			}
		};

		t.play();
		timer.start();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private static <T> void log(T t) {
		System.out.println(t);
	}
}
