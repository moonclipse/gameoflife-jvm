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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a collection of {@link Cell}s.
 *
 * @author Raydhitya Yoseph
 */
record Cells(List<Cell> cells) {

  static Cells of(Cell... cells) {
    return new Cells(Arrays.stream(cells).sorted().toList());
  }

  static Cells of(List<Cell> cells) {
    return new Cells(cells.stream().sorted().toList());
  }

  /**
   * Generate the next generation of cells.
   *
   * @return next generation of cells.
   */
  Cells tick() {
    List<Cell> nextCells = new ArrayList<>(cells.size());

    Cells currentCells = tickCurrent();
    nextCells.addAll(currentCells.cells);

    Cells topCells = expandRow(topBoundary());
    if (topCells.hasAliveCell()) {
      nextCells.addAll(topCells.cells);
    }

    Cells bottomCells = expandRow(bottomBoundary());
    if (bottomCells.hasAliveCell()) {
      nextCells.addAll(bottomCells.cells);
    }

    Cells leftCells = expandCol(leftBoundary());
    if (leftCells.hasAliveCell()) {
      nextCells.addAll(leftCells.cells);
    }

    Cells rightCells = expandCol(rightBoundary());
    if (rightCells.hasAliveCell()) {
      nextCells.addAll(rightCells.cells);
    }

    return Cells.of(nextCells);
  }

  private Cells tickCurrent() {
    List<Cell> nextCells = new ArrayList<>(cells.size());
    for (int row = minCell().row(); row <= maxCell().row(); row++) {
      for (int col = minCell().col(); col <= maxCell().col(); col++) {
        nextCells.add(at(row, col).tick(neighborsAt(row, col)));
      }
    }
    return Cells.of(nextCells);
  }

  private Cells expandRow(Integer row) {
    return Cells.of(
        cells.stream()
            .map(Cell::col)
            .distinct()
            .map(col -> at(row, col).tick(neighborsAt(row, col)))
            .toList());
  }

  private Cells expandCol(Integer col) {
    return Cells.of(
        cells.stream()
            .map(Cell::row)
            .distinct()
            .map(row -> at(row, col).tick(neighborsAt(row, col)))
            .toList());
  }

  private boolean hasAliveCell() {
    return cells.stream().anyMatch(Cell::isAlive);
  }

  /**
   * @return a {@link Cell} at given row and col when it exists; a dead {@link Cell} otherwise.
   * @apiNote package-private for testing purpose.
   */
  Cell at(Integer row, Integer col) {
    return cells.stream()
        .filter(cell -> cell.isAt(row, col))
        .findFirst()
        .orElseGet(() -> Cell.ofDead(row, col));
  }

  /**
   * @return a {@link Cells} neighboring a {@link Cell} at given row and col.
   * @apiNote package-private for testing purpose.
   */
  Cells neighborsAt(Integer row, Integer col) {
    return Cells.of(
        at(row - 1, col - 1),
        at(row - 1, col),
        at(row - 1, col + 1),
        at(row, col - 1),
        at(row, col + 1),
        at(row + 1, col - 1),
        at(row + 1, col),
        at(row + 1, col + 1));
  }

  /**
   * @return the number of alive {@link Cell}s.
   */
  Long aliveCount() {
    return Long.valueOf(cells.stream().filter(Cell::isAlive).count());
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int row = minCell().row(); row <= maxCell().row(); row++) {
      for (int col = minCell().col(); col <= maxCell().col(); col++) {
        stringBuilder.append(at(row, col).toDisplayString());
      }
      stringBuilder.append(System.lineSeparator());
    }
    return stringBuilder.toString();
  }

  private Integer topBoundary() {
    return minCell().prevRow();
  }

  private Integer bottomBoundary() {
    return maxCell().nextRow();
  }

  private Integer leftBoundary() {
    return minCell().prevCol();
  }

  private Integer rightBoundary() {
    return maxCell().nextCol();
  }

  private Cell minCell() {
    return cells.stream().min(Comparator.naturalOrder()).get();
  }

  private Cell maxCell() {
    return cells.stream().max(Comparator.naturalOrder()).get();
  }
}
