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

        jb.addVetoableChangeListener(evt -> {
            if ("priceInCents".equals(evt.getPropertyName()) && ((int) evt.getNewValue()) > 250) {
                throw new PropertyVetoException(String.format("Value cannot exceed 250, but was %d", evt.getNewValue()), evt);
            }
        });

        try {
            jb.setPriceInCents(300);
        } catch (PropertyVetoException e) {
            System.out.println(e.getMessage());
        }

        try {
            jb.setPriceInCents(150);
        } catch (PropertyVetoException e) {
            System.out.println(e.getMessage());
        }
    }
}
