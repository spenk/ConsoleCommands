import java.io.File;
import java.util.logging.Logger;

public class ConsoleCommands extends Plugin
{
	  String name = "ConsoleCommands";
	  String version = "2.3";
	  String author = " spenk";
	  static Logger log = Logger.getLogger("Minecraft");
	  
	  
public void initialize(){
	ConsoleCommandsListener listener = new ConsoleCommandsListener();
	ConsoleCommandsIngame iglistener = new ConsoleCommandsIngame();
log.info(this.name +" version "+ this.version + " by " +this.author+(" is initialized!"));
etc.getLoader().addListener(PluginLoader.Hook.SERVERCOMMAND, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.COMMAND, iglistener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.CHAT, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.LOGIN, listener, this, PluginListener.Priority.MEDIUM);
etc.getLoader().addListener(PluginLoader.Hook.DISCONNECT, listener, this, PluginListener.Priority.MEDIUM);
File f = new File("plugins/CCPlayerLogs");
f.mkdir();
}
public void enable(){
	log.info(this.name + " version " + this.version + " by " + this.author + " is enabled!");
}

public void disable(){
	log.info(this.name + " version " + this.version + " is disabled!");
}
}