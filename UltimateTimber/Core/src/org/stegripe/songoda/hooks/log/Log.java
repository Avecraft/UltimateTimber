package org.stegripe.songoda.hooks.log;

import org.stegripe.songoda.hooks.Hook;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;

public abstract class Log implements Hook {
    public abstract void logPlacement(OfflinePlayer player, Block block);

    public abstract void logRemoval(OfflinePlayer player, Block block);

    public abstract void logInteraction(OfflinePlayer player, Location location);
}
