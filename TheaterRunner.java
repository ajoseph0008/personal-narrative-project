import org.code.theater.*;
import org.code.media.*;

public class TheaterRunner {
  public static void main(String[] args) {

    // Create any 2D arrays & variables to be passed as parameters to constructor
    ImageFilter[][] images = {
      { new ImageFilter("indianFlag.png"), new ImageFilter("Kerala.png") }, 
      { new ImageFilter("pani-puri.png"), new ImageFilter("onam.png") }, 
      { new ImageFilter("chaya.png"), new ImageFilter("houseboat.png") },
      { new ImageFilter("forest.png"), new ImageFilter("cricket.jpg") },
      /**List of images of different things in the state of India I am from, Kerala76
      */
    };

    // Instantiate Scene subclass objects
    SceneOne scene1 = new SceneOne(images);

    
    // Call drawScene methods in each subclass
    scene1.drawScene();

    
    // Play scenes in Theater, in order of arguments
    Theater.playScenes(scene1);
    
  }
}