require_relative '../../lib/input/input_manager'

module Input
  describe 'InputManager' do
    describe '#parse_input_file' do
      context 'when input file is given' do
        it 'should parse the input file and return the input pattern' do
          test_input_manager = InputManager.new
          actual_pattern = test_input_manager.parse_input_file('resources/block.txt')
          expected_pattern = []
          expected_pattern.append(%w[# #])
          expected_pattern.append(%w[# #])
          expect(actual_pattern).to eq(expected_pattern)
        end
      end
    end
  end
end
