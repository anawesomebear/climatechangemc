package com.anawesomebear.climatechangemc.commands;

import com.anawesomebear.climatechangemc.ClimateChangeMc;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityEnterBlockEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class ClimateChangeCmds implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;

        // /climatechangemc:runccm
        if (cmd.getName().equalsIgnoreCase("runccm")) {

            Random rand = new Random();
            BukkitScheduler ccmSched = player.getServer().getScheduler();

            ccmSched.scheduleSyncRepeatingTask(ClimateChangeMc.getPlugin(ClimateChangeMc.class), () -> {
                int randomInt = rand.nextInt(1000);
                int randomIntAgain = rand.nextInt(2000);
                for(int i = 0; i <= (ClimateChangeMc.greenhouseGasCounter * 1000000); i++) {
//                    getServer().getConsoleSender().sendMessage("[Debug]: made it into for loop for storm");
                    if (i == randomInt) {
//                        getServer().getConsoleSender().sendMessage("[Debug]: causing storm");
                        player.getWorld().setStorm(true);
//                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "weather rain 9999");
                    }
                }
                Location loc = player.getLocation();
                Entity[] playerChunkEntities = loc.getChunk().getEntities();
                for (Entity ent : playerChunkEntities) {
                    if ((ent.getType().equals(EntityType.COD)) || (ent.getType().equals(EntityType.DOLPHIN)) || (ent.getType().equals(EntityType.SALMON)) || (ent.getType().equals(EntityType.PUFFERFISH)) || (ent.getType().equals(EntityType.TROPICAL_FISH)) || (ent.getType().equals(EntityType.SQUID)) || (ent.getType().equals(EntityType.TURTLE))) {
                        for(int i = 0; i <= (ClimateChangeMc.oceanAcidity * 4000000); i++) {
                            getServer().getConsoleSender().sendMessage("[Debug]: made it into 2nd for loop for killing marine life");
                            if (i == randomIntAgain) {
                                getServer().getConsoleSender().sendMessage("[Debug]: made it into if statement, killing selected entity");
                                LivingEntity living = (LivingEntity) ent;
                                living.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 2, 255));
                            }
                        }
                    }
                }
            }, 0L, 20L);
        }
        return true;
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
        ClimateChangeMc.greenhouseGasCounter += 0.001;
        ClimateChangeMc.oceanAcidity += 0.0005;
        if (ClimateChangeMc.greenhouseGasCounter > 0.001) {
            ClimateChangeMc.greenhouseGasCounter = 0.001;
        }
        if (ClimateChangeMc.oceanAcidity > 0.0005) {
            ClimateChangeMc.oceanAcidity = 0.0005;
        }
//        getServer().getConsoleSender().sendMessage("ocean acidity has increased to: " + ClimateChangeMc.oceanAcidity + ", greenhouse gases have increased to: " + ClimateChangeMc.greenhouseGasCounter);
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType().equals(EntityType.COD) || event.getEntityType().equals(EntityType.SALMON) || event.getEntityType().equals(EntityType.PUFFERFISH) || event.getEntityType().equals(EntityType.TROPICAL_FISH)) {
            Random rand2 = new Random();
            int randomInt2 = rand2.nextInt(2000);
            for(int i = 0; i <= (ClimateChangeMc.oceanAcidity * 4000000); i++) {
//                getServer().getConsoleSender().sendMessage("[Debug]: for loop for spawn kill");
                if (i == randomInt2) {
//                    getServer().getConsoleSender().sendMessage("[Debug]: if statement for spawn kill");
            event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.HARM, 2, 255));
                }
            }
        }
    }

//    @EventHandler
//    public static void onItemEnterWater(EntityEnterBlockEvent event) {
//        if (event.getBlock().equals(Material.WATER)) {
//            Entity ent = event.getEntity();
//            if (ent.getType().equals(EntityType.DROPPED_ITEM)) {
//                if (ent instanceof Item) {
//                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "Item dropped in water");
//                }
//            }
//        }
//    }

    /* for implementing rising sea level:
     * get all loaded chunks
     * based on greenhouse gas counter, in every loaded chunk
     * get blocks directly above
     */
}
