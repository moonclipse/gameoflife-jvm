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

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.support.ParameterDeclarations;

class CellTest {

  @Test
  void aliveCellAndDeadCellShouldBeNotEqual() {
    Assertions.assertNotEquals(Cell.ofAlive(0, 0), Cell.ofDead(1, 1));
  }

  @Nested
  class Row {
    Cell cell = Cell.ofAlive(1, 1);

    @Test
    void shouldReturnRow() {
      Assertions.assertEquals(1, cell.row());
    }

    @Test
    void shouldReturnPrevRow() {
      Assertions.assertEquals(0, cell.prevRow());
    }

    @Test
    void shouldReturnNextRow() {
      Assertions.assertEquals(2, cell.nextRow());
    }
  }

  @Nested
  class Col {
    Cell cell = Cell.ofAlive(1, 1);

    @Test
    void shouldReturnRow() {
      Assertions.assertEquals(1, cell.col());
    }

    @Test
    void shouldReturnPrevRow() {
      Assertions.assertEquals(0, cell.prevCol());
    }

    @Test
    void shouldReturnNextRow() {
      Assertions.assertEquals(2, cell.nextCol());
    }
  }

  @Nested
  class IsAt {

    @Test
    void whenRowAndColSameShouldReturnTrue() {
      Assertions.assertTrue(Cell.ofAlive(0, 0).isAt(0, 0));
    }

    @Test
    void whenRowDifferentShouldReturnFalse() {
      Assertions.assertFalse(Cell.ofAlive(0, 0).isAt(1, 0));
    }

    @Test
    void whenColDifferentShouldReturnFalse() {
      Assertions.assertFalse(Cell.ofAlive(0, 0).isAt(0, 1));
    }
  }

  @Nested
  class CompareTo {

    @ParameterizedTest
    @ArgumentsSource(CompareToArgumentsProvider.class)
    void shouldReturnComparisonResult(Cell cell1, Cell cell2, Integer comparisonResult) {
      Assertions.assertEquals(comparisonResult, cell1.compareTo(cell2));
    }

    static class CompareToArgumentsProvider implements ArgumentsProvider {

      @Override
      public Stream<? extends Arguments> provideArguments(
          ParameterDeclarations parameters, ExtensionContext context) throws Exception {
        return Stream.of(
            // same row
            Arguments.of(Cell.ofAlive(0, 1), Cell.ofAlive(0, 2), -1),
            Arguments.of(Cell.ofAlive(0, 1), Cell.ofAlive(0, 1), 0),
            Arguments.of(Cell.ofAlive(0, 1), Cell.ofAlive(0, 0), 1),
            // smaller row
            Arguments.of(Cell.ofAlive(0, 1), Cell.ofAlive(1, 2), -1),
            Arguments.of(Cell.ofAlive(0, 1), Cell.ofAlive(1, 1), -1),
            Arguments.of(Cell.ofAlive(0, 1), Cell.ofAlive(1, 0), -1),
            // bigger row
            Arguments.of(Cell.ofAlive(1, 1), Cell.ofAlive(0, 2), 1),
            Arguments.of(Cell.ofAlive(1, 1), Cell.ofAlive(0, 1), 1),
            Arguments.of(Cell.ofAlive(1, 1), Cell.ofAlive(0, 0), 1));
      }
    }
  }
}
