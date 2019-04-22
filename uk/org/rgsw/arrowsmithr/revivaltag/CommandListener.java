package uk.org.rgsw.arrowsmithr.revivaltag;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements org.bukkit.event.Listener
{
  CombatBlock block;
  FileConfiguration configuration;
  
  public CommandListener(CombatBlock block)
  {
    this.block = block;
    configuration = block.getConfig();
  }
  
  @org.bukkit.event.EventHandler
  public void onCommand(PlayerCommandPreprocessEvent event) {
    Player player = event.getPlayer();
    if ((block.isCombatBlocked(event.getPlayer())) && (!player.isOp())) {
      player.sendMessage(Main.processString(configuration, configuration.getString("command-blocked"), player.getDisplayName()));
      event.setCancelled(true);
    }
  }
}
