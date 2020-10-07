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
 * A Junit test for the Animation Change color class.
 */

public class AnimationChangeColorTest {

  Rectangle r;
  Oval c;
  AnimationChangeColor changeR;
  AnimationChangeColor changeC;
  AnimationModel myTestModel;

  @Before
  public void setUp() throws IOException {
    r = new Rectangle("R", 200, 200, 10, 20,
            new Color(1, 0, 0), 0, 60);
    c = new Oval("C", 500, 100, 60, 30, Color.blue,
            6, 100);
    changeR = new AnimationChangeColor(r, new Color(1, 0, 0), Color.green,
            10, 20);
    changeC = new AnimationChangeColor(c, 0, 0, 1, 0, 1,
            0, 10, 20);

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
    //RGB to Color
    assertEquals("Shape R changes color from (1,0,0) to (0,255,0) from "
                    + "t=10 to t=20",
            changeR.toString());

    //Color to RGB
    assertEquals("Shape C changes color from (0,0,1) to (0,1,0) from t=10 to t=20",
            changeC.toString());
  }

  @Test
  public void getSVG() {
    assertEquals("\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"10000ms\" " +
                    "attributeName=\"" +
                    "fill\" from=\"rgb(1,0,0)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n",
            changeR.getSVG(myTestModel));
    assertEquals("\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"10000ms\" " +
                    "attributeName=\"" +
                    "fill\" from=\"rgb(0,0,1)\" to=\"rgb(0,1,0)\" fill=\"freeze\" />\n",
            changeC.getSVG(myTestModel));
  }

  @Test
  public void update() {
    c = new Oval("C", 500, 100, 60, 30, Color.blue,
            6, 100);
    changeC = new AnimationChangeColor(c, 0, 0, 1, 0, 1,
            0, 10, 20);

    changeC.update(19, changeC.getShape());

    //R
    assertEquals(changeC.tweening(10,20,19, 0,0),
            c.getColor().getBlue());
    //G
    assertEquals(changeC.tweening(10,20,19, 0,1),
            c.getColor().getGreen());
    //B
    assertEquals(changeC.tweening(10,20,19, 1,0),
            c.getColor().getBlue());

  }
}