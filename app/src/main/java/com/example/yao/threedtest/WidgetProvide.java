package com.example.yao.threedtest;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.RemoteViews;

import java.util.Timer;

/**
 * Created by Yao on 2016/10/27.
 */
public class WidgetProvide extends AppWidgetProvider{
    private AppWidgetManager appWidgetManager;
    private Timer timer ;
    private Context context;
    private int index=0;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        this.appWidgetManager = appWidgetManager;
        this.context = context;
        timer = new Timer();
        handler.sendEmptyMessageDelayed(1,1000);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    RemoteViews view = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
                    index++;
                    view.setTextViewText(R.id.appshowtv,index+"");
                    ComponentName componentName = new ComponentName(context,WidgetProvide.class);
                    appWidgetManager.updateAppWidget(componentName,view);

                    handler.sendEmptyMessageDelayed(1,1000);
                    break;
            }
        }
    };
}
