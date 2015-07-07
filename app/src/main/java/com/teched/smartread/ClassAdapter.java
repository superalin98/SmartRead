package com.teched.smartread;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONArray;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<Class> cards;
    public List<Class> visibleCards;
    private int rowLayout;
    private Context mContext;

    public ClassAdapter(List<Class> cards, int rowLayout, Context context) {
        this.cards = cards;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    public void flushFilter(){
        visibleCards = cards;
    }

    public String getName(int position) {
        return visibleCards.get(position).name;
    }
    public JSONArray getUsers(int position) {
        return visibleCards.get(position).users;
    }
    public JSONArray getPending(int position) {
        return visibleCards.get(position).pending;
    }
    public String getId(int position) { return visibleCards.get(position).id;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Class card = visibleCards.get(i);
        viewHolder.cardName.setText(card.name);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(viewHolder.cardName.getText().toString().length()>0?Utils.firstChar(viewHolder.cardName.getText().toString()):" ", Utils.calculateColor(viewHolder.cardName.getText().toString()));
        viewHolder.cardImage.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return visibleCards == null ? 0 : visibleCards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cardName;
        public ImageView cardImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardName = (TextView) itemView.findViewById(R.id.className);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }
}