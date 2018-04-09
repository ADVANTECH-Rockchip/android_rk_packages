package com.android.settings;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.UserHandle;
import android.util.Log;

public class AutoPowerBroadcastReceive extends BroadcastReceiver {

	public static final String POWEROFF_TIME = "poweroff_time";
	public static final String POWERON_TIME = "poweron_time";

	@Override
	public void onReceive(Context context, Intent from_intent) {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = context
				.getSharedPreferences("com.android.settings_preferences",
						Activity.MODE_PRIVATE);
		Log.e("Advantech", "ShutdownBroadcastReceive---1");
		boolean autoPowerTimeEnabled = sharedPreferences.getBoolean("auto_power", false);
		if (autoPowerTimeEnabled) {
			if (from_intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){

				String poweroff_time = sharedPreferences.getString(POWEROFF_TIME, "");
				String[] time = poweroff_time.split(":"); 
				
				AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		        Intent poweroff_intent = new Intent(
		                "com.android.settings.action.REQUEST_POWER_OFF");

		        Calendar calendar = Calendar.getInstance();

		        if(Integer.parseInt(time[0]) < calendar.get(Calendar.HOUR_OF_DAY) || 
		        		(Integer.parseInt(time[0]) == calendar.get(Calendar.HOUR_OF_DAY) && 
		        		(Integer.parseInt(time[1]) < calendar.get(Calendar.MINUTE))))
		        	 calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
		        	
		        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));  
		        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
		        calendar.set(Calendar.SECOND, 0);
		        calendar.set(Calendar.MILLISECOND, 0);
		        
		        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
		        		poweroff_intent, PendingIntent.FLAG_CANCEL_CURRENT);
		        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
		        
		        String poweron_time = sharedPreferences.getString(POWERON_TIME, "23:00");
				time = poweron_time.split(":");
				Intent poweron_intent = new Intent(
		                "com.android.settings.action.REQUEST_POWER_ON");
				if(Integer.parseInt(time[0]) < calendar.get(Calendar.HOUR_OF_DAY) || 
		        		(Integer.parseInt(time[0]) == calendar.get(Calendar.HOUR_OF_DAY) && 
		        		(Integer.parseInt(time[1]) < calendar.get(Calendar.MINUTE))))
		        	 calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
		        	
		        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));  
		        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
		        calendar.set(Calendar.SECOND, 0);
		        calendar.set(Calendar.MILLISECOND, 0);
		        
		        PendingIntent poweron_pendingIntent = PendingIntent.getBroadcast(context, 0,
		        		poweron_intent, PendingIntent.FLAG_CANCEL_CURRENT);
		        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), poweron_pendingIntent);
		        
			} else if (from_intent.getAction().equals("com.android.settings.action.REQUEST_POWER_OFF")) {
				Log.e("Advantech", "Receive Shutdown Broadcast\n");

				/*
				 * Intent intent = new Intent(Intent.ACTION_SHUTDOWN); //
				 * intent.putExtra(Intent.EXTRA_KEY_CONFIRM, false);
				 * intent.setFlags(Intent.FLAG_RECEIVER_FOREGROUND);
				 * context.startActivity(intent);
				 */
				Intent intent = new Intent(Intent.ACTION_REQUEST_SHUTDOWN);
				intent.putExtra(Intent.EXTRA_KEY_CONFIRM, false);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivityAsUser(intent, UserHandle.CURRENT);
			} else if (from_intent.getAction().equals("com.android.settings.action.REQUEST_POWER_ON")) {
				Log.e("Advantech", "Receive PowerOn Broadcast\n");
			}
		}
	}
}
