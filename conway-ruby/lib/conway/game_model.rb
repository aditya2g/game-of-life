require_relative 'game_board'

module Conway
  class GameModel
    attr_reader :grid_state, :start_row, :start_col, :end_row, :end_col

    def initialize(max_grid_size)
      @max_grid_size = max_grid_size
      @game_board = Conway::GameBoard.new(max_grid_size)
      @grid_state = @game_board.grid
    end

    def populate_game_board(input_pattern)
      height = height_input_pattern(input_pattern)
      width = width_input_pattern(input_pattern)
      max_dimension_of_input_pattern = [height, width].max
      move_origin_to_point = 0
      if max_dimension_of_input_pattern + 1 < @max_grid_size
        move_origin_to_point = (@max_grid_size - max_dimension_of_input_pattern) / 2
      end
      @game_board.initialize_pattern(input_pattern, move_origin_to_point, move_origin_to_point)
      @start_row = move_origin_to_point
      @start_col = move_origin_to_point
      @end_row = @start_row + height
      @end_col = @start_col + width
    end

    def next_iteration
      temporary_grid = Array.new(@max_grid_size) { Array.new(@max_grid_size, ' ') }
      @flag_extend_left = false
      @flag_extend_right = false
      @flag_extend_top = false
      @flag_extend_bottom = false
      (0..@max_grid_size - 1).each do |row|
        (0..@max_grid_size - 1).each do |col|
          live_adjacent_cells = @game_board.number_of_live_adjacent_cells(row, col)

          temporary_grid[row][col] = '#' if @grid_state[row][col] == ' ' && live_adjacent_cells == 3

          if @grid_state[row][col] == '#' && (live_adjacent_cells >= 2 && live_adjacent_cells < 4)
            temporary_grid[row][col] = '#'
          end

          extend_grid(temporary_grid, row, col)
        end
      end
      change_start_end_coordinates
      @game_board.update_grid(temporary_grid)
    end

    def height_input_pattern(input_pattern)
      input_pattern.length
    end

    def width_input_pattern(input_pattern)
      width = 0
      (0..input_pattern.length - 1).each do |row|
        width = [width, input_pattern[row].length].max
      end
      width
    end

    def extend_grid(temporary_grid, row, col)
      @flag_extend_top = true if temporary_grid[row][col] == '#' && row == @start_row - 1
      @flag_extend_bottom = true if temporary_grid[row][col] == '#' && row == @end_row
      @flag_extend_left = true if temporary_grid[row][col] == '#' && col == @start_col - 1
      @flag_extend_right = true if temporary_grid[row][col] == '#' && col == @end_col
    end

    def change_start_end_coordinates
      @start_row -= 1 if @flag_extend_top
      @start_col -= 1 if @flag_extend_left
      @end_col += 1 if @flag_extend_right
      @end_row += 1 if @flag_extend_bottom
    end

    private :height_input_pattern, :width_input_pattern, :extend_grid, :change_start_end_coordinates
  end
end
