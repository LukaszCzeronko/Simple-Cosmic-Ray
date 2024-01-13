# Simple Camera Cosmic Ray Detector
### How it works?
When a radiation particle with extreme energy  arrives from space, it initiates a cascade of secondary particles in the Earth's atmosphere, called a large atmospheric shower. Secondary particles can number billions of different types of particles, including: muons, electrons, gamma photons. The cascade caused by a single particle, after reaching the Earth's surface, can cover an area up to several kilometers in diameter. Even an ordinary camera can detect such secondary particles by taking a photo with the lens completely covered. When a particle (secondary cosmic radiation) passes through the camera detector, it may excite some of its pixels. Several to several dozen brighter pixels then appear on a uniformly black background. There may be one to even several hundred detections per day.
### Development Prerequisites
You need to have installed:
* ```Java 15 SDK ```
* ```Maven 4.0.x```
### Run Prerequisites
* camera

`Before running application make sure your camera is completely covered.
    You can do this for example by paper or black tape.`
* Variables
In `Main.java` file you can change:
    - `SAVE_LOCATION` - Location of potential detected cosmic rays. Default `src/main/detections`
    - `CAMERA_WIDTH` X `CAMERA_HEIGHT`- Camera resolution. Default `1280x720`
    - `MAX_AVERAGE_BRIGHTNESS` - Threshold value to start detecting. Default `1.9`
    - `BACKGROUND_NOISE` - Threshold brightness for single pixel to be considered as potential detection. Default: `0.2`
