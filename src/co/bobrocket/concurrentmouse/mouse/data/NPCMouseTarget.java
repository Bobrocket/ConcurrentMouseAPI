package co.bobrocket.concurrentmouse.mouse.data;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

import java.awt.*;

/**
 * Created by Bobrocket on 29/05/2016.
 */
public class NPCMouseTarget extends MouseTarget {

    private String name;

    public NPCMouseTarget(String name, MousePriority priority, int mouseClickVal) {
        super(priority, mouseClickVal);
        this.name = name;
    }

    @Override
    public boolean run(Script context) {
        NPC npc = context.getNpcs().closest(name);
        if (npc == null || !npc.exists()) return false;
        Rectangle target = npc.getModel().getBoundingBox(npc.getGridX(), npc.getGridY(), npc.getZ());

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
}
