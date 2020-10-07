package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.ReadOnlyModel;
import cs5004.animator.view.AbstractSwingView;
import cs5004.animator.view.Svg;

import cs5004.animator.view.View;

/**
 * Represents a Controller for our animation model: handles user actions by executing them using
 * the model and conveying the impact to the user through a SwingView.
 */
public class SwingController implements Controller, ActionListener {

  private final ReadOnlyModel model;
  private final AbstractSwingView view;
  private int tickSpeed;
  private final Timer timer;
  private int tick;
  private boolean loopState;


  /**
   * Construct the controller by passing it both the model and the view.
   *
   * @param model the model of the animation.
   * @param view  the view of the animation.
   */
  public SwingController(ReadOnlyModel model, AbstractSwingView view) {
    tick = 0;
    this.model = model;
    this.view = view;
    this.tickSpeed = model.getSpeedMultiplier();
    timer = new Timer(tickSpeed, this);
    timer.stop();
    loopState = false;
  }

  /**
   * Sets the tickSpeed value for the Controller. `
   *
   * @param tickSpeed - an int that represents the new tickSpeed of the controller, milliseconds
   *                  per tick.
   */
  public void setTickSpeed(int tickSpeed) {
    if (tickSpeed <= 0) {
      throw new IllegalArgumentException("Provided tickSpeed must be an integer greater than 0.");
    }
    this.tickSpeed = tickSpeed;
  }

  /**
   * Returns an int representing the tickSpeed of the controller.
   *
   * @return an int representing the tickSpeed of the controller (milliseconds per tick, used as
   *         the delay of the timer).
   */
  public int getTickSpeed() {
    return tickSpeed;
  }

  /**
   * Sets the value of tick, the current time for the SwingController.
   *
   * @param tick - An int representing the current frame of the animation we are looking at.
   */
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * Returns an int representing the current tick (time/frame) value of the animation.
   *
   * @return an int representing the current tick (time/frame) value of the animation.
   */
  public int getTick() {
    return tick;
  }

  /**
   * Returns the loopState of the controller.
   * @return the loopState of the controller.
   */
  public boolean getLoopState() {
    return loopState;
  }

  /**
   * Returns the Timer Object timer.
   * @return the Timer Object timer.
   */
  public Timer getTimer() {
    return timer;
  }

  /**
   * Starts the timer in the Controller.
   */
  public void startTimer() {
    if (!(timer.isRunning())) {
      timer.start();
    }
  }

  /**
   * Function to be executed when the playButton in a View is pressed. Starts the Animation.
   */
  public void playButton() {
    if (!(timer.isRunning())) {
      timer.start();
    }
  }

  /**
   * Function to be executed when the stopButton in a View is pressed. Stops the Animation and sets
   * tick to zero.
   */
  public void stopButton() {
    if (timer.isRunning()) {
      timer.stop();
    }
    tick = 0;
  }

  /**
   * Function to be executed when the playButton in a View is pressed. Pauses the Animation.
   */
  public void pauseButton() {
    if (timer.isRunning()) {
      timer.stop();
    }
  }

  /**
   * Function to be executed when the playButton in a View is pressed. Starts the Animation over
   * from tick 0.
   */
  public void restartButton() {
    if (timer.isRunning()) {
      timer.stop();
    }
    tick = 0;
    timer.start();
  }

  /**
   * Function to be executed when the playButton in a View is pressed. Increases the speed of the
   * Animation by a multiple of 2.
   */
  public void speedUpButton() {
    int newDelay = timer.getDelay() / 2;
    timer.setDelay(newDelay);
  }

  /**
   * Function to be executed when the playButton in a View is pressed. Decreases the speed of the
   * Animation by half.
   */
  public void speedDownButton() {
    int newDelay;
    if (timer.getDelay() == 0) {
      newDelay = 1;
    } else {
      newDelay = timer.getDelay() * 2;
    }
    timer.setDelay(newDelay);
  }

  /**
   * Generates a .svg file representation of the Animation.
   *
   * @throws IOException if there is an issue writing to the outFile using render().
   */
  public void generateSVGButton() throws IOException {
    View svgView = AnimationModelImpl.Factory.build("svg", (AnimationModel) model);
    ((Svg)svgView).setOutFile("mySvgView.svg");
    svgView.render();
  }

  /**
   * Function to be executed when the loopButton in a View is pressed. Toggles the loopState of the
   * controller.
   */
  public void loopButton() {
    loopState = !loopState;
  }

  /**
   * Function to be called to start the Animation.
   */
  public void launch() {
    view.render();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (tick < model.getLastTick()) {
      tick++;
      view.getPanel().refresh();
      timer.start();
    } else if (loopState) {
      tick = 0;
      timer.start();
    }
  }
}
