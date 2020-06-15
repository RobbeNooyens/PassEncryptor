package me.robnoo.passencryptor.tests;

import me.robnoo.passencryptor.generators.SimplePasswordGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordGeneratorTest {

    @Test
    public void testGenerator(){
        final SimplePasswordGenerator passwordGenerator = new SimplePasswordGenerator("Test", "Test");
        String password = passwordGenerator.generate();
        assertEquals(password, "mM4@U#[MN-QM3H+MMV9{J");
    }
}
