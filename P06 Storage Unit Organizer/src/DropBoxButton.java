
/**
 * This class models and implements a DropBoxButton within a StorageUnitOrganizer PApplet
 * application
 * 
 * @author Mouna
 *
 */
public class DropBoxButton extends Button {

  /**
   * Creates a new CreateBoxButton and sets its label field
   * 
   * @param x x-position of this Button on the display window
   * @param y y-position of this Button on the display window
   * @param processing PApplet object where this button will be drawn
   */
  public DropBoxButton(float x, float y, StorageUnitOrganizer processing) {
    super(x, y, processing);
    this.label = "Drop Box";
  }

  /**
   * Called each time the mouse is Pressed. It drops (adds) the StorageUnitOrganizer newBox if any
   * to the StorageUnitOrganizer's storageList
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) { // button clicked (mouse is pressed and is over this button)
      try {
        StorageUnitOrganizer.setWarningMsg(""); // clear any previous warning message
        // add StorageUnitOrganizer's newBox to the StorageUnitOrganizer's storageList
        ((StorageUnitOrganizer) processing).storageList.add(StorageUnitOrganizer.getNewBox());
        // clear StorageUnitOrganizer.NewBox
        StorageUnitOrganizer.setNewNox(null);
      } catch (IllegalStateException | IllegalArgumentException e) {
        StorageUnitOrganizer.setWarningMsg(e.getMessage()); // sets StorageUnitOrganizer's warning
                                                            // message to the exception's warning
                                                            // message
      }
    }

  }


}
