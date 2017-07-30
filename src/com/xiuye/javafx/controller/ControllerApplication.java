package com.xiuye.javafx.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(this.getClass().getResource("controller.fxml"));
		primaryStage.setTitle("controller event");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();


	}

	public static void main(String[] args) {
		launch(args);
	}

}
