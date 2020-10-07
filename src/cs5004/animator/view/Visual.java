package cs5004.animator.view;

import cs5004.animator.model.ReadOnlyModel;


/**
 * Concrete-View for EasyAnimator. Playback is a Swing-view (Extends AbstractSwingView) that gives
 * the end-user no control over playback behavior, but rather just runs the animation.
 */
public class Visual extends AbstractSwingView {


  /**
   * Construct the visual frame using data from the model. Calls the Constructor for
   * AbstractSwingView.
   *
   * @param myModel the model that our animation is representing.
   */
  public Visual(ReadOnlyModel myModel) {
    super(myModel);
  }

  /**
   * Initializes the timer and makes the JFrame visible.
   */
  public void render() {
    super.render();
    setFrameTitle("Easy Animator - Visual");
    getController().startTimer();
  }

}


