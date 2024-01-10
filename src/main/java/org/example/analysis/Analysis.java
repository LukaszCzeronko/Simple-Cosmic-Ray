package org.example.analysis;

import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.openimaj.image.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import static org.bytedeco.opencv.global.opencv_core.cvAvg;

public class Analysis {
    public static boolean brightnessOfImage(IplImage image) {
        final double maxAverageBrightness = 1.2;

        CvScalar averageColor = cvAvg(image, null);
        double averageBrightness = (averageColor.val(0) + averageColor.val(1) + averageColor.val(2)) / 3.0;
        System.out.println("Brightness "+averageBrightness);
        if (averageBrightness > maxAverageBrightness) {
            System.out.println("COVER YOUR DEVICE!");
            return false;
        }
        return true;
    }

    public boolean detectWhitePix(int i) {
        double noise = 0.35;
        try {
            MBFImage image = ImageUtilities.readMBF(new File("src/main/shots" + File.separator + (i) + "shot.jpg"));
            System.out.println(image.colourSpace);
            MBFImage center;
            double bandValue = 0;
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    bandValue = image.getBand(0).pixels[y][x] + image.getBand(1).pixels[y][x] + image.getBand(2).pixels[y][x];
                    if (bandValue > noise) {
                        LocalTime currentTime = LocalTime.now();
                        System.out.println("Spotted flash in pixel");
                        System.out.println("x: " + x + "y: " + y);
                        center = image.extractCenter(x, y, 64, 64);
                        ImageUtilities.write(center, new File("src/main/shot" + File.separator + (i) + currentTime + "_shotZom.jpg"));
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return false;
    }
}
