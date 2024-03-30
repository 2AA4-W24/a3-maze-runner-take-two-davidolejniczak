package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SSSPSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    @Override
    public Path solve(Maze maze) {
        this.maze = maze;
        Position currPos = maze.getStart();
        Position prevPos = null;
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

    }
}
