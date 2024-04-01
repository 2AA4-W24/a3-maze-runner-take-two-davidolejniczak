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
        graphStorage();
        logger.debug("Making graph storage");
        graphweights();
        logger.debug("Assigning weights to nodes and edges");
        return algo();
    }
    private Path algo() {
        Path path = new Path();
        logger.debug("Started solving algo");
        //algo
        logger.debug("Ended solving algo");
        return path;
    }
    private void graphweights() {
        //algo to assign edge weights
    }
    private void graphStorage() {
        int x = maze.getSizeX();
        int y = maze.getSizeY();
        int[][] weights  = new int[x][y];
        weights[currPos.x()][currPos.y()] = 0;
    }

}
