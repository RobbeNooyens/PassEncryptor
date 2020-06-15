package me.robnoo.passencryptor.calculations;

public enum Operation {
    MULTIPLY, DIVIDE, ADD, SUBTRACT;
    public int apply(int num1, int num2){
        switch (this){
            case MULTIPLY:
                return num1 * num2;
            case DIVIDE:
                return num1/num2;
            case ADD:
                return num1 + num2;
            case SUBTRACT:
                return num1 - num2;
        }
        return 0;
    }
}
