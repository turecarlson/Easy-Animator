package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;

import static org.junit.Assert.assertEquals;

/**
 * A series of tests that tests the AnimationCommand.update(int tick) method, which is used
 * throughout the Visual View.
 */
public class TextTest {

  AnimationModel myTestModel;
  View myView;

  /**
   * Sets up a model to use for our method testing.
   */
  @Before
  public void setUp() throws IOException {
    String inputData = "canvas 200 70 360 360\n"
            + "# declares a rectangle shape named R\n"
            + "shape R rectangle\n"
            + "#        t  x   y   w  h   r   g  b    t   x   y   w  h   r   g  b\n"
            + "motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0\n"
            + "motion R 10 200 200 50 100 255 0  0    50  300 300 50 100 255 0  0\n"
            + "motion R 50 300 300 50 100 255 0  0    51  300 300 50 100 255 0  0\n"
            + "motion R 51 300 300 50 100 255 0  0    70  300 300 25 100 255 0  0\n"
            + "motion R 70 300 300 25 100 0 0  30    100 200 200 25 100 60 30  0\n"
            + "motion R 100 300 300 25 100 0 0  30    200 300 300 50 200 0 0 30\n";

    myTestModel = AnimationReader.parseFile(new StringReader(inputData),
            new AnimationModelImpl.Builder());

    myTestModel.setSpeedMultiplier(1);
    myTestModel.setOut("test.txt");

    myView = AnimationModelImpl.Factory.build("text", myTestModel);
    myView.render();
  }

  @Test
  public void testRender() throws IOException {
    String content = Files.readString(Paths.get("test.txt"), StandardCharsets.US_ASCII);
    assertEquals("Shapes:\n" +
                    "\n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Min corner: (200,200), Width: 50, Height: 100, Color: (255,0,0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=200\n" +
                    "\n" +
                    "Shape R moves from (200,200) to (300,300) from t=10 to t=50\n" +
                    "Shape R scales from Width: 50, Height: 100 to Width: 25, Height: 100 " +
                    "from t=51 to t=70\n" +
                    "Shape R moves from (300,300) to (200,200) from t=70 to t=100\n" +
                    "Shape R changes color from (0,0,30) to (60,30,0) from t=70 to t=100\n" +
                    "Shape R scales from Width: 25, Height: 100 to Width: 50, Height: 200 " +
                    "from t=100 to t=200"
            , content);
  }

  @Test
  public void getOutFile() {
    assertEquals("test.txt", ((Text) myView).getOutFile());
    ((Text) myView).setOutFile("test2.txt");
    assertEquals("test2.txt", ((Text) myView).getOutFile());
  }
}