public class InputStringException extends Exception{
    InputStringException(){
        super("Введенная строка не соответствует условию задания\n" +
                "Должно быть введено два числа и знак между ними разделенные пробелами");
    }
}
