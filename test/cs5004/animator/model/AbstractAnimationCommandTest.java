package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for the abstract animation command.
 */
public class AbstractAnimationCommandTest {

  AbstractAnimationCommand moveR;
  AbstractAnimationCommand changeC;
  Rectangle r;
  Oval c;
  AbstractAnimationCommand scale;

  @Before
  public void setUp() {
    r = new Rectangle("r", 200, 200, 10, 20,
            new Color(1, 0, 0), 0, 60);
    c = new Oval("C", 500, 100, 60, 30, Color.BLUE,
            6, 100);
    moveR = new AnimationMove(r, 200, 200, 300, 300,
            10, 50);
    changeC = new AnimationChangeColor(c, 0, 0, 0, 0, 1,
            0, 10, 20);
    scale = new AnimationScale(r, 10, 20, 25, 100,
            10, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeStart() {
    scale = new AnimationScale(r, 100, 100,
            25, 100, -1, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeEnd() {
    scale = new AnimationScale(r, 100, 100,
            25, 100, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startAfterEnd() {
    moveR = new AnimationMove(r, 100, 100,
            300, 300, 60, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startEqualsEnd() {
    changeC = new AnimationChangeColor(r, Color.BLUE, Color.pink, 50, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShape() {
    changeC = new AnimationChangeColor(null, Color.BLUE, Color.pink, 50, 50);
  }

  @Test
  public void getShape() {
    assertEquals(r.toString(), moveR.getShape().toString());
    assertEquals(c.toString(), changeC.getShape().toString());
  }

  @Test
  public void getStartTime() {
    assertEquals(10, moveR.getStartTime());
    assertEquals(10, changeC.getStartTime());
  }

  @Test
  public void getEndTime() {
    assertEquals(50, scale.getEndTime());
    assertEquals(20, changeC.getEndTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativetTweening() {
    changeC.tweening(1, 10, -1, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTweeningAfter() {
    changeC.tweening(1, 10, 11, 10, 20);
  }
}