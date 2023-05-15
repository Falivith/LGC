import java.io.*;
import java.util.ArrayList;

public class MaquinaPilha {

    public static void main(String[] args) throws Exception {
        String nomeArq = "out.txt";
        String buffer;
        int result = 0;

        ArrayList<Integer> stack = new ArrayList<Integer>();

        Reader R = new Reader(nomeArq);
        buffer = R.getNextToken();

        do {
            if (buffer.contains("PUSH")){

                int number;
                number = Integer.parseInt((buffer.split(" ", 0))[1]);
                System.out.println("Number: " + number);
                stack.add(number);
            }

            if (buffer.equals("SUM")){
                result = (stack.get(stack.size() - 1)) + (stack.get(stack.size() - 2));
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);

                System.out.println("Soma: " + result);

                stack.add(result);
            }

            if (buffer.equals("MULT")){
                result = (stack.get(stack.size() - 1)) * (stack.get(stack.size() - 2));
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);

                System.out.println("Multiplicação: " + result);

                stack.add(result);
            }

            if (buffer.equals("SUB")){
                result = (stack.get(stack.size() - 2)) - (stack.get(stack.size() - 1));
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);

                System.out.println("Subtração: " + result);

                stack.add(result);
            }

            if (buffer.equals("DIV")){
                result = (stack.get(stack.size() - 2)) / (stack.get(stack.size() - 1));
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);

                System.out.println("Divisão: " + result);

                stack.add(result);
            }

            if (buffer.equals("PRINT")){
                for (int i = 0; i < stack.size(); i++)
                System.out.println("Resultado: " + stack.get(i) + "");
            }

            buffer = R.getNextToken();

        }while(buffer != null);
    }
}
