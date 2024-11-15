package org.example.platform;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class LinuxTest {

    @Test
    void testName() {
        Assumptions
                .assumeTrue(
                        System.getProperty("os.name").contains("Linux")
                );
    }

}
