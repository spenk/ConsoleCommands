import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class ConsoleCommandsListener extends PluginListener{
	boolean noweather;
	int time;
	int mode;
	static Logger log = Logger.getLogger("Minecraft");
	File filename;
	String directory = "plugins/CCPlayerLogs/";
	File props = new File(directory+"LoggedPlayers.txt");
	public boolean onConsoleCommand(String[] split){
		
		PluginLoader Loader = etc.getLoader();
		if(Loader.getPlugin("NoWeather") != null && Loader.getPlugin("NoWeather").isEnabled()){
			noweather = true;
		}else{
		noweather = false;	
		}
		
		if (split[0].equalsIgnoreCase("xp")){
            if (split.length >= 3)
              try {
            	 Player player = etc.getServer().matchPlayer(split[2]);
            	 if (player == null){
            		 log.info("player does not exist");
            	 }
                if (split[1].equalsIgnoreCase("get")) {
                 log.info(player.getName() + "'s XP: " + player.getXP() + ". Level: " + player.getLevel());
                }
                if (split.length < 4) return true; int exp = Integer.parseInt(split[3]);
                if (split[1].equalsIgnoreCase("set")) {
                  player.setXP(exp);
                  log.info(player.getName() + "'s XP was set to " + exp);
                  player.sendMessage("§a" + "the console" + " set your XP " + exp); return true;
                }
                if (split[1].equalsIgnoreCase("add")) {
                  player.addXP(exp);
                  log.info(exp + " XP was added to " + player.getName() + ".");
                  player.sendMessage("§a" + "the console" + " added " + exp + " to your XP."); return true;
                }
                if (split[1].equalsIgnoreCase("remove")) {
                  exp = player.getXP() - exp;
                  player.setXP(exp);
                  log.info(exp + " XP was removed from " + player.getName() + ".");
                  player.sendMessage("§e" + "the console" + " removed " + exp + " from your XP."); return true;
                }
                log.info("Usage - xp [get|set|add|remove] [player] [amount]");
                return true;
              }
              catch (Throwable t)
              {
                log.info("Usage - xp [get|set|add|remove] [player] [amount]");
                return true;
              }
            else {
              log.info("Usage - xp [get|set|add|remove] [player] [amount]");
            }
            return true;
          }
		if (split[0].equals("warp")){
			if ((split.length < 3) || (split.length >3)){
				log.info("the correct usage is 'warp <player> <warp>'");
				return true;
			}else{
			Player player = etc.getServer().matchPlayer(split[1]);
			Warp warp = etc.getDataSource().getWarp(split[2]);
			if (warp == null){
				log.info("Warp Not Found.");
				return true;
			}else{
			Location location = warp.Location;
			if (player == null){
				log.info("Player Not Found.");
				return true;
			}else{
				player.teleportTo(location);
				player.sendMessage("§2you got Warped By the Console.");
				log.info(player.getName() + " has been warped.");
				return true;
			}
		 }
		}
		}
		
		if (split[0].equals("spawn")){
			if ((split.length == 1) || (split.length > 2)){
				log.info("the correct usage is 'spawn <player>'");
				return true;
			}else{
			Player player = etc.getServer().matchPlayer(split[1]);
			if (player == null){
				log.info("Player Not Found.");
				return true;
			}else{
				World world = player.getWorld();
				player.teleportTo(world.getSpawnLocation());
				player.sendMessage("§3You Got Respawned By The Console.");
				log.info(player.getName()+" has been respawned.");
				return true;
			}
		}
		}
		
		if (split[0].equals("heal")){
			if ((split.length == 1) ||(split.length > 2)){
				log.info("the correct usage is 'heal <player>'");
				return true;
			}else{
			Player player = etc.getServer().matchPlayer(split[1]);
			if (player == null){
				log.info("Player Not Found.");
				return true;
			}
			player.setHealth(20);
			player.sendMessage("§2you got Healed by the Console.");
			log.info(player.getName()+" has been healed.");
			return true;
		}
		}
		
		if (split[0].equals("kill")){
			if ((split.length == 1) ||(split.length > 2)){
				log.info("the correct usage is 'kill <player>'");
				return true;
			}else{
			Player player = etc.getServer().matchPlayer(split[1]);
			if (player == null){
				log.info("Player Not Found.");
				return true;
			}
			player.dropLoot();
			player.setHealth(0);
			player.sendMessage("§cyou got killed by the Console.");
			log.info(player.getName()+" has been killed.");
			return true;
		}
		}
		
		if (split[0].equals("data")){
			if ((split.length == 1) ||(split.length > 2)){
				log.info("the correct usage is 'data <player>'");
				return true;
			}else{
			Player player = etc.getServer().matchPlayer(split[1]);
			if (player == null){
				log.info("Player Not Found.");
				return true;
			}else{
				String health = Integer.toString(player.getHealth()); 
				String Foodl = Integer.toString(player.getFoodLevel());
				String level = Integer.toString(player.getLevel());
				String exp = Integer.toString(player.getXP());
				String X = Double.toString(player.getX());
				String Y = Double.toString(player.getY());
				String Z = Double.toString(player.getZ());
				String gm = Integer.toString(player.getCreativeMode());
				World world = player.getWorld();
				log.info("Player Name: " +player.getName());
				log.info("Player Prefix: "+player.getPrefix());
				log.info("§aPlayer Group: "+"**bugged**");
				log.info("Player Gamemode: "+gm);
				log.info("Player Ip: "+player.getIP());
				log.info("Player Health: "+health);
				log.info("Player Foodl: "+ Foodl);
				log.info("Player XP: "+ exp);
				log.info("Player Level: "+ level);
				log.info("Player Logging:"+ isInList(player.getName()));
				log.info("loaction: ");
				log.info("Player World: " + world.getName());
				log.info("Z: "+X);
				log.info("Y: "+Y);
				log.info("X: "+Z);
				return true;
			}
		}
		}
		
		if (split[0].equals("killall")){
			if (split.length > 1){
				log.info("the correct usage is 'killall'");
				return true;
			}else{
			for (Player p  : etc.getServer().getPlayerList()){
				if (p == null){
					log.info("there are no players online to kill.");
					return true;
				}
			p.dropLoot();
			p.setHealth(0);
			p.sendMessage("§4you were killed by the Console.");
			log.info("all players have been killed.");
			return true;
		}
		}
		}
		
		if (split[0].equals("kickall")){
			if (split.length > 1){
				log.info("the correct usage is 'kickall'");
				return true;
			}else{
			for (Player p  : etc.getServer().getPlayerList()){
				if (p == null){
					log.info("there are no players online to kick.");
					return true;
				}
	                p.kick("you are kicked by the console.");
	                log.info("You just kicked everyone.");
	                return true;
				}
		}
		}
		
		if (split[0].equals("healall")){
			if (split.length > 1){
				log.info("the correct usage is 'healall'");
				return true;
			}else{
			for (Player p  : etc.getServer().getPlayerList()){
				if (p == null){
					log.info("there are no players online to heal.");
					return true;
				}
			p.setHealth(20);
			p.sendMessage("§2you were healed by the Console.");
			log.info("all players have been healed.");
			return true;
		}
		}
		}
		
		if(split[0].equals("tpto")){
			if (split.length == 5){
				Player player = etc.getServer().matchPlayer(split[1]);
				if (player == null){
					log.info("Player Not Found.");
					return true;
				}else{
				double X = 0;
				double Y = 0;
				double Z = 0;
				try{
					X = Double.parseDouble(split[2]);
				    Y = Double.parseDouble(split[3]);
				    Z = Double.parseDouble(split[4]);
				}catch(NumberFormatException nfe){
					log.info("an error occured the correct usage is 'tpto <player> <x> <y> <z>'");
					return true;
				}
				player.setX(X);
				player.setY(Y+1);
				player.setZ(Z);
				player.sendMessage("§2you got teleported by the console.");
				return true;
				}
			}else{
				log.info("the correct usage is tpto '<player> <X> <Y> <Z>'");
				return true;
			}
		}
		//if (split[0].equals("spawnmob")){
        //if (split.length )
		
		if (split[0].equals("mute")){
			if ((split.length <2 )||(split.length >2)){
				log.info("the correct usage is 'mute <player>'");
				return true;
			}else{
				Player player = etc.getServer().matchPlayer(split[1]);
				if (player == null){
					log.info("Player Not Found.");
					return true;
				}else{
					if (player.isMuted()){
						log.info(player.getName() + "is already muted.");
						return true;
					}else{
					player.toggleMute();
					log.info(player.getName()+ " is muted.");
					player.sendMessage("§4*console* you got muted.");
					return true;
				}
			}
			}
		}
		
		if (split[0].equals("unmute")){
			if ((split.length <2 )||(split.length >2)){
				log.info("the correct usage is 'unmute <player>'");
				return true;
			}else{
				Player player = etc.getServer().matchPlayer(split[1]);
				if (player == null){
					log.info("Player Not Found.");
					return true;
				}else{
					if (player.isMuted()){
						log.info(player.getName() + "is unmuted.");
						player.sendMessage("§2 you got unmuted by the console.");
						player.toggleMute();
						return true;
					}else{
					log.info("players is not muted!");
					return true;
				}
			}
			}
		}
		
		if (split[0].equals("weather")){
			if (split.length == 1){
				log.info("the correct usage is 'weather [sun|rain|storm]'");
				return true;
			}else if (split.length == 2){ 
				World world = etc.getServer().getWorld(0);
				if (split[1].equals("sun")){
	    			if (noweather){
		    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
		    			}
					world.setRaining(false);
					world.setThundering(false);
					log.info("the weather has been changed.");
					etc.getServer().messageAll("§2*Console* weather changed.");
	    			if (noweather){
		    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
		    			}
					return true;
				}
				if (split[1].equals("rain")){
	    			if (noweather){
		    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
		    			}
					world.setRaining(true);
					log.info("the weather has been changed.");
					etc.getServer().messageAll("§2*Console* weather changed.");
	    			if (noweather){
		    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
		    			}
	    			return true;
				}
				if (split[1].equals("storm")){
	    			if (noweather){
		    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
		    			}
					world.setThundering(true);
					log.info("the weather has been changed.");
					etc.getServer().messageAll("§2*Console* weather changed.");
	    			if (noweather){
		    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
		    			}
					return true;
				}
				}
			else if (split.length == 3){
				try { Integer.parseInt(split[2]);}catch(NumberFormatException nfe){log.info("The correct usage is 'weather <rain|storm> <time>'");}
				time = Integer.parseInt(split[2])*1000;
				if (split[1].equals("rain")){
					new Thread() {
					     public void run() {
					          try{
					        	  if (noweather){
						    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
						    			}
					        	  etc.getServer().getWorld(0).setRaining(true);
					        	  etc.getServer().messageAll("§2The Console changed the weather for §4"+time/1000+"§2 seconds.");
					        	  log.info("The weather is changed.");
					        	  if (noweather){
						    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
					               }
					                Thread.sleep(time);
					                if (noweather){
						    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
					               }
					               etc.getServer().getWorld(0).setRaining(false);
					               etc.getServer().messageAll("§2The weather turned back!");
					               log.info("The weather is changed back.");
					               if (noweather){
						    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
					               }
					          }catch(InterruptedException e) {}
					     }
					}.start();
					return true;
					}	
				if (split[1].equals("storm")){
					new Thread() {
					     public void run() {
					          try{
					        	  if (noweather){
						    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
						    			}
					        	  etc.getServer().getWorld(0).setRaining(true);
					        	  etc.getServer().getWorld(0).setThundering(true);
					        	  etc.getServer().messageAll("§2The Console changed the weather for §4"+time/1000+"§2 seconds.");
					        	  log.info("The weather is changed.");
					        	  if (noweather){
						    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
					               }
					                Thread.sleep(time);
					                if (noweather){
						    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
					               }
					                etc.getServer().getWorld(0).setThundering(false);
					               etc.getServer().getWorld(0).setRaining(false);
					               etc.getServer().messageAll("§2The weather turned back!");
					               log.info("The weather is changed back.");
					               if (noweather){
						    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
					               }
					          }catch(InterruptedException e) {}
					     }
					}.start();
					return true;
				}
				
			}else{
				log.info("The correct usage is 'weather <rain|storm> <time>'");
				return true;
			}
		}
		
		if (split[0].equals("ci")){
			if ((split.length <2)||(split.length >2)){
				log.info("the correct usage is 'ci <player>'");
				return true;
			}else{
				Player player = etc.getServer().matchPlayer(split[1]);
				if (player == null){
					log.info("Player Not Found");
					return true;
				}else{
					player.getInventory().clearContents();
					log.info("player inventory has been cleared.");
					player.sendMessage("§cyour inventory got cleared by the console");
					return true;
				}
			}
		}
		
		if (split[0].equals("home")){
			if ((split.length <2)||(split.length >2)){
				log.info("the correct usage is 'home <player>'");
				return true;
			}else{
				Player player = etc.getServer().matchPlayer(split[1]);
				if (player == null){
					log.info("Player Not Found.");
					return true;
				}else{
					Warp home = etc.getDataSource().getHome(player.getName());
					if (home == null){
						log.info("this player doesnt have a home.");
						return true;
					}
					Location location = home.Location;
					player.teleportTo(location);
					log.info("player teleported.");
					player.sendMessage("§3you got warped by the Console.");
					return true;
				}
			}
		}
		if (split[0].equals("date")){
			 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
             Date date = new Date();
			if ((split.length <2)||(split.length >2)){
				log.info("the correct usage is 'date [show|notify]'");
				return true;
			}else{
				if ((!split[1].equalsIgnoreCase("show"))&&(!split[1].equalsIgnoreCase("notify"))){
					log.info("the correct usage is 'date [show|notify]'");
					return true;
				}else{
					if (split[1].equalsIgnoreCase("show")){
						log.info("the current date is "+dateFormat.format(date)+".");
						return true;
					}else{
						if(split[1].equals("notify")){
							etc.getServer().messageAll("§2*Console* §3the current date is §4"+dateFormat.format(date)+".");
							log.info("date has been shown.");
							return true;
						}
					}
					return true;
				}
			}
		}
		
		if (split[0].equalsIgnoreCase("rtime")){
			 DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
			if ((split.length <2)||(split.length >2)){
				log.info("the correct usage is 'date [show|notify]'");
				return true;
			}else{
				if ((!split[1].equalsIgnoreCase("show"))&&(!split[1].equalsIgnoreCase("notify"))){
					log.info("the correct usage is 'date [show|notify]'");
					return true;
				}else{
					if (split[1].equalsIgnoreCase("show")){
						log.info("the current time is "+dateFormat.format(date)+".");
						return true;
					}else{
						if(split[1].equals("notify")){
							etc.getServer().messageAll("§2*Console* §3the current time is §4"+dateFormat.format(date)+".");
							log.info("time has been shown.");
							return true;
						}
					}
					return true;
				}
			}
		}
		if (split[0].equalsIgnoreCase("gm")){
			if (split.length == 1){
				log.info("The correct usage is 'gm <player> <1|0>'");
				return true;
			}else if (split.length == 2){
				Player p = etc.getServer().matchPlayer(split[1]);
				if (p == null){
					log.info("This player is currently not logged in or does not excist.");
					return true;
				}
				int m = p.getCreativeMode();
				log.info("this player does currently have mode "+ m+".");
				return true;
			}else if (split.length == 3){
				try{mode = Integer.parseInt(split[2]);}catch(NumberFormatException nfe){log.info("the correct usage is 'gm <player> <1|0>'");}
				Player p = etc.getServer().matchPlayer(split[1]);
				if (p == null){
					log.info("This player is currently not logged in or does not excist.");
					return true;
				}else
				if (mode == p.getCreativeMode()){
					log.info("this player is already gamemode "+mode+".");
					return true;
				}else
					if ((mode != 1)&&(mode != 0)){
						log.info(mode +" is an unacceptable gamemode.");
						return true;
					}else{
				p.setCreativeMode(mode);
				p.sendMessage("§2The Console changed your gamemode to "+mode+".");
				log.info("player gamemode has been changed to "+mode+".");
				return true;
			}
			}
		}
		
		if (split[0].equals("time")){
			int time2 = 0;
			if ((split.length >3) || (split.length <2)){
				log.info("the correct usage is time [show|notify|add|set]");
				return true;
			}
				World world = etc.getServer().getWorld(0);
			 	String time = Double.toString(world.getTime());
			 	if (split[1].equals("show")){
				log.info("the current time is: "+time);
				return true;
			 	}else if (split[1].equals("notify")){
			 		etc.getServer().messageAll("§5the current time is: "+time);
			 		log.info("the time has been shown");
			 		log.info("the current time is: "+time);
			 		return true;
			}else if (split[1].equals("set")){
				if ((split.length >3)||(split.length <3)){
					log.info("the correct usage is 'time [set] [time]");
					return true;
				}else{
					try{time2 = Integer.parseInt(split[2]);}
					catch(NumberFormatException nfe){
						log.info( time2 + "is not an valid number");
						return true;
					}
					world.setTime(time2);
					etc.getServer().messageAll("§6the console changed te time.");
					log.info("the time has been changed");
					return true;
				}
			}else if (split[1].equals("add")){
				if ((split.length >3)||(split.length <3)){
					log.info("the correct usage is 'time [add] [time]");
					return true;
				}else{
					try{time2 = Integer.parseInt(split[2]);}
					catch(NumberFormatException nfe){
						log.info( time2 + "is not an valid number");
						return true;
					}
					long addtime = world.getTime()+time2;
					world.setTime(addtime);
					etc.getServer().messageAll("§6the console changed te time.");
					log.info("the time has been changed");
					return true;
				}
			
			}
		}
		if (split[0].equals("pluginlist")){
			if (split.length != 1){
				log.info("the correct usage is 'pluginlist'");
				return true;
			}else{
			log.info("the current pluginlist is: "+etc.getLoader().getPluginList()+".");
			return true;
		}
		}
		int power = 0;
		if (split[0].equals("bomb")){
			if ((split.length <3)||(split.length >3)){
				log.info("the correct usage is 'bomb <player> <power>'");
				return true;
			}else{
			Player player = etc.getServer().matchPlayer(split[1]);
			if (player == null){
				log.info("player not found");
				return true;
			}else{
				try{ power = Integer.parseInt(split[2]);
				}catch (NumberFormatException nfe){log.info(split[2]+ " is not an number!"); return true;}
			World world = player.getWorld();
			world.explode(player, player.getX(), player.getY()+1, player.getZ()+1, power);
			log.info("player bombarded");
			player.sendMessage("§4the console bombed you.");
			return true;
			}
		}}
		if(split[0].equals("strike")){
			if ((split.length <2)||(split.length >2)){
				log.info("the correct usage is 'strike <player>'");
			return true;
			}else{
				Player player = etc.getServer().matchPlayer(split[1]);
				if (player == null){
					log.info("player not found.");
					return true;
				}else{
					OWorldServer oworld = player.getWorld().getWorld();
					oworld.a(new OEntityLightningBolt(oworld, player.getX(), player.getY(), player.getZ()));
					player.sendMessage("§4The console shot an lightning bolt to your head.");
					log.info("we shot an lightning bolt to "+player.getName()+"master.");
					return true;
				}
			}
		}
		if(split[0].equals("log")){
            if ((split.length <2)||(split.length >2)){
                    log.info("usage 'log <player>'");
                    return true;
            }else{
            Player player = etc.getServer().matchPlayer(split[1]);
            if (player == null){
                    log.info("Player Not Found.");
                    return true;
            }else{
            	if (isInList(player.getName())){
            		log.info("This player is already in the log list!");
            		return true;
            	}else{
            Writer writer = null;
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            try {
                    filename = new File(directory + player.getName() +".txt");
                writer = new BufferedWriter(new FileWriter(filename));
                writer.write("#date/time = "+dateFormat.format(date)+"\n");
                writer.write("player name = "+player.getName()+"\n");
                writer.write("player group = "+"**bugged**"+"\n");
                writer.write("player ip = "+player.getIP()+"\n");
                log.info("player will be logged 'till unlog'");
            } catch (FileNotFoundException e) {
                log.info("[ERROR] File not found.");
                return true;
            } catch (IOException e) {
                    log.info("[ERROR] File IOException Found o.O");
                    return true;
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    log.info("[ERROR] File IOException Found o.O");
                    return true;
                }
            }
            try {
            	 if (!new File(directory+"LoggedPlayers.txt").exists()) {
            		 props.createNewFile();
            		 writer = new FileWriter(directory+"LoggedPlayers.txt", true);
            		 writer.write(player.getName());
            	 }else{
            		 writer = new FileWriter(directory+"LoggedPlayers.txt", true);
				writer.write(player.getName()+"\n");
				writer.close();
            	 }
			} catch (IOException e) {
				log.info("an error occured O.o");
				return true;
			}
            }
            return true;
    }
    }
		}
		if (split[0].equals("cch")){
			log.info("======================ConsoleCommands======================");
			log.info("  warp <player> <warp>                      warps a player to location <warp>. ");
			log.info("  spawn <player>                            respawns a player.  ");
			log.info("  home <player>                             tps a player to his/her home");
			log.info("  heal <player>                             heals a player. ");
			log.info("  data <player>                             gets all player data. ");
			log.info("  kill <player>                             kills a player. ");
			log.info("  help or cch                               shows all help commands. ");
			log.info("  kickall -                                 kicks all players. ");
			log.info("  killall -                                 kills all players. ");
			log.info("  healall -                                 heals all players. ");
			log.info("  tpto <player> <X> <Y> <Z>                 tps an player to <X> <Y> <Z>. ");
			log.info("  mute <player>                             mutes a player.");
			log.info("  unmute <player>                           unmutes a player.");
			log.info("  ci <player>                               clears inventory of a player.");
			log.info("  weather <sun|rain|storm> (time in sec)    changes the weather.");
			log.info("  time [show|notify|set|add] <time>         showes you the time or notify's it to evryone.");
			log.info("  date [show|notify]                        showes you the date or notify's it to evtyone.");
			log.info("  rtime [show|notify]                       showes you the real time ot notify's it to evryone.");
			log.info("  xp [get|set|add|remove] <player> <amount> [get|set|add|remove] player xp.");
			log.info("  bomb <player> <power>                     creating an explosion on player coordinates");
			log.info("  strike <player>                           strikes a player");
			log.info("  log <player>                              log all player data, messages");
			log.info("  unlog <player>                            stops the log of a player");
			log.info("  gm <player> <gamemode(1|0)                changes the player gamemode");
			//log.info("  CAKE                                      The Cake is not a lie!");
			log.info("===========================V2.0============================");
			return true;
		}
			
		if (split[0].equals("help")){
			log.info("======================ConsoleCommands======================");
			log.info("  warp <player> <warp>                      warps a player to location <warp>. ");
			log.info("  spawn <player>                            respawns a player.  ");
			log.info("  home <player>                             tps a player to his/her home");
			log.info("  heal <player>                             heals a player. ");
			log.info("  data <player>                             gets all player data. ");
			log.info("  kill <player>                             kills a player. ");
			log.info("  help or cch                               shows all help commands. ");
			log.info("  kickall -                                 kicks all players. ");
			log.info("  killall -                                 kills all players. ");
			log.info("  healall -                                 heals all players. ");
			log.info("  tpto <player> <X> <Y> <Z>                 tps an player to <X> <Y> <Z>. ");
			log.info("  mute <player>                             mutes a player.");
			log.info("  unmute <player>                           unmutes a player.");
			log.info("  ci <player>                               clears inventory of a player.");
			log.info("  weather <sun|rain|storm> (time in sec)    changes the weather.");
			log.info("  time [show|notify|set|add] <time>         showes you the time or notify's it to evryone.");
			log.info("  date [show|notify]                        showes you the date or notify's it to evtyone.");
			log.info("  rtime [show|notify]                       showes you the real time ot notify's it to evryone.");
			log.info("  xp [get|set|add|remove] <player> <amount> [get|set|add|remove] player xp.");
			log.info("  bomb <player> <power>                     creating an explosion on player coordinates");
			log.info("  strike <player>                           strikes a player");
			log.info("  log <player>                              log all player data, messages");
			log.info("  unlog <player>                            stops the log of a player");
			log.info("  gm <player> <gamemode(1|0)                changes the player gamemode");
			//log.info("  CAKE                                      The Cake is not a lie!");
			log.info("===========================V2.0============================");
		}
		/*if (split[0].equalsIgnoreCase("CAKE")){
			if ((split.length <1)||(split.length >1)){
				log.info("the correct usage is 'CAKE'");
				return false;
		}else{
			     new Thread() {
			     public void run() {
			          try{
			                Thread.sleep(5000);
			    			log.info("THE WORLD TURNS DARK...");
			    			etc.getServer().messageAll("§4THE WORLD TURNS DARK...");
			    			etc.getServer().getWorld(0).setTime(18000);
			    			if (noweather){
				    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
				    			}
			                Thread.sleep(5000);
			    			log.info("IT IS STARTING TO RAIN...");
			    			etc.getServer().messageAll("§4IT IS STARTING TO RAIN...");
			    			etc.getServer().getWorld(0).setRaining(true);
			    			Thread.sleep(7500);
			    			Location spawn = etc.getServer().getWorld(0).getSpawnLocation();
			    			double x = spawn.x;
			    			double y = spawn.y;
			    			double z = spawn.z;

			    			etc.getServer().getWorld(0).dropItem(x+1,y-1,z,354, 1, 0);
			    			etc.getServer().getWorld(0).dropItem(x+1,y-1,z+1,354, 1, 0);
			    			etc.getServer().getWorld(0).dropItem(x+1,y-1,z-1,354, 1, 0);
			    			etc.getServer().getWorld(0).dropItem(x-1,y-1,z+1,354, 1, 0);
			    			etc.getServer().getWorld(0).dropItem(x-1,y-1,z-1,354, 1, 0);
			    			etc.getServer().getWorld(0).dropItem(x-1,y-1,z,354, 1, 0);
			    			etc.getServer().getWorld(0).dropItem(x,y,z+1,354, 1, 0);
			    			etc.getServer().getWorld(0).dropItem(x,y,z-1,354, 1, 0);
			    			log.info("SOMETHING IS HAPPENING AT THE SPAWN ...");
			    			etc.getServer().messageAll("§4SOMETHING IS HAPPENING AT THE SPAWN ...");
			    			Thread.sleep(2500);
			    			log.info("§4GO LOOK PEOPLE FAST !!!");
			    			etc.getServer().messageAll("§4GO LOOK PEOPLE FAST !!!");		
			    			Thread.sleep(50000);
			    			log.info("Event End.");
			    			etc.getServer().messageAll("§2The Event Ended!");
			    			etc.getServer().getWorld(0).setTime(0);
			    			etc.getServer().getWorld(0).setRaining(false);
			    		    Thread.sleep(2500);
			    			if (noweather){
				    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
				    			}
			          }catch(InterruptedException e) {}
			     }
			}.start();
			}
			return true;
			}
			*/

		if(split[0].equals("unlog")){
			if ((split.length <2)||(split.length >2)){
				log.info("usage 'unlog <player>'");
				return true;
			}else{
				if (!isInList(split[1])){
					log.info("this player is not found in the log database!");
					log.info("mind you have to write down full playername 'even caps'");
					return true;
				}else{
					ArrayList<String> filelines = new ArrayList<String>();
					String lineSep = System.getProperty("line.separator");
					try{
						BufferedReader in = new BufferedReader(new FileReader(props));
						String line;
						while ((line = in.readLine()) != null) {
							if (!line.contains(split[1])) {
								filelines.add(line);	
							}
						}
						in.close();
						BufferedWriter out = new BufferedWriter(new FileWriter(props));
						for(String toWrite : filelines){
							out.write(toWrite);
							out.write(lineSep);
						}
						out.close();
						log.info("player unlogged");
						return true;
					}catch(IOException e){ 
						log.info("File IOException found!...");
						return true;
					}
				}
			}
		}
		return false;
	}
	
	 public boolean isInList(String playername) {
	    try {
	      BufferedReader in = new BufferedReader(new FileReader(props));
	      String line = in.readLine();
	      while (line != null) {
	        if (line.equalsIgnoreCase(playername)) {
	          in.close();
	          return true;
	        }
	        line = in.readLine();
	      }
	      in.close();
	    } catch (IOException localIOException) {
	    }
	    return false; }
	 
	public boolean onChat(Player player,String message){
		if (isInList(player.getName())){
        if (!new File(directory+player.getName()+".txt").exists()) {
        	return false;
        }else{
             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
             Date date = new Date();
        try {
        	FileWriter fw = new FileWriter(directory+player.getName()+".txt", true);
                fw.write(dateFormat.format(date)+": "+message+"\n");
                fw.close();
        } catch (IOException e) {
                log.info("Error IOException Found!");
                return false;
        }
        }
}
		return false;
}
	public void onLogin(Player player){
		if (isInList(player.getName())){
	        if (!new File(directory+player.getName()+".txt").exists()) {
	        	return;
	        }else{
	             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	             Date date = new Date();
	        try {
	        	FileWriter fw = new FileWriter(directory+player.getName()+".txt", true);
	                fw.write(dateFormat.format(date)+": PLAYER LOGGED IN!\n");
	                fw.close();
	        } catch (IOException e) {
	                log.info("Error IOException Found!");
	                return;
	        }
	        }
	}
	}
	public void onDisconnect(Player player){
		if (isInList(player.getName())){
	        if (!new File(directory+player.getName()+".txt").exists()) {
	        	return;
	        }else{
	             DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	             Date date = new Date();
	        try {
	        	FileWriter fw = new FileWriter(directory+player.getName()+".txt", true);
	                fw.write(dateFormat.format(date)+": PLAYER LOGGED OUT!\n");
	                fw.close();
	        } catch (IOException e) {
	                log.info("Error IOException Found!");
	                return;
	        }
	        }
	}	
	}
}