package com.gojek.pasti.conway;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameController {
  private GameModel gameModel;
  private GamePresenter gamePresenter;

  public GameController(GameModel gameModel, GamePresenter gamePresenter) {
    this.gameModel = gameModel;
    this.gamePresenter = gamePresenter;
  }

  public void startGame(ArrayList<ArrayList<Character>> inputPattern, int iterations)
      throws InterruptedException {
    gameModel.populateGameBoard(inputPattern);
    int currentIteration = 0;

    while (currentIteration != iterations) {
      gamePresenter.printPattern();
      gameModel.nextIteration();
      TimeUnit.MILLISECONDS.sleep(100);
      currentIteration += 1;
    }
  }
}
