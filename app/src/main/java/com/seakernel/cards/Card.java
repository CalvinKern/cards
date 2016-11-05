package com.seakernel.cards;

/**
 * Created by Calvin on 11/3/2016.
 */

@SuppressWarnings("unused")
public class Card {

    private String mName = "Default Name";
    private String mDescription = "Default Description";
    private int mXpValue = 1;

    public Card() {
    }

    public Card(String name, String description, int xpValue) {
        mName = name == null ? mName : name;
        mDescription = description == null ? mDescription : description;
        mXpValue = xpValue < 0 ? mXpValue : xpValue;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getXpValue() {
        return mXpValue;
    }
}
