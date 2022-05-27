package edu.skku.cs.pa3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import edu.skku.cs.pa3.model.Recipe;

public class StepListAdapter extends BaseAdapter {
    ArrayList<String> items;

    public StepListAdapter() {
        items = new ArrayList<>();
        items.add("");
        items.add("");
        items.add("");
        items.add("");
        items.add("");
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        String item = items.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.cell_step, viewGroup, false);
        }

        return view;
    }
}
