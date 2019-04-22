package uk.org.rgsw.arrowsmithr.revivaltag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener
{
  private CombatBlock block;
  
  public DeathListener(CombatBlock block)
  {
    this.block = block;
  }
  
  @EventHandler
  public void onDeath(PlayerDeathEvent event) {
    org.bukkit.entity.Player player = event.getEntity();
    if (block.isCombatBlocked(player)) {
      block.clearBlock(player);
    }
  }
}
