package com.seakernel.cards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardGridAdapter mCardGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardGridAdapter = new CardGridAdapter();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerView.setAdapter(mCardGridAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCardGridAdapter = null;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_shuffle:
                if (mCardGridAdapter != null) {
                    mCardGridAdapter.shuffle();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class CardGridAdapter extends RecyclerView.Adapter<CardViewHolder>{

        List<Card> mCards;

        CardGridAdapter() {
            mCards = CardManager.getCards();
            setHasStableIds(true); // Stable ID's to make shuffle animation
        }

        @Override
        public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_card, parent, false));
        }

        @Override
        public void onBindViewHolder(CardViewHolder holder, int position) {
            Card card = mCards.get(position);

            holder.nameView.setText(card.getName());
            holder.xpView.setText(String.valueOf(card.getXpValue()));
            holder.descriptionView.setText(card.getDescription());
            holder.cardBackground.setCardBackgroundColor(card.getTypeColor(holder.itemView.getContext()));
        }

        @Override
        public int getItemCount() {
            return mCards.size();
        }

        @Override
        public long getItemId(int position) {
            return mCards.get(position).getXpValue();
        }

        /**
         * Handles a shuffle on the cards and animates the changes
         */
        void shuffle() {
            mCards = CardManager.shuffleCards(mCards);
            notifyItemRangeChanged(0, getItemCount());
        }
    }

    private static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView xpView;
        TextView nameView;
        TextView descriptionView;
        CardView cardBackground;

        CardViewHolder(View itemView) {
            super(itemView);

            cardBackground = (CardView) itemView;
            xpView = (TextView) itemView.findViewById(R.id.holder_card_xp);
            nameView = (TextView) itemView.findViewById(R.id.holder_card_name);
            descriptionView = (TextView) itemView.findViewById(R.id.holder_card_description);
        }
    }
}
