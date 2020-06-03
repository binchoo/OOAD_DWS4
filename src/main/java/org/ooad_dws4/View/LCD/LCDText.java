package org.ooad_dws4.View.LCD;

public class LCDText extends LCDComponent {
    FontRegister fg;
    LCDText(char text, int size, int x, int y, int lcdNum){
        super(lcdNum);
        fg = new FontRegister();
        this.setSize(30,100);
        this.setFeature(text, size, x, y);
    }

    private void setFeature(char text, int size, int x, int y){
        this.setText(String.valueOf(text));
        this.setLocation(x,y);
        switch (size){
            case 1:
                this.setFont(fg.getFont14segment().deriveFont(10f));
                break;
            case 2:
                this.setFont(fg.getFont14segment().deriveFont(20f));
                break;
            case 3:
                this.setFont(fg.getFont14segment().deriveFont(35f));
                break;
            default:
                break;
        }
    }
}
