package me.Rxhys.SimpleBungeeWelcome;

import java.io.File;
import java.io.IOException;
import net.md_5.bungee.api.plugin.Plugin;

public class SimpleBungeeWelcome extends Plugin {
	@Override
	public void onEnable() {
		setupDbFile(this);
		getProxy().getPluginManager().registerListener(this, new JoinEvent(this));
		super.onEnable();
	}

	public void setupDbFile(Plugin plugin) {
		if (!getDataFolder().exists()) getDataFolder().mkdir();
		final File playersfile = new File(plugin.getDataFolder(), "/players.sbw");
		if (!playersfile.exists())
			try {
				playersfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		final File file = new File(getDataFolder(), "/config.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
