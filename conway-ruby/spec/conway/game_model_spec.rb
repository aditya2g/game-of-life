require_relative '../../lib/conway/game_model'

module Conway
  describe 'GameModel' do
    context 'when game_model object is created' do
      it 'should initialize empty grid of all dead cells of size 3x3' do
        test_game_model = GameModel.new(3)
        expected_grid = Array.new(3) { Array.new(3, ' ') }
        expect(test_game_model.grid_state).to eq(expected_grid)
      end
    end

    context 'when game_model object is created' do
      it 'should initialize empty grid of all dead cells of given max size' do
        test_game_model = GameModel.new(15)
        expected_grid = Array.new(15) { Array.new(15, ' ') }
        expect(test_game_model.grid_state).to eq(expected_grid)
      end
    end

    describe '#populate_game_board' do
      context 'when input pattern is block' do
        it 'should show block on grid at origin' do
          test_input_pattern = []
          test_input_pattern.append(%w[# #])
          test_input_pattern.append(%w[# #])
          test_game_model = GameModel.new(2)
          test_game_model.populate_game_board(test_input_pattern)
          expected_grid = Array.new(2) { Array.new(2, '#') }
          expect(test_game_model.grid_state).to eq(expected_grid)
        end

        it 'should show block in middle of the grid' do
          grid_size = 40
          test_input_pattern = []
          test_input_pattern.append(%w[# # # # #])
          test_input_pattern.append(%w[# # # # #])
          test_input_pattern.append(%w[# # # # #])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)

          expected_grid = Array.new(grid_size) { Array.new(grid_size, ' ') }
          (17..21).each do |col|
            expected_grid[17][col] = '#'
            expected_grid[18][col] = '#'
            expected_grid[19][col] = '#'
          end
          expect(test_game_model.grid_state).to eq(expected_grid)
        end
      end
    end

    describe 'next_iteration' do
      context 'when given cell is dead with 3 alive adjacent cells' do
        it 'should be alive in next time step' do
          grid_size = 40
          test_input_pattern = []
          test_input_pattern.append([' ', '#', ' '])
          test_input_pattern.append([' ', '#', ' '])
          test_input_pattern.append([' ', '#', ' '])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)
          test_game_model.next_iteration
          expect(test_game_model.grid_state[19][18]).to eq('#')
        end
      end

      context 'when given cell is dead with alive adjacent cells not equal to 3' do
        it 'should remain dead in next time step' do
          grid_size = 40
          test_input_pattern = []
          test_input_pattern.append([' ', '#', ' '])
          test_input_pattern.append([' ', '#', ' '])
          test_input_pattern.append([' ', '#', ' '])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)
          test_game_model.next_iteration
          expect(test_game_model.grid_state[18][18]).to eq(' ')
        end
      end

      context 'when given cell is alive with 2 alive adjacent cells' do
        it 'should remain alive in next time step' do
          grid_size = 40
          test_input_pattern = []
          test_input_pattern.append([' ', '#', ' '])
          test_input_pattern.append([' ', '#', ' '])
          test_input_pattern.append([' ', '#', ' '])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)
          test_game_model.next_iteration
          expect(test_game_model.grid_state[19][19]).to eq('#')
        end
      end

      context 'when given cell is alive with 3 alive adjacent cells' do
        it 'should remain alive in next time step' do
          grid_size = 40
          test_input_pattern = []
          test_input_pattern.append([' ', '#', ' '])
          test_input_pattern.append(['#', '#', ' '])
          test_input_pattern.append([' ', '#', ' '])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)
          test_game_model.next_iteration
          expect(test_game_model.grid_state[19][19]).to eq('#')
        end
      end

      context 'when given cell is alive with 4 alive adjacent cells' do
        it 'should be dead in next time step' do
          grid_size = 40
          test_input_pattern = []
          test_input_pattern.append([' ', '#', ' '])
          test_input_pattern.append(%w[# # #])
          test_input_pattern.append([' ', '#', ' '])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)
          test_game_model.next_iteration
          expect(test_game_model.grid_state[19][19]).to eq(' ')
        end
      end

      context 'when input pattern is block at the boundary' do
        it 'should remain block in next time step' do
          grid_size = 2
          test_input_pattern = []
          test_input_pattern.append(%w[# #])
          test_input_pattern.append(%w[# #])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)
          test_game_model.next_iteration

          expected_grid = Array.new(2) { Array.new(2, '#') }
          expect(test_game_model.grid_state).to eq(expected_grid)
        end
      end

      context 'when input pattern is block' do
        it 'should not increase grid size' do
          grid_size = 10
          test_input_pattern = []
          test_input_pattern.append(%w[# #])
          test_input_pattern.append(%w[# #])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)

          expected_start_row = test_game_model.start_row
          expected_start_col = test_game_model.start_col
          expected_end_row = test_game_model.end_row
          expected_end_col = test_game_model.end_col

          test_game_model.next_iteration
          test_game_model.next_iteration
          test_game_model.next_iteration
          expect(test_game_model.start_row).to eq(expected_start_row)
          expect(test_game_model.start_col).to eq(expected_start_col)
          expect(test_game_model.end_row).to eq(expected_end_row)
          expect(test_game_model.end_col).to eq(expected_end_col)
        end
      end

      context 'when input pattern is horizontal blinker' do
        it 'should increase grid size vertically' do
          grid_size = 10
          test_input_pattern = []
          test_input_pattern.append(%w[# # #])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)

          expected_start_row = test_game_model.start_row
          expected_start_col = test_game_model.start_col
          expected_end_row = test_game_model.end_row
          expected_end_col = test_game_model.end_col

          test_game_model.next_iteration
          expect(test_game_model.start_row).to_not eq(expected_start_row)
          expect(test_game_model.end_row).to_not eq(expected_end_row)
          expect(test_game_model.start_col).to eq(expected_start_col)
          expect(test_game_model.end_col).to eq(expected_end_col)
        end
      end

      context 'when input pattern is vertical blinker' do
        it 'should increase grid size horizontally' do
          grid_size = 10
          test_input_pattern = []
          test_input_pattern.append(%w[#])
          test_input_pattern.append(%w[#])
          test_input_pattern.append(%w[#])

          test_game_model = GameModel.new(grid_size)
          test_game_model.populate_game_board(test_input_pattern)

          expected_start_row = test_game_model.start_row
          expected_start_col = test_game_model.start_col
          expected_end_row = test_game_model.end_row
          expected_end_col = test_game_model.end_col

          test_game_model.next_iteration
          expect(test_game_model.start_row).to eq(expected_start_row)
          expect(test_game_model.end_row).to eq(expected_end_row)
          expect(test_game_model.start_col).to_not eq(expected_start_col)
          expect(test_game_model.end_col).to_not eq(expected_end_col)
        end
      end
    end
  end
end
