package com.xiuye.javafx;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.stage.Stage;

public class SixthDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// 点光源图形亮度暗,不好看
		PointLight pl = new PointLight(Color.WHITE);
		pl.setRotate(45);
		pl.setTranslateZ(-600);// 屏幕外为z轴负方向,内为正方向!

		// 整个材质发光效果不好,看不清图形边缘(轮廓线)
		AmbientLight al = new AmbientLight(Color.WHITE);

		Cylinder myCyLinker = new Cylinder(50, 70);
		Material m = new PhongMaterial(Color.GREEN);
		Image normalMap = new Image("file:\\D:\\programming\\DevelopingProjects\\JavaAPILearning\\background.jpg");
		((PhongMaterial)m).setBumpMap(normalMap);
		myCyLinker.setRotationAxis(new Point3D(1, 1, 0));
		myCyLinker.setMaterial(m);
		myCyLinker.setTranslateX(200);
		myCyLinker.setTranslateY(200);

		myCyLinker.setRotate(100);

		Group lg = new Group();
		// 光源和图形在一个group中!
		// 环境光和点光源一起效果好!
		lg.getChildren().addAll(myCyLinker, pl, al);
		SubScene subScene = new SubScene(lg, 600, 400, true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BLACK);
		subScene.setCamera(new PerspectiveCamera());

		FlowPane root = new FlowPane();

		root.setHgap(10);
		root.setVgap(10);

		root.getChildren().add(subScene);

		Slider s = new Slider(0, 360, 50);
		root.getChildren().add(s);

		myCyLinker.rotateProperty().bind(s.valueProperty());

		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
