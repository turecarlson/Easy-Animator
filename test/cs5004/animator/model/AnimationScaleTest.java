package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.IOException;
import java.io.StringReader;

import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.View;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for the Animation Scale class.
 */
public class AnimationScaleTest {
  public Shape r;
  public Shape c;

  public AnimationScale scale;
  AnimationModel myTestModel;

  @Before
  public void setUp() throws IOException {
    r = new Rectangle("R", 200, 200, 50, 100,
            new Color(1, 0, 0), 0, 60);
    c = new Oval("C", 500, 100, 60, 30, Color.BLUE,
            6, 100);
    scale = new AnimationScale(r, 50, 100, 25, 100,
            10, 50);

    String inputData = "canvas 200 70 360 360\n"
            + "# declares a rectangle shape named R\n"
            + "shape R rectangle\n"
            + "# describes the motions of shape R, between two moments of animation:\n"
            + "# t == tick\n"
            + "# (x,y) == position\n"
            + "# (w,h) == dimensions\n"
            + "# (r,g,b) == color (with values between 0 and 255)\n"
            + "#                  start                           end\n"
            + "#        --------------------------    ----------------------------\n"
            + "#        t  x   y   w  h   r   g  b    t   x   y   w  h   r   g  b\n"
            + "motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0\n"
            + "motion R 10 200 200 50 100 255 0  0    50  300 300 50 100 255 0  0\n"
            + "motion R 50 300 300 50 100 255 0  0    51  300 300 50 100 255 0  0\n"
            + "motion R 51 300 300 50 100 255 0  0    70  300 300 25 100 255 0  0\n"
            + "motion R 70 300 300 25 100 255 0  0    100 200 200 25 100 255 0  0\n"
            + "\n"
            + "shape C ellipse\n"
            + "motion C 6  440 70 120 60 0 0 255 # start state\n"
            + "         20 440 70 120 60 0 0 255 # end state\n"
            + "motion C 20 440 70 120 60 0 0 255      50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255     70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85    80 440 370 120 60 0 255 0\n"
            + "motion C 80 440 370 120 60 0 255 0     100 440 370 120 60 0 255 0";

    myTestModel = AnimationReader.parseFile(new StringReader(inputData),
            new AnimationModelImpl.Builder());

    myTestModel.setSpeedMultiplier(1000);
    myTestModel.setOut("AnimationMoveTest.svg");

    View myView = AnimationModelImpl.Factory.build("svg", myTestModel);
    myView.render();

  }

  @Test
  public void testToString() {
    assertEquals("Shape R scales from Width: 50, Height: 100 to Width: 25, "
            + "Height: 100 from t=10 to t=50", scale.toString());
  }

  @Test
  public void getSVGC() {

    c = new Oval("C", 50, 100, 60, 30, Color.BLUE,
            6, 100);
    scale = new AnimationScale(c, 50, 100, 25, 100,
            10, 50);

    assertEquals("\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" " +
            "attributeName=\"" +
            "rx\" from=\"60\" to=\"25\" fill=\"freeze\" />\n", scale.getSVG(myTestModel));
  }

  @Test
  public void getSVGR() {

    assertEquals("\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" " +
            "attributeName=\"" +
            "width\"" + " from=\"50\" to=\"25\" fill=\"freeze\" />\n", scale.getSVG(myTestModel));
  }

  @Test
  public void update() {
    c = new Oval("C", 500, 100, 60, 30, Color.BLUE,
            0, 100);
    scale = new AnimationScale(c, 60, 30, 25, 100,
            10, 50);

    scale.update(11, scale.getShape());
    assertEquals(c.getM2(), scale.tweening(10, 50, 11, 30,
            100));
  }
}