public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println(calc("1 + 3"));
    }

    public static String calc(String input) throws Exception {
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex == -1){
            throw new InputStringException();
        }
        String firstOperand = input.substring(0, firstSpaceIndex);
        int lastSpaceIndex = input.lastIndexOf(" ");
        if (lastSpaceIndex == -1){
            throw new InputStringException();
        }
        String secondOperand = input.substring(lastSpaceIndex + 1);
        if (firstOperand.length() + secondOperand.length() != input.length() - 3){
            throw new InputStringException();
        }
        char operation = input.charAt(firstSpaceIndex + 1);

        int firstNum;
        int secondNum;
        if ((!isArabic(firstOperand) && !isRoman(firstOperand)) ||
                (!isArabic(secondOperand) && !isRoman(secondOperand))){
            throw new InputStringException();
        }

        if (isArabic(firstOperand)){
            if(isRoman(secondOperand)) {
                throw new RomanAndArabicException();
            }
            firstNum = Integer.parseInt(firstOperand);
            secondNum = Integer.parseInt(secondOperand);
            if (firstNum > 10 || secondNum > 10 || firstNum <= 0 || secondNum <= 0){
                throw new InputNumberException();
            }
            switch (operation) {
                case '+':
                    return Integer.toString (firstNum + secondNum);
                case '*':
                    return Integer.toString(firstNum * secondNum);
                case '/':
                    return Integer.toString(firstNum / secondNum);
                case '-':
                    return Integer.toString(firstNum - secondNum);
                default:
                    throw new InputStringException();
            }

        }
        if (isRoman(firstOperand) ){
            if (isArabic(secondOperand)) {
                throw new RomanAndArabicException();
            }
            firstNum = romanToInt(firstOperand);
            secondNum = romanToInt(secondOperand);
            switch (operation) {
                case '+':
                    return intToRoman(firstNum + secondNum);
                case '*':
                    return intToRoman(firstNum * secondNum);
                case '/':
                    return intToRoman(firstNum / secondNum);
                case '-':
                    return intToRoman(firstNum - secondNum);
                default:
                    throw new InputStringException();
            }
        }




        System.out.println();
        return "";

    }
    private static int romanToInt (String roman) throws InputNumberException {
        String current = roman;
        int sum = 0;
        while (!current.equals("")){
            if (current.endsWith("IX")){
                sum += 9;
                current = current.substring(0, current.length() - 2);
                continue;
            }
            if (current.endsWith("IV")){
                sum += 4;
                current = current.substring(0, current.length() - 2);
                continue;
            }
            if (current.endsWith("I")){
                sum += 1;
                current = current.substring(0, current.length() - 1);
                continue;
            }
            if (current.endsWith("V")){
                sum += 5;
                current = current.substring(0, current.length() - 1);
                continue;
            }
            if (current.endsWith("X")){
                sum += 10;
                current = current.substring(0, current.length() - 1);
            }
        }
        if (sum > 10) {
            throw new InputNumberException();

        }
        return sum;

    }

    private static boolean isRoman(String operand){
        return operand.matches("[IVX]+");
    }

    private static boolean isArabic(String operand){
        return operand.matches("\\d+");
    }
    private static String intToRoman(int num) throws ArabianResultException {
        if (num < 1){
            throw new ArabianResultException();
        }
        // Самое большое возможное число получается при умножении 10 на 10
        // Затем 9 на 10, больше результатов с выводом "C" нет
        // Обработаем эти случаи отдельно
        if (num == 100) return "C";
        if (num == 90) return "XC";
        StringBuilder result = new StringBuilder();
        int current = num;
        if (current >= 50) {
            result.append("L");
            current -= 50;
        }
        if (current >= 40) {
            result.append("XL");
            current -= 40;
        }
        int tens = current / 10;
        result.append("X".repeat(tens));
        current %= 10;
        if (current == 9){
            result.append("IX");
            return result.toString();
        }
        if (current >= 5){
            result.append("V");
            current -= 5;
        }
        if (current == 4){
            result.append("IV");
            return result.toString();
        }
        result.append("I".repeat(current));
        return  result.toString();


    }
}
