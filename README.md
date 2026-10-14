# Conway's Game of Life

This project implements Conway's Game of Life.

Please read https://conwaylife.com/wiki/Conway%27s_Game_of_Life to know what it is.

## Usage

A Java runtime is needed. Advised to use SDKMAN! https://sdkman.io/

```shell
# install java
sdk install java 21.0.9-tem

# interactive
./gradlew gameoflife-java:run

# with input file
./gradlew gameoflife-java:run --args="../patterns/glider.cells"
```

Pattern files are in the `patterns` directory.

More patterns can be downloaded from https://conwaylife.com/wiki/Category:Patterns.

## Limitation

The program is using ANSI Escape Codes to clear screen.

It has been tested in a Linux machine.

It should work in a macOS machine.

It has not been tested in a Windows machine.

## File Formats

This implementation supports plain text format.

Please see the format description in https://conwaylife.com/wiki/Plaintext.

Example plain text file:
```text
!Name: Glider
!Author: Richard K. Guy
!The smallest, most common, and first discovered spaceship.
!www.conwaylife.com/wiki/index.php?title=Glider
.O
..O
OOO
```

For other file formats please see https://conwaylife.com/wiki/File_formats.

## Design

The program is designed with functional paradigm in mind.

The universe is implemented a collection of cells.

On every tick, a new cells will be generated from the current cells.

The generation itself is a _pure function_.

A same cells as an input will always generate the same next generation cells.

Each cell is represented using an object.

On every tick, new objects will be created.

The bigger the universe, the slower the program will run as it has to create and destroy a lot of objects.

The Java runtime is designed for short-lived objects, but there is no performance testing being done yet.

## Implementation

The program is implemented in Java. Future plan is to implement the program in Scala and Kotlin.
