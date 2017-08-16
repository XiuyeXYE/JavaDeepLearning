package com.xiuye.javafx.controller;

import java.awt.SplashScreen;

import com.xiuye.util.ProcessUtil;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerApplication extends Application {

	private String resPath(String file){
		return this.getClass().getResource(file).toString();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(this.getClass().getResource("controller.fxml"));
		primaryStage.setTitle("controller event");
		Scene s = new Scene(root);
		ObservableList<String> styleSheets = s.getStylesheets();
		String p = resPath("scene.css");
		System.out.println(p);
		styleSheets.add(p);

		primaryStage.setScene(s);
		primaryStage.show();


	}

	public static void main(String[] args) {
		SplashScreen.getSplashScreen().close();
		ProcessUtil.attachJar("agent1.jar","55555");
		launch(args);
	}



}
