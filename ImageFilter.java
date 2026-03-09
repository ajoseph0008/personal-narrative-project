import org.code.theater.*;
import org.code.media.*;

public class ImageFilter extends ImagePlus {

  /** Instance Variables */
  
  /** Constructor */
  public ImageFilter(String fileName) {
    super(fileName);
  }

  // Here are all the color filter functions
  /** The color shift filter, applied to
  the first array in the 2d Array of images
    */
    public void colorShift(int value) {
      Pixel[][] pixels = getImagePixels();
     for (int row = 0; row < pixels.length; row++) {
        for (int col = 0; col < pixels[row].length; col++) {
            Pixel currentPixel = pixels[row][col];

            // Shift red value
            int newRed = currentPixel.getRed() + value;
            if (newRed > 255) {
                newRed = 255;
            }
            // failsafe
            if (newRed < 0) {
                newRed = 0;
            }
            currentPixel.setRed(newRed);

            // Shift green value
            int newGreen = currentPixel.getGreen() + value;
            if (newGreen > 255) {
                newGreen = 255;
            }
            if (newGreen < 0) {
                newGreen = 0;
            }
            currentPixel.setGreen(newGreen);

            // Shift blue value
            int newBlue = currentPixel.getBlue() + value;
            if (newBlue > 255) {
                newBlue = 255;
            }
            if (newBlue < 0) {
                newBlue = 0;
            }
            currentPixel.setBlue(newBlue);
        }
    }
}
  /** The make negative filter, applied to
  the second row in the 2d Array of Images
    */
  public void makeNegative() {
     Pixel[][] pixels = getImagePixels();
    //Traverse pixels 
    for (int r = 0; r < pixels.length; r++) {
      for (int c = 0; c < pixels[0].length; c++) {
        Pixel currentPixel = pixels[r][c];

        //Get RGB values
        int currentRed = currentPixel.getRed();
        int currentBlue = currentPixel.getBlue();
        int currentGreen = currentPixel.getGreen(); 
        
// Subtract RGB Values from 255
        int newRed = 255 - currentRed; 
        int newBlue = 255 - currentBlue;
        int newGreen = 255 - currentGreen; 
//Update the values
        currentPixel.setRed(newRed);
        currentPixel.setGreen(newGreen);
        currentPixel.setBlue(newBlue);
      }
    }



    
  }

  /** The motion blur filter, applied to
  the third row in the 2d Array of Images
    */
   public void motionBlur(int length, String direction) {
    Pixel[][] pixels = getImagePixels();

    // traverse all pixels
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        
        // variables to total RBG valuse
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;

        // since we are bluring in a direction, these variables help us
        // reference the area we will total RBG value
        int x = col;
        int y = row;
        int count = 0; // use to repeat the length number of times

        // complex conditional to keep in bounds of width/height
        while (count < length && x < getWidth() && y < getHeight()) {
          // add RGB to the variables
          Pixel currentPixel = pixels[y][x];
          totalRed += currentPixel.getRed();
          totalGreen += currentPixel.getGreen();
          totalBlue += currentPixel.getBlue();
          // increase count to move to ending condition of count < length
          count++;
          // update x & y based on the definition of the bluring
          if (direction.equals("horizontal")) {
            x++;
          }
          else if (direction.equals("vertical")) {
            y++;
          }
          else if (direction.equals("diagonal")) {
            x++;
            y++;
          }
        }

        // calculate avg RBG
        int avgRed = totalRed / count;
        int avgGreen = totalGreen / count;
        int avgBlue = totalBlue / count;
        
        // update RBG values
        Pixel currentPixel = pixels[row][col];
        currentPixel.setRed(avgRed);
        currentPixel.setGreen(avgGreen);
        currentPixel.setBlue(avgBlue);
      }
    }
    
  }
  /** The make darken filter, applied to
  the fourth row in the 2d Array of Images
    */
  public void darken(int value) {
    Pixel[][] pixels = getImagePixels();
    //Traverse pixels array
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        Pixel p = pixels[row][col]; 
        //Subtracting value makes the image darker, math.max prevents it from going negative
        p.setRed(Math.max(0, p.getRed() - value));
        p.setGreen(Math.max(0, p.getGreen() - value)); 
        p.setBlue(Math.max(0, p.getBlue() - value)); 
      }
    }
  }
  
  
}