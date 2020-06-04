package org.ooad_dws4.View.LCD;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontRegister {
    private Font font14segment;
    public FontRegister(){
        try {
            font14segment = Font.createFont(Font.TRUETYPE_FONT, new File("./font/DSEG14Classic-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font14segment);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Font getFont14segment() {
        return font14segment;
    }
}
