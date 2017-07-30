package com.xiuye.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SecondDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(this.getClass().getResource("javafx.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("javafx");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
