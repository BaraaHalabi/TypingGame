package com.company;
import processing.core.*;
import java.util.*;

class Textbox {
    PApplet applet;
    ArrayList chars;
    String view, label;
    int boxColor, boxAlpha;
    int cursorColor, cursorAlpha;
    int textColor;
    int labelColor;
    int borderColor, borderWeight;
    int cornerRadius;
    int x, y, w, h, cx, cy;
    int shiftValue;
    int textSize;
    boolean focus, cursorMode;

    Textbox (int x, int y, int w, int h, PApplet applet) {
        chars = new ArrayList();
        this.applet = applet;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        cx = x + w * 5 / 100;
        cy = y + h / 2;
        view = "";
        focus = false;
        boxAlpha = 50;
        cursorAlpha = 255;
        textSize = h * 60 / 100;
        borderWeight = 2;
        cornerRadius = 0;
        cursorMode = false;
    }

    void run() {
        render();
        focus();
        shiftText();
        graphics();
    }

    void render() {
        applet.textAlign(applet.CORNER);
        if(borderWeight > 0) {
            applet.strokeWeight(borderWeight);
            applet.stroke(borderColor);
        } else {
            applet.noStroke();
        }
        applet.fill(boxColor, boxAlpha);
        applet.rect(x, y, w, h, cornerRadius);
        if(label != null) {
            applet.textSize(h * 30 / 100);
            applet.fill(labelColor, 150);
            applet.text(label, x, y - h / 10);
        }
        applet.textSize(textSize);
        applet.fill(textColor);
        applet.text(view, x + w * 5 / 100, y + h/2 + (applet.textAscent() - applet.textDescent())/2);
        if (focus) {
            applet.stroke(cursorColor, cursorAlpha);
            applet.strokeWeight(1.5f);
            applet.line(cx + applet.textWidth(view), cy - h / 2 + h * 10 / 100, cx + applet.textWidth(view), cy + h / 2 - h * 10 / 100);
        }
    }

    void editable () {
        if (focus) {
            if ((applet.keyCode >= 32 && applet.keyCode <= 126) || applet.keyCode == 222) {
                chars.add(applet.key);
                viewText();
            } else if (applet.keyCode == 8 && chars.size() > 0) {
                if (shiftValue > 0)
                    shiftValue --;
                chars.remove(chars.size() - 1);
                viewText();
            }
        }
        shiftText();
    }

    void focus () {
        if (applet.mousePressed) {
            if (applet.mouseX > x && applet.mouseX < x + w && applet.mouseY > y && applet.mouseY < y + h)
                focus = true;
            else
                focus = false;
        }
    }

    void shiftText () {
        if (applet.textWidth(view) > w - w * 10 / 100) {
            view = "";
            shiftValue ++;
            for (int i = shiftValue; i < chars.size(); i ++) {
                view += chars.get(i);
            }
        }
    }

    void graphics () {
        if (focus) {
            if (boxAlpha < 150) {
                boxAlpha += 10;
            }
            if(cursorMode) {
                cursorAlpha = (int)applet.map(applet.sin(applet.millis() / 80), -1, 1, 0, 255);
            } else {
                cursorAlpha = ((applet.millis() / 500) % 2 == 0 ? 255 : 0);
            }
        } else {
            if (boxAlpha > 50) {
                boxAlpha -= 10;
            }
        }
    }

    void viewText () {
        view = "";
        for (int i = shiftValue; i < chars.size(); i ++) {
            view += chars.get(i);
        }
    }

    String getText (boolean clear) {
        String text = "";
        for (int i = 0; i < chars.size(); i ++) {
            text += chars.get(i);
        }
        if (clear) {
            chars.clear();
            view = "";
            shiftValue = 0;
        }
        return text;
    }

    void clear() {
        chars.clear();
        view = "";
        shiftValue = 0;
    }

    void setLabel (String label) {
        this.label = label;
    }

    void setFocus(boolean focus) {
        this.focus = focus;
    }

    void setCursorColor(int cursorColor) {
        this.cursorColor = cursorColor;
    }

    void setBoxColor(int boxColor) {
        this.boxColor = boxColor;
    }

    void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
    }

    void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    void setBorderWeight(int borderWeight) {
        this.borderWeight = borderWeight;
    }

    void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    void setCursorMode(boolean cursorMode) {
        this.cursorMode = cursorMode;
    }
}