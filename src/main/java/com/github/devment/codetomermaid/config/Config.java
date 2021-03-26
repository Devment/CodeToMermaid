package com.github.devment.codetomermaid.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Singleton, storing all configuration data.
 *
 * @author Devment
 */
public class Config {
    private static final Config instance = new Config();

    /**
     * Private constructor so that this class cannot be instantiated.
     */
    private Config()
    {
        restoreStandardConfig();
    }

    /**
     * @return the current configuration.
     */
    public static Config getInstance(){
        return instance;
    }

    private String lineSeparator;
    private Charset charset;

    /**
     * Setting all configuration data back to the default values.
     */
    public void restoreStandardConfig(){
        lineSeparator = System.getProperty("line.separator");
        charset = StandardCharsets.US_ASCII;
    }

    /**
     * @return the preferred line separator '\n' or '\r\n'.
     */
    public String getLineSeparator() {
        return lineSeparator;
    }

    /**
     * @return the preferred charset.
     */
    public Charset getCharset() {
        return charset;
    }
}
