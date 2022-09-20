package com.gojek.pasti.conway;

import java.util.ArrayList;

public class GameBoard {
  private char[][] grid;
  private final int maxGridSize;

  public GameBoard(int maxGridSize) {
    grid = new char[maxGridSize][maxGridSize];
    this.maxGridSize = maxGridSize;
    createEmptyGridWithDeadCells();
  }

  protected char[][] getCurrentGrid() {
    return grid;
  }

  private void createEmptyGridWithDeadCells() {
    for (int row = 0; row < maxGridSize; row++) {
      for (int col = 0; col < maxGridSize; col++) {
        grid[row][col] = ' ';
      }
    }
  }

  protected void initializePattern(
      ArrayList<ArrayList<Character>> inputPattern, int startX, int startY) {
    for (int row = 0; row < inputPattern.size(); row++) {
      for (int col = 0; col < inputPattern.get(row).size(); col++) {
        grid[startX + row][startY + col] = inputPattern.get(row).get(col);
      }
    }
  }

  protected int numberOfLiveAdjacentCells(int x, int y) {
    int numberOfLiveNeighbors = 0;
    if (x - 1 >= 0 && y - 1 >= 0 && isCellAlive(x - 1, y - 1)) numberOfLiveNeighbors += 1;
    if (x - 1 >= 0 && isCellAlive(x - 1, y)) numberOfLiveNeighbors += 1;
    if (x - 1 >= 0 && y + 1 < maxGridSize && isCellAlive(x - 1, y + 1)) numberOfLiveNeighbors += 1;
    if (y - 1 >= 0 && isCellAlive(x, y - 1)) numberOfLiveNeighbors += 1;
    if (y + 1 < maxGridSize && isCellAlive(x, y + 1)) numberOfLiveNeighbors += 1;
    if (x + 1 < maxGridSize && y - 1 >= 0 && isCellAlive(x + 1, y - 1)) numberOfLiveNeighbors += 1;
    if (x + 1 < maxGridSize && isCellAlive(x + 1, y)) numberOfLiveNeighbors += 1;
    if (x + 1 < maxGridSize && y + 1 < maxGridSize && isCellAlive(x + 1, y + 1))
      numberOfLiveNeighbors += 1;
    return numberOfLiveNeighbors;
  }

  private boolean isCellAlive(int x, int y) {
    return grid[x][y] == '#';
  }

  protected void updateGrid(char[][] temporaryBoard) {
    for (int row = 0; row < maxGridSize; row++) {
      System.arraycopy(temporaryBoard[row], 0, grid[row], 0, maxGridSize);
    }
  }
}
