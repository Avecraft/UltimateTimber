package com.songoda.ultimatetimber.utils;

import com.songoda.ultimatetimber.tree.ITreeBlock;
import com.songoda.ultimatetimber.tree.TreeBlockType;
import org.bukkit.Sound;
import org.bukkit.Location;

public class SoundUtils {

    public static void playFallingSound(ITreeBlock block) {
        Location location = block.getLocation();
        if (location.getWorld() == null) return;
        location.getWorld().playSound(location, Sound.BLOCK_CHEST_OPEN, 2F, 0.1F);
    }

    public static void playLandingSound(ITreeBlock block) {
        Location location = block.getLocation();
        if (location.getWorld() == null) return;

        if (block.getTreeBlockType().equals(TreeBlockType.LOG)) {
            location.getWorld().playSound(location, Sound.BLOCK_WOOD_FALL, 2F, 0.1F);
        } else {
            location.getWorld().playSound(location, Sound.BLOCK_GRASS_BREAK, 0.5F, 0.75F);
        }
    }

}
