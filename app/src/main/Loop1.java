public List < Coordinate > treasureHunt(int[][] maze, Coordinate start, Coordinate exit) {
    List < Coordinate > resultPath = new ArrayList < > ();
    resultPath.add(start);
    maze[start.row][start.col] = 1; // set as visited / invalid future path
    if (dfsSolution(maze, start, exit, resultPath)) {
        return path;
    }
    return new ArrayList < > (); // return empty list
}

private boolean Solution1(int[][] maze, Coordinate start, Coordinate exit, List < Coordinate > path) {
    if (start.col == exit.col && start.row == exit.row) {
        return true;
    }

    private final int[][] POSSIBLE_MOVES = {
        {
            0,
            -1
        }, // move left
        {
            -1,
            0
        }, // move up
        {
            0,
            1
        }, // move right
        {
            1,
            0
        }, // move down
    }

    for (int[] move: POSSIBLE_MOVES) {
        // Create Coordiinate object for next position
        Coordinate nextCoordinate = new Coordinate(start.row + move[0], start.col + move[1]);

        // Check if coordinate can be travelled to
        if (validMove(maze, nextCoordinate)) {
            // Set to visited, add to path
            maze[nextCoordinate.row][nextCoordinate.col] = 1;
            path.add(nextCoordinate);
        }

        return dfsSolution(maze, nextCoordinate, exit, path);
    }
}

private boolean validMove(Coordinate move, int[][] maze) {
    return move.row >= 0 && move.row < maze.length && move.col >= 0 && move.col < maze[move.row].length && maze[move.row][move.col] == 0;
}