import processing.core.PApplet;
/**
 * This class models and implements a Button for a PApplet application
 * @author Mouna Ayari Ben Hadj Kacem
 *
 */
public class Button {
  private static final int WIDTH = 85; // Width of the Button
  private static final int HEIGHT = 32; // Height of the Button
  protected PApplet processing; // PApplet object where the button will be drawn
  private float[] position; // array storing x and y positions of the Button with respect to 
                            // the display window 
  protected String label;   // text/label that represents the button

  /**
   * Creates a new Button and initializes its instance fields
   * @param x x-position of this Button on the display window
   * @param y y-position of this Button on the display window
   * @param processing PApplet object where this button will be drawn
   */
  public Button(float x, float y, PApplet processing) {
    this.processing = processing;
    this.position = new float[2];
    this.position[0] = x;
    this.position[1] = y;
    this.label = "Button";
  }

  /**
   * draws this button to the display window 
   */
  public void draw() {
    this.processing.stroke(0);// set line value to black
    this.processing.strokeWeight(1);  // set line width to 1
    if (isMouseOver())
      processing.fill(100); // set the fill color to dark gray if the mouse is over the button
    else
      processing.fill(200); // set the fill color to light gray otherwise

    // draw the button (rectangle with a centered text)
    processing.rect(position[0] - WIDTH / 2.0f, position[1] - HEIGHT / 2.0f,
        position[0] + WIDTH / 2.0f, position[1] + HEIGHT / 2.0f);
    processing.fill(0); // set the fill color to black
    processing.text(label, position[0], position[1]); // display the text of the current button
  }

  
  /**
   * Called each time the mouse is pressed
   */
  public void mousePressed() {
    if (isMouseOver())
      System.out.println("A button was pressed.");
  }

  /**
   * Checks whether the mouse is over this button
   * @return true if the mouse is over this button, false otherwise
   */
  public boolean isMouseOver() {
    if (this.processing.mouseX > this.position[0] - WIDTH / 2
        && this.processing.mouseX < this.position[0] + WIDTH / 2
        && this.processing.mouseY > this.position[1] - HEIGHT / 2
        && this.processing.mouseY < this.position[1] + HEIGHT / 2)
      return true; // mouse is over this button
    return false;  // mouse is not over this button
  }
}
