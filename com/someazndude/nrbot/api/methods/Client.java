package com.someazndude.nrbot.api.methods;

import com.someazndude.nrbot.NRBot;
import com.someazndude.nrbot.asm.accessors.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Client {

    private static client client;

    public static client getClient() {
        if (client == null) {
            try {
                client = (client) URLClassLoader.newInstance(new URL[]{NRBot.getLoader().getTransformedClient().toURI().toURL()}).loadClass("client").newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return client;
    }
}
