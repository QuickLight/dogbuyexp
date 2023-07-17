package cn.goucraft.dogbuyexp.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DogConfig {
    private final JavaPlugin plugin;
    private boolean exp_Is_Eanbale;
    private double expRateLevel0;
    private double expRateLevel1;
    private double expRateLevel2;
    private double expRateLevel3;
    private double expRateLevel4;
    private double expRateLevel5;
    private double keepWorld;
    private double keepOther;
    private int minDropWorld;
    private int maxDropWorld;
    private int minDropOther;
    private int maxDropOther;

    public int getMinDropWorld() {
        return minDropWorld;
    }

    public void setMinDropWorld(int minDropWorld) {
        this.minDropWorld = minDropWorld;
    }

    public int getMaxDropWorld() {
        return maxDropWorld;
    }

    public void setMaxDropWorld(int maxDropWorld) {
        this.maxDropWorld = maxDropWorld;
    }

    public int getMinDropOther() {
        return minDropOther;
    }

    public void setMinDropOther(int minDropOther) {
        this.minDropOther = minDropOther;
    }

    public int getMaxDropOther() {
        return maxDropOther;
    }

    public void setMaxDropOther(int maxDropOther) {
        this.maxDropOther = maxDropOther;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public double getKeepWorld() {
        return keepWorld;
    }

    public void setKeepWorld(double keepWorld) {
        this.keepWorld = keepWorld;
    }

    public double getKeepOther() {
        return keepOther;
    }

    public void setKeepOther(double keepOther) {
        this.keepOther = keepOther;
    }

    public DogConfig(JavaPlugin plugin) {
        this.plugin = plugin;
    }

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

    public void saveResourceIfNotExists(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    }

    public void loadConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
        setExp_Is_Eanbale(configuration.getBoolean("exprate.enable"));
        setExpRateLevel0(configuration.getDouble("exprate.level.0"));
        setExpRateLevel1(configuration.getDouble("exprate.level.1"));
        setExpRateLevel2(configuration.getDouble("exprate.level.2"));
        setExpRateLevel3(configuration.getDouble("exprate.level.3"));
        setExpRateLevel4(configuration.getDouble("exprate.level.4"));
        setExpRateLevel5(configuration.getDouble("exprate.level.5"));
        setKeepWorld(configuration.getDouble("deathkeep.keepworld"));
        setKeepOther(configuration.getDouble("deathkeep.keepother"));
        setMinDropWorld(configuration.getInt("deathkeep.minDropWorld"));
        setMinDropOther(configuration.getInt("deathkeep.minDropOther"));
        setMaxDropWorld(configuration.getInt("deathkeep.maxDropWorld"));
        setMaxDropOther(configuration.getInt("deathkeep.maxDropOther"));
    }
}
