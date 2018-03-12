package com.xiuye.javafx;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SeventhDemo extends Application {

	@Override
	public void start(Stage stage) {
		if (!Platform.isSupported(ConditionalFeature.SCENE3D)) {
			throw new RuntimeException("*** ERROR: common conditional SCENE3D is not supported");
		}

		stage.setTitle("JavaFX MSAA demo");

		Group root = new Group();
		Scene scene = new Scene(root, 1000, 600);
		scene.setFill(Color.color(0.5, 0.5, 0.5, 1.0));

		HBox hbox = new HBox();
		hbox.setLayoutX(75);//设置HBox坐标
		hbox.setLayoutY(200);
		//白色材质
		//相当于设置图形的颜色,白色的光源,可以看到材质的颜色
		PhongMaterial phongMaterial = new PhongMaterial(Color.color(1., 1., 1.));
		Cylinder cylinder1 = new Cylinder(100, 200);
		cylinder1.setMaterial(phongMaterial);
		SubScene noMsaa = createSubScene("MSAA = false", cylinder1, Color.TRANSPARENT, new PerspectiveCamera(), false);
		hbox.getChildren().add(noMsaa);

		Cylinder cylinder2 = new Cylinder(100, 200);
		phongMaterial = new PhongMaterial(Color.color(0.0, 0.7, 0.0));
		cylinder2.setMaterial(phongMaterial);
		SubScene msaa = createSubScene("MSAA = true", cylinder2, Color.TRANSPARENT, new PerspectiveCamera(), true);
		hbox.getChildren().add(msaa);

		Slider slider = new Slider(0, 360, 0);
		slider.setBlockIncrement(1);
		slider.setTranslateX(425);//设置Slider坐标
		slider.setTranslateY(550);
		//旋转角度值绑定,不用编写Event代码了
		cylinder1.rotateProperty().bind(slider.valueProperty());
		cylinder2.rotateProperty().bind(slider.valueProperty());
		//添加到一个group中
		root.getChildren().addAll(hbox, slider);

		stage.setScene(scene);
		stage.show();
	}

	private static Parent setTitle(String str) {
		final VBox vbox = new VBox();
		final Text text = new Text(str);
		text.setFont(Font.font("Times New Roman", 24));
		text.setFill(Color.RED);
		vbox.getChildren().add(text);
		return vbox;
	}

	private static SubScene createSubScene(String title, Node node/*Shape*/, Paint fillPaint, Camera camera, boolean msaa) {
		Group root = new Group();

		PointLight light = new PointLight(Color.YELLOW);
		//x y z 坐标(相当于(0,0,0) -> x,y,z的平移)
		light.setTranslateX(250);
		light.setTranslateY(180);
		light.setTranslateZ(-400);
		PointLight light2 = new PointLight(Color.color(0.6, 0.3, 0.4));
		light2.setTranslateX(400);
		light2.setTranslateY(0);
		light2.setTranslateZ(-400);

		//图像所有的面都有光.
		AmbientLight ambientLight = new AmbientLight(Color.YELLOW);
		//坐标轴原点在图形的中心,下面设置x轴方向是旋转转轴
		node.setRotationAxis(new Point3D(1, 0, 0)/*方向向量*/.normalize());
		node.setTranslateX(180);
		node.setTranslateY(180);
//		node.setTranslateZ(0);
		//如果不添加光源的话,默认为点光源,且在正前方
		//光源和图形添加到一个group中,然后加入到scene中
		//自己可以一个一个光源添加到group中,来测试效果
		root.getChildren().addAll(setTitle(title),light,light2,ambientLight, node);

		//SceneAntialiasing 可以设置抗锯齿
		SubScene subScene = new SubScene(root, 500, 400, true,
				msaa ? SceneAntialiasing.BALANCED : SceneAntialiasing.DISABLED);
		subScene.setFill(fillPaint);//设置背景色
		//设置照相机,如果不设置的话,旋转不会有立体感
		subScene.setCamera(camera);

		return subScene;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
