package com.gojek.pasti.conway;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GameModelTest {
  @Test
  public void
      gridState_WhenGameModelObjectIsCreated_ShouldInitializeEmptyGridOfSize3x3WithDeadCells() {
    int gridSize = 3;
    GameModel gameModel = new GameModel(gridSize);
    char[][] expectedGrid = new char[gridSize][gridSize];
    for (int row = 0; row < gridSize; row++) {
      for (int col = 0; col < gridSize; col++) {
        expectedGrid[row][col] = ' ';
      }
    }
    assertArrayEquals(expectedGrid, gameModel.gridState);
  }

  @Test
  public void
      gridState_WhenGameModelObjectIsCreated_ShouldInitializeEmptyGridOfGivenSizeWithDeadCells() {
    int gridSize = 5;
    GameModel gameModel = new GameModel(gridSize);
    char[][] expectedGrid = new char[gridSize][gridSize];
    for (int row = 0; row < gridSize; row++) {
      for (int col = 0; col < gridSize; col++) {
        expectedGrid[row][col] = ' ';
      }
    }
    assertArrayEquals(expectedGrid, gameModel.gridState);
  }

  @Test
  public void populateGameBoard_WhenInputPatternIsBlock_ShouldShowBlockOnGridAtOrigin() {
    int gridSize = 2;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);

    char[][] expectedGrid = new char[2][2];
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        expectedGrid[row][col] = '#';
      }
    }
    assertArrayEquals(expectedGrid, testGameModel.gridState);
  }

  @Test
  public void
      populateGameBoard_WhenInputPatternIsRectangleOfSize3X5_ShouldShowPatternInMiddleOfGrid() {
    int gridSize = 40;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<Character>(Arrays.asList('#', '#', '#', '#', '#')));
    testInputPattern.add(new ArrayList<Character>(Arrays.asList('#', '#', '#', '#', '#')));
    testInputPattern.add(new ArrayList<Character>(Arrays.asList('#', '#', '#', '#', '#')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);

    char[][] expectedGrid = new char[gridSize][gridSize];
    for (int row = 0; row < gridSize; row++) {
      for (int col = 0; col < gridSize; col++) {
        if (row >= 17 && row < 20 && col >= 17 && col < 22) {
          expectedGrid[row][col] = '#';
        } else {
          expectedGrid[row][col] = ' ';
        }
      }
    }
    assertArrayEquals(expectedGrid, testGameModel.gridState);
  }

  @Test
  public void nextIteration_WhenGivenCellIsDeadWith3AliveAdjacentCells_ShouldBeAliveInNextStep() {
    int gridSize = 40;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);
    testGameModel.nextIteration();
    assertEquals('#', testGameModel.gridState[19][18]);
  }

  @Test
  public void
      nextIteration_WhenGivenCellIsDeadWithAliveAdjacentCellsNotEqualTo3_ShouldRemainDeadInNextStep() {
    int gridSize = 40;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);
    testGameModel.nextIteration();
    assertEquals(' ', testGameModel.gridState[18][18]);
  }

  @Test
  public void
      nextIteration_WhenGivenCellIsAliveWith2AliveAdjacentCells_ShouldRemainAliveInNextStep() {
    int gridSize = 40;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);
    testGameModel.nextIteration();
    assertEquals('#', testGameModel.gridState[19][19]);
  }

  @Test
  public void
      nextIteration_WhenGivenCellIsAliveWith3AliveAdjacentCells_ShouldRemainAliveInNextStep() {
    int gridSize = 40;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);
    testGameModel.nextIteration();
    assertEquals('#', testGameModel.gridState[19][19]);
  }

  @Test
  public void nextIteration_WhenGivenCellIsAliveWith4AliveAdjacentCells_ShouldBeDeadInNextStep() {
    int gridSize = 40;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#', '#')));
    testInputPattern.add(new ArrayList<>(Arrays.asList(' ', '#', ' ')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);
    testGameModel.nextIteration();
    assertEquals(' ', testGameModel.gridState[19][19]);
  }

  @Test
  public void nextIteration_WhenInputPatternIsBlock_ShouldRemainBlockInNextStep() {
    int gridSize = 4;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);
    testGameModel.nextIteration();

    char[][] expectedGrid = new char[4][4];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 4; col++) {
        if ((row == 1 || row == 2) && (col == 1 || col == 2)) {
          expectedGrid[row][col] = '#';
        } else {
          expectedGrid[row][col] = ' ';
        }
      }
    }
    assertArrayEquals(expectedGrid, testGameModel.gridState);
  }

  @Test
  public void nextIteration_WhenInputPatternIsBlock_ShouldNotIncreaseGridSize() {
    int gridSize = 10;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);

    int startRow = testGameModel.startRow;
    int endRow = testGameModel.endRow;
    int startCol = testGameModel.startCol;
    int endCol = testGameModel.endCol;

    testGameModel.nextIteration();
    testGameModel.nextIteration();
    testGameModel.nextIteration();
    assertEquals(startRow, testGameModel.startRow);
    assertEquals(endRow, testGameModel.endRow);
    assertEquals(startCol, testGameModel.startCol);
    assertEquals(endCol, testGameModel.endCol);
  }

  @Test
  public void nextIteration_WhenInputPatternIsHorizontalBlinker_ShouldIncreaseGridSizeVertically() {
    int gridSize = 10;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Arrays.asList('#', '#', '#')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);

    int startRow = testGameModel.startRow;
    int endRow = testGameModel.endRow;
    int startCol = testGameModel.startCol;
    int endCol = testGameModel.endCol;

    testGameModel.nextIteration();
    assertNotEquals(startRow, testGameModel.startRow);
    assertNotEquals(endRow, testGameModel.endRow);
    assertEquals(startCol, testGameModel.startCol);
    assertEquals(endCol, testGameModel.endCol);
  }

  @Test
  public void nextIteration_WhenInputPatternIsVerticalBlinker_ShouldIncreaseGridSizeHorizontally() {
    int gridSize = 10;
    ArrayList<ArrayList<Character>> testInputPattern = new ArrayList<>();
    testInputPattern.add(new ArrayList<>(Collections.singletonList('#')));
    testInputPattern.add(new ArrayList<>(Collections.singletonList('#')));
    testInputPattern.add(new ArrayList<>(Collections.singletonList('#')));

    GameModel testGameModel = new GameModel(gridSize);
    testGameModel.populateGameBoard(testInputPattern);

    int startRow = testGameModel.startRow;
    int endRow = testGameModel.endRow;
    int startCol = testGameModel.startCol;
    int endCol = testGameModel.endCol;

    testGameModel.nextIteration();
    assertEquals(startRow, testGameModel.startRow);
    assertEquals(endRow, testGameModel.endRow);
    assertNotEquals(startCol, testGameModel.startCol);
    assertNotEquals(endCol, testGameModel.endCol);
  }
}
