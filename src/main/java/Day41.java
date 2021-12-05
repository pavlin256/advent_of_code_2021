import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day41 {
    private static List<Integer> numbers;
    private static List<Board> boards = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        read();

        Board wonBoard = null;
        int wonNumber = -1;
        for (Integer number : numbers) {
            for (Board board : boards) {
                if (board.mark(number)) {
                    wonBoard = board;
                    wonNumber = number;
                    break;
                }
            }
            if (wonBoard != null) break;
        }

        System.out.println(wonNumber * wonBoard.sumUnMarked());
    }

    private static void read() throws FileNotFoundException {
        File myObj = new File("/Users/koalena/Downloads/multithreading_parallel/adventofcode_2021/src/main/resources/day4");
        Scanner scanner = new Scanner(myObj);

        numbers = Arrays.stream(scanner.nextLine().split(",")) //"1,2,3" -> ["1", "2", "3"]
                .map(Integer::parseInt) // ["1", "2", "3"] -> [1, 2, 3]
                .collect(Collectors.toList());

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (!s.isEmpty()) throw new IllegalArgumentException("should be empty str");

            int[][] newBoard = new int[5][5];
            for (int i = 0; i < 5; i++) {
                int[] boardLine = Arrays.stream(scanner.nextLine().split(" ")) // "1  2 31 4 5" -> ["1", " ", "2", "31", "4", "5"]
                        .filter(cur -> !cur.isEmpty()) // ["1", " ", "2", "31", "4", "5"] -> ["1", "2", "31", "4", "5"]
                        .mapToInt(Integer::parseInt) // ["1", "2", "31", "4", "5"] -> [1, 2, 31, 4, 5]
                        .toArray();

                newBoard[i] = boardLine;
            }
            boards.add(new Board(newBoard));
        }
    }
}
