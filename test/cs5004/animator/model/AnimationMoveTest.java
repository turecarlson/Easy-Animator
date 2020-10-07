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
 * A Junit test for the AnimationMove class.
 */

public class AnimationMoveTest {

  AnimationModel myTestModel;
  public AbstractAnimationCommand moveR;
  public AbstractAnimationCommand moveC;
  public Rectangle r;
  public Oval c;

  @Before
  public void setUp() throws IOException {
    r = new Rectangle("r", 200, 200, 10, 20,
            new Color(1, 0, 0), 0, 60);
    c = new Oval("C", 500, 100, 60, 30, Color.BLUE,
            6, 100);
    moveR = new AnimationMove(r, 200, 200, 300, 300,
            10, 50);
    moveC = new AnimationMove(c, 500, 100, 500, 400,
            20, 70);

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
    myTestModel.setOut("AnimationMoveTestOut.svg");

    View myView = AnimationModelImpl.Factory.build("svg", myTestModel);
    myView.render();
  }

  @Test
  public void moveToString() {
    assertEquals("Shape r moves from (200,200) to (300,300) from t=10 to t=50",
            moveR.toString());
    assertEquals("Shape C moves from (500,100) to (500,400) from t=20 to t=70",
            moveC.toString());
  }

  @Test
  public void getSVGY() {
    c = new Oval("C", 500, 100, 60, 30, Color.BLUE,
            6, 100);
    moveC = new AnimationMove(c, 500, 100, 500, 400,
            20, 70);

    assertEquals("\t<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"50000ms\" " +
            "attributeName=\"" +
            "cy\" " + "" + "from=\"30\" to=\"330\" fill=\"freeze\" />\n",
            moveC.getSVG(myTestModel));
  }

  @Test
  public void getSVGX() {

    c = new Oval("C", 500, 100, 60, 30, Color.BLUE,
            6, 100);
    AnimationMove move = new AnimationMove(c, 500, 100, 600, 100,
            20, 70);

    assertEquals("\t<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"50000ms\" " +
            "attributeName=\"" +
            "cx\" " + "" + "from=\"300\" to=\"400\" fill=\"freeze\" />\n",
            move.getSVG(myTestModel));
  }

  @Test
  public void getSVGXY() {

    moveR = new AnimationMove(r, 200, 200, 300, 300,
            10, 50);

    assertEquals("\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" " +
            "attributeName=\"x\" " + "" + "from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" " +
            "attributeName=\"y\" " +
            "from=\"130\" " + "to=\"230\" fill=\"freeze\" />\n", moveR.getSVG(myTestModel));
  }

  @Test
  public void update() {
    r = new Rectangle("r", 200, 200, 10, 20,
            new Color(1, 0, 0), 0, 60);

    moveR = new AnimationMove(r, 200, 200, 300, 300,
            10, 50);

    moveR.update(15, moveR.getShape());
    assertEquals(r.getXPosition(), moveR.tweening(10,50,15,200,
            300));
  }
}