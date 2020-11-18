package org.reso.certification.containers;

public interface TestContainer {

  /**
   * Used to initialize the container from the given RESOScript
   */
  void initialize();


  /**
   * Used to resetMarkupBuffer the state of the given TestContainer.
   */
  void resetState();
}
