import java.util.List;

public class Exercise {
    public String readOne(
            List<String> events,
            boolean failOnRead,
            boolean failOnClose) throws Exception {
        // COLD WRITE START: xóa phần này và tự viết lại.
        try (TrackedResource resource = new TrackedResource("A", events, failOnClose)) {
            return resource.read(failOnRead);
        }
        // COLD WRITE END
    }

    public void readTwo(List<String> events) throws Exception {
        // COLD WRITE START: xóa phần này và tự viết lại.
        try (TrackedResource first = new TrackedResource("A", events, false);
             TrackedResource second = new TrackedResource("B", events, false)) {
            first.read(false);
            second.read(false);
        }
        // COLD WRITE END
    }
}
