package util;

public class TestRunner {
    private static int passed = 0;
    private static int failed = 0;

    public static void check(Object actual, Object expected, String caseDesc) {
        boolean ok = java.util.Objects.equals(actual, expected);
        if (ok) {
            passed++;
            System.out.println("PASS  " + caseDesc);
        } else {
            failed++;
            System.out.println("FAIL  " + caseDesc
                    + "  expected=" + expected + "  actual=" + actual);
        }
    }

    public static void summary() {
        System.out.println(passed + " passed, " + failed + " failed");
    }
}