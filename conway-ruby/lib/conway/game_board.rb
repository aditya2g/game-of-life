module Conway
  class GameBoard
    attr_reader :grid

    def initialize(max_grid_size)
      @max_grid_size = max_grid_size
      @grid = Array.new(@max_grid_size) { Array.new(@max_grid_size, ' ') }
    end

    def initialize_pattern(input_pattern, start_row, start_col)
      (0..input_pattern.length - 1).each do |row|
        (0..input_pattern[row].length - 1).each do |col|
          @grid[row + start_row][col + start_col] = input_pattern[row][col]
        end
      end
    end

    def number_of_live_adjacent_cells(x_pos, y_pos)
      number_of_live_cells = 0
      number_of_live_cells += number_live_cells_top_row(x_pos, y_pos)
      number_of_live_cells += number_live_cells_same_row(x_pos, y_pos)
      number_of_live_cells += number_live_cells_bottom_row(x_pos, y_pos)
      number_of_live_cells
    end

    def update_grid(temporary_grid)
      (0..@max_grid_size - 1).each do |row|
        (0..@max_grid_size - 1).each do |col|
          @grid[row][col] = temporary_grid[row][col]
        end
      end
    end

    def number_live_cells_top_row(x_pos, y_pos)
      number_of_live_neighbors = 0
      number_of_live_neighbors += 1 if x_pos - 1 >= 0 && y_pos - 1 >= 0 && cell_alive?(x_pos - 1, y_pos - 1)
      number_of_live_neighbors += 1 if x_pos - 1 >= 0 && cell_alive?(x_pos - 1, y_pos)
      number_of_live_neighbors += 1 if x_pos - 1 >= 0 && y_pos + 1 < @max_grid_size && cell_alive?(x_pos - 1, y_pos + 1)
      number_of_live_neighbors
    end

    def number_live_cells_bottom_row(x_pos, y_pos)
      number_of_live_neighbors = 0
      number_of_live_neighbors += 1 if x_pos + 1 < @max_grid_size && y_pos - 1 >= 0 && cell_alive?(x_pos + 1, y_pos - 1)
      number_of_live_neighbors += 1 if x_pos + 1 < @max_grid_size && cell_alive?(x_pos + 1, y_pos)
      if x_pos + 1 < @max_grid_size && y_pos + 1 < @max_grid_size && cell_alive?(x_pos + 1, y_pos + 1)
        number_of_live_neighbors += 1
      end
      number_of_live_neighbors
    end

    def number_live_cells_same_row(x_pos, y_pos)
      number_of_live_neighbors = 0
      number_of_live_neighbors += 1 if y_pos - 1 >= 0 && cell_alive?(x_pos, y_pos - 1)
      number_of_live_neighbors += 1 if y_pos + 1 < @max_grid_size && cell_alive?(x_pos, y_pos + 1)
      number_of_live_neighbors
    end

    def cell_alive?(x_pos, y_pos)
      @grid[x_pos][y_pos] == '#'
    end

    private :number_live_cells_bottom_row, :number_live_cells_top_row, :number_live_cells_same_row, :cell_alive?
  end
end
