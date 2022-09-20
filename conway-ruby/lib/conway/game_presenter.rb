module Conway
  class GamePresenter
    def initialize(game_model)
      @game_model = game_model
    end

    def print_pattern
      system 'clear'
      puts('Board')
      board_string = ''
      (@game_model.start_row - 1..@game_model.end_row).each do |row|
        (@game_model.start_col - 1..@game_model.end_col).each do |col|
          board_string += @game_model.grid_state[row][col]
        end
        board_string += "\n"
      end

      puts(board_string)
    end
  end
end
