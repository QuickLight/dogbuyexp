package cn.goucraft.dogbuyexp.listener;

import cn.goucraft.dogbuyexp.main.Dogbuyexp;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DogListener implements Listener {
    private static final double BASE_DEATH_LEVEL_WORLD = 0.06;
    private static final double BASE_DEATH_LEVEL_OTHER = 0.11;
    //获取玩家扣费倍率
    double deathlevel;
    //获取玩家死亡需要保护最低狗隔币
    double keepInv;
    int minDrop = 0;
    int maxDrop = 0;
    int countDrop = 0;



    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        double keepWorld=Dogbuyexp.getDogConfig().getKeepWorld();
        double keepOther=Dogbuyexp.getDogConfig().getKeepOther();
        Player player = event.getPlayer();
        //获取玩家狗鸽币余额
        double hasmoney = Dogbuyexp.getEcon().getBalance(player.getName());
        //获取玩家死亡世界
        String worldname = player.getWorld().getName();

        if (hasmoney >=keepWorld  && worldname.equalsIgnoreCase("world")) {
            deathlevel = BASE_DEATH_LEVEL_WORLD;
            keepinv(event,hasmoney);
        } else if (hasmoney >= keepOther && !worldname.equalsIgnoreCase("world")) {
            deathlevel = BASE_DEATH_LEVEL_OTHER;
            keepinv(event,hasmoney);
        } else {
            if (worldname.equalsIgnoreCase("world")) {
                minDrop = Dogbuyexp.getDogConfig().getMinDropWorld();
                maxDrop = Dogbuyexp.getDogConfig().getMaxDropWorld();
            } else {
                minDrop = Dogbuyexp.getDogConfig().getMinDropOther();
                maxDrop = Dogbuyexp.getDogConfig().getMaxDropOther();
            }
            Random random = new Random();
            List<ItemStack> itemList = new ArrayList<ItemStack>();
            Inventory inventory = player.getInventory();
            //获取背包中装备数量
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack itemStack = inventory.getItem(i);
                if (itemStack != null && itemStack.getType() != Material.AIR) {
                    itemList.add(itemStack);
                }
            }

            countDrop = Math.min(random.nextInt(maxDrop - minDrop + 1) + minDrop, itemList.size());
            List<ItemStack> dropItems=new ArrayList<>();
            for (int i = 0; i < countDrop; i++) {
                int randomSlot = random.nextInt(itemList.size());
                ItemStack itemStack = itemList.get(randomSlot);
                itemList.remove(randomSlot);
                dropItems.add(itemStack);
                inventory.remove(itemStack);
            }
            event.getDrops().clear();
            event.getDrops().addAll(dropItems);
            event.setKeepInventory(true);
            //event.getDrops().clear();
            //掉东西
            return;
        }
    }

    public void keepinv(PlayerDeathEvent event, double hasmoney){
       Player player=event.getPlayer();
        for (int i = 1; i < 5; i++) {
            String permission = "dogbuyexp.death" + i;
            if (player.hasPermission(permission)) {
                deathlevel -= i * 0.01;
                break;
            }else {
                return;
            }
        }

        keepInv = deathlevel * hasmoney;
        keepInv = Double.parseDouble(String.format("%.2f", keepInv));
        EconomyResponse economyResponse = Dogbuyexp.getEcon().withdrawPlayer(player.getName(), keepInv);
        if (economyResponse.transactionSuccess()) {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.setDroppedExp(0);
            event.getDrops().clear();
            event.getPlayer().sendMessage("你花费了" + keepInv + "狗鸽币");
        }
    }
}
