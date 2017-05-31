package com.udacity.stockhawk.widget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.support.v4.content.ContextCompat;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;



public class StockWidgetService extends RemoteViewsService {

    private static final int POSITION_ID = 0;
    private static final int POSITION_SYMBOL = 1;
    private static final int POSITION_PRICE = 2;
    private static final int POSITION_ABSOLUTE_CHANGE = 3;
    private static final int POSITION_PERCENTAGE_CHANGE = 4;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {

            }

            @Override
            public void onDataSetChanged() {
                if (data != null)
                    data.close();
                final long binderID = Binder.clearCallingIdentity();
                data = getContentResolver().query(
                        Contract.Quote.URI,
                        new String[]{Contract.Quote._ID,
                                Contract.Quote.COLUMN_SYMBOL,
                                Contract.Quote.COLUMN_PRICE,
                                Contract.Quote.COLUMN_ABSOLUTE_CHANGE,
                                Contract.Quote.COLUMN_PERCENTAGE_CHANGE},
                        null,
                        null,
                        Contract.Quote.COLUMN_SYMBOL + " ASC"
                );
                Binder.restoreCallingIdentity(binderID);
            }

            @Override
            public void onDestroy() {
                if (data != null)
                    data.close();
                data = null;
            }

            @Override
            public int getCount() {
                if (data == null)
                    return 0;
                else
                    return data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (AdapterView.INVALID_POSITION == position || data == null || !data.moveToPosition(position))
                    return null;
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_detail);
                String stockSymbol = data.getString(POSITION_SYMBOL);
                String currentPrice = "$"+ data.getString(POSITION_PRICE);
                String valueChange = "";

                if (data.getFloat(POSITION_ABSOLUTE_CHANGE) > 0) {
                    remoteViews.setTextColor(R.id.widget_percentage_change_tv, ContextCompat.getColor(getBaseContext(),R.color.material_green_700));
                    valueChange += "+";
                } else {
                    remoteViews.setTextColor(R.id.widget_percentage_change_tv, ContextCompat.getColor(getBaseContext(),R.color.material_red_700));
                }
                valueChange+= data.getString(POSITION_PERCENTAGE_CHANGE)+"%";
                remoteViews.setTextViewText(R.id.symbol, stockSymbol);
                remoteViews.setTextViewText(R.id.price, currentPrice);
                remoteViews.setTextViewText(R.id.widget_percentage_change_tv, valueChange);

                Intent fillInIntent = new Intent();
                remoteViews.setOnClickFillInIntent(R.id.list_item_ll, fillInIntent);

                return remoteViews;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_detail);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getLong(POSITION_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
