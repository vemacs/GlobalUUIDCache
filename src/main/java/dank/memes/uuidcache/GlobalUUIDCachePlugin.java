package dank.memes.uuidcache;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.net.URL;

public class GlobalUUIDCachePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        unsetURLStreamHandlerFactory();
        getLogger().info("Global API cache enabled - All requests to Mojang's API will be " +
                "handled by GlobalUUIDCache.");
        URL.setURLStreamHandlerFactory(new CachedStreamHandlerFactory());
    }

    private static String unsetURLStreamHandlerFactory() {
        try {
            Field f = URL.class.getDeclaredField("factory");
            f.setAccessible(true);
            Object curFac = f.get(null);
            f.set(null, null);
            URL.setURLStreamHandlerFactory(null);
            return curFac.getClass().getName();
        } catch (Exception e) {
            return null;
        }
    }
}