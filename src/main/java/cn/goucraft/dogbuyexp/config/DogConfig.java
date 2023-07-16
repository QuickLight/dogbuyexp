package cn.goucraft.dogbuyexp.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DogConfig {
    private static JavaPlugin plugin;
    private boolean exp_Is_Eanbale;
    private double expRateLevel0;
    private double expRateLevel1;
    private double expRateLevel2;
    private double expRateLevel3;
    private double expRateLevel4;
    private double expRateLevel5;

    public boolean isExp_Is_Eanbale() {
        return exp_Is_Eanbale;
    }

    public void setExp_Is_Eanbale(boolean exp_Is_Eanbale) {
        this.exp_Is_Eanbale = exp_Is_Eanbale;
    }

    public double getExpRateLevel0() {
        return expRateLevel0;
    }

    public void setExpRateLevel0(double expRateLevel0) {
        this.expRateLevel0 = expRateLevel0;
    }

    public double getExpRateLevel1() {
        return expRateLevel1;
    }

    public void setExpRateLevel1(double expRateLevel1) {
        this.expRateLevel1 = expRateLevel1;
    }

    public double getExpRateLevel2() {
        return expRateLevel2;
    }

    public void setExpRateLevel2(double expRateLevel2) {
        this.expRateLevel2 = expRateLevel2;
    }

    public double getExpRateLevel3() {
        return expRateLevel3;
    }

    public void setExpRateLevel3(double expRateLevel3) {
        this.expRateLevel3 = expRateLevel3;
    }

    public double getExpRateLevel4() {
        return expRateLevel4;
    }

    public void setExpRateLevel4(double expRateLevel4) {
        this.expRateLevel4 = expRateLevel4;
    }

    public double getExpRateLevel5() {
        return expRateLevel5;
    }

    public void setExpRateLevel5(double expRateLevel5) {
        this.expRateLevel5 = expRateLevel5;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }


    public DogConfig(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void saveResourceIfNotExists(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    }

    public void loadConfig(){
        File configFile=new File(plugin.getDataFolder(),"config.yml");
        if (!configFile.exists()){
            plugin.saveResource("config.yml",false);
        }
        FileConfiguration configuration=YamlConfiguration.loadConfiguration(configFile);
        setExp_Is_Eanbale(configuration.getBoolean("exprate.enable"));
        setExpRateLevel0(configuration.getDouble("exprate.level.0"));
        setExpRateLevel1(configuration.getDouble("exprate.level.1"));
        setExpRateLevel2(configuration.getDouble("exprate.level.2"));
        setExpRateLevel3(configuration.getDouble("exprate.level.3"));
        setExpRateLevel4(configuration.getDouble("exprate.level.4"));
        setExpRateLevel5(configuration.getDouble("exprate.level.5"));
    }
}
