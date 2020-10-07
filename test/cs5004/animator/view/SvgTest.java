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
 * A JUnit test for the SVG view.
 */


public class SvgTest {
  AnimationModel myTestModel;
  String outString;
  View myView;

  @Before
  public void setUp() throws Exception {
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
    myTestModel.setOut("SVGtest.svg");

    myView = AnimationModelImpl.Factory.build("svg", myTestModel);
    myView.render();
  }

  @Test
  public void render() throws IOException {
    String content = Files.readString(Paths.get("SVGtest.svg"), StandardCharsets.US_ASCII);
    assertEquals("<svg width=\"360\" height=\"360\" version=\"1.1\"\n" +
            "\txmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"R\" x=\"0\" y=\"130\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\"" +
            " visibility=\"visible\" >\n" +
            "\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" " +
            "attributeName=\"x\"" +
            " from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" " +
            "attributeName=\"y\"" +
            " from=\"130\" to=\"230\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"51000ms\" dur=\"19000ms\" " +
            "attributeName=\"width\"" +
            " from=\"50\" to=\"25\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"70000ms\" dur=\"30000ms\" " +
            "attributeName=\"x\" " +
            "from=\"100\" to=\"0\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"70000ms\" dur=\"30000ms\" " +
            "attributeName=\"y\" " +
            "from=\"230\" to=\"130\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "<ellipse id=\"C\" cx=\"240\" cy=\"0\" rx=\"120\" ry=\"60\" fill=\"rgb(0,0,255)\" " +
            "visibility=\"visible\" >\n" +
            "\t<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"30000ms\" " +
            "attributeName=\"cy\" " +
            "from=\"0\" to=\"180\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\" " +
            "attributeName=\"cy\" " +
            "from=\"180\" to=\"300\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\" " +
            "attributeName=\"fill\" " +
            "from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"70000ms\" dur=\"10000ms\" " +
            "attributeName=\"fill\" " +
            "from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>", content);
  }

  @Test
  public void getOutFile() {
    assertEquals("SVGtest.svg", ((Svg)myView).getOutFile());
    ((Svg) myView).setOutFile("test.svg");
    assertEquals("test.svg", ((Svg)myView).getOutFile());

  }
}