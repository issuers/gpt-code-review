static class QueueNode {
    Coordinate coordinate;
    int distance;
    public QueueNode(Coordinate coordinate, int distance) {
        this.coordinate = coordinate;
        this.distance = distance;
    }
}

    private final int[][] POSSIBLE_MOVES = {
            {0, -1}, // move left
            {-1, 0}, // move up
            {0, 1}, // move right
            {1, 0}, // move down
    }

    public List<Coordinate> treasureHunt(int[][] maze, Coordinate start, Coordinate exit) {
        List<Coordinate> resultPath = new ArrayList<>();
        resultPath.add(start);
        maze[start.row][start.col] = 1; // set as visited / invalid future path
        if (dfsSolution(maze, start, exit, resultPath)) {
            return path;
        }
        return new ArrayList<>(); // return empty list
    }

    private boolean dfsSolution(int[][] maze, Coordinate start, Coordinate exit, List<Coordinate> path) {
        if (start.col == exit.col && start.row == exit.row) {
            return true;
        }
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start.row][start.col] = true;

        Queue<QueueNode> queue = new LinkedList<>();
        QueueNode startNode = new QueueNode(start, 0);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            QueueNode current = queue.peek();
            Coordinate coord = current.coordinate;

            if (coord.col == exit.col && coord.row == exit.row) {
                return current.distance;
            }

            queue.remove();

            for (int[] move : POSSIBLE_MOVES) {
                // Create Coordiinate object for next position
                Coordinate nextCoordinate = new Coordinate(coord.row + move[0], coord.col + move[1]);

                // Check if coordinate can be travelled to
                if (validMove(maze, nextCoordinate)) {
                    // Set to visited, add to path
                    visited[nextCoordinate.row][nextCoordinate.col] = true;
                    QueueNode newNode = new QueueNode(nextCoordinate, current.distance + 1);
                    queue.add(nextCoordinate);
                }
            }
        }
        return -1;
    }

    private boolean validMove(Coordinate move, int[][] maze) {
        return move.row >= 0 && move.row < maze.length && move.col >= 0 && move.col < maze[move.row].length && maze[move.row][move.col] == 0;
    }