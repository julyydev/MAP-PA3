package edu.skku.cs.pa3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.skku.cs.pa3.R;
import edu.skku.cs.pa3.model.Step;

public class StepListAdapter extends BaseAdapter {
    ArrayList<Step> items;

    public StepListAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Step getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        Step item = items.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.cell_step, viewGroup, false);
        }

        TextView textView = view.findViewById(R.id.cellTextView);
        textView.setText("STEP " + item.getNumber() + ": " + item.getStep());

        return view;
    }

    public void setItems(Step[] steps) {
        items = new ArrayList<>();
        for (int i = 0; i < steps.length; i++) {
            items.add(steps[i]);
        }
    }
}
