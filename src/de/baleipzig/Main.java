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

        jb.addPropertyChangeListener(evt -> {
            System.out.println(String.format("Changed property %s from %s to %s",
                    evt.getPropertyName(), evt.getOldValue(), evt.getNewValue()));
        });

        jb.setColor(Color.BLACK);

        try {
            jb.setPriceInCents(200);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
