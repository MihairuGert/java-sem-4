package task3.engine;

import java.io.Serializable;

public class Point implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
