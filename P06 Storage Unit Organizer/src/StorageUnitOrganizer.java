import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * This class models and implements the graphic application for a Storage Unit Organizer
 * 
 * @author Mouna Kacem
 *
 */
public class StorageUnitOrganizer extends PApplet {
  private PImage backgroundImage; // PImage object that represents the background image
  private PImage boxImage; // PImage object of a box
  private ArrayList<Button> buttons; // ArrayList storing the button objects related to this
                                     // application
  protected LinkedBoxList storageList; // linked list storing the storage units present in
                                       // this application
  private static GraphicBox newBox; // a reference to a graphic box created and not yet
                             // dropped into the storage list
  private static float[] coordinates = new float[] {50, 34, 200, 634}; // coordinates of the
                                                                       // storage unit
  private static String warningMsg = ""; // warning message to be displayed if any

  /**
   * This main method starts the application
   * 
   * @param args
   */
  public static void main(String[] args) {
    // starts the application (calls PApplet main() method with the name
    // of the PApplet class to run as parameter)
    PApplet.main("StorageUnitOrganizer");
  }
  

  /**
   * Sets the size of the application display window
   */
  @Override
  public void settings() {
    size(635, 635); // sets the size of the display window to 800 x 632 pixels
  }

  /**
   * CallBack method. It Defines initial environment properties such as screen size and to load
   * background images and fonts as the program starts
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Storage Unit Organizer"); // Displays text in the title of the
                                                          // display window
    this.textAlign(StorageUnitOrganizer.CENTER, StorageUnitOrganizer.CENTER); // Sets the current
                                                                              // alignment for
                                                                              // drawing text
    // to CENTER
    this.imageMode(StorageUnitOrganizer.CENTER); // Sets the location from which images are drawn to
                                                 // CENTER
    this.rectMode(StorageUnitOrganizer.CORNERS); // Sets the location from which rectangles are
                                                 // drawn.
    // rectMode(CORNERS) interprets the first two parameters of rect() method as the location of one
    // corner, and the third and fourth parameters as the location of the opposite corner.
    // rect() method draws a rectangle to the display window
    this.focused = true; // Confirms that our Processing program is "focused," meaning that
    // it is active and will accept mouse or keyboard input.
    backgroundImage = this.loadImage("images" + File.separator + "background.png"); // load the
                                                                                    // background
                                                                                    // image
    boxImage = loadImage("images" + File.separator + "box.png"); // load the box image

    warningMsg = ""; // clear warning message
    // load StorageUnitNode class's graphic static fields
    GraphicBox.setProcessing(this);
    GraphicBox.loadImage();

    buttons = new ArrayList<Button>(); // create the buttons ArrayList
    buttons.add(new CreateBoxButton(43, 16, this)); // create a CreateBoxButton and add it to
                                                    // buttons ArrayList
    buttons.add(new DropBoxButton(129, 16, this)); // create a DropBoxButton and add it to buttons
                                                   // ArrayList
    buttons.add(new ClearButton(215, 16, this)); // create a ClearButton and add it to buttons
                                                 // ArrayList

    storageList = new LinkedBoxList(10); // create an empty storage list with an initial capacity 10

  }


  /**
   * Callback method called in an infinite loop. It draws this application window display
   */
  @Override
  public void draw() {
    // Set the color used for the background of the Processing window
    this.background(245, 255, 250); // Set the mint cream color background
    this.image(backgroundImage, this.width / 2, this.height / 2); // draw the background image at
                                                                  // the center of the display
                                                                  // window
    this.stroke(0); // set line value to black
    // this.strokeWeight(2.0f); // set line width
    this.fill(255, 250, 240); // set the fill color to floral white
    this.rect(coordinates[0], coordinates[1], coordinates[2], coordinates[3]); // draw the container
    // traverse the buttons ArrayList and draw each button to the display windows
    for (int i = 0; i < buttons.size(); i++)
      buttons.get(i).draw();
    // draw newBox if any
    if (newBox != null) {
      newBox.draw(this.width * 2 / 3, this.height * 2 / 3);
    }
    // display warning message if any
    this.displayMessage(warningMsg);
    
    drawStorageList(); // draw boxes stored within storageList
  }

