package com.gojek.pasti.conway;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameControllerTest {
  private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @BeforeAll
  public static void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @BeforeEach
  public void clearUpStreams() {
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @AfterAll
  public static void restoreStreams() {
    System.setOut(System.out);
  }

  @Test
  public void startGame_WhenInputPatternIsGiven_ShouldInitializeGridWithInputPattern()
      throws InterruptedException {
    GameModel testGameModel = new GameModel(2);
    GamePresenter testGamePresenter = new GamePresenter(testGameModel);
    GameController testGameController = new GameController(testGameModel, testGamePresenter);

    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));

    testGameController.startGame(testInputPattern, 0);
    char[][] expectedGrid = new char[2][2];
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        expectedGrid[row][col] = '#';
      }
    }
    assertArrayEquals(expectedGrid, testGameModel.gridState);
  }

  @Test
  public void startGame_WhenInputPatternIsHorizontalBlinker_ShouldDisplayHorizontalBlinker()
      throws InterruptedException {
    GameModel testGameModel = new GameModel(20);
    GamePresenter testGamePresenter = new GamePresenter(testGameModel);
    GameController testGameController = new GameController(testGameModel, testGamePresenter);

    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#', '#')));

    testGameController.startGame(testInputPattern, 0);
    testGamePresenter.printPattern();
    String expectedOutput = "\033[H\033[2JBoard\n     \n ### \n     \n";
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void
      startGame_WhenInputPatternIsHorizontalBlinker_ShouldDisplayVerticalBlinkerAfterOneIteration()
          throws InterruptedException {
    GameModel testGameModel = new GameModel(20);
    GamePresenter testGamePresenter = new GamePresenter(testGameModel);
    GameController testGameController = new GameController(testGameModel, testGamePresenter);

    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#', '#')));

    testGameController.startGame(testInputPattern, 0);
    testGameModel.nextIteration();
    testGamePresenter.printPattern();
    String expectedOutput = "\033[H\033[2JBoard\n     \n  #  \n  #  \n  #  \n     \n";
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void
      startGame_WhenInputPatternIsHorizontalBlinker_ShouldDisplayHorizontalBlinkerAfterTwoIterations()
          throws InterruptedException {
    GameModel testGameModel = new GameModel(20);
    GamePresenter testGamePresenter = new GamePresenter(testGameModel);
    GameController testGameController = new GameController(testGameModel, testGamePresenter);

    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#', '#')));

    testGameController.startGame(testInputPattern, 0);
    testGameModel.nextIteration();
    testGameModel.nextIteration();
    testGamePresenter.printPattern();
    String expectedOutput = "\033[H\033[2JBoard\n     \n     \n ### \n     \n     \n";
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void startGame_WhenInputPatternIsStaticBlock_ShouldShowBlockForNumberOfIterations()
      throws InterruptedException {
    GameModel testGameModel = new GameModel(40);
    GamePresenter testGamePresenter = new GamePresenter(testGameModel);
    GameController testGameController = new GameController(testGameModel, testGamePresenter);

    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));

    testGameController.startGame(testInputPattern, 1);
    String expectedOutput = "\033[H\033[2JBoard\n    \n ## \n ## \n    \n";
    assertEquals(expectedOutput, outContent.toString());
  }
}
