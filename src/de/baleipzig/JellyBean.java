package de.baleipzig;

import java.awt.*;
import java.beans.*;


public final class JellyBean {

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoes = new VetoableChangeSupport(this);
    private Color ourColor = Color.orange;
    private int ourPriceInCents = 2;

    public Color getColor() {
        return ourColor;
    }

    public void setColor(Color newColor) {
        Color oldColor = ourColor;
        ourColor = newColor;
        changes.firePropertyChange("color", oldColor, newColor);
    }

    public int getPriceInCents() {
        return ourPriceInCents;
    }

    public void setPriceInCents(int newPriceInCents) throws PropertyVetoException {
        int oldPriceInCents = ourPriceInCents;
        // First tell the vetoers about the change. If anyone objects, we
        // let the PropertyVetoException propagate back to our caller.
        vetoes.fireVetoableChange("priceInCents", new Integer(oldPriceInCents), new Integer(newPriceInCents));
        // No one vetoed, so go ahead and make the change.
        ourPriceInCents = newPriceInCents;
        changes.firePropertyChange("priceInCents", new Integer(oldPriceInCents), new Integer(newPriceInCents));
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public void addVetoableChangeListener(VetoableChangeListener l) {
        vetoes.addVetoableChangeListener(l);
    }

    public void removeVetoableChangeListener(VetoableChangeListener l) {
        vetoes.removeVetoableChangeListener(l);
    }
}

