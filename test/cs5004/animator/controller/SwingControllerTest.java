package cs5004.animator.controller;

import cs5004.animator.model.AnimationCommand;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.AnimationMove;
import cs5004.animator.model.Rectangle;
import cs5004.animator.view.AbstractSwingView;
import cs5004.animator.view.Playback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A series of tests for methods of the SwingController class.
 */
public class SwingControllerTest {
  AnimationModel testModel;
  Rectangle r;
  AnimationCommand moveR;
  SwingController testController;
  AbstractSwingView testView;

  @org.junit.Before
  public void setUp() throws Exception {
    testModel = new AnimationModelImpl();
    r = new Rectangle("R", 200, 200, 50, 100, 1,
            0, 0,
            1, 100);
    moveR = new AnimationMove(r, 200, 200, 300, 300,
            10, 50);
    testModel.setSpeedMultiplier(20);
    testView = new Playback(testModel);
    testController = new SwingController(testModel, testView);
  }

  @org.junit.Test
  public void setTickSpeed() {
    testController.setTickSpeed(25);
    assertEquals(25, testController.getTickSpeed());
  }

  @org.junit.Test
  public void getTickSpeed() {
    assertEquals(20, testController.getTickSpeed());
  }

  @org.junit.Test
  public void setTick() {
    testController.setTick(5);
    assertEquals(5, testController.getTick());
  }

  @org.junit.Test
  public void getTick() {
    assertEquals(0, testController.getTick());
  }

  @org.junit.Test
  public void getLoopState() {
    assertFalse(testController.getLoopState());
  }

  @org.junit.Test
  public void startTimer() {
    assertFalse(testController.getTimer().isRunning());
    testController.startTimer();
    assertTrue(testController.getTimer().isRunning());
  }

  @org.junit.Test
  public void playButton() {
    assertFalse(testController.getTimer().isRunning());
    testController.playButton();
    assertTrue(testController.getTimer().isRunning());
    testController.playButton();
    assertTrue(testController.getTimer().isRunning());
  }

  @org.junit.Test
  public void stopButton() throws InterruptedException {
    assertFalse(testController.getTimer().isRunning());
    assertEquals(0, testController.getTick());
    testController.stopButton();
    assertFalse(testController.getTimer().isRunning());
    testController.startTimer();
    assertTrue(testController.getTimer().isRunning());
    testController.stopButton();
    assertFalse(testController.getTimer().isRunning());
    assertEquals(0, testController.getTick());
  }

  @org.junit.Test
  public void pauseButton() {
    assertFalse(testController.getTimer().isRunning());
    testController.pauseButton();
    assertFalse(testController.getTimer().isRunning());
    testController.startTimer();
    assertTrue(testController.getTimer().isRunning());
    testController.pauseButton();
    assertFalse(testController.getTimer().isRunning());
  }

  @org.junit.Test
  public void restartButton() {
    assertFalse(testController.getTimer().isRunning());
    testController.pauseButton();
    assertFalse(testController.getTimer().isRunning());
    testController.startTimer();
    assertTrue(testController.getTimer().isRunning());
    testController.pauseButton();
    assertFalse(testController.getTimer().isRunning());
  }

  @org.junit.Test
  public void speedUpButton() {
    assertEquals(20, testController.getTimer().getDelay());
    assertFalse(testController.getTimer().isRunning());
    testController.speedUpButton();
    assertEquals(10, testController.getTimer().getDelay());
    assertFalse(testController.getTimer().isRunning());
    testController.startTimer();
    assertTrue(testController.getTimer().isRunning());
    testController.speedUpButton();
    assertEquals(5, testController.getTimer().getDelay());
    assertTrue(testController.getTimer().isRunning());
  }

  @org.junit.Test
  public void speedDownButton() {
    assertEquals(20, testController.getTimer().getDelay());
    assertFalse(testController.getTimer().isRunning());
    testController.speedDownButton();
    assertEquals(40, testController.getTimer().getDelay());
    assertFalse(testController.getTimer().isRunning());
    testController.startTimer();
    assertTrue(testController.getTimer().isRunning());
    testController.speedDownButton();
    assertEquals(80, testController.getTimer().getDelay());
    assertTrue(testController.getTimer().isRunning());
  }

  @org.junit.Test
  public void loopButton() {
    assertFalse(testController.getLoopState());
    testController.loopButton();
    assertTrue(testController.getLoopState());
  }

}