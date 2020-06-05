package me.Rxhys.SimpleBungeeWelcome;

import java.io.File;
import java.io.IOException;
import net.md_5.bungee.api.plugin.Plugin;

public class SimpleBungeeWelcome extends Plugin {
	public void onEnable() {
		super.onEnable();
		setupDbFile(this);
		getProxy().getPluginManager().registerListener(this, new JoinEvent(this));
	}

	public void setupDbFile(Plugin plugin) {
		if (!getDataFolder().exists()) getDataFolder().mkdir();
		File playersfile = new File(plugin.getDataFolder(), "/players.sbw");
		if (!playersfile.exists())
			try {
				playersfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		File file = new File(getDataFolder(), "/config.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onDisable() {
		super.onDisable();
	}
}
