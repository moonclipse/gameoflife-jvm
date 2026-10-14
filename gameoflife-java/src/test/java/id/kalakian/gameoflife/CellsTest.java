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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CellsTest {

  @Nested
  class At {
    Cells cells = Cells.of(Cell.ofAlive(0, 0));

    @Test
    void shouldReturnExistCell() {
      Assertions.assertEquals(Cell.ofAlive(0, 0), cells.at(0, 0));
    }

    @Test
    void shouldReturnExtinctCell() {
      Assertions.assertEquals(Cell.ofDead(0, 1), cells.at(0, 1));
    }
  }

  @Test
  void shouldReturnNeighbors() {
    Cells neighbors =
        Cells.of(
            Cell.ofDead(-1, -1),
            Cell.ofDead(-1, 0),
            Cell.ofDead(-1, 1),
            Cell.ofDead(0, -1),
            Cell.ofDead(0, 1),
            Cell.ofDead(1, -1),
            Cell.ofDead(1, 0),
            Cell.ofDead(1, 1));
    Assertions.assertEquals(neighbors, Cells.of(Cell.ofAlive(0, 0)).neighborsAt(0, 0));
  }

  @Nested
  class Tick {

    @Test
    void block() {
      Cells block = Patterns.block();

      Assertions.assertEquals(block, block.tick());
    }

    @Test
    void blinker() {
      Cells expectedNextCells =
          Cells.of(
              Cell.ofDead(0, 0),
              Cell.ofAlive(0, 1),
              Cell.ofDead(0, 2),
              Cell.ofDead(1, 0),
              Cell.ofAlive(1, 1),
              Cell.ofDead(1, 2),
              Cell.ofDead(2, 0),
              Cell.ofAlive(2, 1),
              Cell.ofDead(2, 2));

      Assertions.assertEquals(expectedNextCells, Patterns.blinker().tick());
    }

    @Test
    void shouldExpandTopBottom() {
      Cells expectedNextCells =
          Cells.of(
              Cell.ofDead(-1, 0),
              Cell.ofAlive(-1, 1),
              Cell.ofDead(-1, 2),
              Cell.ofDead(0, 0),
              Cell.ofAlive(0, 1),
              Cell.ofDead(0, 2),
              Cell.ofDead(1, 0),
              Cell.ofAlive(1, 1),
              Cell.ofDead(1, 2));

      Assertions.assertEquals(expectedNextCells, Patterns.tightBlinker1().tick());
    }

    @Test
    void shouldExpandLeftRight() {
      Cells expectedNextCells =
          Cells.of(
              Cell.ofDead(0, -1),
              Cell.ofDead(0, 0),
              Cell.ofDead(0, 1),
              Cell.ofAlive(1, -1),
              Cell.ofAlive(1, 0),
              Cell.ofAlive(1, 1),
              Cell.ofDead(2, -1),
              Cell.ofDead(2, 0),
              Cell.ofDead(2, 1));

      Assertions.assertEquals(expectedNextCells, Patterns.tightBlinker2().tick());
    }
  }

  @Nested
  class Equals {

    @Test
    void shouldBeEqual() {
      Assertions.assertEquals(Cells.of(Cell.ofAlive(0, 0)), Cells.of(Cell.ofAlive(0, 0)));
    }

    @Test
    void shouldBeNotEqual() {
      Assertions.assertNotEquals(Cells.of(Cell.ofAlive(0, 0)), Cells.of(Cell.ofDead(0, 0)));
    }
  }

  @Nested
  class ToString {

    @Test
    void shouldReturnOneCell() {
      Assertions.assertEquals("O\n", Cells.of(Cell.ofAlive(0, 0)).toString());
    }

    @Test
    void shouldReturnOneRow() {
      Assertions.assertEquals(
          "O.O\n", Cells.of(Cell.ofAlive(0, 0), Cell.ofDead(0, 1), Cell.ofAlive(0, 2)).toString());
    }

    @Test
    void shouldReturnOneCol() {
      Assertions.assertEquals(
          "O\n.\nO\n",
          Cells.of(Cell.ofAlive(0, 0), Cell.ofDead(1, 0), Cell.ofAlive(2, 0)).toString());
    }

    @Test
    void shouldReturnMultipleRowAndCols() {
      Assertions.assertEquals(
          "O.O\n.O.\nO.O\n",
          Cells.of(
                  Cell.ofAlive(0, 0),
                  Cell.ofDead(0, 1),
                  Cell.ofAlive(0, 2),
                  Cell.ofDead(1, 0),
                  Cell.ofAlive(1, 1),
                  Cell.ofDead(1, 2),
                  Cell.ofAlive(2, 0),
                  Cell.ofDead(2, 1),
                  Cell.ofAlive(2, 2))
              .toString());
    }
  }
}
