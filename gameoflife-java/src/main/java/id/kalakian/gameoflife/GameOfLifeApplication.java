/*
 * Copyright 2026 authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.kalakian.gameoflife;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class GameOfLifeApplication {
  Parser terminalParser = new TerminalParser();
  Parser plainTextParser = new PlainTextParser();

  void run(String[] args) {
    switch (args.length) {
      case 0 -> runTerminal();
      case 1 -> runFile(args);
      default -> runInvalid();
    }
  }

  private void runTerminal() {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Welcome to Game of Life!");
      System.out.println("Press Ctrl+C to stop.");
      System.out.println("1. Block");
      System.out.println("2. Blinker");
      System.out.println("3. Glider");
      System.out.print("Please select a number or a file path: ");

      String number = scanner.nextLine();
      Cells cells = terminalParser.parse(number);
      run(cells);
    } catch (IllegalArgumentException e) {
      System.out.println("Number not recognized.");
    }
  }

  private void runFile(String[] args) {
    try {
      System.out.println("Welcome to Game of Life!");
      System.out.println("Press Ctrl+C to stop.");
      System.out.println("Reading file...");
      TimeUnit.MILLISECONDS.sleep(3000);

      Cells cells = plainTextParser.parse(args[0]);
      run(cells);
    } catch (InterruptedException e) {
      System.out.println("Something terrible happened.");
    }
  }

  private void runInvalid() {
    System.out.println("Wrong arguments.");
  }

  private void run(Cells cells) {
    try {
      while (true) {
        clearScreen();
        System.out.println(cells);
        TimeUnit.MILLISECONDS.sleep(500);
        cells = cells.tick();
      }
    } catch (InterruptedException e) {
      System.out.println("Something terrible happened.");
    }
  }

  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
