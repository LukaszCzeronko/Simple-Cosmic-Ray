package org.example;

import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.example.analysis.Analysis;
import org.example.capture.ManageImage;

import java.util.Scanner;

public class Main {
    static final String SAVE_LOCATION = "src/main/shots";

    public static void main(String[] args) throws InterruptedException, FrameGrabber.Exception {
        int i = 0;
        System.out.println("Welcome");
        System.out.println("Press Enter to start");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();

        ManageImage manageImage = new ManageImage();
        Analysis analysis = new Analysis();
        FrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();

        while (true) {
            if (!manageImage.getImage(i, grabber, SAVE_LOCATION) || !analysis.detectWhitePix(i, SAVE_LOCATION)) {
                manageImage.deleteImage(i, SAVE_LOCATION);
            }
            i++;
        }
    }
}