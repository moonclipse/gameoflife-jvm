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
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.support.ParameterDeclarations;

class AliveCellTest {

  @Test
  void whenSamePositionShouldBeEqual() {
    Assertions.assertEquals(Cell.ofAlive(0, 0), Cell.ofAlive(0, 0));
  }

  @Test
  void whenDifferentPositionShouldBeNotEqual() {
    Assertions.assertNotEquals(Cell.ofAlive(0, 0), Cell.ofAlive(1, 1));
  }

  @Test
  void isAliveShouldReturnTrue() {
    Assertions.assertTrue(Cell.ofAlive(0, 0).isAlive());
  }

  @Test
  void toDisplayStringShouldReturnO() {
    Assertions.assertEquals("O", Cell.ofAlive(0, 0).toDisplayString());
  }

  @ParameterizedTest
  @ArgumentsSource(AliveCellNextArgumentsProvider.class)
  void nextShouldReturnNextCell(Cells neighbors, Cell nextCell) {
    Assertions.assertEquals(nextCell, Cell.ofAlive(0, 0).tick(neighbors));
  }

  static class AliveCellNextArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
        ParameterDeclarations parameters, ExtensionContext context) throws Exception {
      return Stream.of(
          Arguments.of(generate(6, 0), Cell.ofDead(0, 0)),
          Arguments.of(generate(6, 1), Cell.ofDead(0, 0)),
          Arguments.of(generate(6, 2), Cell.ofAlive(0, 0)),
          Arguments.of(generate(6, 3), Cell.ofAlive(0, 0)),
          Arguments.of(generate(6, 4), Cell.ofDead(0, 0)),
          Arguments.of(generate(6, 5), Cell.ofDead(0, 0)),
          Arguments.of(generate(6, 6), Cell.ofDead(0, 0)));
    }

    private Cells generate(int count, int aliveCount) {
      int index = 0;
      List<Cell> cells = new ArrayList<>(count);
      while (index < count) {
        if (index < aliveCount) {
          cells.add(Cell.ofAlive(0, 0));
        } else {
          cells.add(Cell.ofDead(0, 0));
        }
        index++;
      }
      return Cells.of(cells);
    }
  }
}
