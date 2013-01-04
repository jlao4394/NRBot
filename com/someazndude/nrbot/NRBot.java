package com.someazndude.nrbot;

import com.someazndude.nrbot.asm.Loader;
import com.someazndude.nrbot.gui.Gui;
import com.someazndude.nrbot.net.Downloader;

import javax.swing.*;
import java.io.File;

public class NRBot {

    private static Gui gui;
    private static Loader loader;
    private static Downloader downloader = new Downloader();

    public static void main(String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui = new Gui();
            }
        });

        downloader.download("http://www.nrclient.com/webclient/client.jar", new File(System.getProperty("java.io.tmpdir"), "NRBot"), "client.jar");

        loader = new Loader(downloader.getClient(), new File(downloader.getClient().getParent(), "client2.jar"));
        loader.transform();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.loadApplet();
            }
        });
    }

    public static Downloader getDownloader() {
        return downloader;
    }

    public static Loader getLoader() {
        return loader;
    }
}
