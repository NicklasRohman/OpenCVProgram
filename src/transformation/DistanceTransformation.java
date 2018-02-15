package transformation;

import java.io.IOException;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import core.InterfaceImage;
import javafx.application.Application;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class DistanceTransformation extends Application implements InterfaceImage{

	public DistanceTransformation(){}
	public DistanceTransformation(String[] args) {
	launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		DistanceTransformation obj = new DistanceTransformation();
		WritableImage writableImage = obj.loadImage();
		
		showImage(writableImage, stage, "DistanceTransformation image");
		
	}
	private WritableImage loadImage() throws IOException {

		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	      String file ="apa.jpg";
	      Mat src = Imgcodecs.imread(file,0);
	      
		
		Mat dst = new Mat();
		Mat binary = new Mat();
		
		Imgproc.threshold(src, binary, 100, 255, Imgproc.THRESH_BINARY);
		Imgproc.distanceTransform(src, dst, Imgproc.DIST_C, 3);
		
		WritableImage writableImage = buffImage(dst);
		
		
		return writableImage;
	}

}
