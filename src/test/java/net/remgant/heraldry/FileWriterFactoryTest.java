package net.remgant.heraldry;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FileWriterFactoryTest {
    @Test
    public void testCreateEPSFileWriter() {
        FileWriter fileWriter = new FileWriterFactory("EPS", new Properties()).getInstance();
        assertNotNull(fileWriter);
        assertTrue(fileWriter instanceof EPSFileWriter);
    }
}
