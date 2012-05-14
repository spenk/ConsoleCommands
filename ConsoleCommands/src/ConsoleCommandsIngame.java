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

public class ConsoleCommandsIngame extends PluginListener{
		boolean noweather;
		int time;
		int mode;
		File filename;
		String directory = "plugins/CCPlayerLogs/";
		File props = new File(directory+"LoggedPlayers.txt");
		public boolean onCommand(final Player player,String[] split){
			
			PluginLoader Loader = etc.getLoader();
			if(Loader.getPlugin("NoWeather") != null && Loader.getPlugin("NoWeather").isEnabled()){
				noweather = true;
			}else{
			noweather = false;	
			}
			
			if (split[0].equalsIgnoreCase("/#xp")){
				if (player.isOp()){
	            if (split.length >= 3)
	              try {
	            	 Player player2 = etc.getServer().matchPlayer(split[2]);
	            	 if (player2 == null){
	            		 player.notify("player does not exist");
	            	 }
	                if (split[1].equalsIgnoreCase("get")) {
	                  player.sendMessage("§a" + player2.getName() + "'s XP: " + player2.getXP() + ". Level: " + player2.getLevel());
	                }
	                if (split.length < 4) return true; int exp = Integer.parseInt(split[3]);
	                if (split[1].equalsIgnoreCase("set")) {
	                  player2.setXP(exp);
	                  player2.sendMessage("§a" + player.getName() + " set your XP " + exp); 
	                  player.sendMessage("§a"+player2.getName() + "'s XP was set to " + exp);
	                  return true;
	                }
	                if (split[1].equalsIgnoreCase("add")) {
	                  player2.addXP(exp);
	                 player.sendMessage("§a"+exp + " XP was added to " + player2.getName() + ".");
	                  player2.sendMessage("§a" + player.getName() + " added " + exp + " to your XP."); return true;
	                }
	                if (split[1].equalsIgnoreCase("remove")) {
	                  exp = player2.getXP() - exp;
	                  player2.setXP(exp);
	                 player.sendMessage("§a"+exp + " XP was removed from " + player2.getName() + ".");
	                  player2.sendMessage("§e" + player.getName() + " removed " + exp + " from your XP."); return true;
	                }
	               player.notify("§a"+"Usage - xp [get|set|add|remove] [player] [amount]");
	                return true;
	              }
	              catch (Throwable t)
	              {
	            	  player.notify("Usage - xp [get|set|add|remove] [player] [amount]");
	                return true;
	              }
	            else {
	            	player.notify("Usage - xp [get|set|add|remove] [player] [amount]");
	            }
	            return true;
				}
	          }
			
			if (split[0].equalsIgnoreCase("/#warp")){
				if (player.isOp()){
				if ((split.length < 3) || (split.length >3)){
					player.notify("the correct usage is 'warp <player> <warp>'");
					return true;
				}else{
				Player player2 = etc.getServer().matchPlayer(split[1]);
				Warp warp = etc.getDataSource().getWarp(split[2]);
				if (warp == null){
					player.notify("Warp Not Found.");
					return true;
				}else{
				Location location = warp.Location;
				if (player2 == null){
					player.notify("Player Not Found.");
					return true;
				}else{
					player2.teleportTo(location);
					player2.sendMessage("§2you got Warped By "+player.getName()+".");
					player.sendMessage("§a"+player2.getName() + " has been warped.");
					return true;
				}
				}
			 }
			}
			}
			
			if (split[0].equalsIgnoreCase("/#spawn")){
				if (player.isOp()){
				if ((split.length == 1) || (split.length > 2)){
					player.notify("the correct usage is 'spawn <player>'");
					return true;
				}else{
				Player player1 = etc.getServer().matchPlayer(split[1]);
				if (player1 == null){
					player.notify("Player Not Found.");
					return true;
				}else{
					World world = player1.getWorld();
					player1.teleportTo(world.getSpawnLocation());
					player1.sendMessage("§3You Got Respawned By "+player.getName()+".");
					player.sendMessage("§a"+player1.getName()+" has been respawned.");
					return true;
				}
			}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#heal")){
				if (player.isOp()){
				if ((split.length == 1) ||(split.length > 2)){
					player.notify("the correct usage is 'heal <player>'");
					return true;
				}else{
				Player player1 = etc.getServer().matchPlayer(split[1]);
				if (player1 == null){
					player.notify("Player Not Found.");
					return true;
				}
				player1.setHealth(20);
				player1.sendMessage("§2you got Healed by "+player.getName()+".");
				player.sendMessage("§a"+player1.getName()+" has been healed.");
				return true;
			}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#kill")){
				if (player.isOp()){
				if ((split.length == 1) ||(split.length > 2)){
					player.notify("the correct usage is 'kill <player>'");
					return true;
				}else{
				Player player1 = etc.getServer().matchPlayer(split[1]);
				if (player1 == null){
					player.notify("Player Not Found.");
					return true;
				}
				player1.dropLoot();
				player1.setHealth(0);
				player1.sendMessage("§cyou got killed by "+player.getName()+".");
				player.sendMessage("§a"+player1.getName()+" has been killed.");
				return true;
			}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#data")){
				if (player.isOp()){
				if ((split.length == 1) ||(split.length > 2)){
					player.notify("the correct usage is 'data <player>'");
					return true;
				}else{
				Player player1 = etc.getServer().matchPlayer(split[1]);
				if (player1 == null){
					player.notify("Player Not Found.");
					return true;
				}else{
					String health = Integer.toString(player1.getHealth()); 
					String Foodl = Integer.toString(player1.getFoodLevel());
					String level = Integer.toString(player1.getLevel());
					String exp = Integer.toString(player1.getXP());
					String X = Double.toString(player1.getX());
					String Y = Double.toString(player1.getY());
					String Z = Double.toString(player1.getZ());
					String gm = Integer.toString(player1.getCreativeMode());
					World world = player1.getWorld();
					player.sendMessage("§aPlayer Name: " +player1.getName());
					player.sendMessage("§aPlayer Prefix: "+player1.getPrefix());
					player.sendMessage("§aPlayer Group: "+"**bugged**");
					player.sendMessage("§aPlayer Gamemode: "+gm);
					player.sendMessage("§aPlayer Ip: "+player1.getIP());
					player.sendMessage("§aPlayer Health: "+health);
					player.sendMessage("§aPlayer Foodl: "+ Foodl);
					player.sendMessage("§aPlayer XP: "+ exp);
					player.sendMessage("§aPlayer Level: "+ level);
					player.sendMessage("§aPlayer Logging:"+ isInList(player1.getName()));
					player.sendMessage("§aloaction: ");
					player.sendMessage("§aPlayer World: " + world.getName());
					player.sendMessage("§aZ: "+X);
					player.sendMessage("§aY: "+Y);
					player.sendMessage("§aX: "+Z);
					return true;
				}
			}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#killall")){
				if (player.isOp()){
				if (split.length > 1){
					player.notify("the correct usage is 'killall'");
					return true;
				}else{
				for (Player p  : etc.getServer().getPlayerList()){
					if (p != player){
					if (p == null){
						player.notify("there are no players online to kill.");
						return true;
					}
					}
				p.dropLoot();
				p.setHealth(0);
				p.sendMessage("§4you were killed by "+player.getName()+".");
				player.sendMessage("§aall players have been killed.");
				return true;
			}
				}
			}
			}
			
			if (split[0].equalsIgnoreCase("/#kickall")){
				if (player.isOp()){
				if (split.length > 1){
					player.notify("the correct usage is 'kickall'");
					return true;
				}else{
				for (Player p  : etc.getServer().getPlayerList()){
					if (p != player){
						if (p == null){
							player.notify("There is nobody online to kick!");
							return true;
						}
					}
		                p.kick("you are kicked by "+player.getName()+".");
		                player.sendMessage("§aYou just kicked everyone.");
		                return true;
					}
			}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#healall")){
				if (player.isOp()){
				if (split.length > 1){
					player.notify("the correct usage is 'healall'");
					return true;
				}else{
				for (Player p  : etc.getServer().getPlayerList()){
					if (p != player){
					if (p == null){
						player.notify("there are no players online to heal.");
						return true;
					}
					}
				p.setHealth(20);
				p.sendMessage("§2you were healed by "+player.getName()+".");
				player.sendMessage("§aall players have been healed.");
				return true;
			}
			}
				}
			}
			
			if(split[0].equalsIgnoreCase("/#tpto")){
				if (player.isOp()){
				if (split.length == 5){
					Player player1 = etc.getServer().matchPlayer(split[1]);
					if (player1 == null){
						player.notify("Player Not Found.");
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
						player.notify("an error occured the correct usage is 'tpto <player> <x> <y> <z>'");
						return true;
					}
					player1.setX(X);
					player1.setY(Y+1);
					player1.setZ(Z);
					player1.sendMessage("§2you got teleported by "+player.getName()+".");
					return true;
					}
				}else{
					player.notify("the correct usage is tpto '<player> <X> <Y> <Z>'");
					return true;
				}
				}
			}
			//TODO if (split[0].equalsIgnoreCase("spawnmob")){
	        //if (split.length )
			
			if (split[0].equalsIgnoreCase("/#mute")){
				if (player.isOp()){
				if ((split.length <2 )||(split.length >2)){
					player.notify("the correct usage is 'mute <player>'");
					return true;
				}else{
					Player player1 = etc.getServer().matchPlayer(split[1]);
					if (player1 == null){
						player.notify("Player Not Found.");
						return true;
					}else{
						if (player1.isMuted()){
							player.notify(player1.getName() + "is already muted.");
							return true;
						}else{
						player1.toggleMute();
						player.sendMessage("§a"+player1.getName()+ " is muted.");
						player1.sendMessage("§4you got muted by "+player.getName()+".");
						return true;
					}
				}
				}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#unmute")){
				if (player.isOp()){
				if ((split.length <2 )||(split.length >2)){
					player.notify("the correct usage is 'unmute <player>'");
					return true;
				}else{
					Player player1 = etc.getServer().matchPlayer(split[1]);
					if (player1 == null){
						player.notify("Player Not Found.");
						return true;
					}else{
						if (player1.isMuted()){
                            player.sendMessage("§a"+player1.getName() + "is unmuted.");
							player1.sendMessage("§2 you got unmuted by "+player.getName()+".");
							player1.toggleMute();
							return true;
						}else{
						player.notify("players is not muted!");
						return true;
					}
				}
				}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#weather")){
				if (player.isOp()){
				if (split.length == 1){
					player.notify("the correct usage is 'weather [sun|rain|storm]'");
					return true;
				}else if (split.length == 2){ 
					World world = etc.getServer().getWorld(0);
					if (split[1].equalsIgnoreCase("sun")){
		    			if (noweather){
			    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
			    			}
						world.setRaining(false);
						world.setThundering(false);
						player.sendMessage("§athe weather has been changed.");
						etc.getServer().messageAll("§2weather changed by "+player.getName()+".");
		    			if (noweather){
			    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
			    			}
						return true;
					}
					if (split[1].equalsIgnoreCase("rain")){
		    			if (noweather){
			    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
			    			}
						world.setRaining(true);
						player.sendMessage("§athe weather has been changed.");
						etc.getServer().messageAll("§2weather changed by"+player.getName()+".");
		    			if (noweather){
			    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
			    			}
		    			return true;
					}
					if (split[1].equalsIgnoreCase("storm")){
		    			if (noweather){
			    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
			    			}
						world.setThundering(true);
						player.sendMessage("the weather has been changed.");
						etc.getServer().messageAll("§2weather changed by"+player.getName()+".");
		    			if (noweather){
			    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
			    			}
						return true;
					}
					}
				else if (split.length == 3){
					try { Integer.parseInt(split[2]);}catch(NumberFormatException nfe){player.notify("The correct usage is 'weather <rain|storm> <time>'");}
					time = Integer.parseInt(split[2])*1000;
					if (split[1].equalsIgnoreCase("rain")){
						new Thread() {
						     public void run() {
						          try{
						        	  if (noweather){
							    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
							    			}
						        	  etc.getServer().getWorld(0).setRaining(true);
						        	  etc.getServer().messageAll("§a"+player.getName()+" changed the weather for §4"+time/1000+"§2 seconds.");
						        	 player.sendMessage("§aThe weather is changed.");
						        	  if (noweather){
							    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
						               }
						                Thread.sleep(time);
						                if (noweather){
							    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
						               }
						               etc.getServer().getWorld(0).setRaining(false);
						               etc.getServer().messageAll("§2The weather turned back!");
						               player.sendMessage("§aThe weather is changed back.");
						               if (noweather){
							    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
						               }
						          }catch(InterruptedException e) {}
						     }
						}.start();
						return true;
						}	
					if (split[1].equalsIgnoreCase("storm")){
						new Thread() {
						     public void run() {
						          try{
						        	  if (noweather){
							    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", false});
							    			}
						        	  etc.getServer().getWorld(0).setRaining(true);
						        	  etc.getServer().getWorld(0).setThundering(true);
						        	  etc.getServer().messageAll("§a"+player.getName()+" changed the weather for §4"+time/1000+"§2 seconds.");
						        	  player.sendMessage("§aThe weather is changed.");
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
						               player.sendMessage("§aThe weather is changed back.");
						               if (noweather){
							    			etc.getLoader().callCustomHook("NoWeather", new Object[] {"SET", true});
						               }
						          }catch(InterruptedException e) {}
						     }
						}.start();
						return true;
					}
					
				}else{
					player.notify("The correct usage is 'weather <rain|storm> <time>'");
					return true;
				}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#ci")){
				if (player.isOp()){
				if ((split.length <2)||(split.length >2)){
					player.notify("the correct usage is 'ci <player>'");
					return true;
				}else{
					Player player1 = etc.getServer().matchPlayer(split[1]);
					if (player1 == null){
						player.notify("Player Not Found");
						return true;
					}else{
						player1.getInventory().clearContents();
						player.sendMessage("§aplayer inventory has been cleared.");
						player1.sendMessage("§cyour inventory got cleared by "+player.getName()+".");
						return true;
					}
				}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#home")){
				if (player.isOp()){
				if ((split.length <2)||(split.length >2)){
					player.notify("the correct usage is 'home <player>'");
					return true;
				}else{
					Player player1 = etc.getServer().matchPlayer(split[1]);
					if (player1 == null){
						player.notify("Player Not Found.");
						return true;
					}else{
						Warp home = etc.getDataSource().getHome(player1.getName());
						if (home == null){
							player.notify("this player doesnt have a home.");
							return true;
						}
						Location location = home.Location;
						player1.teleportTo(location);
						player.sendMessage("§aplayer teleported.");
						player1.sendMessage("§3you got warped by "+player.getName()+".");
						return true;
					}
				}
				}
			}
			if (split[0].equalsIgnoreCase("/#date")){
				if (player.isOp()){
				 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	             Date date = new Date();
				if ((split.length <2)||(split.length >2)){
					player.notify("the correct usage is 'date [show|notify]'");
					return true;
				}else{
					if ((!split[1].equalsIgnoreCase("show"))&&(!split[1].equalsIgnoreCase("notify"))){
						player.notify("the correct usage is 'date [show|notify]'");
						return true;
					}else{
						if (split[1].equalsIgnoreCase("show")){
							player.sendMessage("§athe current date is "+dateFormat.format(date)+".");
							return true;
						}else{
							if(split[1].equalsIgnoreCase("notify")){
								etc.getServer().messageAll("§3 the current date is §4"+dateFormat.format(date)+".");
								player.sendMessage("§adate has been shown.");
								return true;
							}
						}
						return true;
					}
				}
				}
			}
			if (split[0].equalsIgnoreCase("/#rtime")){
				if (player.isOp()){
				 DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	            Date date = new Date();
				if ((split.length <2)||(split.length >2)){
					player.notify("the correct usage is 'date [show|notify]'");
					return true;
				}else{
					if ((!split[1].equalsIgnoreCase("show"))&&(!split[1].equalsIgnoreCase("notify"))){
						player.notify("the correct usage is 'date [show|notify]'");
						return true;
					}else{
						if (split[1].equalsIgnoreCase("show")){
							player.sendMessage("§athe current time is "+dateFormat.format(date)+".");
							return true;
						}else{
							if(split[1].equalsIgnoreCase("notify")){
								etc.getServer().messageAll("§3 the current time is §4"+dateFormat.format(date)+".");
								player.sendMessage("§atime has been shown.");
								return true;
							}
						}
						return true;
					}
				}
				}
			}
			if (split[0].equalsIgnoreCase("/#gm")){
				if (player.isOp()){
				if (split.length == 1){
					player.notify("The correct usage is 'gm <player> <1|0>'");
					return true;
				}else if (split.length == 2){
					Player p = etc.getServer().matchPlayer(split[1]);
					if (p == null){
						player.notify("This player is currently not logged in or does not excist.");
						return true;
					}
					int m = p.getCreativeMode();
					player.sendMessage("§athis player does currently have mode "+ m+".");
					return true;
				}else if (split.length == 3){
					try{mode = Integer.parseInt(split[2]);}catch(NumberFormatException nfe){player.notify("the correct usage is 'gm <player> <1|0>'");}
					Player p = etc.getServer().matchPlayer(split[1]);
					if (p == null){
						player.notify("This player is currently not logged in or does not excist.");
						return true;
					}else
					if (mode == p.getCreativeMode()){
						player.notify("this player is already gamemode "+mode+".");
						return true;
					}else
						if ((mode != 1)&&(mode != 0)){
							player.notify(mode +" is an unacceptable gamemode.");
							return true;
						}else{
					p.setCreativeMode(mode);
					p.sendMessage("§2"+player.getName()+" changed your gamemode to "+mode+".");
					player.sendMessage("§aplayer gamemode has been changed to "+mode+".");
					return true;
				}
				}
				}
			}
			
			if (split[0].equalsIgnoreCase("/#time")){
				if (player.isOp()){
				int time2 = 0;
				if ((split.length >3) || (split.length <2)){
					player.notify("the correct usage is time [show|notify|add|set]");
					return true;
				}
					World world = etc.getServer().getWorld(0);
				 	String time = Double.toString(world.getTime());
				 	if (split[1].equalsIgnoreCase("show")){
					player.sendMessage("§athe current time is: "+time);
					return true;
				 	}else if (split[1].equalsIgnoreCase("notify")){
				 		etc.getServer().messageAll("§5the current time is: "+time);
				 		player.sendMessage("§athe time has been shown");
				 		player.sendMessage("§athe current time is: "+time);
				 		return true;
				}else if (split[1].equalsIgnoreCase("set")){
					if ((split.length >3)||(split.length <3)){
                    player.notify("the correct usage is 'time [set] [time]");
						return true;
					}else{
						try{time2 = Integer.parseInt(split[2]);}
						catch(NumberFormatException nfe){
							player.notify( time2 + "is not an valid number");
							return true;
						}
						world.setTime(time2);
						etc.getServer().messageAll("§6"+player.getName()+" changed te time.");
						player.sendMessage("§athe time has been changed");
						return true;
					}
				}else if (split[1].equalsIgnoreCase("add")){
					if ((split.length >3)||(split.length <3)){
						player.notify("the correct usage is 'time [add] [time]");
						return true;
					}else{
						try{time2 = Integer.parseInt(split[2]);}
						catch(NumberFormatException nfe){
							player.notify( time2 + "is not an valid number");
							return true;
						}
						long addtime = world.getTime()+time2;
						world.setTime(addtime);
						etc.getServer().messageAll("§6"+player.getName()+" changed te time.");
						player.notify("§athe time has been changed");
						return true;
					}
				
				}
				}
			}
			if (split[0].equalsIgnoreCase("/#pluginlist")){
				if (player.isOp()){
				if (split.length != 1){
					player.notify("the correct usage is 'pluginlist'");
					return true;
				}else{
				player.sendMessage("§athe current pluginlist is: "+etc.getLoader().getPluginList()+".");
				return true;
			}
				}
			}
			int power = 0;
			if (split[0].equalsIgnoreCase("/#bomb")){
				if (player.isOp()){
				if ((split.length <3)||(split.length >3)){
					player.notify("the correct usage is 'bomb <player> <power>'");
					return true;
				}else{
				Player p = etc.getServer().matchPlayer(split[1]);
				if (p == null){
					player.notify("player not found");
					return true;
				}else{
					try{ power = Integer.parseInt(split[2]);
					}catch (NumberFormatException nfe){player.notify(split[2]+ " is not an number!"); return true;}
				World world = p.getWorld();
				world.explode(p, p.getX(), p.getY()+1, p.getZ()+1, power);
				player.sendMessage("§aplayer bombarded");
				p.sendMessage("§4"+player.getName() +" bombed you.");
				return true;
				}
			}}}
			if(split[0].equalsIgnoreCase("/#strike")){
				if (player.isOp()){
				if ((split.length <2)||(split.length >2)){
					player.notify("the correct usage is 'strike <player>'");
				return true;
				}else{
					Player p = etc.getServer().matchPlayer(split[1]);
					if (p == null){
						player.notify("player not found.");
						return true;
					}else{
						OWorldServer oworld = p.getWorld().getWorld();
						oworld.a(new OEntityLightningBolt(oworld, p.getX(), p.getY(), p.getZ()));
						p.sendMessage("§4"+player.getName() +"shot an lightning bolt to your head.");
						player.sendMessage("§awe shot an lightning bolt to "+player.getName()+" master.");
						return true;
					}
				}
				}
			}
			if(split[0].equalsIgnoreCase("/#log")){
				if (player.isOp()){
	            if ((split.length <2)||(split.length >2)){
	            	player.notify("usage 'log <player>'");
	                    return true;
	            }else{
	            Player p = etc.getServer().matchPlayer(split[1]);
	            if (p == null){
	            	player.notify("Player Not Found.");
	                    return true;
	            }else{
	            	if (isInList(p.getName())){
	            		player.notify("This player is already in the log list!");
	            		return true;
	            	}else{
	            Writer writer = null;
	            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	            Date date = new Date();
	            try {
	                    filename = new File(directory + p.getName() +".txt");
	                writer = new BufferedWriter(new FileWriter(filename));
	                writer.write("#date/time = "+dateFormat.format(date)+"\n");
	                writer.write("player name = "+p.getName()+"\n");
	                writer.write("player group = "+"**bugged**"+"\n");
	                writer.write("player ip = "+p.getIP()+"\n");
	                player.sendMessage("§aplayer will be logged 'till unlog'");
	            } catch (FileNotFoundException e) {
	            	player.notify("[ERROR] File not found.");
	                return true;
	            } catch (IOException e) {
	            	player.notify("[ERROR] File IOException Found o.O");
	                    return true;
	            } finally {
	                try {
	                    if (writer != null) {
	                        writer.close();
	                    }
	                } catch (IOException e) {
	                	player.notify("[ERROR] File IOException Found o.O");
	                    return true;
	                }
	            }
	            try {
	            	 if (!new File(directory+"LoggedPlayers.txt").exists()) {
	            		 props.createNewFile();
	            		 writer = new FileWriter(directory+"LoggedPlayers.txt", true);
	            		 writer.write(p.getName());
	            	 }else{
	            		 writer = new FileWriter(directory+"LoggedPlayers.txt", true);
					writer.write(p.getName()+"\n");
					writer.close();
	            	 }
				} catch (IOException e) {
					player.notify("an error occured O.o");
					return true;
				}
	            }
	            return true;
	    }
	    }
				}
			}
			if (split[0].equalsIgnoreCase("/#cch")||split[0].equalsIgnoreCase("/cchelp")){
				if (player.isOp()){
				player.sendMessage("§a======================ConsoleCommands======================");
				player.sendMessage("§a  warp <player> <warp>                      warps a player to location <warp>. ");
				player.sendMessage("§a  spawn <player>                            respawns a player.  ");
				player.sendMessage("§a  home <player>                             tps a player to his/her home");
				player.sendMessage("§a  heal <player>                             heals a player. ");
				player.sendMessage("§a  data <player>                             gets all player data. ");
				player.sendMessage("§a  kill <player>                             kills a player. ");
				player.sendMessage("§a  help or cch                               shows all help commands. ");
				player.sendMessage("§a  kickall -                                 kicks all players. ");
				player.sendMessage("§a  killall -                                 kills all players. ");
				player.sendMessage("§a  healall -                                 heals all players. ");
				player.sendMessage("§a  tpto <player> <X> <Y> <Z>                 tps an player to <X> <Y> <Z>. ");
				player.sendMessage("§a  mute <player>                             mutes a player.");
				player.sendMessage("§a  unmute <player>                           unmutes a player.");
				player.sendMessage("§a  ci <player>                               clears inventory of a player.");
				player.sendMessage("§a  weather <sun|rain|storm> (time in sec)    changes the weather.");
				player.sendMessage("§a  time [show|notify|set|add] <time>         showes you the time or notify's it to evryone.");
				player.sendMessage("§a  date [show|notify]                        showes you the date or notify's it to evtyone.");
				player.sendMessage("§a  rtime [show|notify]                       showes you the real time ot notify's it to evryone.");
				player.sendMessage("§a  xp [get|set|add|remove] <player> <amount> [get|set|add|remove] player xp.");
				player.sendMessage("§a  bomb <player> <power>                     creating an explosion on player coordinates");
				player.sendMessage("§a  strike <player>                           strikes a player");
				player.sendMessage("§a  log <player>                              log all player data, messages");
				player.sendMessage("§a  unlog <player>                            stops the log of a player");
				player.sendMessage("§a  gm <player> <gamemode(1|0)                changes the player gamemode");
				//log.info("  CAKE                                      The Cake is not a lie!");
				player.sendMessage("§a===========================V2.0============================");
				return true;
			}
				
			if (split[0].equalsIgnoreCase("/#help")){
				if (player.isOp()){
				player.sendMessage("§a======================ConsoleCommands======================");
				player.sendMessage("§a  warp <player> <warp>                      warps a player to location <warp>. ");
				player.sendMessage("§a  spawn <player>                            respawns a player.  ");
				player.sendMessage("§a  home <player>                             tps a player to his/her home");
				player.sendMessage("§a  heal <player>                             heals a player. ");
				player.sendMessage("§a  data <player>                             gets all player data. ");
				player.sendMessage("§a  kill <player>                             kills a player. ");
				player.sendMessage("§a  help or cch                               shows all help commands. ");
				player.sendMessage("§a  kickall -                                 kicks all players. ");
				player.sendMessage("§a  killall -                                 kills all players. ");
				player.sendMessage("§a  healall -                                 heals all players. ");
				player.sendMessage("§a  tpto <player> <X> <Y> <Z>                 tps an player to <X> <Y> <Z>. ");
				player.sendMessage("§a  mute <player>                             mutes a player.");
				player.sendMessage("§a  unmute <player>                           unmutes a player.");
				player.sendMessage("§a  ci <player>                               clears inventory of a player.");
				player.sendMessage("§a  weather <sun|rain|storm> (time in sec)    changes the weather.");
				player.sendMessage("§a  time [show|notify|set|add] <time>         showes you the time or notify's it to evryone.");
				player.sendMessage("§a  date [show|notify]                        showes you the date or notify's it to evtyone.");
				player.sendMessage("§a  rtime [show|notify]                       showes you the real time ot notify's it to evryone.");
				player.sendMessage("§a  xp [get|set|add|remove] <player> <amount> [get|set|add|remove] player xp.");
				player.sendMessage("§a  bomb <player> <power>                     creating an explosion on player coordinates");
				player.sendMessage("§a  strike <player>                           strikes a player");
				player.sendMessage("§a  log <player>                              log all player data, messages");
				player.sendMessage("§a  unlog <player>                            stops the log of a player");
				player.sendMessage("§a  gm <player> <gamemode(1|0)                changes the player gamemode");
				//log.info("  CAKE                                      The Cake is not a lie!");
				player.sendMessage("§a===========================V2.0============================");
				return true;
			}
			}
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

			if(split[0].equalsIgnoreCase("/#unlog")){
				if (player.isOp()){
				if ((split.length <2)||(split.length >2)){
					player.notify("usage 'unlog <player>'");
					return true;
				}else{
					if (!isInList(split[1])){
						player.notify("this player is not found in the log database!");
						player.notify("mind you have to write down full playername 'even caps'");
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
							player.notify("player unlogged");
							return true;
						}catch(IOException e){ 
							player.notify("File IOException found!...");
							return true;
						}
					}
				}
			}
			}
			return false;
		}
		
		Logger log = Logger.getLogger("Minecraft");
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
	}
