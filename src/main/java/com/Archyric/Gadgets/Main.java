package com.Archyric.Gadgets;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Archyric.Gadgets.Handlers.GadgetHandler;
import com.Archyric.Gadgets.Utils.Utils;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable(){
		Listeners();
		final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    console.sendMessage(Main.color("&e[&bAG&e] &aHave been enabled!"));
	}
	
	public void onDisable(){
		final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(Main.color("&e[&bAG&e] &cHave been disabled!"));
	}
	
	public void Listeners(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Utils(this), this);
		pm.registerEvents(new GadgetHandler(this), this);
	}
	
	public static String color(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static int randomNum(int Low, int High){
		Random r = new Random();
		int R = r.nextInt(High-Low) + Low;
		return R;
	}

}
