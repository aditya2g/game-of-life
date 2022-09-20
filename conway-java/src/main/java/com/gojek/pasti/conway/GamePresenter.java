package com.gojek.pasti.conway;

public class GamePresenter {
  private GameModel gameModel;

  public GamePresenter(GameModel gameModel) {
    this.gameModel = gameModel;
  }

  public void printPattern() {
    clearScreen();
    System.out.println("Board");
    for (int row = gameModel.startRow - 1; row <= gameModel.endRow; row++) {
      for (int col = gameModel.startCol - 1; col <= gameModel.endCol; col++) {
        System.out.print(gameModel.gridState[row][col]);
      }
      System.out.print("\n");
    }
  }

  private void clearScreen() {
    System.out.print("\033[H\033[2J");
  }
}
