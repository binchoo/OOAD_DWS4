package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TimeRunnerTest {

    @Test
    void systemTimeUpdate() {
        TimeRunner tr = new TimeRunner();
        HashMap<String, String> arg = new HashMap<>();
        arg.put("newTime", "1023423");
        tr.systemTimeUpdate(new Message(21, "updateSystemTime", arg));
        assertEquals(1023423, tr.getSystemTime());
        arg.put("newTime", "-23423");
        tr.systemTimeUpdate(new Message(21, "updateWorldTime", arg));
        assertEquals(1000000, tr.getSystemTime());
    }
}