package com.seakernel.cards;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Calvin on 11/3/2016.
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class Card {

    // Keeps track of the current card count for unique card numbers
    private static int mCurrentCardCount = 0;

    // Card types
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Type.MELEE, Type.DEFENSE, Type.SPELL, Type.EQUIPMENT, Type.ENEMY})
    public @interface Type {
        int MELEE = 0;
        int DEFENSE = 1;
        int SPELL = 2;
        int EQUIPMENT = 3;
        int ENEMY = 4;
    }

    // Member variables
    private String mName = "Default Name";
    private String mDescription = "Default Description";
    private int mXpValue = 1;
    private int mCardNumber = (mCurrentCardCount += 1);
    @Type
    private int mType = Type.MELEE;

    // Constructors
    public Card() {
    }

    public Card(String name, String description, int xpValue, @Type int type) {
        mName = name == null ? mName : name;
        mDescription = description == null ? mDescription : description;
        mXpValue = xpValue < 0 ? mXpValue : xpValue;
        mType = type;
    }

    // Static methods
    /**
     * Given a card type, get the color from the resources that matches.
     *
     * @param context used to access resources
     * @param cardType the card type to get the matching color for
     * @return the color of the type
     */
    public static @ColorInt int getTypeColor(Context context, @Type int cardType) {

        int colorId = R.color.white;

        switch (cardType) {
            case Type.MELEE:
                colorId = R.color.background_melee;
                break;
            case Type.DEFENSE:
                colorId = R.color.background_defense;
                break;
            case Type.SPELL:
                colorId = R.color.background_spell;
                break;
            case Type.EQUIPMENT:
                colorId = R.color.background_equipment;
                break;
            case Type.ENEMY:
                colorId = R.color.background_enemy;
                break;
        }
        return ContextCompat.getColor(context, colorId);
    }

    // Public methods
    /**
     * @return the name of this card
     */
    public String getName() {
        return mName;
    }

    /**
     * @return the description of this card
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * @return the XP value of this card
     */
    public int getXpValue() {
        return mXpValue;
    }

    /**
     * @return the {@link Type} of this card
     */
    @Type
    public int getType() {
        return mType;
    }

    /**
     * @return the unique card number of this card
     */
    public int getCardNumber() {
        return mCardNumber;
    }

    /**
     * Gets the color from the resources that matches this card's type.
     *
     * @param context used to access resources
     * @return the color of the type
     */
    @ColorInt
    public int getTypeColor(Context context) {
        return getTypeColor(context, mType);
    }
}
