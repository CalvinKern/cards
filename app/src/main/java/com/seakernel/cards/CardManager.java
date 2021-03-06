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
    private static final List<Card> mCards = new ArrayList<>();

    static {
        mCards.clear(); // Clear the cards if there were any before

        // Create a new card until we've reached the DECK_SIZE
        for (int i = 0; i < DECK_SIZE; i++) {
            mCards.add(createCard(i + 1));
        }
    }

    /**
     * @return a generated a list of cards
     */
    public static List<Card> getCards() {
        return new ArrayList<>(mCards);
    }

    /**
     * Generates a card based on the callers card number to return.
     *
     * @param cardNumber the card number to determine card type
     */
    private static Card createCard(int cardNumber) {
        if (cardNumber < NUM_CARDS_MELEE) {
            return createCardMelee();
        } else if (cardNumber < NUM_CARDS_MELEE + NUM_CARDS_DEFENSE) {
            return createCardDefense();
        } else if (cardNumber < NUM_CARDS_MELEE + NUM_CARDS_DEFENSE + NUM_CARDS_SPELL) {
            return createCardSpell();
        } else if (cardNumber < NUM_CARDS_MELEE + NUM_CARDS_DEFENSE + NUM_CARDS_SPELL + NUM_CARDS_EQUIPMENT) {
            return createCardEquipment();
        } else {
            return createCardEnemy();
        }
    }

    private static Card createCardMelee() {
        return new Card("Default Name: Melee", null, 2, Card.Type.MELEE);
    }

    private static Card createCardDefense() {
        return new Card("Default Name: Defense", null, 1, Card.Type.DEFENSE);
    }

    private static Card createCardSpell() {
        return new Card("Default Name: Spell", null, 3, Card.Type.SPELL);
    }

    private static Card createCardEquipment() {
        return new Card("Default Name: Equipment", null, 2, Card.Type.EQUIPMENT);
    }

    private static Card createCardEnemy() {
        return new Card("Default Name: Enemy", null, 4, Card.Type.ENEMY);
    }

    /**
     * Copies a list of cards, shuffles it, then returns it. This is to avoid any concurrent modifications.
     *
     * @param cardList the list of cards to make a copy of and shuffle
     * @return the copied list shuffled
     */
    public static List<Card> shuffleCards(List<Card> cardList) {
        // Setup
        List<Card> cards = new ArrayList<>(cardList);
        Card temp;
        int randIndex;
        int size = cards.size();

        // Shuffle by the SHUFFLE_COUNT to get a "perfect" shuffle
        for (int countDown = 0; countDown < SHUFFLE_COUNT; countDown++) {
            // Do a simple swap at each index with a random index to shuffle the cards
            for (int i = 0; i < size; i++) {
                // Get necessary variables
                temp = cards.get(i);
                randIndex = (int) (Math.random() * size);

                // Swap the two cards
                cards.set(i, cards.get(randIndex));
                cards.set(randIndex, temp);
            }
        }

        return cards;
    }
}
