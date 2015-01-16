package de.baleipzig;

import java.awt.*;
import java.beans.PropertyVetoException;

public class Main {

    public static void main(String[] args) {

        final JellyBean jb = new JellyBean();
        jb.setColor(Color.BLUE);

        try {
            jb.setPriceInCents(100);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
