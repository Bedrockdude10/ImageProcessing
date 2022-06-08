import org.junit.Test;

import java.io.StringReader;

import controller.ImageControllerImpl;

public class LoadTest {

  @Test
  public void go() {
    Readable inp = new StringReader("load Images/Koala.ppm koala");
    ImageControllerImpl controller = new ImageControllerImpl(inp);
    controller.playGame();
    System.out.println(controller.getVersions());
  }
}