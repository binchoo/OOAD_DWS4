package org.ooad_dws4;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class FontRegister {
    private Font font14segment;

    public FontRegister() {
        try {
            InputStream font = getClass().getResourceAsStream("/font/DSEG14Classic-Regular.ttf");
            font14segment = Font.createFont(Font.TRUETYPE_FONT, font);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font14segment);
            font.close();
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Font getFont14segment() {
        return font14segment;
    }
}
