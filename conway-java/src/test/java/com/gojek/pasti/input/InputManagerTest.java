package com.gojek.pasti.input;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputManagerTest {
  @Test
  public void parseInputFile_WhenFileDoesNotExist_ShouldReturnException() {
    InputManager testInputManager = new InputManager();
    Throwable exception =
        assertThrows(
            FileNotFoundException.class, () -> testInputManager.parseInputFile("Invalid.txt"));
    assertEquals("Invalid.txt (No such file or directory)", exception.getMessage());
  }

  @Test
  public void parseInputFile_WhenFileExists_ShouldReturnInputPattern()
      throws FileNotFoundException {
    String inputFileName = "resources/block.txt";
    InputManager testInputManager = new InputManager();

    ArrayList<ArrayList<Character>> expectedInputPattern = new ArrayList<>();
    expectedInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));
    expectedInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));

    ArrayList<ArrayList<Character>> actualPattern = testInputManager.parseInputFile(inputFileName);
    for (int row = 0; row < expectedInputPattern.size(); row++) {
      for (int col = 0; col < expectedInputPattern.get(row).size(); col++) {
        assertEquals(expectedInputPattern.get(row).get(col), actualPattern.get(row).get(col));
      }
    }
  }
}
