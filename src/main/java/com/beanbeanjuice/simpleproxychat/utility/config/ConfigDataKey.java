package com.beanbeanjuice.simpleproxychat.utility.config;

public enum ConfigDataKey {
    // CONFIG
    USE_DISCORD,
    BOT_TOKEN,
    CHANNEL_ID,
    SERVER_UPDATE_INTERVAL,
    ALIASES,
    USE_PERMISSIONS,
    USE_INITIAL_SERVER_STATUS,
    USE_FAKE_MESSAGES,
    TIMESTAMP_FORMAT,
    TIMESTAMP_TIMEZONE,

    // MESSAGES
    MINECRAFT_JOIN_USE,
    MINECRAFT_JOIN,
    MINECRAFT_LEAVE_USE,
    MINECRAFT_LEAVE,
    MINECRAFT_MESSAGE,
    MINECRAFT_DISCORD_MESSAGE,
    MINECRAFT_DISCORD_EMBED_USE,
    MINECRAFT_DISCORD_EMBED_TITLE,
    MINECRAFT_DISCORD_EMBED_MESSAGE,
    MINECRAFT_DISCORD_EMBED_COLOR,
    MINECRAFT_DISCORD_EMBED_USE_TIMESTAMP,
    MINECRAFT_SWITCH_USE,
    MINECRAFT_SWITCH_DEFAULT,
    MINECRAFT_SWITCH_SHORT,
    MINECRAFT_SUCCESSFUL_RELOAD,
    MINECRAFT_NO_PERMISSION,

    DISCORD_JOIN_USE,
    DISCORD_JOIN_MESSAGE,
    DISCORD_JOIN_USE_TIMESTAMP,
    DISCORD_LEAVE_USE,
    DISCORD_LEAVE_MESSAGE,
    DISCORD_LEAVE_USE_TIMESTAMP,
    DISCORD_SWITCH_USE,
    DISCORD_SWITCH_MESSAGE,
    DISCORD_SWITCH_USE_TIMESTAMP,
    DISCORD_MINECRAFT_MESSAGE,
    DISCORD_PROXY_ENABLED,
    DISCORD_PROXY_DISABLED,
    DISCORD_PROXY_TITLE,
    DISCORD_PROXY_MESSAGE,
    DISCORD_PROXY_STATUS_ONLINE,
    DISCORD_PROXY_STATUS_OFFLINE,
    DISCORD_PROXY_STATUS_USE_TIMESTAMP,

    // External
    VANISH_ENABLED,
    LUCKPERMS_ENABLED,
    PLUGIN_STARTING
}
