# ConcurrentMouseAPI
A concurrent mouse scheduling API for OSBot
This is a work in progress. Feel free to use it and tweak it, but beware it's not 100% finetuned.

# Usage
View Main.java for example usage and scheduling

# How does it work?
MouseScheduler spawns a new thread, which, when items are scheduled, executes them in order of their priority (IMMEDIATE being first, LOWEST being last).
All scheduled mouse tasks are done immediately one after the other, which should be taken in to account.

# Where to use this
Prayer flicking. Prayer flicking requires mouse clicks AT THE TIME, with intense precision. Create a thread to click the quick prayer widget every ~500ms
3 tick fishing.
Easy inventory management (eg drink potion before eating food, teleport out immediately, loot before killing etc.)

# Creating custom targets
Extend the MouseTarget class to make your life a lot easier. You could simply implement the IMouseTarget interface, but MouseTarget has some of the work already done for you.
