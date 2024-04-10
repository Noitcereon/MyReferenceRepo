package me.noitcereon;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.time.LocalDateTime;

public class ConfigurationHelper {
    private static Config configApi;
    private static long lastConfigRefresh;
    private static final String CUSTOM_APP_CONF_FILE = "noitcereon.conf";
    private static final int CACHE_TIME_SECONDS = 15;
    private ConfigurationHelper(){
        // Should not be instantiated.
    }

    public static String getProperty(ConfigKeys key){
        reloadConfig();
        return configApi.getString(key.configPath);
    }
    private static void reloadConfig(){
        if(configApi == null){
            readConfigFile();
            System.out.println("Initialized config file at " +  LocalDateTime.now());
        }
        if(lastConfigRefresh + (CACHE_TIME_SECONDS*1000) < System.currentTimeMillis()){
            lastConfigRefresh = System.currentTimeMillis();
            readConfigFile();
            System.out.println("Reloaded configuration file: " + CUSTOM_APP_CONF_FILE + " at " + LocalDateTime.now());
        }
    }
    private static void readConfigFile(){
        File customConfigFile = new File(System.getProperty("user.dir") + File.separator + CUSTOM_APP_CONF_FILE);
        configApi = ConfigFactory.parseFile(customConfigFile)
                .withFallback(ConfigFactory.load());
        configApi.checkValid(ConfigFactory.defaultReference());
        lastConfigRefresh = System.currentTimeMillis();
    }

    public enum ConfigKeys{
        HOST("host.endpoint"),
        MY_DUKES_AGE_ENDPOINT("my.dukes.age.endpoint");

        public final String configPath;
        ConfigKeys(String configKey){
            this.configPath = configKey;
        }
    }
}
