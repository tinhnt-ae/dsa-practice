import java.util.ArrayList;
import java.util.List;
import util.TestRunner;

public class ExerciseTest {
    public static void main(String[] args) throws Exception {
        Exercise exercise = new Exercise();

        List<String> normalEvents = new ArrayList<>();
        String result = exercise.readOne(normalEvents, false, false);
        TestRunner.check(result, "data:A", "returns the value read from the resource");
        TestRunner.check(
                normalEvents,
                List.of("open:A", "read:A", "close:A"),
                "closes the resource after successful use");

        List<String> twoResourceEvents = new ArrayList<>();
        exercise.readTwo(twoResourceEvents);
        TestRunner.check(
                twoResourceEvents,
                List.of("open:A", "open:B", "read:A", "read:B", "close:B", "close:A"),
                "closes multiple resources in reverse declaration order");

        List<String> failureEvents = new ArrayList<>();
        Throwable failure = capture(() -> exercise.readOne(failureEvents, true, true));
        TestRunner.check(
                failure.getClass(),
                IllegalStateException.class,
                "keeps the body exception as the primary exception");
        TestRunner.check(failure.getMessage(), "read failed: A", "keeps the primary message");
        TestRunner.check(failure.getSuppressed().length, 1, "attaches a close failure as suppressed");
        TestRunner.check(
                failure.getSuppressed()[0].getMessage(),
                "close failed: A",
                "preserves the suppressed close failure");
        TestRunner.check(
                failureEvents,
                List.of("open:A", "read:A", "close:A"),
                "still closes the resource after a body failure");

        TestRunner.summary();
    }

    private static Throwable capture(ThrowingAction action) {
        try {
            action.run();
            return new AssertionError("expected an exception, but none was thrown");
        } catch (Throwable failure) {
            return failure;
        }
    }

    @FunctionalInterface
    private interface ThrowingAction {
        void run() throws Exception;
    }
}
