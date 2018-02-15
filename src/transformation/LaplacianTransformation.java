package transformation;

import java.io.IOException;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import core.InterfaceImage;
import javafx.application.Application;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class LaplacianTransformation extends Application implements InterfaceImage{

	public LaplacianTransformation() {}

	public LaplacianTransformation(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		LaplacianTransformation obj = new LaplacianTransformation();
		WritableImage writableImage = obj.loadImage();
		
		showImage(writableImage, stage, "LaplacianTransformation image");
		
		
	}

	private WritableImage loadImage() throws IOException {
		Mat src = loadImageAndLibrary();
		Mat dst = new Mat();
		
		Imgproc.Laplacian(src, dst,10);
		
		WritableImage writableImage = buffImage(dst);
		
		return writableImage;
	}

}
