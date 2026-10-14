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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final class PlainTextParser implements Parser {
  private final char COMMENT = '!';

  @Override
  public Cells parse(String arg) {
    try {
      List<String> lines = Files.readAllLines(Path.of(arg));
      List<String> cellLines = excludeComments(lines);

      int row = 0;
      int maxCol = maxCol(cellLines);

      List<Cell> cells = new ArrayList<>();
      for (String cellLine : cellLines) {
        int col;
        for (col = 0; col < cellLine.length(); col++) {
          Cell cell = parse(row, col, cellLine.charAt(col));
          cells.add(cell);
        }
        for (col = cellLine.length(); col < maxCol; col++) {
          cells.add(Cell.ofDead(row, col));
        }
        row++;
      }
      return Cells.of(cells);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Invalid file.");
    }
  }

  private Cell parse(int row, int col, char status) {
    return switch (status) {
      case 'O' -> Cell.ofAlive(row, col);
      case '.' -> Cell.ofDead(row, col);
      default -> throw new IllegalArgumentException("Invalid file.");
    };
  }

  private List<String> excludeComments(List<String> lines) {
    return lines.stream().filter(this::isNotComment).toList();
  }

  private boolean isNotComment(String line) {
    return line.charAt(0) != COMMENT;
  }

  private Integer maxCol(List<String> cellLines) {
    return cellLines.stream().map(String::length).max(Comparator.naturalOrder()).get();
  }
}
