package UI;

import Model.GraphPaper;
import Model.Line;

import java.util.Scanner;

public class TextualInterface {
    Scanner input;
    Line inputLine;
    GraphPaper grapher;
    private boolean keepRunning;

    public TextualInterface() {
        keepRunning = true;
        while(keepRunning) {
            receiveInput();
            graph();
        }
    }

    public void receiveInput() {
        input = new Scanner(System.in);
        System.out.println("Enter your function below:");
        System.out.print("f(x) = ");
        String inputString = input.nextLine();
        if (inputString.equals("exit")) {
            System.exit(0);
        } else {
            inputLine = new Line(inputString);
        }
    }

    public void graph() {
        grapher = new GraphPaper(inputLine);
    }
}
