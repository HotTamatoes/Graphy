package Model;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Hashtable;
import java.util.LinkedList;

import static Model.Line.*;

/*
Graph represents the graph paper that one would draw a graph on

it contains a list of lines
 */
public class GraphPaper extends JFrame {
    private JPanel output;
    private LinkedList<Line2D> lineSegments;
    private static final int WINDOW_HEIGHT = 1000;
    private static final int WINDOW_WIDTH = 1000;

    public GraphPaper(Line line) {
        super("Graph");
        lineSegments = new LinkedList<Line2D>();
        output = initialize();
        graph(line);
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        repaint();
        this.add(output);
        this.setVisible(true);
    }

    public JPanel initialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point frameMiddle = new Point((screenSize.width / 2) - (WINDOW_WIDTH / 2),
                (screenSize.height / 2) - (WINDOW_HEIGHT / 2));
        this.setLocation(frameMiddle);

        JPanel initialPanel = new JPanel();
        initialPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        addInitialLines();
        return initialPanel;
    }

    public void addInitialLines() {
        lineSegments.add(new Line2D.Double(0, WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT/2));
        lineSegments.add(new Line2D.Double(WINDOW_WIDTH / 2, 0, WINDOW_WIDTH / 2, WINDOW_HEIGHT));
    }

    public void graph(@NotNull Line line) {
        Hashtable<Double, Double> points = line.getPoints();
        addInitialLines();
        double xScalingFactor = (WINDOW_WIDTH / (X_MAX - X_MIN));
        double yScalingFactor;
        double zeroVal = points.get(line.yIntercept);
        if (zeroVal > 400  || zeroVal < 400) {
            yScalingFactor = xScalingFactor;
        } else if (zeroVal > 2.5 || zeroVal < -2.5) {
            //System.out.println("hi :)" + zero + " " + points.get(zero));
            yScalingFactor = xScalingFactor * (2.5/zeroVal);
        } else {
            yScalingFactor = xScalingFactor;
        }
        for (double i = X_MIN; i + LINE_SPACING < X_MAX; i += LINE_SPACING) {
            lineSegments.add(new Line2D.Double((i - X_MIN)*xScalingFactor,
                    (WINDOW_HEIGHT / 2) - points.get(i)*yScalingFactor,
                    (i + LINE_SPACING - X_MIN)*xScalingFactor,
                    ((WINDOW_HEIGHT / 2) - points.get(i + LINE_SPACING))*yScalingFactor));
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        while(!lineSegments.isEmpty()){
            g2.draw(lineSegments.pop());
        }
    }

    public static void main(String []args){
        new GraphPaper(new Line("x^2"));
    }
}
