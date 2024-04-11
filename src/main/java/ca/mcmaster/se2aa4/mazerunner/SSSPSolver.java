package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class SSSPSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private final Map<Position, List<Position>> BFSGraph = new HashMap<>();
    private Position startNode;
    private Position endNode;
    private Direction direction;
    private List<Position> neighbours;

    @Override
    public Path solve(Maze maze) {
        logger.info("Starting graph");
        this.maze = maze;
        graphStorage();
        return BFS();
    }

    /**
     * This Graph storage function is based off of the adjacency list representation
     * that can be found in the lecture material from 2c03
     * Slide Set 9, slide 14
     */
    private void graphStorage() {
        logger.info("Storing graph");
        this.neighbours = new ArrayList<>();
        startNode = maze.getStart();
        endNode = maze.getEnd();
        if (startNode.x() == 0) {
            direction = Direction.RIGHT;
        } else {
            direction = Direction.LEFT;
        }
        for (int i = 0; i < maze.getSizeX(); i++) {
            for (int j = 0; j < maze.getSizeY(); j++) {
                Position currPos = new Position(i, j);
                if (!maze.isWall(currPos)) {
                    neighbours = getNeighbours(currPos);
                    BFSGraph.put(currPos, neighbours);
                }
            }
        }
    }
    private List<Position> getNeighbours(Position currPos) {
        int PosX = currPos.x();
        int PosY = currPos.y();
        List<Position> posNeighbours = new ArrayList<>();
        Position[] tempPosArray = {
                new Position(PosX - 1, PosY),
                new Position(PosX + 1, PosY),
                new Position(PosX, PosY - 1),
                new Position(PosX, PosY + 1) };
        for (Position tempPos : tempPosArray) {
            if (tempPos.x() >= 0 && tempPos.x() < maze.getSizeX() && tempPos.y() >= 0 && tempPos.y() < maze.getSizeY() && !maze.isWall(tempPos)) {
                posNeighbours.add(tempPos);
            }
        }
        return posNeighbours;
    }

    /**
     * This BFS algorithm was based of the lecture material from 2c03
     * Slide Set 9, slide 20 and slide 22
     * @return the shortest path
     */
    private Path BFS() {
        logger.info("Beginning BFS Algorithm");
        Map<Position, Boolean> distance = new HashMap<>();
        Map<Position, Position> marked = new HashMap<>();
        Queue<Position> Q = new LinkedList<>();
        for (Position node : BFSGraph.keySet()){;
            distance.put(node, false);
            marked.put(node, null);
        }
        Q.add(startNode);
        distance.put(startNode, true);
        while (!Q.isEmpty()) {
            Position tempPos = Q.remove();
            if (tempPos == endNode) break;
            for (Position neighbourPos : BFSGraph.get(tempPos)) {
                if (!distance.get(neighbourPos)) {
                    Q.add(neighbourPos);
                    distance.put(neighbourPos, true);
                    marked.put(neighbourPos, tempPos);
                }
            }
        }
        return getPath(marked);
    }

    private Path getPath(Map<Position, Position> marked){
        Path shortestPath = new Path();
        List<Position> tempPath = new ArrayList<>();
        Position currPos = endNode;
        while (currPos!=null){
            tempPath.add(currPos);
            currPos = marked.get(currPos);
        }
        Collections.reverse(tempPath);
        Position prevPos = tempPath.getFirst();
        for (int i = 1; i < tempPath.size(); i++){
            Position tempPos = tempPath.get(i);
            Direction tempDir = direction.directionTurn(tempPos,prevPos);
            char tempChar = direction.stepTurn(direction, tempDir);
            if (tempChar != 'F'){
                shortestPath.addStep(tempChar);
            }
            direction = tempDir;
            prevPos = tempPos;
            shortestPath.addStep('F');
        }
        return shortestPath;
    }

    public Map<Position, List<Position>> getBFSGraph() {
        return BFSGraph;
    }
}



