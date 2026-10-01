import util.TestRunner;

public class SolutionTest {
    public static void main(String[] args) {
        Solution solution = new Solution();

        TestRunner.check(
                solution.isPalindrome("A man, a plan, a canal: Panama"),
                true,
                "ignores punctuation and letter case"
        );
        TestRunner.check(
                solution.isPalindrome("race a car"),
                false,
                "rejects a non-palindrome"
        );
        TestRunner.check(solution.isPalindrome(" "), true, "accepts an empty normalized value");

        TestRunner.summary();
    }
}