  /**
   * Draws the storage list content to the display window
   */
  private void drawStorageList() {
    float positionX = (coordinates[0] + coordinates[2]) / 2;
    float positionY = coordinates[3] - boxImage.height / 2;
    float step = boxImage.height;

    for (int i = 0; i < storageList.size(); i++) {
      ((GraphicBox)storageList.get(i)).draw(positionX, positionY);
      positionY -= step;
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  @Override
  public void mousePressed() {
    // traverse listGUI and call mousePressed() of the first graphical object which the mouse is
    // over
    for (int i = 0; i < buttons.size(); i++)
      if (buttons.get(i).isMouseOver()) {
        buttons.get(i).mousePressed();
        break;
      }
  }

  /**
   * Callback method called each time the user presses a key
   */
  @Override
  public void keyPressed() {
    // if R-key is pressed and the mouse is over a box, remove that box from storageList
    switch (Character.toUpperCase(this.key)) {
      case 'R': // remove a box from the Storage Unit list if the mouse is over it
        int index = isMouseOverBox();
        if (index != -1) {
          Box removedBox = storageList.remove(index); // remove box
          warningMsg ="Box ("+ removedBox.getWeight()+ "lbs) removed!";
        }
        break;
    }

  }

  /**
   * Checks whether the mouse is over a Box and returns its index within the storage list
   * @return the index of the box if the mouse is over a box, -1 otherwise
   */
  private int isMouseOverBox() {
    // checks first if the mouse is over the storage list
    if(isMouseOverStorageList()) {
      // returns the index of the box over which the mouse is over
      float coordinateY = coordinates[3]; // bottom y-coordinate of storage list
      float step = boxImage.height; 
      return new Float((coordinateY - this.mouseY)/step).intValue();
    }      
    return -1;
  }
  
  /**
   * Checks whether the mouse is over the storage list
   * 
   * @return true if the mouse is over the storage list, false otherwise
   */
  private boolean isMouseOverStorageList() {
    if (storageList.isEmpty())
      return false; 
    else { // storageList not empty
      float width = boxImage.width; // width of the storage list
      float height = boxImage.height * storageList.size(); // height of the storage list
      float x1 = (coordinates[0] + coordinates[2]) / 2 - width/2; // left x-coordinate bound
      float x2 = x1 + width; // right x-coordinate bound 
      float y1 = coordinates[3]-height; // top y-coordinate bound
      float y2 = coordinates[3]; // bottom y-coordinate bound
      if (this.mouseX > x1 && this.mouseX < x2 && this.mouseY > y1 && this.mouseY < y2) {
        return true;
      }
      return false;
    }
  }

  /**
   * Displays a warning message to the display window
   * @param message to be displayed to the display window
   */
  public void displayMessage(String message) {
    textSize(16);
    this.text(message, this.width * 2 / 3, this.height / 3);
    textSize(12);
  }

 
  /**
   * Sets newNode
   * 
   * @param newNode the newNode to set
   */
  public static void setNewNox(GraphicBox newBox) {
    StorageUnitOrganizer.newBox = newBox;
  }


  /**
   * Returns newBox
   * 
   * @return the newBox
   */
  public static Box getNewBox() {
    return newBox;
  }

  /**
   * Returns the current warning message
   * 
   * @return the warningMsg
   */
  public static String getWarningMsg() {
    return warningMsg;
  }

  /**
   * Sets the warning message
   * 
   * @param warningMsg the warningMsg to set
   */
  public static void setWarningMsg(String warningMsg) {
    StorageUnitOrganizer.warningMsg = warningMsg;
  }

}
