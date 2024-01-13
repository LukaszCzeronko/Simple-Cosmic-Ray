package org.example;

import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.example.capture.ManageImage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static final String SAVE_LOCATION = "src/main/detections";
    static final int CAMERA_WIDTH = 1280;
    static final int CAMERA_HEIGHT = 720;
    static final double MAX_AVERAGE_BRIGHTNESS = 1.9;
    static final double BACKGROUND_NOISE = 0.2;

    public static void main(String[] args) throws InterruptedException, IOException {
        int i = 0;
        System.out.println("Welcome");
        System.out.println("Press Enter to start");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
        Files.createDirectories(Paths.get(SAVE_LOCATION));
        ManageImage manageImage = new ManageImage();
        FrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.setImageHeight(CAMERA_HEIGHT);
        grabber.setImageWidth(CAMERA_WIDTH);
        grabber.start();

        while (true) {
            manageImage.getImage(i, grabber, SAVE_LOCATION, MAX_AVERAGE_BRIGHTNESS, BACKGROUND_NOISE);
            i++;
        }
    }
}