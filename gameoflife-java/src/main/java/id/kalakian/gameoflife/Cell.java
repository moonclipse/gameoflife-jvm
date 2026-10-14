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

import java.util.Objects;

/**
 * Represents an alive or a dead cell at a row and a col.
 *
 * <ul>
 *   <li>Any live cell with fewer than two live neighbors dies, as if by underpopulation.
 *   <li>Any live cell with two or three live neighbors lives on to the next generation.
 *   <li>Any live cell with more than three live neighbors dies, as if by overpopulation.
 *   <li>Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * </ul>
 *
 * @author Raydhitya Yoseph
 */
abstract sealed class Cell implements Comparable<Cell> permits AliveCell, DeadCell {

  static Cell ofAlive(Integer row, Integer col) {
    return new AliveCell(row, col);
  }

  static Cell ofDead(Integer row, Integer col) {
    return new DeadCell(row, col);
  }

  protected Cell(Integer row, Integer col) {
    this.row = row;
    this.col = col;
  }

  private final Integer row;
  private final Integer col;

  Integer row() {
    return this.row;
  }

  Integer prevRow() {
    return this.row - 1;
  }

  Integer nextRow() {
    return this.row + 1;
  }

  Integer col() {
    return this.col;
  }

  Integer prevCol() {
    return this.col - 1;
  }

  Integer nextCol() {
    return this.col + 1;
  }

  boolean isAt(Integer row, Integer col) {
    return isRow(row) && isCol(col);
  }

  private boolean isRow(Integer row) {
    return this.row.equals(row);
  }

  private boolean isCol(Integer col) {
    return this.col.equals(col);
  }

  abstract boolean isAlive();

  abstract Cell tick(Cells neighbors);

  abstract String toDisplayString();

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Cell cell = (Cell) o;
    return Objects.equals(row, cell.row) && Objects.equals(col, cell.col);
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }

  @Override
  public int compareTo(Cell o) {
    if (row.equals(o.row)) {
      return col.compareTo(o.col);
    } else if (row < o.row) {
      return -1;
    } else {
      return 1;
    }
  }
}
