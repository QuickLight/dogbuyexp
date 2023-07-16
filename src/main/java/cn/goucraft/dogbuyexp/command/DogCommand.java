package cn.goucraft.dogbuyexp.command;

import cn.goucraft.dogbuyexp.config.DogConfig;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DogCommand {
    private int exp;
    private double needMoney;
    private double hasMoney;
    private static JavaPlugin plugin;
    DogConfig dogConfig=new DogConfig(plugin);


    public void buyexp(String[] args, Economy econ, Player player, double expRate) {
        try {
            //获取玩家购买经验需要多少钱
            exp=Integer.parseInt(args[1]);
            needMoney=exp*dogConfig.getExpRateLevel0();
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
