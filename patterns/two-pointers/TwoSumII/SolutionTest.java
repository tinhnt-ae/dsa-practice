import util.TestRunner;

public class SolutionTest {
    public static void main(String[] args) {
        Solution solution = new Solution();

        TestRunner.check(
                solution.twoSum(new int[]{2, 7, 11, 15}, 9),
                new int[]{1, 2},
                "finds the first pair"
        );
        TestRunner.check(
                solution.twoSum(new int[]{2, 3, 4}, 6),
                new int[]{1, 3},
                "uses both ends"
        );
        TestRunner.check(
                solution.twoSum(new int[]{-1, 0}, -1),
                new int[]{1, 2},
                "supports negative numbers"
        );

        TestRunner.summary();
    }
}
