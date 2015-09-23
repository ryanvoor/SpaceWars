/**
* Class that represents an animated image
* @author Ryan Voor
* @version 1.0
*/
import javafx.scene.image.Image;

public class AnimatedImage {
    private Image[] frames;
    private double duration;

    /**
    * constructor for the Animated Image class
    * @param frames the frames that make up the animated image
    * @param duration how long the animation will last
    */
    public AnimatedImage(Image[] frames, double duration) {
      this.frames = frames;
      this.duration = duration;
    }

    /**
    * calculates the current frame of the image based on the current time
    * @param time the current time
    */
    public Image getCurrentFrame(double time) {
      int index = (int)((time % (frames.length * duration)) / duration);
      return frames[index];
    }

    /**
    * getter for an individual frame of this AnimatedImage
    * @param frame the frame to get of this AnimatedImage
    */
    public Image getFrame(int index) {
      return frames[index];
    }

    /**
    * getter for the duration of this AnimatedImage
    * @return double the duration of this AnimatedImage
    */
    public double getDuration() {
      return duration;
    }

    /**
    * setter for an individual frame of this AnimatedImage
    * @param frame the Image to set this frame to
    * @param index the index of the frame to be set
    * @return Image the previous image located at that index
    */
    public Image setFrame(Image frame, int index) {
      Image oldFrame = frames[index];
      frames[index] = frame;
      return oldFrame;
    }

    /**
    * setter for the frames of this AnimatedImage
    * @param frames the Image[] to set the frames to
    * @return Image[] the previous image located at that index
    */
    public Image[] setFrames(Image[] frames) {
      Image[] oldFrames = this.frames;
      this.frames = frames;
      return oldFrames;
    }


    /**
    * setter for the duration of this AnimatedImage
    * @param duration the duration to set this AnimatedIamge to
    * @return double the previous duration of this AnimatedImage
    */
    public double setDuration(double duration) {
      double oldDuration = this.duration;
      this.duration = duration;
      return oldDuration;
    }
}
