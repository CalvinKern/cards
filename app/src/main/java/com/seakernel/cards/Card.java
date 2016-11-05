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

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Type.MELEE, Type.DEFENSE, Type.SPELL, Type.EQUIPMENT, Type.ENEMY})
    public @interface Type {
        int MELEE = 0;
        int DEFENSE = 1;
        int SPELL = 2;
        int EQUIPMENT = 3;
        int ENEMY = 4;
    }

    private String mName = "Default Name";
    private String mDescription = "Default Description";
    private int mXpValue = 1;
    @Type
    private int mType = Type.MELEE;

    public Card() {
    }

    public Card(String name, String description, int xpValue, @Type int type) {
        mName = name == null ? mName : name;
        mDescription = description == null ? mDescription : description;
        mXpValue = xpValue < 0 ? mXpValue : xpValue;
        mType = type;
    }

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

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getXpValue() {
        return mXpValue;
    }

    @Type
    public int getType() {
        return mType;
    }

    @ColorInt
    public int getTypeColor(Context context) {
        return getTypeColor(context, mType);
    }
}
