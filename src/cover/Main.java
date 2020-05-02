package cover;

import cover.interpreter.DataInterpreter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DataInterpreter dataInterpreter = new DataInterpreter();

        String nextLine;
        while(!(nextLine = sc.nextLine()).equals("")) {
            String result = dataInterpreter.processNextLine(nextLine);

            if (!result.equals(""))
                System.out.println(result);
        }

    }
}
