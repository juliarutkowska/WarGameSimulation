package test.models;

import models.Secretary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecretaryTest {

    @Test
    public void testLogAndReportCount() {
        Secretary sec = new Secretary();
        sec.log("Test message");
        sec.log("Another message");

        assertEquals(2, sec.getReports().size());
        assertTrue(sec.getReports().get(0).toString().contains("Test message"));
    }
}
