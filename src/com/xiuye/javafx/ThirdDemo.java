package com.xiuye.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThirdDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn = new Button("JFX Button");
//		btn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				System.out.println("Hello World!");
//			}
//		});
//		EventHandler<ActionEvent> handler = (event)->log("Hello World!");
//		btn.setOnAction(handler);


		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Welcome");
		scenetitle.setFill(Color.RED);
		scenetitle.setId("welcome-text");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		grid.add(btn,1,3);


		Scene scene = new Scene(grid, 300, 275);

		StackPane root1 = new StackPane();
		Button btn1 = new Button("JFX Button");
		root1.getChildren().add(btn1);
		Scene scene1 = new Scene(root1,500,300);
		btn.setOnAction((event)->{
			log("intput User Name:"+userTextField.getText()+"\ninput Password:"+pwBox.getText());

			log(primaryStage.getScene() == scene1);
			primaryStage.setScene(primaryStage.getScene() == scene1 ? scene : scene1);
		});
		btn1.setOnAction((event)->{
			log("intput User Name:"+userTextField.getText()+"\ninput Password:"+pwBox.getText());

			log(primaryStage.getScene() == scene1);
			primaryStage.setScene(primaryStage.getScene() == scene1 ? scene : scene1);
		});
		scene.getStylesheets().add
		 (ThirdDemo.class.getResource("ThirdDemo.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX");
		primaryStage.show();
	}

	private void log(boolean b) {

		System.out.println(b);
	}

	private static void log(String string) {
		System.out.println(string);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
