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
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.support.ParameterDeclarations;

class TerminalParserTest {

  @ParameterizedTest
  @ArgumentsSource(TerminalParserArgumentsProvider.class)
  void parseShouldReturnCells(String arg, Cells expectedCells) {
    Parser parser = new TerminalParser();
    Assertions.assertEquals(expectedCells, parser.parse(arg));
  }

  static class TerminalParserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
        ParameterDeclarations parameters, ExtensionContext context) throws Exception {
      return Stream.of(
          Arguments.of("1", Patterns.block()),
          Arguments.of("2", Patterns.blinker()),
          Arguments.of("3", Patterns.glider()));
    }
  }
}
