import util.TestRunner;

public class SolutionTest {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Replace the sample with calls to your solution method.
        TestRunner.check(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}), 49, "example from problem");
        TestRunner.check(solution.maxArea(new int[]{1,1}), 1, "minimum case, width 1");
        TestRunner.check(solution.maxArea(new int[]{4,3,2,1,4}), 16, "two tallest bars are at the ends");
        TestRunner.check(solution.maxArea(new int[]{1,2,1}), 2, "middle bar is a useless endpoint");

        TestRunner.summary();
    }
}
