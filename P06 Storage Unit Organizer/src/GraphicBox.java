import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class models and implements a GraphicBox that extends Box
 * @author Mouna
 *
 */
public class GraphicBox extends Box{
  private static PImage image; // image of this box
  private static PApplet processing; // PApplet object that represents the display window

  /**
   * Draws this box to the display window
   * @param positionX x-coordinate of the center of this box
   * @param positionY y-coordinate of the center of this box
   */
  public void draw(float positionX, float positionY) {
    processing.image(image, positionX, positionY);
    processing.fill(this.getColor()); // set the fill color to the color of this box
    processing.rect(positionX+3-image.width/2.0f, positionY+2-image.height/2.0f, 
        positionX-2+image.width/2.0f, positionY-image.height/10.0f);
    processing.fill(0);
    processing.text(String.valueOf(this.getWeight())+" lbs", positionX, positionY+image.height/5);
  }
  
  /**
   * Sets the processing PApplet instance field
   * @param processing 
   */
  public static void setProcessing(PApplet processing) {
    GraphicBox.processing =  processing;
  }
  
  /**
   * Loads the image of this Box
   */
  public static void loadImage() {
    image = processing.loadImage("images"+File.separator+"box.png");
  }


}
