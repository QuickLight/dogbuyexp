package cn.goucraft.dogbuyexp.command;

import cn.goucraft.dogbuyexp.main.Dogbuyexp;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DogCommand {
    private int exp;
    private double needMoney;
    private double hasMoney;
    private double expRate;


    public void buyexp(String[] args, Economy econ, Player player) {
        try {
            //获取玩家购买经验需要多少钱
            exp = Integer.parseInt(args[1]);
            if (Dogbuyexp.getDogConfig().isExp_Is_Eanbale()) {
                if (player.hasPermission("dogbuyexp.level0")) {
                    expRate = Dogbuyexp.getDogConfig().getExpRateLevel0();
                } else if (player.hasPermission("dogbuyexp.level1")) {
                    expRate = Dogbuyexp.getDogConfig().getExpRateLevel1();
                } else if (player.hasPermission("dogbuyexp.level2")) {
                    expRate = Dogbuyexp.getDogConfig().getExpRateLevel2();
                } else if (player.hasPermission("dogbuyexp.level3")) {
                    expRate = Dogbuyexp.getDogConfig().getExpRateLevel3();
                } else if (player.hasPermission("dogbuyexp.level4")) {
                    expRate = Dogbuyexp.getDogConfig().getExpRateLevel4();
                } else if (player.hasPermission("dogbuyexp.level5")) {
                    expRate = Dogbuyexp.getDogConfig().getExpRateLevel5();
                }
            } else {
                exp = 0;
                expRate = 0;
            }
            needMoney = exp * expRate;
            hasMoney = econ.getBalance(player.getName());

            if (exp != 0) {
                if (hasMoney >= needMoney) {
                    //有钱
                    EconomyResponse economyResponse = econ.withdrawPlayer(player.getName(), needMoney);
                    if (economyResponse.transactionSuccess()) {
                        player.giveExp(exp);
                        player.sendMessage(ChatColor.GREEN + "你花费了 " + ChatColor.RED + needMoney + ChatColor.GREEN + " 狗鸽币" + ChatColor.GREEN + "购买了 " + ChatColor.RED + exp + ChatColor.GREEN + " 经验");

                    } else {
                        player.sendMessage(ChatColor.RED + "购买失败发生未知错误");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "你没有足够的狗鸽币来购买 " + ChatColor.GREEN + exp + ChatColor.RED + " 经验");
                    player.sendMessage(ChatColor.RED + "购买 " + ChatColor.GREEN + exp + ChatColor.RED + " 经验需要 " + ChatColor.GREEN + needMoney + ChatColor.RED + " 狗鸽币");
                }
            } else {
                player.sendMessage(ChatColor.RED + "你没有权限来购买经验");
            }
            hasMoney = econ.getBalance(player.getName());
            player.sendMessage(ChatColor.RED + "你的当前狗鸽币余额为：" + ChatColor.GREEN + hasMoney);

        } catch (NumberFormatException e) {
        }
    }
}
