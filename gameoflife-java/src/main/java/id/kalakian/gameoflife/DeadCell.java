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

/**
 * Represents a dead cell at a row or and a col.
 *
 * @author Raydhitya Yoseph
 */
final class DeadCell extends Cell {

  DeadCell(Integer row, Integer col) {
    super(row, col);
  }

  @Override
  public boolean isAlive() {
    return false;
  }

  @Override
  Cell tick(Cells neighbors) {
    return shouldReproduceGiven(neighbors) ? reproduces() : keep();
  }

  private boolean shouldReproduceGiven(Cells neighbors) {
    return 3 == neighbors.aliveCount();
  }

  private Cell reproduces() {
    return Cell.ofAlive(row(), col());
  }

  private Cell keep() {
    return Cell.ofDead(row(), col());
  }

  @Override
  String toDisplayString() {
    return ".";
  }

  @Override
  public String toString() {
    return "DeadCell{row=" + row() + ", col=" + col() + "}";
  }
}
