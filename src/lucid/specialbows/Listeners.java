package lucid.specialbows;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {
	private Entity arrow;
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent event) {
		ItemStack bow = event.getBow();
		if(bow.getItemMeta().hasLore() && bow.getItemMeta().getLore().get(0).equals("Smitey Boi") && event.getEntity().hasPermission("sbows.use")) {
			arrow = event.getProjectile();
			arrow.setCustomName("Smite Arrow");
		}
		if(bow.getItemMeta().hasLore() && bow.getItemMeta().getLore().get(0).equals("Spawner Boi") && event.getEntity().hasPermission("sbows.use")) {
			arrow = event.getProjectile();
			arrow.setCustomName("Spawner Arrow-" + bow.getItemMeta().getLore().get(2));
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		Entity arrow2 = event.getEntity();
		
		if(arrow2.getCustomName() != null && arrow2.getCustomName().equals("Smite Arrow")) {
			for(int i = 0; i < Main.spawnAmount; i++) {
				arrow2.getWorld().strikeLightning(arrow2.getLocation());
			}
			arrow2.remove();
		}
		if(arrow2.getCustomName() != null && arrow2.getCustomName().contains("Spawner Arrow")) {
			String mobToSpawn = arrow2.getCustomName().split("-")[1];
			
			switch(mobToSpawn.toLowerCase()) {
			case "tnt":
				for(int i = 0; i < Main.spawnAmount; i++) {
					arrow2.getWorld().spawnEntity(arrow2.getLocation(), EntityType.PRIMED_TNT);
				}
				arrow2.remove();
				break;
			case "zombie":
				for(int i = 0; i < Main.spawnAmount; i++) {
					arrow2.getWorld().spawnEntity(arrow2.getLocation(), EntityType.ZOMBIE);
				}
				arrow2.remove();
				break;
			case "skeleton":
				for(int i = 0; i < Main.spawnAmount; i++) {
					arrow2.getWorld().spawnEntity(arrow2.getLocation(), EntityType.SKELETON);
				}
				arrow2.remove();
				break;
			case "wither":
				for(int i = 0; i < Main.spawnAmount; i++) {
					arrow2.getWorld().spawnEntity(arrow2.getLocation(), EntityType.WITHER);
				}
				arrow2.remove();
				break;
			case "cow":
				for(int i = 0; i < Main.spawnAmount; i++) {
					arrow2.getWorld().spawnEntity(arrow2.getLocation(), EntityType.COW);
				}
				arrow2.remove();
				break;
			case "chicken":
				for(int i = 0; i < Main.spawnAmount; i++) {
					arrow2.getWorld().spawnEntity(arrow2.getLocation(), EntityType.CHICKEN);
				}
				arrow2.remove();
				break;
			case "pig":
				for(int i = 0; i < Main.spawnAmount; i++) {
					arrow2.getWorld().spawnEntity(arrow2.getLocation(), EntityType.PIG);
				}
				arrow2.remove();
				break;
			case "creeper":
				for(int i = 0; i < Main.spawnAmount; i++) {
					arrow2.getWorld().spawnEntity(arrow2.getLocation(), EntityType.CREEPER);
				}
				arrow2.remove();
				break;
			}
		}
	}
}
