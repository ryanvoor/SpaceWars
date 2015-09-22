/**
  write javadocs for this class
  also should make those variables private and use getters and setters
*/
import javafx.scene.image.Image;
// import java.awt.Image;

public class AnimatedImage
{
    public Image[] frames;
    public double duration;

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
}
