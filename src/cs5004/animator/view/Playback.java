package cs5004.animator.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import cs5004.animator.model.ReadOnlyModel;

/**
 * Concrete-View for EasyAnimator. Playback is a Swing-view (Extends AbstractSwingView) that gives
 * the end-user control over playback behavior through use of JButtons.
 */
public class Playback extends AbstractSwingView {
  JButton playButton;
  JButton stopButton;
  JButton pauseButton;
  JButton restartButton;
  JButton speedUpButton;
  JButton speedDownButton;
  JButton loopStateButton;
  JButton generateSVGButton;

  /**
   * Constructor for a Playback View Object. Calls the Constructor for that Parent-Class
   * AbstractSwingView.
   *
   * @param model The AnimationModel that this View is representing.
   */
  public Playback(ReadOnlyModel model) {
    super(model);

    //Add Buttons:
    playButton = new JButton();
    stopButton = new JButton();
    pauseButton = new JButton();
    restartButton = new JButton();
    speedUpButton = new JButton();
    speedDownButton = new JButton();
    loopStateButton = new JButton();
    generateSVGButton = new JButton();


    //Set Button Text
    playButton.setText("PLAY");
    stopButton.setText("STOP");
    pauseButton.setText("PAUSE");
    restartButton.setText("RESTART");
    speedUpButton.setText("speedUp");
    speedDownButton.setText("speedDown");
    loopStateButton.setText("LoopState: false");
    generateSVGButton.setText("Generate .svg");
  }

  /**
   * Creates the graphical elements of the Playback View. Calls AbstractSwingView.Render(), and
   * adds JButtons with ActionListeners to control Playback behavior.
   */
  public void render() {
    super.render();

    //Add ActionListeners for Each Button:
    //actionPerformed calls a method specific to each button in the controller.
    ActionListener playAction = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getController().playButton();
      }
    };
    playButton.addActionListener(playAction);

    ActionListener stopAction = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getController().stopButton();
      }
    };
    stopButton.addActionListener(stopAction);

    ActionListener pauseAction = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getController().pauseButton();
      }
    };
    pauseButton.addActionListener(pauseAction);

    ActionListener restartAction = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getController().restartButton();
      }
    };
    restartButton.addActionListener(restartAction);

    ActionListener speedUpAction = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getController().speedUpButton();
      }
    };
    speedUpButton.addActionListener(speedUpAction);

    ActionListener speedDownAction = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getController().speedDownButton();
      }
    };
    speedDownButton.addActionListener(speedDownAction);

    ActionListener loopStateAction = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getController().loopButton();
        if (getController().getLoopState()) {
          loopStateButton.setText("LoopState: true");
        }
        if (!(getController().getLoopState())) {
          loopStateButton.setText("LoopState: false");
        }
      }
    };
    loopStateButton.addActionListener(loopStateAction);

    ActionListener generateSVGAction = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          getController().generateSVGButton();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    };
    generateSVGButton.addActionListener(generateSVGAction);

    //Add Buttons to Panel
    getPanel().add(playButton);
    getPanel().add(stopButton);
    getPanel().add(pauseButton);
    getPanel().add(restartButton);
    getPanel().add(speedUpButton);
    getPanel().add(speedDownButton);
    getPanel().add(loopStateButton);
    getPanel().add(generateSVGButton);
  }
}
