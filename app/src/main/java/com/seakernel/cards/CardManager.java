package com.seakernel.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Calvin on 11/3/2016.
 */

public class CardManager {

    private static final int DECK_SIZE = 40;
    private static final int SHUFFLE_COUNT = 7;

    /**
     * Generates a list of cards
     * @return
     */
    public static List<Card> getCards() {
        List<Card> cards = new ArrayList<>();

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
        return new Card(null, null, cardNumber);
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
