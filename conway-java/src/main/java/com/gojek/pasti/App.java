package com.gojek.pasti;

import com.gojek.pasti.conway.GameController;
import com.gojek.pasti.conway.GameModel;
import com.gojek.pasti.conway.GamePresenter;
import com.gojek.pasti.input.InputManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) throws FileNotFoundException, InterruptedException {
    if (args.length == 1) {
      String fileName = args[0];
      InputManager inputManager = new InputManager();
      ArrayList<ArrayList<Character>> inputPattern = inputManager.parseInputFile(fileName);

      int maxGridSize = 500;
      int iterations = -1;

      GameModel gameModel = new GameModel(maxGridSize);
      GamePresenter gamePresenter = new GamePresenter(gameModel);
      GameController controller = new GameController(gameModel, gamePresenter);

      controller.startGame(inputPattern, iterations);
    }
  }
}
