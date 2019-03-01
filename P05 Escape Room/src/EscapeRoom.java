import processing.core.PApplet;
import processing.core.PImage;

public class EscapeRoom extends PApplet {

  private PImage backgroundImage;

  @Override
  public void settings() {
    size(800, 600);
  }

  @Override
  public void setup() {
    backgroundImage = super.loadImage("images/computerCenter.png");
  }

  @Override
  public void draw() {
    super.image(backgroundImage, 0f, 0f);
  }

  public static void main(String[] args) {
    PApplet.main("EscapeRoom");
  }

}
