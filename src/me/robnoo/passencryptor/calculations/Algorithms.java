package me.robnoo.passencryptor.calculations;

public class Algorithms {
    // "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789!\"#$%&()*+-/<=>?@[]_{}"
    private static String chars = "abcdeLMNOPQRST}fghstqrXYZ01234<=>?@[567890123UVWijklmno-/]_{45uvp6789!\"#$%&()*+wxyzABCDEFGHIJK";

    public String shift(final String s, final int positions, final Operation operation){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
            output.append(intToChar(operation.apply(charToInt(s.charAt(i)), positions)));
        return output.toString();
    }

    public String merge(final String s1, final String s2, final Operation operation){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < s1.length() || i < s2.length(); i++){
            char c1 = s1.charAt(i % s1.length());
            char c2 = s2.charAt(i % s2.length());
            int sum = operation.apply(charToInt(c1), charToInt(c2));
            output.append(intToChar(sum));
        }
        return output.toString();
    }

    public String compress(String s){
        StringBuilder temp = new StringBuilder();
        for(char c: s.toCharArray())
            temp.append(charToInt(c) / 10).append(charToInt(c) % 10);
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < temp.length()-2; i+=3){
            int index = Integer.parseInt(temp.subSequence(i, i+2).toString());
            output.append(intToChar(index));
        }
        return output.toString();
    }

    public String reverse(final String s){
        return new StringBuilder(s).reverse().toString();
    }

    public String invertIndividualChars(String s) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for(char c: s.toCharArray())
            builder.append((i++ % 2) == 0 ? invertedChar(c) : c);
        return builder.toString();
    }

    public String split(String s, int splitIndex){
        String s1 = s.substring(0, (splitIndex % s.length()));
        String s2 = s.substring(splitIndex % s.length());
        return s2 + s1;
    }

    public String endingAlgorithm(String s){
        StringBuilder builder = new StringBuilder();
        int i = 0;
        int sumOfChars = sumOfChars(s);
        for(char c: s.toCharArray())
            builder.append((i++ % 2) == 0 ? intToChar(charToInt(c) + (sumOfChars % (i + 1))) : c);
        return builder.toString();
    }

    public String complement(String s){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
            output.append(intToChar(chars.length() - charToInt(s.charAt(i))));
        return output.toString();
    }

    public String ensureRequirements(String s){
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specials = "!\"#$%&()*+-/<=>?@[]_{}";
        int sum = sumOfChars(s);
        sum = sum * sum;
        String prefix = String.valueOf(lowercase.charAt(sum % lowercase.length())) + uppercase.charAt(sum % uppercase.length()) + numbers.charAt(sum % numbers.length()) +
                specials.charAt(sum % specials.length());
        return prefix + s;
    }

    public String simplify(String s){
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!";
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
            output.append(allowedChars.charAt(chars.indexOf(s.charAt(i)) % allowedChars.length()));
        output.insert(sumOfChars(output.toString()) % output.length(), '!');
        return output.toString();
    }



    // Helpers

    public int charToInt(char c){
        return chars.indexOf(c);
    }

    public char intToChar(int i){
        return chars.charAt(i % chars.length());
    }

    public int sumOfChars(String s){
        int output = 0;
        for(char c: s.toCharArray())
            output += charToInt(c);
        return output;
    }

    public char invertedChar(char s){
        int intVal = charToInt(s);
        int invertedVal = Integer.parseInt((intVal % 10) + "" + (intVal / 10));
        return intToChar(invertedVal);
    }

}
