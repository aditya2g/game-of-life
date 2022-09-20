package com.gojek.pasti.conway;

import java.util.ArrayList;

public class GameModel {
  private final GameBoard gameBoard;
  private final int maxGridSize;
  public char[][] gridState;
  public int startRow;
  public int startCol;
  public int endRow;
  public int endCol;

  public GameModel(int maxGridSize) {
    this.maxGridSize = maxGridSize;
    gameBoard = new GameBoard(maxGridSize);
    gridState = gameBoard.getCurrentGrid();
  }

  public void populateGameBoard(ArrayList<ArrayList<Character>> inputPattern) {
    int height = heightInputPattern(inputPattern);
    int width = widthInputPattern(inputPattern);
    int maxDimensionOfInputPattern = Math.max(height, width);
    int moveOriginToPoint = 0;
    if (maxDimensionOfInputPattern + 1 < maxGridSize) {
      moveOriginToPoint = (maxGridSize - maxDimensionOfInputPattern) / 2;
    }
    gameBoard.initializePattern(inputPattern, moveOriginToPoint, moveOriginToPoint);
    startRow = moveOriginToPoint;
    startCol = moveOriginToPoint;
    endRow = startRow + height;
    endCol = startCol + width;
  }

  public void nextIteration() {
    boolean flagExtendLeft = false;
    boolean flagExtendRight = false;
    boolean flagExtendTop = false;
    boolean flagExtendBottom = false;
    char[][] temporaryBoard = new char[maxGridSize][maxGridSize];

    for (int row = 0; row < maxGridSize; row++) {
      for (int col = 0; col < maxGridSize; col++) {
        if (gridState[row][col] == ' ') {
          if (gameBoard.numberOfLiveAdjacentCells(row, col) == 3) {
            temporaryBoard[row][col] = '#';
          } else {
            temporaryBoard[row][col] = ' ';
          }
        } else {
          if (gameBoard.numberOfLiveAdjacentCells(row, col) == 2
              || gameBoard.numberOfLiveAdjacentCells(row, col) == 3) {
            temporaryBoard[row][col] = '#';
          } else {
            temporaryBoard[row][col] = ' ';
          }
        }

        if (temporaryBoard[row][col] == '#' && row == startRow - 1) flagExtendTop = true;
        if (temporaryBoard[row][col] == '#' && row == endRow) flagExtendBottom = true;
        if (temporaryBoard[row][col] == '#' && col == startCol - 1) flagExtendLeft = true;
        if (temporaryBoard[row][col] == '#' && col == endCol) flagExtendRight = true;
      }
    }
    extendGrid(flagExtendLeft, flagExtendRight, flagExtendTop, flagExtendBottom);
    gameBoard.updateGrid(temporaryBoard);
  }

  private int heightInputPattern(ArrayList<ArrayList<Character>> inputPattern) {
    return inputPattern.size();
  }

  private int widthInputPattern(ArrayList<ArrayList<Character>> inputPattern) {
    int width = 0;
    for (ArrayList<Character> inputPatternRow : inputPattern) {
      width = Math.max(width, inputPatternRow.size());
    }
    return width;
  }

  private void extendGrid(
      boolean flagLeft, boolean flagRight, boolean flagTop, boolean flagBottom) {
    if (flagTop) startRow -= 1;
    if (flagLeft) startCol -= 1;
    if (flagRight) endCol += 1;
    if (flagBottom) endRow += 1;
  }
}
