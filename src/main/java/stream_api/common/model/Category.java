package stream_api.common.model;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

@RequiredArgsConstructor
public enum Category implements Serializable {
    JUNIORS (0, 14100),
    MIDDLE (14101, 21000),
    SENIORS (21001, Integer.MAX_VALUE);

    private final int min;
    private final int max;

    public static Category findBySalary(int salary) {
        return Arrays.stream(values())
                .filter(status -> status.min <= salary)
                .filter(status -> status.max >= salary)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("length is invalid"));
    }
}
