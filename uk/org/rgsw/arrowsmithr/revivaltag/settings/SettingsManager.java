package uk.org.rgsw.arrowsmithr.revivaltag.settings;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager
{
  public SettingsManager() {}
  
  static SettingsManager instance = new SettingsManager();
  
  public static SettingsManager getInstance() {
    return instance;
  }
  

  Plugin p;
  
  FileConfiguration config;
  File cfile;
  FileConfiguration data;
  File dfile;
  public void setup(Plugin p)
  {
    cfile = new File(p.getDataFolder(), "config.yml");
    config = p.getConfig();
    


    if (!p.getDataFolder().exists()) {
      p.getDataFolder().mkdir();
    }
    
    dfile = new File(p.getDataFolder(), "config.yml");
    
    if (!dfile.exists()) {
      try {
        dfile.createNewFile();
      }
      catch (IOException e) {
        Bukkit.getServer().getLogger().severe(org.bukkit.Color.RED + "Could not create config.yml!");
      }
    }
    
    data = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(dfile);
  }
  
  public FileConfiguration getData() {
    return data;
  }
  
  public void saveData() {
    try {
      data.save(dfile);
    }
    catch (IOException e) {
      Bukkit.getServer().getLogger().severe("Could not save data.yml!");
    }
  }
  
  public void reloadData() {
    data = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(dfile);
  }
  
  public FileConfiguration getConfig() {
    return config;
  }
  
  public void saveConfig() {
    try {
      config.save(cfile);
    }
    catch (IOException e) {
      Bukkit.getServer().getLogger().severe("Could not save config.yml!");
    }
  }
  
  public void reloadConfig() {
    config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(cfile);
  }
  
  public org.bukkit.plugin.PluginDescriptionFile getDesc() {
    return p.getDescription();
  }
}
