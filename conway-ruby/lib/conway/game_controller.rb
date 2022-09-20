module Conway
  class GameController
    def initialize(game_model, game_presenter)
      @game_model = game_model
      @game_presenter = game_presenter
    end

    def start_game(input_pattern, iterations)
      @game_model.populate_game_board(input_pattern)
      @iterations = 0
      while @iterations != iterations
        @game_presenter.print_pattern
        @game_model.next_iteration
        sleep 0.05
        @iterations += 1
      end
    end
  end
end
