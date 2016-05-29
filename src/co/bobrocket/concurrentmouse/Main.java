package co.bobrocket.concurrentmouse;

import co.bobrocket.concurrentmouse.mouse.MouseScheduler;
import co.bobrocket.concurrentmouse.mouse.data.MousePriority;
import co.bobrocket.concurrentmouse.mouse.data.MouseTarget;
import co.bobrocket.concurrentmouse.mouse.data.NPCMouseTarget;
import co.bobrocket.concurrentmouse.mouse.data.RectangleMouseTarget;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

/**
 * Created by Bobrocket on 03/01/2016.
 */
@ScriptManifest(author = "Bobrocket", version = 1.0D, name = "ConcurrentMouse", info = "ConcurrentMouse", logo = "")
public class Main extends Script {

    private MouseScheduler scheduler;

    @Override
    public void onStart() {
        scheduler = new MouseScheduler(this);
        scheduler.start(); //This should be at the very end of your onStart function
    }

    @Override
    public int onLoop() throws InterruptedException {
        scheduler.schedule(new RectangleMouseTarget(new Rectangle(200, 200, 20, 20), MousePriority.NORMAL, MouseTarget.MOUSE_NO_CLICK)); //Despite being scheduled first, this will be ran second (after the IMMEDIATE task)
        scheduler.schedule(new RectangleMouseTarget(new Rectangle(500, 500, 20, 20), MousePriority.LOWEST, MouseTarget.MOUSE_NO_CLICK)); //Despite being scheduled second, this will be ran last
        scheduler.schedule(new NPCMouseTarget("Banker tutor", MousePriority.IMMEDIATE, MouseTarget.MOUSE_NO_CLICK)); //Despite being scheduled last, this will be ran first
        return 1500;
    }

    @Override
    public void onPaint(Graphics2D g) {
        //g.setColor(Color.WHITE);
        //g.drawString("x", (int)(getMouse().getPosition().getX() - 7), (int)(getMouse().getPosition().getY() - 7));
    }

    @Override
    public void onExit() {
        scheduler.stop();
    }
}
