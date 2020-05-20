package com.example.widgetproject;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetServiceFactory(getApplicationContext());
    }

     static class WidgetServiceFactory implements RemoteViewsFactory{
        private final Context context;
        public static final String NAME = "com.example.widgetproject.name";
        public static final String COLOR = "com.example.widgetproject.color";
        public static final String AMOUNT = "com.example.widgetproject.amount";
        private final Model model1 = new Model("Apple","Green",3);
        private final Model model2 = new Model("Blueberry","Blue",10);
        private final Model model3 = new Model("Strawberry","Red",20);
        private final List<Model> fruits = new ArrayList<>();

        WidgetServiceFactory(Context context ){
            this.context = context;
        }

        private void initializeList(){
            fruits.add(model1);
            fruits.add(model2);
            fruits.add(model3);
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            initializeList();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return fruits.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_widget);

            views.setTextViewText(R.id.item_name, fruits.get(position).getName());
            views.setTextViewText(R.id.item_color, fruits.get(position).getColor());
            views.setTextViewText(R.id.item_amount, String.valueOf(fruits.get(position).getAmount()));

            Intent clickIntent = new Intent(context,MainActivity.class);
            clickIntent.putExtra(NAME, fruits.get(position).getName());
            clickIntent.putExtra(COLOR, fruits.get(position).getColor());
            clickIntent.putExtra(AMOUNT, String.valueOf(fruits.get(position).getAmount()));
            views.setOnClickFillInIntent(R.id.item_widget, clickIntent);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
