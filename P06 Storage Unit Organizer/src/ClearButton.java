/**
 * This class models a clear button in a PApplet StorageUnitOrganizer application
 * @author Mouna AYARI BEN HADJ KACEM
 *
 */
public class ClearButton extends Button {

  /**
   * Creates a new Clear Button and sets its label field
   * @param x x-position of this Clear Button on the display window
   * @param y y-position of this Clear Button on the display window
   * @param processing PApplet object where this button will be drawn
   */
  public ClearButton(float x, float y, StorageUnitOrganizer processing) {
    super(x, y, processing);
    this.label = "Clear All";
  }

  /**
   * Called each time the mouse is Pressed. It clears the storage list if this
   * button is clicked 
   */
  @Override
  public void mousePressed() {
    // clear the storage list if the mouse is pressed and it is over this button
    if (isMouseOver()) {
      StorageUnitOrganizer.setWarningMsg(""); // reset warning message to an empty String
      ((StorageUnitOrganizer)processing).storageList.clear(); // clear the storage list
    }
  }
}
