package com.guciowons.simplecuboids.cuboid;

import com.guciowons.simplecuboids.SimpleCuboids;
import org.bukkit.event.Listener;

public abstract class BasicCuboidListener implements Listener {
    protected SimpleCuboids plugin = SimpleCuboids.getPlugin(SimpleCuboids.class);
    protected final CuboidRepository cuboidRepository;

    public BasicCuboidListener(CuboidRepository cuboidRepository) {
        this.cuboidRepository = cuboidRepository;
    }
}
