
/**
 * This class models a Create Box Button
 * @author Mouna
 *
 */
public class CreateBoxButton extends Button {
  
  /**
   * Creates a new CreateBoxButton and sets its label field
   * @param x x-position of this Button on the display window
   * @param y y-position of this Button on the display window
   * @param processing PApplet object where this button will be drawn
   */
  public CreateBoxButton(float x, float y, StorageUnitOrganizer processing) {
    super(x, y, processing);
    this.label = "Create Box";
  }

  /**
   * Called each time the mouse is Pressed. It creates a new Box when this
   * button is clicked 
   */
  @Override
  public void mousePressed() {
    // create a new box if this button is clicked
    if (isMouseOver()) {   
      StorageUnitOrganizer.setWarningMsg(""); // clear any displayed message
                                              // reset warning message to an empty String
      StorageUnitOrganizer.setNewNox(new GraphicBox()); // sets newBox field defined in the 
                                                 // StorageUnitOrganizer class to a new Box
    }
  }
}
