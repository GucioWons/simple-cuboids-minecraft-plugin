package com.guciowons.simplecuboids.cuboid.piston;

import com.guciowons.simplecuboids.cuboid.BasicCuboidListener;
import com.guciowons.simplecuboids.cuboid.CuboidRepository;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPistonEvent;

import java.util.List;

public abstract class BasicPistonListener extends BasicCuboidListener {
    public BasicPistonListener(CuboidRepository cuboidRepository) {
        super(cuboidRepository);
    }

    public boolean cancelPistonEvent(List<Block> blocks, Block piston){
        if (blocks.stream().anyMatch(block -> cuboidRepository.isBlockAtCuboid(block.getX(), block.getZ()))) {
            return !cuboidRepository.isBlockAtCuboid(piston.getX(), piston.getZ()) &&
                    plugin.getConfig().getBoolean("DisablePistons");
        }
        return false;
    }
}
