package org.example.analysis;

import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.example.capture.ManageImage;
import org.openimaj.image.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import static org.bytedeco.opencv.global.opencv_core.cvAvg;


public class Analysis {
    static final double MAX_AVERAGE_BRIGHTNESS = 1.9;
    static final double BACKGROUND_NOISE = 0.2;

    public static boolean brightnessOfImage(IplImage image, double maxBrightness) {
        CvScalar averageColor = cvAvg(image, null);
        double averageBrightness = (averageColor.val(0) + averageColor.val(1) + averageColor.val(2)) / 3.0;
        System.out.println("Brightness: " + averageBrightness);
        if (averageBrightness > maxBrightness) {
            System.out.println("COVER YOUR CAMERA DEVICE!");
            return false;
        }
        return true;
    }

    public boolean detectWhitePix(int i, String saveLocation, IplImage photo, double bgNoise) {
        try {
            final BufferedImage bufferedImage = ManageImage.IplImageToBufferedImage(photo);
            MBFImage image = ImageUtilities.createMBFImage(bufferedImage, false);
            MBFImage center;
            double bandValue;
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    bandValue = image.getBand(0).pixels[y][x] + image.getBand(1).pixels[y][x] + image.getBand(2).pixels[y][x];
                    //   int grayscalePixel = (0.21 * pRed) + (0.71 * pGreen) + (0.07 * pBlue)
                    if (bandValue > bgNoise) {
                        LocalTime currentTime = LocalTime.now();
                        System.out.println("Spotted flash in pixel");
                        System.out.println("x: " + x + "y: " + y);
                        center = image.extractCenter(x, y, 64, 64);
                        ImageUtilities.write(image, new File(saveLocation + File.separator + (i) + "_shot.jpg"));
                        ImageUtilities.write(center, new File(saveLocation + File.separator + (i) + "_shotZom.jpg"));
                        return true;
                    }
                }
            }
            System.out.println("Samples: " + i);
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
