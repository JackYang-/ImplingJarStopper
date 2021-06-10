package com.jarstopper;

import java.awt.event.MouseEvent;
import net.runelite.client.input.MouseAdapter;

public class JarStopperMouseListener extends MouseAdapter {
    private final JarStopperPlugin plugin;

    JarStopperMouseListener(JarStopperPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public MouseEvent mouseClicked(MouseEvent event) {
        plugin.checkInventory();
        //event.consume();
        return event;
    }
}
