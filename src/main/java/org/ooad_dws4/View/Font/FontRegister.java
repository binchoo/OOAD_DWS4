package org.ooad_dws4.View.Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class FontRegister {
    private Font font14segment;

    public FontRegister() {
        try {
<<<<<<< HEAD:src/main/java/org/ooad_dws4/View/LCD/FontRegister.java
            font14segment = Font.createFont(Font.TRUETYPE_FONT, new File("./resource/DSEG14Classic-Regular.ttf"));
=======
            InputStream font = getClass().getResourceAsStream("/font/DSEG14Classic-Regular.ttf");
            font14segment = Font.createFont(Font.TRUETYPE_FONT, font);
>>>>>>> mainline:src/main/java/org/ooad_dws4/View/Font/FontRegister.java
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
