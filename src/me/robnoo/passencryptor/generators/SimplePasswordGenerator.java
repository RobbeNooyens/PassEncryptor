package me.robnoo.passencryptor.generators;

import me.robnoo.passencryptor.calculations.Algorithms;
import me.robnoo.passencryptor.calculations.Operation;

public class SimplePasswordGenerator implements PasswordGenerator {

    private final String inputPassword;
    private final String inputApplication;
    private String generatedPassword;

    public SimplePasswordGenerator(String password, String application){
        this.inputPassword = fill(password, 30, 50);
        this.inputApplication = ensureEqualApplicationNames(application);
    }

    @Override
    public String generate() {
        final Algorithms algorithms = new Algorithms();
        final String shiftedApplication = algorithms.shift(inputApplication, 3, Operation.ADD);
        final String initial = algorithms.merge(inputPassword, shiftedApplication, Operation.ADD);
        generatedPassword = algorithms.compress(initial);
        for(int i = 0; i < initial.length(); i++){
            switch (algorithms.charToInt(initial.charAt(i)) % 7){
                case 0:
                    generatedPassword = algorithms.shift(generatedPassword, i/2, Operation.ADD);
                    break;
                case 1:
                    generatedPassword = algorithms.shift(generatedPassword, 2, Operation.MULTIPLY);
                    break;
                case 2:
                    generatedPassword = algorithms.invertIndividualChars(generatedPassword);
                    break;
                case 3:
                    generatedPassword = algorithms.split(generatedPassword, (i+1)*2);
                case 4:
                    generatedPassword = algorithms.reverse(generatedPassword);
                    break;
                case 5:
                    generatedPassword = algorithms.shift(generatedPassword, i, Operation.ADD);
                    break;
                case 6:
                    generatedPassword = algorithms.shift(generatedPassword, 6, Operation.ADD);
                    break;
                default:
                    generatedPassword = algorithms.shift(generatedPassword, 3, Operation.MULTIPLY);
            }
        }
        generatedPassword = algorithms.compress(generatedPassword);
        generatedPassword = algorithms.endingAlgorithm(generatedPassword);
        generatedPassword = algorithms.complement(generatedPassword);
        generatedPassword = algorithms.ensureRequirements(generatedPassword);
        return generatedPassword;
    }

    @Override
    public String generateSimple() {
        final Algorithms algorithms = new Algorithms();
        return algorithms.simplify(generate());
    }

    private String fill(final String s, final int minCap, final int maxCap){
        String output = s;
        Algorithms compression = new Algorithms();
        while(output.length() < minCap )
            output = compression.reverse(output) + compression.shift(s, output.length(), Operation.ADD);
        while(output.length() > maxCap)
            output = compression.compress(output);
        return output;
    }

    private String ensureEqualApplicationNames(final String application){
        return application.toLowerCase().replaceAll("[ .,_:;&]", "").replaceAll("-", "");
    }
}
