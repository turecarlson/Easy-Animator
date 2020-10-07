package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;


/**
 * A Junit test for the animation model.
 */
public class AnimationModelTest {

  AnimationModel test;
  AnimationModel model;

  Rectangle r;
  Oval c;
  AnimationCommand moveR;
  AnimationCommand changeC;

  @Before
  public void setUp() {

    test = new AnimationModelImpl();
    r = new Rectangle("R", 200, 200, 50, 100, 1,
            0, 0,
            1, 100);
    c = new Oval("C", 500, 100, 60, 30, Color.BLUE,
            5, 10);
    moveR = new AnimationMove(r, 200, 200, 300, 300,
            10, 50);
    changeC = new AnimationChangeColor(c, 0, 0, 1, 0, 1,
            0, 10, 20);

    model = new AnimationModelImpl();
    model.addShape(r);
    model.addShape(c);
    model.addAnimationCommand(moveR);
    model.addAnimationCommand(changeC);
  }

  @Test
  public void addShape() {
    Oval ellipse = new Oval("C", 500, 100, 60, 30,
            new Color(0, 0, 1),
            5, 10);
    test.addShape(ellipse);
    assertEquals("Shapes:\n\nName: C\n"
            + "Type: oval\n"
            + "Center: (500,100), X radius: 60, Y radius: 30 Color: (0,1,0)\n"
            + "Appears at t=5\n"
            + "Disappears at t=10\n", test.outputDescription());
  }

  @Test
  public void removeShape() {
    Oval ellipse = new Oval("C", 500, 100, 60, 30,
            new Color(0, 0, 1),
            5, 10);
    test.addShape(ellipse);
    assertEquals("Shapes:\n\nName: C\n"
            + "Type: oval\n"
            + "Center: (500,100), X radius: 60, Y radius: 30 Color: (0,1,0)\n"
            + "Appears at t=5\n"
            + "Disappears at t=10\n", test.outputDescription());
    assertEquals(1, test.getShapeList().size());
    test.removeShape(ellipse, test.getShapeList());
    assertEquals(0, test.getShapeList().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShape() {
    test.addShape(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullCommand() {
    test.addAnimationCommand(null);
  }

  @Test
  public void addTwoShapes() {
    test.addShape(c);
    test.addShape(r);
    assertEquals("Shapes:\n\nName: C\n"
            + "Type: oval\n"
            + "Center: (500,100), X radius: 60, Y radius: 30 Color: (0,255,0)\n"
            + "Appears at t=5\n"
            + "Disappears at t=10\n\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200,200), Width: 50, Height: 100, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n", test.outputDescription());
  }

  @Test
  public void addAnimationCommand() {
    test.addAnimationCommand(moveR);
    assertEquals("Shapes:\n\nShape R moves from (200,200) to (300,300) " +
            "from t=10 to t=50", test.outputDescription());
  }

  @Test
  public void addTwoAnimationCommands() {

    r = new Rectangle("R", 200, 200, 50, 100,
            1, 0, 0, 1, 100);
    Oval ellipse = new Oval("C", 500, 100, 60,
            30, new Color(0, 1, 0), 5, 10);

    moveR = new AnimationMove(r, 200, 200, 300, 300,
            10, 50);
    AnimationChangeColor changeColor = new AnimationChangeColor(ellipse, 0, 1,
            0, 0, 0, 1, 10, 20);

    test.addAnimationCommand(moveR);
    test.addAnimationCommand(changeColor);

    assertEquals("Shapes:\n\nShape R moves from (200,200) to (300,300) from t=10 "
                    + "to t=50\n"
                    + "Shape C changes color from (0,1,0) to (0,0,1) from t=10 to t=20",
            test.outputDescription());
  }

  @Test
  public void outputDescription() {
    r = new Rectangle("R", 200, 200, 50, 100, 1,
            0, 0,
            1, 100);
    c = new Oval("C", 500, 100, 60, 30, new Color(0, 0, 1),
            5, 10);

    test.addShape(c);
    test.addShape(r);
    test.addAnimationCommand(moveR);
    test.addAnimationCommand(changeC);

    assertEquals("Shapes:\n\nName: C\n"
                    + "Type: oval\n"
                    + "Center: (500,100), X radius: 60, Y radius: 30 Color: (0,1,0)\n"
                    + "Appears at t=5\n"
                    + "Disappears at t=10\n\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200,200), Width: 50, Height: 100, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n\n"
                    + "Shape R moves from (200,200) to (300,300) from t=10 to t=50\n"
                    + "Shape C changes color from (0,0,1) to (0,1,0) from t=10 to t=20",
            test.outputDescription());
  }

  @Test
  public void getOutfile() {
    test.setOut("SampleOut.svg");
    assertEquals("SampleOut.svg", test.getOutFile());
  }

  @Test
  public void getShapesByTick() {
    r = new Rectangle("R", 200, 200, 50, 100, 1,
            0, 0,
            1, 100);
    moveR = new AnimationMove(r, 200, 200, 300, 300,
            10, 50);
    AnimationModel tickTestingModel = new AnimationModelImpl();
    tickTestingModel.addShape(r);
    tickTestingModel.addAnimationCommand(moveR);
    tickTestingModel.createShapesByTick();

    //Start of Animation
    assertEquals(200, tickTestingModel.getShapesByTick().get(0).get(0).getXPosition());

    //Start of moveR
    assertEquals(200, tickTestingModel.getShapesByTick().get(10).get(0).getXPosition());

    //Halfway through moveR
    assertEquals(250, tickTestingModel.getShapesByTick().get(30).get(0).getXPosition());

    //End of moveR
    assertEquals(300, tickTestingModel.getShapesByTick().get(50).get(0).getXPosition());

    //3/4 of way through Animation
    assertEquals(300, tickTestingModel.getShapesByTick().get(75).get(0).getXPosition());

  }

}
