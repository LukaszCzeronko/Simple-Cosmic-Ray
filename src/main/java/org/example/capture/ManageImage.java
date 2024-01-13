package org.example.capture;

import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.example.analysis.Analysis;

import java.awt.image.BufferedImage;

public class ManageImage {
    public boolean getImage(int i, FrameGrabber grabber, String saveLocation, double avgBrightness, double bgNoise) throws FrameGrabber.Exception, InterruptedException {
        Frame frame = grabber.grab();
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        IplImage img = converter.convert(frame);
        Analysis analysis = new Analysis();
        if (Analysis.brightnessOfImage(img, avgBrightness)) {
            analysis.detectWhitePix(i, saveLocation, img, bgNoise);
            return true;
        }
        return false;
    }

    public static BufferedImage IplImageToBufferedImage(IplImage src) {
        OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        Frame frame = grabberConverter.convert(src);
        return paintConverter.getBufferedImage(frame, 1);
    }
}
