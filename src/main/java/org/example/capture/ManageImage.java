package org.example.capture;

import org.bytedeco.javacv.*;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.example.analysis.Analysis;

import java.io.File;

public class ManageImage {
    public boolean getImage(int i, FrameGrabber grabber, String saveLocation) throws FrameGrabber.Exception, InterruptedException {
        Frame frame = grabber.grab();
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        IplImage img = converter.convert(frame);
        if (Analysis.brightnessOfImage(img)) {
            opencv_imgcodecs.cvSaveImage(saveLocation + File.separator + (i) + "_shot.jpg", img);
            return true;
        }
        return false;
    }

    public void deleteImage(int i, String saveLocation) throws InterruptedException {
        File fullSize = new File(saveLocation + File.separator + (i) + "_shot.jpg");
        fullSize.delete();
    }
}
