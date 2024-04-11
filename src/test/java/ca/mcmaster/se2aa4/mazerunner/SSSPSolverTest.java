package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class SSSPSolverTest {

    @Test
    public void mazeTest() {
        SSSPSolver solver = new SSSPSolver();
        assertThrows(NullPointerException.class, () -> solver.solve(null));
    }

    @Test
    public void graphStorageTestSmall() throws Exception {
        Maze maze = new Maze("examples/straight.maz.txt");
        SSSPSolver solver = new SSSPSolver();
        solver.solve(maze);
        Map<Position, List<Position>> BFSGraph = solver.getBFSGraph();
        List<Position> neighboursStart = BFSGraph.get(maze.getStart());
        List<Position> neighboursEnd = BFSGraph.get(maze.getEnd());
        assertTrue(BFSGraph.containsKey(maze.getStart()));
        assertTrue(BFSGraph.containsKey(maze.getEnd()));
        assertNotNull(neighboursStart);
        assertNotNull(neighboursEnd);
        assertFalse(neighboursStart.isEmpty());
        assertFalse(neighboursEnd.isEmpty());
    }
    @Test
    public void graphStorageTestLarge() throws Exception {
        Maze maze = new Maze("examples/giant.maz.txt");
        SSSPSolver solver = new SSSPSolver();
        solver.solve(maze);
        Map<Position, List<Position>> BFSGraph = solver.getBFSGraph();
        List<Position> neighboursStart = BFSGraph.get(maze.getStart());
        List<Position> neighboursEnd = BFSGraph.get(maze.getEnd());
        assertTrue(BFSGraph.containsKey(maze.getStart()));
        assertTrue(BFSGraph.containsKey(maze.getEnd()));
        assertNotNull(neighboursStart);
        assertNotNull(neighboursEnd);
        assertFalse(neighboursStart.isEmpty());
        assertFalse(neighboursEnd.isEmpty());
    }
    @Test
    public void testSSSPSmallMaze() throws Exception {
        Maze maze = new Maze("examples/straight.maz.txt");
        SSSPSolver solver = new SSSPSolver();
        Path path = solver.solve(maze);
        String answer = "4F";
        String SSSPAnswer = path.getFactorizedForm();
        assertNotNull(path);
        assertEquals(answer, SSSPAnswer);
    }
    @Test
    public void testSSSPLargeMaze() throws Exception {
        Maze maze = new Maze("examples/giant.maz.txt");
        SSSPSolver solver = new SSSPSolver();
        Path path = solver.solve(maze);
        String answer = "F L 2F R 2F L 6F R 2F L 6F R 2F R 2F L 2F R 2F L 2F R 8F L 4F R 4F L 6F R 2F L 4F R 2F L 2F R 4F L 4F R 2F L 18F R 4F L 4F R 2F L 2F R 2F L 4F R 4F L 2F R 2F L 2F L 2F R 4F L 2F R 4F L 2F R 10F L 6F R 2F L 2F R 6F L 2F R 2F R 4F L 2F R 2F L 14F R 4F L 4F R 2F L 2F R 8F L 10F R 2F L 4F R 2F L 6F R 2F L 4F R 2F L 6F L 2F R 2F L 4F R 5F";
        String SSSPAnswer = path.getFactorizedForm();
        assertNotNull(path);
        assertEquals(answer, SSSPAnswer);
    }
}
