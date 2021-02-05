package Model;
import org.mariuszgromada.math.mxparser.*;

/*
FunctionClass translates a string into a function that can be used to get points from
 */
public class FunctionClass {
    Function func;

    // EFFECTS: creates a new function, decoding the input string
    public FunctionClass(String funcStr) {
        func = new Function("f(x) = " + funcStr);
    }

    public double getValue(double xValue) {
        Expression expr = new Expression("f("+ xValue + ")", func);
        return expr.calculate();
    }
}
