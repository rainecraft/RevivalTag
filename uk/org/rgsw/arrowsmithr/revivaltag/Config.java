package uk.org.rgsw.arrowsmithr.revivaltag;

import org.bukkit.configuration.Configuration;

public class Config
{
  public Config() {}
  
  public static String COMBAT_BLOCKED = "You are now combat blocked for $duration$ seconds!";
  public static String NO_LONGER_COMBAT_BLOCKED = "You are no longer combat blocked!";
  public static String LOGGER_REJOIN = "You logged out whilst combat-blocked!";
  public static String LOGGER_ANNOUNCE = "$player$ logged out whilst combat blocked!";
  public static String COMMAND_BLOCKED = "You cannot issue commands whilst combat blocked!";
  public static int DURATION = 20;
  
  public static void loadValues(org.bukkit.plugin.java.JavaPlugin plugin) {
    Configuration config = plugin.getConfig();
    if (config != null) {
      String combatBlock = config.getString("combat-blocked");
      if (isNotNullOrEmpty(combatBlock)) {
        COMBAT_BLOCKED = combatBlock;
      }
      String noCombatBlock = config.getString("no-longer-blocked");
      if (isNotNullOrEmpty(noCombatBlock)) {
        NO_LONGER_COMBAT_BLOCKED = noCombatBlock;
      }
      String loggerRejoin = config.getString("logger-rejoin");
      if (isNotNullOrEmpty(loggerRejoin)) {
        LOGGER_REJOIN = loggerRejoin;
      }
      String loggerAnnounce = config.getString("logger-announce");
      if (isNotNullOrEmpty(loggerAnnounce)) {
        LOGGER_ANNOUNCE = loggerAnnounce;
      }
      String commandBlocked = config.getString("command-blocked");
      if (isNotNullOrEmpty(commandBlocked)) {
        COMMAND_BLOCKED = commandBlocked;
      }
      int duration = config.getInt("duration");
      if (duration <= 0) {
        DURATION = duration;
      }
      System.out.println(combatBlock + " | " + noCombatBlock + " | " + loggerRejoin + " | " + loggerAnnounce + " | " + commandBlocked + " | " + duration);
    }
  }
  
  private static boolean isNotNullOrEmpty(String s) {
    return (s != null) && (s.trim().isEmpty());
  }
}
