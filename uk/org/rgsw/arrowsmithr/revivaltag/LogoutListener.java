package uk.org.rgsw.arrowsmithr.revivaltag;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogoutListener implements org.bukkit.event.Listener
{
  private CombatBlock block;
  private FileConfiguration config;
  
  public LogoutListener(CombatBlock block)
  {
    this.block = block;
    config = block.getConfig();
  }
  
  @org.bukkit.event.EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
    Player player = e.getPlayer();
    if (block.isCombatBlocked(player)) {
      player.getServer().broadcastMessage(Main.processString(config, config.getString("logger-announce"), player.getServer().getOfflinePlayer(player.getUniqueId()).getName()));
      player.setHealth(0.0D);
      block.clearBlock(player);
      block.addAsCombatLogger(player);
    }
  }
}
