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
 * A factory of common patterns.
 *
 * @author Raydhitya Yoseph
 */
public class Patterns {

  private Patterns() {}

  static Cells block() {
    return Cells.of(Cell.ofAlive(0, 0), Cell.ofAlive(0, 1), Cell.ofAlive(1, 0), Cell.ofAlive(1, 1));
  }

  static Cells blinker() {
    return Cells.of(
        Cell.ofDead(0, 0),
        Cell.ofDead(0, 1),
        Cell.ofDead(0, 2),
        Cell.ofAlive(1, 0),
        Cell.ofAlive(1, 1),
        Cell.ofAlive(1, 2),
        Cell.ofDead(2, 0),
        Cell.ofDead(2, 1),
        Cell.ofDead(2, 2));
  }

  static Cells glider() {
    return Cells.of(
        Cell.ofDead(0, 0),
        Cell.ofAlive(0, 1),
        Cell.ofDead(0, 2),
        Cell.ofDead(1, 0),
        Cell.ofDead(1, 1),
        Cell.ofAlive(1, 2),
        Cell.ofAlive(2, 0),
        Cell.ofAlive(2, 1),
        Cell.ofAlive(2, 2));
  }

  static Cells tightBlinker1() {
    return Cells.of(Cell.ofAlive(0, 0), Cell.ofAlive(0, 1), Cell.ofAlive(0, 2));
  }

  static Cells tightBlinker2() {
    return Cells.of(Cell.ofAlive(0, 0), Cell.ofAlive(1, 0), Cell.ofAlive(2, 0));
  }
}
