# Conway's Game of Life

## Problem Description

This project is a simulation showing the popular zero-player game Conway's Game
of Life showing the cellular automation based on the following rules:

A 2D infinite orthogonal grid of square cells represents a board with each cell
in 2 possible states live or dead. Every cell interacts with its eight
neighbours, which are the cells that are horizontally, vertically, or
diagonally adjacent. At each step in time, the following transitions occur:

- Any live cell with fewer than 2 neighbors dies by the underpopulation.
- Any live cell with 2 or 3 neighbors survives the current time stamp and lives
 on to the next generation.
- Any live cell with more than 3 neighbors dies by overpopulation.
- Any dead cell with exactly three live neighbors becomes a live cell.

## Dependencies

- Ruby >= 2.5.0
- Bundler 2.1.4

## Installation

Install the dependencies by running: `bundle install`

## Run Instructions

Run the project by following the instructions given below:

```shell script
bundle exec ruby ./lib/app.rb <input-file.txt>
```

For sample patterns use the given paths:

- Block -> 'resources/block.txt'
- LWSS -> 'resources/LWSS.txt'
- Boat -> 'resources/boat.txt'
- Glider -> 'resources/glider.txt'
- Gosper Glider -> 'resources/gosper.txt'
- Loaf -> 'resources/loaf.txt'
- Pulsar -> 'resources/pulsar.txt'
- Toad -> 'resources/toad.txt'

## Testing

Run the tests with coverage report by running: `bundle exec rspec`

To view coverage report, open `./coverage/index.html` or run `open ./coverage/index.html`

## Linting

Run the linter before committing the code by doing: `bundle exec rubocop -a`
