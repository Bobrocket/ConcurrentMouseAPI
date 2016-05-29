package co.bobrocket.concurrentmouse.mouse.data;

/**
 * Created by Bobrocket on 03/01/2016.
 */
public abstract class MouseTarget implements IMouseTarget {
    public static int MOUSE_NO_CLICK = 0;
    public static int MOUSE_LEFT_CLICK = 1;
    public static int MOUSE_RIGHT_CLICK = 2;

    private MousePriority priority;
    private int mouseClickVal;

    public MouseTarget(MousePriority priority, int mouseClickVal) {
        this.priority = priority;
        this.mouseClickVal = mouseClickVal;
    }

    protected boolean isClick() { return mouseClickVal != 0; }
    protected boolean isRightClick() { return isClick() && mouseClickVal == 2; }

    @Override
    public MousePriority getPriority() { return this.priority; }
}
