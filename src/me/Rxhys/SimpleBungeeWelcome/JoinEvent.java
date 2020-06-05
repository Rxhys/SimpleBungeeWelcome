package me.Rxhys.SimpleBungeeWelcome;

import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import java.io.IOException;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.Listener;

public class JoinEvent implements Listener
{
    public Plugin plugin;
    
    public JoinEvent(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onLogin(PostLoginEvent e) {
        final File file = new File(this.plugin.getDataFolder(), "/players.sbw");
        if (file.exists()) {
            Configuration configuration = null;
            try {
                configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            }
            catch (IOException exception) {
            	exception.printStackTrace();
                return;
            }
            if (!configuration.contains(e.getPlayer().getUniqueId().toString())) {
                configuration.set(e.getPlayer().getUniqueId().toString(), true);
                try {
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
                }
                catch (IOException exception) {
                	exception.printStackTrace();
                    return;
                }
                final File configfile = new File(this.plugin.getDataFolder(), "/config.yml");
                Configuration configConfig = null;
                try {
                    configConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configfile);
                }
                catch (IOException exception) {
                	exception.printStackTrace();
                    return;
                }
                String message = configConfig.getString("message");
                message = message.replace("{PLAYER}", e.getPlayer().getName());
                TextComponent messageComponent = new TextComponent();
                messageComponent.setText(ChatColor.translateAlternateColorCodes('&', message));
                ProxyServer.getInstance().broadcast((BaseComponent)messageComponent);
            }
        }
    }
}