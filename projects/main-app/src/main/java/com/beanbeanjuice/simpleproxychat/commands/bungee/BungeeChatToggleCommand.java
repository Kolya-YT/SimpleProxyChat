package com.beanbeanjuice.simpleproxychat.commands.bungee;

import com.beanbeanjuice.simpleproxychat.SimpleProxyChatBungee;
import com.beanbeanjuice.simpleproxychat.utility.helper.Helper;
import com.beanbeanjuice.simpleproxychat.utility.Tuple;
import com.beanbeanjuice.simpleproxychat.utility.config.Config;
import com.beanbeanjuice.simpleproxychat.utility.config.ConfigKey;
import com.beanbeanjuice.simpleproxychat.utility.config.Permission;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BungeeChatToggleCommand extends Command implements TabExecutor {

    private final SimpleProxyChatBungee plugin;
    private final Config config;

    public BungeeChatToggleCommand(final SimpleProxyChatBungee plugin, final String... aliases) {
        super("Spc-chat", null, aliases);
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1 || args.length > 2) {
            executeError(sender);
            return;
        }

        if (args[0].equalsIgnoreCase("all")) {
            if (args.length != 2) {
                executeError(sender);
                return;
            }

            executeAll(sender, args[1]);
            return;
        }

        executeSingle(sender, args[0]);
    }

    private void executeAll(CommandSender sender, String type) {
        if (!sender.hasPermission(Permission.COMMAND_TOGGLE_CHAT_ALL.getPermissionNode()) && sender instanceof ProxiedPlayer) {
            String message = config.get(ConfigKey.MINECRAFT_COMMAND_NO_PERMISSION).asString();
            message = Helper.replaceKeys(
                    message,
                    Tuple.of("plugin-prefix", config.get(ConfigKey.PLUGIN_PREFIX).asString())
            );
            sender.sendMessage(Helper.convertToBungee(message));
            return;
        }

        Map<String, ServerInfo> serverMap = plugin.getProxy().getServers();
        switch (type.toLowerCase()) {
            case "lock" -> {
                serverMap.forEach((serverName, serverInfo) -> config.getServerChatLockHelper().addServer(serverName));
                String message = config.get(ConfigKey.MINECRAFT_COMMAND_CHAT_LOCK_ALL_LOCKED).asString();
                message = Helper.replaceKeys(
                        message,
                        Tuple.of("plugin-prefix", config.get(ConfigKey.PLUGIN_PREFIX).asString())
                );
                sender.sendMessage(Helper.convertToBungee(message));
            }

            case "unlock" -> {
                serverMap.forEach((serverName, serverInfo) -> config.getServerChatLockHelper().removeServer(serverName));
                String message = config.get(ConfigKey.MINECRAFT_COMMAND_CHAT_LOCK_ALL_UNLOCKED).asString();
                message = Helper.replaceKeys(
                        message,
                        Tuple.of("plugin-prefix", config.get(ConfigKey.PLUGIN_PREFIX).asString())
                );
                sender.sendMessage(Helper.convertToBungee(message));
            }

            default -> executeError(sender);
        }

    }

    private void executeSingle(CommandSender sender, String type) {
        if (!sender.hasPermission(Permission.COMMAND_TOGGLE_CHAT.getPermissionNode()) && sender instanceof ProxiedPlayer) {
            String message = config.get(ConfigKey.MINECRAFT_COMMAND_NO_PERMISSION).asString();
            message = Helper.replaceKeys(
                    message,
                    Tuple.of("plugin-prefix", config.get(ConfigKey.PLUGIN_PREFIX).asString())
            );
            sender.sendMessage(Helper.convertToBungee(message));
            return;
        }

        if (!(sender instanceof ProxiedPlayer player)) {
            String message = config.get(ConfigKey.MINECRAFT_COMMAND_MUST_BE_PLAYER).asString();
            message = Helper.replaceKeys(
                    message,
                    Tuple.of("plugin-prefix", config.get(ConfigKey.PLUGIN_PREFIX).asString())
            );
            sender.sendMessage(Helper.convertToBungee(message));
            return;
        }

        String currentServerName = player.getServer().getInfo().getName();
        Stream<String> servers = plugin.getProxy().getServers().keySet().stream()
                .filter(serverName -> serverName.equals(currentServerName));

        switch (type.toLowerCase()) {
            case "lock" -> {
                servers.forEach((serverName) -> config.getServerChatLockHelper().addServer(serverName));
                String message = config.get(ConfigKey.MINECRAFT_COMMAND_CHAT_LOCK_SINGLE_LOCKED).asString();
                message = Helper.replaceKeys(
                        message,
                        Tuple.of("plugin-prefix", config.get(ConfigKey.PLUGIN_PREFIX).asString()),
                        Tuple.of("server", currentServerName)
                );
                sender.sendMessage(Helper.convertToBungee(message));
            }
            case "unlock" -> {
                servers.forEach((serverName) -> config.getServerChatLockHelper().removeServer(serverName));
                String message = config.get(ConfigKey.MINECRAFT_COMMAND_CHAT_LOCK_SINGLE_UNLOCKED).asString();
                message = Helper.replaceKeys(
                        message,
                        Tuple.of("plugin-prefix", config.get(ConfigKey.PLUGIN_PREFIX).asString()),
                        Tuple.of("server", currentServerName)
                );
                sender.sendMessage(Helper.convertToBungee(message));
            }
            default -> executeError(sender);
        }
    }

    private void executeError(CommandSender sender) {
        String message = config.get(ConfigKey.MINECRAFT_COMMAND_CHAT_LOCK_USAGE).asString();
        message = Helper.replaceKeys(
                message,
                Tuple.of("plugin-prefix", config.get(ConfigKey.PLUGIN_PREFIX).asString())
        );
        sender.sendMessage(Helper.convertToBungee(message));
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) return List.of("all", "lock", "unlock");
        else if (args.length == 2) return List.of("lock", "unlock");
        else return List.of();
    }
}
