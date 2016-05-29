package co.bobrocket.concurrentmouse.mouse.data;

import org.osbot.rs07.script.Script;

import java.awt.*;

/**
 * Created by Bobrocket on 03/01/2016.
 *
 * RectangleMouseTarget is a mouse target for a static rectangle.
 */
public class RectangleMouseTarget extends MouseTarget {
    private Rectangle target;

    public RectangleMouseTarget(Rectangle target, MousePriority priority, int mouseClickVal) {
        super(priority, mouseClickVal);
        this.target = target;
    }

    @Override
    public boolean run(Script context) {
        if (target.contains(context.getMouse().getPosition())) return true;

        Point suitablePoint = DestinationFactory.getSuitablePoint(target, context);
        if (!isClick()) {
            if (!context.getMouse().move((int)suitablePoint.getX(), (int)suitablePoint.getY())) return false;
            //context.log("checking if contains " + target.contains(context.getMouse().getPosition()));
            return target.contains(context.getMouse().getPosition());
        }
        else {
            if (!context.getMouse().click((int)suitablePoint.getX(), (int)suitablePoint.getY(), isRightClick())) return false;
            //context.log("checking if contains " + target.contains(context.getMouse().getPosition()));
            return target.contains(context.getMouse().getPosition());
        }
    }

    protected void setRect(Rectangle target) {
        this.target = target;
    }
}
