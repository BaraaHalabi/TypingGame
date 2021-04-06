package com.company;
import processing.core.*;

public class Main extends PApplet {
    Textbox tb = new Textbox(100, 100, 200, 50, this);
    public void settings() {
        size(500, 500);
    }

    public void setup() {

    }

    public void draw() {
        background(255);
        tb.run();
    }

    public void keyPressed() {
        tb.editable();
    }

    public static void main(String[] args) {
        String[] appletArgs = new String[] {"com.company.Main"};
        if (args != null) {
            PApplet.main(concat(appletArgs, args));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
