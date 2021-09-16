package de.zweistein2.minestats

enum class Pages(val pageUrl: String, val pageName: String, val pageIcon: String?) {
    DASHBOARD("dashboard", "Dashboard", "fad fa-home"),
    BESTLIST("bestlist"," Bestenlisten","fad fa-chart-bar"),
    PLAYER("player"," Spieler","fad fa-user"),
    BLOCKS("blocks", " Blockstatistiken", "fad fa-cubes"),
    ITEMS("items", " Itemstatistiken", "fad fa-gem"),
    MOBS("mobs", " Mobstatistiken", "fad fa-spider"),
    LIST("list", " Bestenliste", null),
    SERVER("server"," Server","fad fa-cogs"),
    COINS("devstats"," Entwicklung","fad fa-brackets-curly"),
    EVENT("event"," Events","fad fa-dungeon"),
    HALLOWEEN("halloween"," Halloweenevent", "fad fa-bat"),
    CHRISTMAS("christmas"," Weihnachtsevent", "fad fa-tree-christmas"),
    SEARCH("search", " Suche nach Spielern", null),
    ERROR("error_404", "404 - Seite nicht gefunden", null);
}