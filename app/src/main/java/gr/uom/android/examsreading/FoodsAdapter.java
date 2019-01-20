package gr.uom.android.examsreading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodsAdapter extends ArrayAdapter<Foods> {
    private ArrayList<Foods> foods;
    public FoodsAdapter(Context context, ArrayList<Foods> foods) {
        super(context,0, foods);
        this.foods = foods;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder;
        Foods food = foods.get(position);
        if(rowView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            rowView = inflater.inflate(R.layout.simple_list_item, parent, false);
            viewHolder = new ViewHolder(rowView);
            rowView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)rowView.getTag();
        }
        viewHolder.setData(food);
        return rowView;
    }

    static class ViewHolder{
        public final TextView nameView;
        public final TextView rankView;
        public final TextView idView;

        public ViewHolder(View view) {
            this.nameView = view.findViewById(R.id.nameView);
            this.rankView = view.findViewById(R.id.rankView);
            this.idView = view.findViewById(R.id.idView);
        }
        public void setData(Foods aFood){
            nameView.setText(aFood.getName());
            rankView.setText(String.valueOf(aFood.getRank()));
            idView.setText(aFood.getId());
        }
    }
}
