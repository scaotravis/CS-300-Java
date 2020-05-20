
import java.io.File;
import processing.core.PImage;

/**
 * Represents a visible object with a graphical representation in our Escape Room game
 * 
 * @author Travis Cao
 */
public class VisibleThing extends Thing {

  private PImage image; // the graphical representation of this thing
  private int x; // the horizontal position (in pixels of this thing's left side)
  private int y; // the vertical position (in pixels of this thing's top side)

  // Constructor
  /**
   * Initializes a new VisibleThing object
   * 
   * @param name name of the object
   * @param x    the horizontal position
   * @param y    the vertical position
   */
  public VisibleThing(String name, int x, int y) {
    super(name);
    this.image = getProcessing().loadImage("images" + File.separator + name + ".png");
    this.x = x;
    this.y = y;
  }

  // Methods
  /*
   * (non-Javadoc)
   * 
   * @see Thing#update()
   * 
   * Draws image at its position before returning null
   */
  public Action update() {
    getProcessing().image(image, x, y);
    return null;
  }

  /**
   * Changes the image position by adding dx to x and dy to y
   * 
   * @param dx Increment to add on x image position
   * @param dy Increment to add on y image position
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }

  /**
   * Checks if a point (x, y) is over image in object of class VisibleThing
   * 
   * @param x x-coordinate of a point
   * @param y y-coordinate of a point
   * @return true only when point (x, y) is over image
   */
  public boolean isOver(int x, int y) {
    int width = this.image.width;
    int height = this.image.height;

    boolean pointIsOver = true;

    // point is outside if it is to the left or right of the image
    if (x < this.x || this.x + width < x) {
      pointIsOver = false;
    }

    // point is outside if it is higher or lower than the image
    if (y < this.y || this.y + height < y) {
      pointIsOver = false;
    }

    return pointIsOver;
  }

  /**
   * Checks if another object of class VisibleThing overlaps with the current VisibleThing object
   * 
   * @param other Another object of class VisibleThing
   * @return true only when another VisibleThing image overlaps with the current one
   */
  public boolean isOver(VisibleThing other) {
    int thisWidth = this.image.width;
    int thisHeight = this.image.height;
    int otherWidth = other.image.width;
    int otherHeight = other.image.height;

    boolean otherIsOver = true;

    // if one image is on the left side of the other, then it is not over
    if (this.x + thisWidth < other.x || other.x + otherWidth < this.x) {
      otherIsOver = false;
    }

    // if one image is above the other, then it is not over
    if (this.y + thisHeight < other.y || other.y + otherHeight < this.y) {
      otherIsOver = false;
    }

    return otherIsOver;
  }

}
