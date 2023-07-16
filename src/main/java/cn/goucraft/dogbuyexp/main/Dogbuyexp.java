package cn.goucraft.dogbuyexp.main;

import cn.goucraft.dogbuyexp.command.DogCommand;
import cn.goucraft.dogbuyexp.config.DogConfig;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dogbuyexp extends JavaPlugin {


    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;
    DogCommand dogCommand=new DogCommand();
    private static Dogbuyexp instance;
    private static DogConfig dogConfig;
    public static DogConfig getDogConfig(){
        return dogConfig;
    }
    public static Dogbuyexp getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance=this;

        reload();

        if (!setupEconomy()) {
            //log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
        // Plugin startup logic

    }
    public void reload(){
        saveDefaultConfig();
        reloadConfig();
        dogConfig=new DogConfig(this);
        dogConfig.loadConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("dog.reload")) {
                    sender.sendMessage("重置成功");
                    return true;
                }
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("buy")) {
                dogCommand.buyexp(args, econ, player);
                return true;
            }
            //sender.sendMessage(ChatColor.RED + "Usage: /dogbuyexp buy <exp>");

        }
        return false;
    }
}
