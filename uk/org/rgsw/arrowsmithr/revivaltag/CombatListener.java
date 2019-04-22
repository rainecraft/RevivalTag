package uk.org.rgsw.arrowsmithr.revivaltag;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener implements org.bukkit.event.Listener
{
  private CombatBlock block;
  private WorldGuardPlugin wg;
  
  public CombatListener(WorldGuardPlugin wg, CombatBlock block)
  {
    this.block = block;
    this.wg = wg;
  }
  
  @org.bukkit.event.EventHandler
  public void onDamage(EntityDamageByEntityEvent event) {
    if ((event.isCancelled()) || (event.getDamage() == 0.0D)) return;
    if (((event.getDamager() instanceof Player)) && ((event.getEntity() instanceof Player)) && 
      (!isPVPEnabled(event.getDamager().getLocation())) && (!isPVPEnabled(event.getEntity().getLocation()))) {
      try {
        block.setCombatBlocked((Player)event.getDamager());
        block.setCombatBlocked((Player)event.getEntity());
      }
      catch (Exception localException) {}
    }
  }
  
  @org.bukkit.event.EventHandler
  public void onWeaponDamage(WeaponDamageEntityEvent event)
  {
    if ((event.getDamage() == 0.0D) || (event.isCancelled())) return;
    if ((!isPVPEnabled(event.getPlayer().getLocation())) && (!isPVPEnabled(event.getVictim().getLocation())) && 
      ((event.getVictim() instanceof Player))) {
      block.setCombatBlocked(event.getPlayer());
      block.setCombatBlocked((Player)event.getVictim());
    }
  }
  
  public boolean isPVPEnabled(Location location)
  {
    String global = "__global__";
    if (wg == null) {
      return true;
    }
    if (wg.getRegionManager(location.getWorld()) == null)
      return true;
    RegionManager regionManager = wg.getRegionManager(location.getWorld());
    com.sk89q.worldguard.protection.ApplicableRegionSet arset = regionManager.getApplicableRegions(location);
    ProtectedRegion region = regionManager.getRegion(global);
    int priority = 55536;
    for (ProtectedRegion r : arset.getRegions()) {
      if (r.getPriority() > priority) {
        region = r;
        priority = r.getPriority();
      }
    }
    if (region == null) {
      if (regionManager.getRegion(global) == null)
        return false;
      return "ALLOW".equals(((StateFlag.State)regionManager.getRegion(global).getFlag(DefaultFlag.PVP)).toString());
    }
    if (region.getFlag(DefaultFlag.PVP) == null)
      return true;
    return "ALLOW".equalsIgnoreCase(((StateFlag.State)region.getFlag(DefaultFlag.PVP)).toString());
  }
}
