package com.xiuye.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class Controller2 implements Initializable {


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("location is : " + location);
	}

	@FXML
	public void onDragDetected(MouseEvent e) {
		System.out.println("onDragDetected:"+e.getSource());
	}
	@FXML
	public void onDragDone(MouseEvent e) {
		System.out.println("onDragDone:"+e.getSource());
	}
	@FXML
	public void onDragDropped(MouseEvent e) {
		System.out.println("onDragDropped:"+e.getSource());
	}
	@FXML
	public void onDragEntered(MouseEvent e) {
		System.out.println("onDragEntered:"+e.getSource());
	}
	@FXML
	public void onDragExited(MouseEvent e) {
		System.out.println("onDragExited:"+e.getSource());
	}
}
