package com.xiuye.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

public class ForthDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setSpecularColor(Color.ORANGE);
		redMaterial.setDiffuseColor(Color.RED);

		Cylinder myCylinder = new Cylinder(150, 100);
		Box box = new Box(100, 100, 100);
		box.setMaterial(redMaterial);
		Sphere mySphere = new Sphere(100);

		FlowPane root = new FlowPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setVgap(10);
		root.setHgap(10);

		TriangleMesh mesh = new TriangleMesh();
		float []points = {1,2,3,4,5,6};
		mesh.getPoints().addAll(points);



		root.getChildren().add(myCylinder);
		root.getChildren().add(box);
		root.getChildren().add(mySphere);

		Scene scene = new Scene(root, 800, 600);

		Camera camera = new PerspectiveCamera(true);
	    scene.setCamera(camera);

		primaryStage.setScene(scene);

		primaryStage.setTitle("Forth JavaFX Demo");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
