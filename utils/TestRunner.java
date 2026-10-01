package util;

import java.util.Arrays;
import java.util.Objects;

public final class TestRunner {
    private static int passed;
    private static int failed;

    private TestRunner() {
    }

    public static void check(Object actual, Object expected, String description) {
        if (Objects.deepEquals(actual, expected)) {
            passed++;
            System.out.println("PASS  " + description);
            return;
        }

        failed++;
        System.out.println("FAIL  " + description);
        System.out.println("      expected: " + format(expected));
        System.out.println("      actual:   " + format(actual));
    }

    public static void summary() {
        System.out.printf("%nResult: %d passed, %d failed%n", passed, failed);
        if (failed > 0) {
            throw new AssertionError(failed + " test case(s) failed");
        }
    }

    private static String format(Object value) {
        if (value == null || !value.getClass().isArray()) {
            return String.valueOf(value);
        }
        if (value instanceof int[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof long[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof double[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof boolean[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof char[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof byte[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof short[] array) {
            return Arrays.toString(array);
        }
        if (value instanceof float[] array) {
            return Arrays.toString(array);
        }
        return Arrays.deepToString((Object[]) value);
    }
}
