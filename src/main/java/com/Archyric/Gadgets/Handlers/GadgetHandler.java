package com.Archyric.Gadgets.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

import com.Archyric.Gadgets.Main;
import com.Archyric.Gadgets.Utils.Cooldowns;
import com.Archyric.Gadgets.Utils.Utils;

public class GadgetHandler implements Listener {
	
	private static Main plugin;
	
	
	public GadgetHandler(Main hub) {
		GadgetHandler.plugin = hub;
	}
	
	public static void Grenade(final Player TargetPlayer,final String NameOfCooldown, Material GrenadeItem,Integer AmountOfItem,Integer DataValueOfItem, Integer LaunchDistance, Integer CoolDownTimeInSeconds, final Integer TimeInSecondsBeforeGrenadeExplodes, final String PrefixOfCooldownMessage, final String AbleToUseGadgetAgainMessage, final String TextBeforeCooldownSecondsAreDisplayed, final String TextAfterCooldownSecondsAreDisplayed) {
		World world = TargetPlayer.getWorld();
		if (Cooldowns.tryCooldown(TargetPlayer, Main.color(NameOfCooldown), CoolDownTimeInSeconds * 1000)) {
			final Item grenade = world.dropItem(TargetPlayer.getEyeLocation().subtract(0, 0.7, 0), Utils.createItem(GrenadeItem, AmountOfItem, DataValueOfItem, null, null));
			grenade.setVelocity(TargetPlayer.getLocation().getDirection().multiply(LaunchDistance));
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					TargetPlayer.sendMessage(Main.color(PrefixOfCooldownMessage + " " + AbleToUseGadgetAgainMessage));
					grenade.getWorld().playEffect(grenade.getLocation(),Effect.MOBSPAWNER_FLAMES,Integer.MAX_VALUE);
					grenade.getWorld().playEffect(grenade.getLocation(),Effect.FLAME,Integer.MAX_VALUE);
					grenade.getWorld().playEffect(grenade.getLocation(),Effect.SMOKE,Integer.MAX_VALUE);
					grenade.getWorld().playEffect(grenade.getLocation(),Effect.EXPLOSION,Integer.MAX_VALUE);
					grenade.getWorld().playEffect(grenade.getLocation(),Effect.FIREWORKS_SPARK,Integer.MAX_VALUE);
					grenade.getWorld().playEffect(grenade.getLocation(),Effect.LAVA_POP,Integer.MAX_VALUE);
					TargetPlayer.playSound(grenade.getLocation(),Sound.FIREWORK_LARGE_BLAST2,Integer.MAX_VALUE, Integer.MAX_VALUE);
					TargetPlayer.playSound(grenade.getLocation(),Sound.FIREWORK_TWINKLE, Integer.MAX_VALUE,Integer.MAX_VALUE);
					TargetPlayer.playSound(grenade.getLocation(),Sound.FIREWORK_LAUNCH, Integer.MAX_VALUE,Integer.MAX_VALUE);
					TargetPlayer.playSound(grenade.getLocation(),Sound.EXPLODE, Integer.MAX_VALUE,Integer.MAX_VALUE);
					grenade.remove();
				}
			}, TimeInSecondsBeforeGrenadeExplodes * 20L);
		} else {
			TargetPlayer.sendMessage(Main.color(PrefixOfCooldownMessage + " " + TextBeforeCooldownSecondsAreDisplayed + " " + (Cooldowns.getCooldown(TargetPlayer, Main.color(NameOfCooldown)) / 1000) + " " + TextAfterCooldownSecondsAreDisplayed));
		}
	}
}
