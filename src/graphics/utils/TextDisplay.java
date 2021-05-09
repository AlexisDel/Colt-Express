package graphics.utils;

import java.awt.*;

public class TextDisplay {

    public static void drawCenteredString(Graphics g2D, String text, Rectangle boxIn ) {

        FontMetrics fm = g2D.getFontMetrics();

        int x = boxIn.x + ((boxIn.width - fm.stringWidth(text)) / 2);
        int y = boxIn.y + (((boxIn.height - fm.getHeight()) / 2) + fm.getAscent());

        g2D.drawString((text), x, y);

    }
}
