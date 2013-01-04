package com.someazndude.nrbot.net;

import java.io.*;
import java.net.URL;

public class Downloader {
    private File client;

    public void download(final String url, final File dir, final String name) {
        try {
            final BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());

            if (!dir.isDirectory()) {
                dir.mkdirs();
            }

            client = new File(dir, name);

            final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(client));

            int read;
            byte[] bytes = new byte[512];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getClient() {
        return client;
    }
}
