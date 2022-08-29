import java.util.Scanner;

public class Main {
    public static ROME_NUMBER[] romeValueArr = ROME_NUMBER.values();;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(calc(line));
    }

    public static String calc (String input) throws Exception {
        int firstValue;
        int secondValue;
        int result;
        boolean isRome = false;
        String[] splitLine = input.split(" ");
        if (splitLine.length < 3) {
            throw new Exception("Неверный формат");
        }
        try {
            firstValue = Integer.parseInt(splitLine[0]);
            secondValue = Integer.parseInt(splitLine[2]);
        } catch (Exception e) {
            isRome = true;
            firstValue = convertToInt(splitLine[0]);
            secondValue = convertToInt(splitLine[2]);
        }
        if (splitLine[1].equals("+")) {
            result = firstValue + secondValue;
        } else if (splitLine[1].equals("-")) {
            result = firstValue - secondValue;
        } else if (splitLine[1].equals("*")) {
            result = firstValue * secondValue;
        } else if (splitLine[1].equals("/")) {
            result = firstValue / secondValue;
        } else {
            throw new Exception("Неверный формат");
        }
        if (result < 0 && isRome) {
            throw new Exception("Отрицательное значение");
        }
        return isRome ? convertToRome(result) : String.valueOf(result);
    }

    public static int convertToInt (String input) throws Exception {
        String romeValue = input.toUpperCase();
        int i = romeValueArr.length-1;
        int result = 0;
        while ((i >= 0) && (romeValue.length() > 0)) {
            ROME_NUMBER digInArr = romeValueArr[i];
            if (romeValue.startsWith(digInArr.name())) {
                result += digInArr.getValue();
                romeValue = romeValue.substring(digInArr.name().length());
            } else {
                i--;
            }
        }
        if (romeValue.length() > 0) {
            throw new Exception("Неверный формат");
        }
        return result;
    }

    public static String convertToRome (int input) throws Exception {
        if (input >= 4000) {
            throw new Exception("Большое значение");
        }
        int i = romeValueArr.length-1;
        StringBuilder sb = new StringBuilder();
        while ((i >= 0) && (input > 0)) {
            ROME_NUMBER currentValue = romeValueArr[i];
            if (input >= currentValue.getValue()) {
                input -= currentValue.getValue();
                sb.append(currentValue.name());
            } else {
                i--;
            }
        }
        return sb.toString();
    }

    enum ROME_NUMBER {
        I(1), IV(4), V(5), IX(9), X(10), XL(40),
        L(50), XC(90), C(100), CD(400), D(500), CM(900), M(1000);
        private int value;
        ROME_NUMBER(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
