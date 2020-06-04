package org.ooad_dws4.View.LCD;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontRegister {
    private Font font14segment;

    public FontRegister() {
        try {
            File file = new File(
                    getClass().getClassLoader().getResource("./font/DSEG14Classic-Regular.ttf").getFile()
            );
            font14segment = Font.createFont(Font.TRUETYPE_FONT, file);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font14segment);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Font getFont14segment() {
        return font14segment;
    }
}
