import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int[][] numbers;
    private Map<Integer, Position> positionMap;
    private Map<Position, Boolean> marked;

    public Board(int[][] numbers) {
        this.numbers = numbers;
        initMap();
    }

    private void initMap()
    {
        marked = new HashMap<>();
        positionMap = new HashMap<>();

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                positionMap.put(numbers[i][j], new Position(i, j));
            }
        }
    }

    /**
     * Will mark a number in board (if exists) and validate if we have bingo afterwards.
     * @param num Number to mark in board
     * @return True, if after marking number board has bingo and false otherwise.
     */
    public boolean mark(int num)
    {
        Position pos = positionMap.get(num);
        if (pos == null) return false;

        marked.put(pos, true);
        return validate(pos);
    }

    private boolean validate(Position pos)
    {
        // validate if we have bingo in horizontal
        int sum = 0;
        for (int x = 0; x < 5; x++) {
            if (marked.get(new Position(x, pos.y)) == null)  break;
            sum += 1;
        }
        if (sum == 5) return true;

        // validate if we have bingo in vertical
        sum = 0;
        for (int y = 0; y < 5; y++) {
            if (marked.get(new Position(pos.x, y)) == null)  break;
            sum += 1;
        }

        return sum == 5;
    }

    public int sumUnMarked()
    {
        int sum = 0;
        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                Position pos = new Position(x, y);
                if (marked.get(pos) == null) sum += numbers[x][y];
            }
        }

        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.equals(numbers, board.numbers);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(numbers);
    }
}
