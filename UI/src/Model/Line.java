package Model;

import java.util.Hashtable;

/*
Line is a class that consists of a list of points that map out a line on a graph.
 */
public class Line {
    public static final double LINE_SPACING = 0.01;
    public static final double X_MAX = 5;
    public static final double X_MIN = -5;
    public double yIntercept;
    Hashtable<Double, Double> points;
    FunctionClass function;

    public Line(String input) {
        function = new FunctionClass(input);
        points = new Hashtable<>();
        fillPoints();
    }

    public void fillPoints() {
        boolean interceptFound = false;
         for(double i = X_MIN; i < X_MAX; i += LINE_SPACING) {
             points.put(i, function.getValue(i));
             if((!interceptFound) && (i >= 0)) {
                 yIntercept = i;
                 interceptFound = true;
                 //System.out.println(i + " " + points.get(i));
             }
         }
    }

    public Hashtable<Double, Double> getPoints() {
        return points;
    }

}
