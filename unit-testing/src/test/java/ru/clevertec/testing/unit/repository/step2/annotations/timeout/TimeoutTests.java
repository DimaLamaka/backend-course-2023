package ru.clevertec.testing.unit.repository.step2.annotations.timeout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ru.clevertec.testing.unit.util.UuidUtil;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

class TimeoutTests {

    @Test
    void testTimeoutWithAssert() {
        Assertions.assertTimeout(Duration.ofMillis(10), UuidUtil::generateUuid);

    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MICROSECONDS)
    void testTimeoutWithAnnotation() {
        UuidUtil.generateUuid();
    }
}
