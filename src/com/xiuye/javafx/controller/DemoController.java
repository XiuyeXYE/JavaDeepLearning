package com.xiuye.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;//needed or not needed
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;


public class DemoController implements Initializable{
	@FXML
	private Button btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void onAction(ActionEvent e){
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Javafx对话框");
		a.setContentText(e.toString()+"被点击了!");
		a.show();
		System.out.println("button click by "+btn);
	}
}
