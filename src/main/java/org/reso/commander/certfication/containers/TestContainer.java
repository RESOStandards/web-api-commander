package org.reso.commander.certfication.containers;

public interface TestContainer {

  /**
   * Used to initialize the container from the given RESOScript
   */
  void initialize();


  /**
   * Used to reset the state of the given TestContainer.
   */
  void resetState();
}
