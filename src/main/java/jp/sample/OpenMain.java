package jp.sample;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * OpenCVサンプル 顔検出
 *
 */
public class OpenMain {
	public static void main(String[] args) throws Exception {
		// ライブラリを読み込む
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String path = "input\\sample.png";
		String settingPath = "opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_default.xml";
		String outputFileName = "out\\sample.png";
		File target = new File(path);
		// 対象の画像ファイルを読み込む
		Mat image = Imgcodecs.imread(target.getAbsolutePath());

		File setting = new File(settingPath);

		MatOfRect faces = new MatOfRect();
		// 設定ファイルを読み込む(顔検出用)
		CascadeClassifier faceDetector = new CascadeClassifier(setting.getAbsolutePath());
		// 検出し結果をMatOfRectにマッピングする
		faceDetector.detectMultiScale(image, faces);
		// マッピングデータをもとに顔部分に四角を出力する
		for (Rect rect : faces.toArray()) {
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 0));
		}
		// ファイルを出力する
		Imgcodecs.imwrite(outputFileName, image);
	}
}
