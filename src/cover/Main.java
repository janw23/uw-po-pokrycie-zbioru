package cover;

import cover.interpreter.DataInterpreter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DataInterpreter dataInterpreter = new DataInterpreter();

        while(sc.hasNextLine()) {
            String result = dataInterpreter.processNextLine(sc.nextLine());

            if (!result.equals(""))
                System.out.println(result);
        }

    }
}
