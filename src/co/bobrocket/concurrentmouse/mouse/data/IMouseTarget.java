package co.bobrocket.concurrentmouse.mouse.data;

import org.osbot.rs07.script.Script;

import java.awt.*;

/**
 * Created by Bobrocket on 03/01/2016.
 */
public interface IMouseTarget {
    boolean run(Script context);
    MousePriority getPriority();
}
