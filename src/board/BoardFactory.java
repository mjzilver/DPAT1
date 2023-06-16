package board;

import builder.IBoardBuilder;
import builder.JigsawBoardBuilder;
import builder.NormalBoardBuilder;
import builder.SamuraiBoardBuilder;
import reader.FileReader;

import java.io.InputStream;
import java.util.*;

public class BoardFactory implements FileReader {
    private HashMap<String, IBoardBuilder> boardBuilders = new HashMap<>();

    public BoardFactory() {
        boardBuilders.put("9x9", new NormalBoardBuilder(9, 9, 3, 3, 9));
        boardBuilders.put("6x6", new NormalBoardBuilder(6, 6, 3, 2, 6));
        boardBuilders.put("4x4", new NormalBoardBuilder(4, 4, 2, 2, 4));
        boardBuilders.put("samurai", new SamuraiBoardBuilder());
        boardBuilders.put("jigsaw", new JigsawBoardBuilder());
    }

    public Board createBoard(String fileName) {
        String boardType = fileName.substring(fileName.indexOf(".") + 1);
        List<String> fileContent = LoadFile(fileName);
        IBoardBuilder builder = boardBuilders.get(boardType);
        if (builder == null) {
            throw new RuntimeException("Board type not found");
        }
        return builder.build(fileContent);
    }

    @Override
    public List<String> LoadFile(String fileName) {
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

        return lines;
    }
}
