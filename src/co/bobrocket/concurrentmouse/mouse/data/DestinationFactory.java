package co.bobrocket.concurrentmouse.mouse.data;

import org.osbot.rs07.script.Script;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bobrocket on 03/01/2016.
 */
public class DestinationFactory {

    /**
     * Get a suitable point from a {@link Rectangle}
     *
     * @param rect - The rectangle region
     * @param context - Script context
     *
     * @return A suitable, distributed point within {@param rect}
     * */
    public static Point getSuitablePoint(Rectangle rect, Script context) {
        int mouseX = (int) context.getMouse().getPosition().getX();
        int mouseY = (int) context.getMouse().getPosition().getY();
        int startX = (int) rect.getX();
        int startY = (int) rect.getY();
        int endX = (int)(startX + rect.getWidth());
        int endY = (int)(startY + rect.getHeight());
        int centreX = (int)(startX + (rect.getWidth() / 2));
        int centreY = (int)(startY + (rect.getHeight() / 2));
        return getDistributedRandom(mouseX, mouseY, startX, startY, endX, endY, centreX, centreY);
    }

    public static Point getDistributedRandom(int mouseX, int mouseY, int startX, int startY, int endX, int endY, int centreX, int centreY) {
		/*
		 * We want to split our bounding box into the following conditions:
		 * Between start and finish -> skewed random point from the x/y within rect
		 * Before start -> skewed random point from start x (positive)
		 * After finish -> skewed random point from start x (negative)
		 * */

        boolean north = (mouseY <= startY);
        boolean east = (mouseX >= endX);
        boolean south = (mouseY >= endY);
        boolean west = (mouseX <= startX);

        boolean betweenX = (mouseX > startX) && (mouseX < endX);
        boolean betweenY = (mouseY > startY) && (mouseY < endY);

        int finalX = 0;
        int finalY = 0;

        if (betweenX) {
            if (mouseX > centreX) finalX = negativeSkewedRandom(startX, mouseX);
            else finalX = positiveSkewedRandom(mouseX, endX);
        }
        else {
            if (west) finalX = positiveSkewedRandom(startX, endX);
            else finalX = negativeSkewedRandom(startX, endX);
        }

        if (betweenY) {
            if (mouseY > centreY) finalY = negativeSkewedRandom(startY, mouseY);
            else finalY = positiveSkewedRandom(mouseY, endY);
        }
        else {
            if (north) finalY = positiveSkewedRandom(startY, endY);
            else finalY = negativeSkewedRandom(startY, endY);
        }

        return new Point(finalX, finalY);
    }

    private static int positiveSkewedRandom(int min, int max) {
        return skewedRandom((double) min, (double) max, 2.55, -1.68);
    }

    private static int negativeSkewedRandom(int min, int max) {
        return skewedRandom((double) min, (double) max, 2.55, 1.68);
    }

    //http://stackoverflow.com/a/13548135
    private static int skewedRandom(double min, double max, double skew, double bias) {
        Random r = new Random(System.currentTimeMillis());
        double range = max - min;
        double mid = min + range / 2.0;
        double unitGaussian = r.nextGaussian();
        double biasFactor = Math.exp(bias);
        double retval = mid + (range * (biasFactor / (biasFactor + Math.exp(-unitGaussian / skew)) - 0.5));
        return (int) retval;
    }
}
