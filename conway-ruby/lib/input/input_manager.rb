module Input
  class InputManager
    def parse_input_file(file_name)
      input_pattern = []
      File.open(file_name, 'r').each do |line|
        input_pattern << line.chomp.chars
      end
      input_pattern
    end
  end
end
