require_relative 'input/input_manager'
require_relative 'conway/game_presenter'
require_relative 'conway/game_model'
require_relative 'conway/game_controller'

exit(1) if ARGV.empty?
file_name = ARGV[0]

input_manager = Input::InputManager.new
input_pattern = input_manager.parse_input_file(file_name)

max_grid_size = 250
iterations = -1

game_model = Conway::GameModel.new(max_grid_size)
game_presenter = Conway::GamePresenter.new(game_model)
game_controller = Conway::GameController.new(game_model, game_presenter)

game_controller.start_game(input_pattern, iterations)
