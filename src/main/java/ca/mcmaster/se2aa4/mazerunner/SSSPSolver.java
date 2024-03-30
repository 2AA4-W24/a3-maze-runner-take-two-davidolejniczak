package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SSSPSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private Position currPos;
    private Position prevPos;
    @Override
    public Path solve(Maze maze) {
        this.maze = maze;
        currPos = maze.getStart();
        prevPos = null;
        logger.debug("Marking entrance");
        edgeweightsStorage();
        edgeweights();
        return algo();
    }
    private Path algo() {
        Path path = new Path();
        //algo
        return path;
    }
    private void edgeweights() {
        //algo to assign edge weights
    }
    private void edgeweightsStorage() {
        int x = maze.getSizeX();
        int y = maze.getSizeY();
        int[][] weights  = new int[x][y];
        weights[currPos.x()][currPos.y()] = 0;
    }

}
