package lucid.specialbows;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static boolean enabled = false;
	public static int spawnAmount = 1;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Listeners(), this);
	}
	
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(label.equalsIgnoreCase("sbows")) {
				if(args.length == 0) {
					player.sendMessage("§b--- Special Bows ---");
					player.sendMessage("§b/sbows - Gives info on Special Bows");
					player.sendMessage("§b/sbows create <type [smite], [entity]> [entity] - Create a Special Bow");
					player.sendMessage("§b/sbows setspawnamount <amount> - Sets the amount of lightning strikes or mobs spawned per shot");
					return true;
				}
				if(args[0].equalsIgnoreCase("create")) {
					if(!player.hasPermission("sbows.create")) {
						player.sendMessage("§bYou do not have permission to use this command");
						return true;
					}
					if(args.length < 2|| !args[1].equalsIgnoreCase("mob") && !args[1].equalsIgnoreCase("smite")) {
						player.sendMessage("§bPlease provide the type of the bow, mob (which will spawn the specified mob) or smite (which will summon lighning from the heavens).");
						return true;
					}
					if(args[1].equalsIgnoreCase("mob")) {
						if(args.length < 3) {
							player.sendMessage("§bPlease choose one of these mobs to spawn: tnt, zombie, skeleton, creeper, wither, cow, chicken, and pig");
							return true;
						}
						if(!args[2].equalsIgnoreCase("tnt") && !args[2].equalsIgnoreCase("zombie") && !args[2].equalsIgnoreCase("skeleton") && !args[2].equalsIgnoreCase("wither") && !args[2].equalsIgnoreCase("cow") && !args[2].equalsIgnoreCase("chicken") && !args[2].equalsIgnoreCase("pig") && !args[2].equalsIgnoreCase("creeper")) {
							player.sendMessage("§bPlease choose one of these mobs to spawn: tnt, zombie, skeleton, creeper, wither, cow, chicken, and pig");
							return true;
						}
						ItemStack lbow = new ItemStack(Material.BOW, 1);
						ItemMeta lbowMeta = lbow.getItemMeta();
						lbowMeta.setDisplayName("§4Spawner Bow");
						List<String> lore = new ArrayList<>();
						lore.add("Spawner Boi");
						lore.add("Spawns: ");
						lore.add(args[2]);
						lbowMeta.setLore(lore);
						lbowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
						lbowMeta.setUnbreakable(true);
						lbow.setItemMeta(lbowMeta);
						player.getInventory().addItem(lbow);
						player.sendMessage("§bSucessfully created a bow that spawns " + args[2]);
					}
					if(args[1].equalsIgnoreCase("smite")) {
						ItemStack lbow = new ItemStack(Material.BOW, 1);
						ItemMeta lbowMeta = lbow.getItemMeta();
						lbowMeta.setDisplayName("§4Smite Bow");
						List<String> lore = new ArrayList<>();
						lore.add("Smitey Boi");
						lbowMeta.setLore(lore);
						lbowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
						lbowMeta.setUnbreakable(true);
						lbow.setItemMeta(lbowMeta);
						player.getInventory().addItem(lbow);
					}
				}
				if(args[0].equalsIgnoreCase("setspawnamount")) {
					if(args.length < 2) {
						player.sendMessage("§bPlease provide a number between 1 and 200.");
						return true;
					}
					if(!StringUtils.isNumeric(args[1])) {
						player.sendMessage("§bPlease provide a number between 1 and 200.");
						return true;
					}
					if(Integer.parseInt(args[1]) < 1 || Integer.parseInt(args[1]) > 200 ) {
						player.sendMessage("§bPlease provide a number between 1 and 200.");
						return true;
					}
					player.sendMessage("§bSucessfully set spawn amount to " + args[1]);
					spawnAmount = Integer.parseInt(args[1]);
				}
			}
		}
        return true;
    }
}
