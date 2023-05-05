package board;

import builder.IBoardBuilder;
import builder.NormalBoardBuilder;
import reader.FileReader;

import java.io.InputStream;
import java.util.*;

public class BoardFactory implements FileReader {
    private HashMap<String, IBoardBuilder> _boardBuilders = new HashMap<>();

    public BoardFactory() {
        _boardBuilders.put("9x9", new NormalBoardBuilder(9, 9, 3, 3, 9));
        _boardBuilders.put("6x6", new NormalBoardBuilder(6, 6, 3, 2, 6));
        _boardBuilders.put("4x4", new NormalBoardBuilder(4, 4, 2, 2, 4));
    }

    public Board createBoard(String fileName) {
        String boardType = fileName.substring(fileName.indexOf(".") + 1);
        List<Integer> nums = LoadFile(fileName);
        IBoardBuilder builder = _boardBuilders.get(boardType);
        if (builder == null) {
            throw new RuntimeException("Board type not found");
        }
        return builder.build(nums);
    }

    public Board createBoard() {
        Board board = new Board(9, 9, 3, 3, 9);
        Random random = new Random();

        ArrayList<CellHolder> rows = board.getRows();
        for (int y = 0; y < rows.size(); y++) {
            ArrayList<Cell> cells = board.getRows().get(y).getCells();
            for (int x = 0; x < cells.size(); x++) {
                board.setCell(y, x, random.nextInt(10), CellType.FINAL);
            }
        }

        return board;
    }

    @Override
    public List<Integer> LoadFile(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);

        List<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Integer> nums = new ArrayList<>();
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                nums.add(Character.getNumericValue(c));
            }
        }

        return nums;
    }
}
