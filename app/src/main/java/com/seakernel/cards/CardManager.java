package com.seakernel.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Calvin on 11/3/2016.
 */

@SuppressWarnings("WeakerAccess")
public class CardManager {

    // Card counts
    private static final int NUM_CARDS_MELEE = 12;
    private static final int NUM_CARDS_DEFENSE = 15;
    private static final int NUM_CARDS_SPELL = 11;
    private static final int NUM_CARDS_EQUIPMENT = 11;
    private static final int NUM_CARDS_ENEMY = 11;
    private static final int DECK_SIZE = NUM_CARDS_MELEE + NUM_CARDS_DEFENSE + NUM_CARDS_SPELL + NUM_CARDS_EQUIPMENT + NUM_CARDS_ENEMY;

    private static final int SHUFFLE_COUNT = 7;

    /**
     * @return a generated a list of cards
     */
    public static List<Card> getCards() {
        List<Card> cards = new ArrayList<>();

        // Create a new card until we've reached the DECK_SIZE
        for (int i = 0; i < DECK_SIZE; i++) {
            cards.add(createCard(i + 1));
        }

        return cards;
    }

    /**
     * Generates a card based on the callers card number to return.
     *
     * @param cardNumber the card number to determine card type
     */
    private static Card createCard(int cardNumber) {
        if (cardNumber < NUM_CARDS_MELEE) {
            return createCardMelee(cardNumber);
        } else if (cardNumber < NUM_CARDS_MELEE + NUM_CARDS_DEFENSE) {
            return createCardDefense(cardNumber);
        } else if (cardNumber < NUM_CARDS_MELEE + NUM_CARDS_DEFENSE + NUM_CARDS_SPELL) {
            return createCardSpell(cardNumber);
        } else if (cardNumber < NUM_CARDS_MELEE + NUM_CARDS_DEFENSE + NUM_CARDS_SPELL + NUM_CARDS_EQUIPMENT) {
            return createCardEquipment(cardNumber);
        } else {
            return createCardEnemy(cardNumber);
        }
    }

    private static Card createCardMelee(int cardNumber) {
        return new Card("Default Name: Melee", null, cardNumber, Card.Type.MELEE);
    }

    private static Card createCardDefense(int cardNumber) {
        return new Card("Default Name: Defense", null, cardNumber, Card.Type.DEFENSE);
    }

    private static Card createCardSpell(int cardNumber) {
        return new Card("Default Name: Spell", null, cardNumber, Card.Type.SPELL);
    }

    private static Card createCardEquipment(int cardNumber) {
        return new Card("Default Name: Equipment", null, cardNumber, Card.Type.EQUIPMENT);
    }

    private static Card createCardEnemy(int cardNumber) {
        return new Card("Default Name: Enemy", null, cardNumber, Card.Type.ENEMY);
    }

    /**
     * Copies a list of cards, shuffles it, then returns it. This is to avoid any concurrent modifications.
     *
     * @param cardList the list of cards to make a copy of and shuffle
     * @return the copied list shuffled
     */
    public static List<Card> shuffleCards(List<Card> cardList) {
        List<Card> cards = new ArrayList<>(cardList);
        Card temp;
        int randIndex;
        int size = cards.size();

        for (int countDown = 0; countDown < SHUFFLE_COUNT; countDown++) {
            for (int i = 0; i < size; i++) {
                temp = cards.get(i);
                randIndex = (int) (Math.random() * size);

                cards.set(i, cards.get(randIndex));
                cards.set(randIndex, temp);
            }
        }

        return cards;
    }
}
