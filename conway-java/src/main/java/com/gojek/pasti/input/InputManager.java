package com.gojek.pasti.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {
  Scanner readFile;

  private void readFile(String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    readFile = new Scanner(file);
  }

  public ArrayList<ArrayList<Character>> parseInputFile(String fileName)
      throws FileNotFoundException {
    readFile(fileName);
    return InputPatterFromFile();
  }

  private ArrayList<ArrayList<Character>> InputPatterFromFile() {
    ArrayList<ArrayList<Character>> inputPattern = new ArrayList<>();
    while (readFile.hasNextLine()) {
      String line = readFile.nextLine();
      ArrayList<Character> inputLine = new ArrayList<>();
      for (int index = 0; index < line.length(); index++) {
        inputLine.add(line.charAt(index));
      }
      inputPattern.add(inputLine);
    }
    return inputPattern;
  }
}
