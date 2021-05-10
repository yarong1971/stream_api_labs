package stream_api.common.model;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

@RequiredArgsConstructor
public enum Gender implements Serializable {
    MALE,
    FEMALE;
}
