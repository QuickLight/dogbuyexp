package cn.goucraft.dogbuyexp.command;

import cn.goucraft.dogbuyexp.config.DogConfig;
import cn.goucraft.dogbuyexp.main.Dogbuyexp;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DogCommand {
    private int exp;
    private double needMoney;
    private double hasMoney;


    public void buyexp(String[] args, Economy econ, Player player) {
        //                if (config.isExp_Is_Eanbale()){
//                    if (player.hasPermission("dogbuyexp.level0")){
//                        expRate= config.getExpRateLevel0();
//                    }else if (player.hasPermission("dogbuyexp.level1")){
//                        expRate=config.getExpRateLevel1();
//                    }else if (player.hasPermission("dogbuyexp.level2")){
//                        expRate=config.getExpRateLevel2();
//                    }else if (player.hasPermission("dogbuyexp.level3")){
//                        expRate=config.getExpRateLevel3();
//                    }else if (player.hasPermission("dogbuyexp.level4")){
//                        expRate=config.getExpRateLevel4();
//                    }else if (player.hasPermission("dogbuyexp.level5")){
//                        expRate=config.getExpRateLevel5();
//                    }
//                }
        try {
            //获取玩家购买经验需要多少钱
            exp=Integer.parseInt(args[1]);
            needMoney=exp*Dogbuyexp.getDogConfig().getExpRateLevel0();
            player.sendMessage("测试"+Dogbuyexp.getDogConfig().getExpRateLevel0());
            hasMoney= econ.getBalance(player.getName());

            if (hasMoney>=needMoney){
                //有钱
                EconomyResponse economyResponse= econ.withdrawPlayer(player.getName(), needMoney);
                if (economyResponse.transactionSuccess()) {
                    player.giveExp(exp);
                    player.sendMessage(ChatColor.GREEN + "你花费了 " + ChatColor.RED + needMoney + ChatColor.GREEN + " 狗鸽币"+ChatColor.GREEN + "购买了 " + ChatColor.RED + exp + ChatColor.GREEN + " 经验");

                }else {
                    player.sendMessage(ChatColor.RED+"购买失败发生未知错误");
                }
            }else {
                player.sendMessage(ChatColor.RED+"你没有足够的狗鸽币来购买 "+ChatColor.GREEN+exp + ChatColor.RED+" 经验");
                player.sendMessage(ChatColor.RED+"购买 "+ChatColor.GREEN+exp + ChatColor.RED+" 经验需要 "+ ChatColor.GREEN + needMoney + ChatColor.RED + " 狗鸽币");
            }
            player.sendMessage(ChatColor.RED+"你的当前狗鸽币余额为："+ChatColor.GREEN+hasMoney);

        }catch (NumberFormatException e){
        }
    }
}
