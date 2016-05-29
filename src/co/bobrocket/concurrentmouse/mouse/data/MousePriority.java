package co.bobrocket.concurrentmouse.mouse.data;

/**
 * Created by Bobrocket on 03/01/2016.
 *
 * MousePriority is an enum that manages the priority of a mouse target event.
 */
public enum MousePriority {
    LOWEST(1),
    LOW(2),
    NORMAL(3),
    HIGH(4),
    HIGHEST(5),
    IMMEDIATE(999),
    ;

    int ordinal;
    MousePriority(int pri) {
        ordinal = pri;
    }

    /**
     * Get the ordinal value (ie where it would stand in a list) of the {@link MousePriority} type
     *
     * @return Ordinal value
     * */
    public int getOrdinal() {
        return ordinal;
    }
}
