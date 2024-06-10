package me.classy.baapi.utility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {

    public static String setColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String getCardinalDirection(Player p) {

        double rotation = (p.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }

        if (0 <= rotation && rotation <= 22.5) {
            return "W";
        } else if (22.5 <= rotation && rotation <= 67.6) {
            return "NW";
        } else if (67.5 <= rotation && rotation <= 112.5) {
            return "N";
        } else if (112.5 <= rotation && rotation <= 157.5) {
            return "NE";
        } else if (157.5 <= rotation && rotation <= 202.5) {
            return "E";
        } else if (202.5 <= rotation && rotation <= 247.5) {
            return "SE";
        } else if (247.5 <= rotation && rotation <= 292.5) {
            return "S";
        } else if (292.5 <= rotation && rotation <= 337.5) {
            return "SW";
        } else if (337.5 <= rotation && rotation <= 360.0) {
            return "W";
        } else {
            return null;
        }
    }

    public short getPaneColor(ChatColor color) {
        switch (color) {
            case BLACK:
                return 15;
            case DARK_BLUE:
                return 11;
            case DARK_GREEN:
                return 13;
            case DARK_AQUA:
                return 9;
            case DARK_RED:
            case RED:
                return 14;
            case DARK_PURPLE:
                return 10;
            case GOLD:
                return 1;
            case GRAY:
                return 8;
            case DARK_GRAY:
                return 7;
            case BLUE:
            case AQUA:
                return 3;
            case GREEN:
                return 5;
            case LIGHT_PURPLE:
                return 2;
            case YELLOW:
                return 4;
            default:
                return 0;
        }
    }
}
