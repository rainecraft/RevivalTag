package uk.org.rgsw.arrowsmithr.revivaltag;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class LogInListener implements org.bukkit.event.Listener
{
  private CombatBlock block;
  private FileConfiguration config;
  
  public LogInListener(CombatBlock block)
  {
    this.block = block;
    config = block.getConfig();
  }
  
  @org.bukkit.event.EventHandler
  public void onLogin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    if (block.checkIfCombatLogger(player)) {
      player.sendMessage(Main.processString(config, config.getString("logger-rejoin"), player.getServer().getOfflinePlayer(player.getUniqueId()).getName()));
      block.removeAsCombatLogger(player);
    }
  }
}
