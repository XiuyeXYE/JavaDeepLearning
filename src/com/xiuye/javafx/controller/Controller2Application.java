package com.xiuye.javafx.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller2Application extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = FXMLLoader.load(this.getClass().getResource("controller2.fxml"));
		primaryStage.setTitle("Controller2Application");
		Scene s = new Scene(pane);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {

		launch(args);

	}

}
