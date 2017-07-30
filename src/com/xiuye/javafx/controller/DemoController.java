package com.xiuye.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;//needed or not needed
import javafx.scene.control.Button;

public class DemoController implements Initializable{
	@FXML
	private Button btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void onAction(ActionEvent e){
		System.out.println("button click");
	}
}
