import java.io.IOException;
import java.util.List;

final class TrackedResource implements AutoCloseable {
    private final String name;
    private final List<String> events;
    private final boolean failOnClose;

    TrackedResource(String name, List<String> events, boolean failOnClose) {
        this.name = name;
        this.events = events;
        this.failOnClose = failOnClose;
        events.add("open:" + name);
    }

    String read(boolean fail) {
        events.add("read:" + name);
        if (fail) {
            throw new IllegalStateException("read failed: " + name);
        }
        return "data:" + name;
    }

    @Override
    public void close() throws IOException {
        events.add("close:" + name);
        if (failOnClose) {
            throw new IOException("close failed: " + name);
        }
    }
}
