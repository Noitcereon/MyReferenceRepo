package me.noitcereon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConfigurationHelperTest {

    @Test
    void getProperty() {
        String expected = "http://localhost:4444";
        String actual = ConfigurationHelper.getProperty(ConfigurationHelper.ConfigKeys.HOST);
        Assertions.assertEquals(expected, actual);
    }
}