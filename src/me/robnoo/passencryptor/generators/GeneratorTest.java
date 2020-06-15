package me.robnoo.passencryptor.generators;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratorTest {

    @Test
    public void test(){
        SimplePasswordGenerator generator = new SimplePasswordGenerator("TestKey", "TestApp");
        String s = generator.generate();
        assertEquals(s, "wW4@d-wKy<w]vFwG_K0");
    }
}
