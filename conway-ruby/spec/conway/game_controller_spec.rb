require_relative '../../lib/conway/game_controller'
require_relative '../../lib/conway/game_model'
require_relative '../../lib/conway/game_presenter'

module Conway
  describe 'GameController' do
    describe 'start_game' do
      context 'when input pattern is given to controller' do
        it 'should initialize grid with input pattern' do
          test_game_model = GameModel.new(2)
          test_game_presenter = GamePresenter.new(test_game_model)
          test_game_controller = GameController.new(test_game_model, test_game_presenter)
          test_input_pattern = []
          test_input_pattern.append(%w[# #])
          test_input_pattern.append(%w[# #])
          test_game_controller.start_game(test_input_pattern, 0)
          expected_grid = Array.new(2) { Array.new(2, '#') }
          expect(test_game_model.grid_state).to eq(expected_grid)
        end
      end

      context 'when input pattern is horizontal blinker' do
        it 'should display horizontal blinker on console' do
          test_game_model = GameModel.new(20)
          test_game_presenter = GamePresenter.new(test_game_model)
          test_game_controller = GameController.new(test_game_model, test_game_presenter)
          test_input_pattern = []
          test_input_pattern.append(%w[# # #])
          test_game_controller.start_game(test_input_pattern, 0)
          expected_output = "Board\n     \n ### \n     \n"
          expect do
            test_game_presenter.print_pattern
          end.to output(expected_output).to_stdout
        end
      end

      context 'when input pattern is horizontal blinker' do
        it 'should display vertical blinker after one iteration' do
          test_game_model = GameModel.new(20)
          test_game_presenter = GamePresenter.new(test_game_model)
          test_game_controller = GameController.new(test_game_model, test_game_presenter)
          test_input_pattern = []
          test_input_pattern.append(%w[# # #])
          test_game_controller.start_game(test_input_pattern, 0)
          test_game_model.next_iteration
          expected_output = "Board\n     \n  #  \n  #  \n  #  \n     \n"
          expect do
            test_game_presenter.print_pattern
          end.to output(expected_output).to_stdout
        end

        it 'should display horizontal blinker after two iterations' do
          test_game_model = GameModel.new(20)
          test_game_presenter = GamePresenter.new(test_game_model)
          test_game_controller = GameController.new(test_game_model, test_game_presenter)
          test_input_pattern = []
          test_input_pattern.append(%w[# # #])
          test_game_controller.start_game(test_input_pattern, 0)
          test_game_model.next_iteration
          test_game_model.next_iteration
          expected_output = "Board\n     \n     \n ### \n     \n     \n"
          expect do
            test_game_presenter.print_pattern
          end.to output(expected_output).to_stdout
        end
      end

      context 'when input pattern is static block to controller' do
        it 'should remain static block after few iterations' do
          test_game_model = GameModel.new(40)
          test_game_presenter = GamePresenter.new(test_game_model)
          test_game_controller = GameController.new(test_game_model, test_game_presenter)
          test_input_pattern = []
          test_input_pattern.append(%w[# #])
          test_input_pattern.append(%w[# #])

          expected_output = "Board\n    \n ## \n ## \n    \n"
          expect do
            test_game_controller.start_game(test_input_pattern, 1)
          end.to output(expected_output).to_stdout
        end
      end
    end
  end
end
