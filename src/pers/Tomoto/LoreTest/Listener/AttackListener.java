package pers.Tomoto.LoreTest.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AttackListener implements Listener {
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if(event.getEntity() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity)event.getEntity();
                if(damager.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.DARK_BLUE + "Wither")) {
                    target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 3));
                }
            }
            else if(event.getEntity() instanceof Player) {
                Player target = (Player)event.getEntity();
                if(damager.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.DARK_BLUE + "Wither")) {
                    target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 3));
                }
            }
        }
    }
}
