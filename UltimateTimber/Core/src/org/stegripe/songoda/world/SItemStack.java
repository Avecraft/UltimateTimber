package org.stegripe.songoda.world;

import org.bukkit.Sound;
import org.stegripe.songoda.compatibility.ServerVersion;
import org.stegripe.songoda.compatibility.CompatibleHand;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class SItemStack {
    protected final ItemStack item;

    public SItemStack(ItemStack item) {
        this.item = item;
    }

    public SItemStack(CompatibleHand hand, Player player) {
        this.item = hand.getItem(player);
    }

    public ItemStack addDamage(Player player, int damage) {
        return addDamage(player, damage, false);
    }

    /**
     * Damage the selected item
     *
     * @param player the player whose item you want to damage
     * @param damage the amount of damage to apply to the item
     */
    public ItemStack addDamage(Player player, int damage, boolean respectVanillaUnbreakingEnchantments) {
        if (item == null) {
            return null;
        }

        if (item.getItemMeta() == null) {
            return item;
        }

        int maxDurability = item.getType().getMaxDurability();
        int durability;

        if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_13)) {
            // ItemStack.setDurability(short) still works in 1.13-1.14, but use these methods now
            ItemMeta meta = item.getItemMeta();
            if (meta instanceof Damageable) {
                Damageable damageable = ((Damageable) meta);

                if (respectVanillaUnbreakingEnchantments) {
                    damage = shouldApplyDamage(meta.getEnchantLevel(Enchantment.DURABILITY), damage);
                }

                damageable.setDamage(((Damageable) meta).getDamage() + damage);
                item.setItemMeta(meta);
                durability = damageable.getDamage();
            } else {
                return item;
            }
        } else {
            if (respectVanillaUnbreakingEnchantments) {
                damage = shouldApplyDamage(item.getEnchantmentLevel(Enchantment.DURABILITY), damage);
            }

            item.setDurability((short) Math.max(0, item.getDurability() + damage));
            durability = item.getDurability();
        }

        if (durability >= maxDurability && player != null) {
            destroy(player);
        }

        return item;
    }

    public void destroy(Player player) {
        destroy(player, 1);
    }

    public void destroy(Player player, int amount) {
        PlayerItemBreakEvent breakEvent = new PlayerItemBreakEvent(player, item);
        Bukkit.getServer().getPluginManager().callEvent(breakEvent);
        player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
    }

    public ItemStack getItem() {
        return item;
    }

    private static int shouldApplyDamage(int unbreakingEnchantLevel, int damageAmount) {
        int result = 0;

        for (int i = 0; i < damageAmount; ++i) {
            if (shouldApplyDamage(unbreakingEnchantLevel)) {
                ++result;
            }
        }

        return result;
    }

    private static boolean shouldApplyDamage(int unbreakingEnchantLevel) {
        if (unbreakingEnchantLevel <= 0) {
            return true;
        }

        return Math.random() <= 1.0 / (unbreakingEnchantLevel + 1);
    }
}
