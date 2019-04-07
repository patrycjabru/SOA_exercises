package src.com.example;

import java.util.HashMap;

public class Model {
    HashMap<String[],String[]> beer = new HashMap<String[], String[]>() {
        {
            put(new String[]{"słomkowy", "jasnożółty", "jasnozłoty"},
                    new String[]{"lekki lager", "Berliner Weiße", "Kölsch"});
            put(new String[]{"żółty", "złoty"},
                    new String[] {"lager", "pilzner", "pszeniczne jasne", "jasne monachijskie"});
            put(new String[]{"ciemnozłoty", "ciemnożółty", "bursztynowy"},
                    new String[]{"marcowe", "pale ale", "pszeniczne", "jasne monachijskie"});
            put(new String[]{"jasnobrązowy", "brązowy", "miedziany"},
                    new String[]{"ciemny lager", "irlandzki ale", "pale ale", "Alt"});
            put(new String[]{"brązowy"},
                    new String[]{"koźlak", "pszeniczne ciemne"});
            put(new String[]{"ciemnobrązowy", "czarny"},
                    new String[]{"stout", "porter", "porter bałtycki", "czarne"});
        }
    };
}
