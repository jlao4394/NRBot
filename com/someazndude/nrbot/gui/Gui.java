package com.someazndude.nrbot.gui;

import com.someazndude.nrbot.api.methods.Client;
import com.someazndude.nrbot.api.methods.Players;
import com.someazndude.nrbot.asm.accessors.Entity;
import com.someazndude.nrbot.asm.accessors.Player;

import javax.swing.*;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    private Applet loader;

    public Gui() {
        initialize();
    }

    private void initialize() {
        setVisible(true);
        setTitle("NRBot");
        setSize(781, 542);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void loadApplet() {
        loader = (Applet) Client.getClient();
        loader.init();
        loader.start();
        loader.setVisible(true);
        getContentPane().add(loader);
        loader.revalidate();
        loader.repaint();
    }
}
