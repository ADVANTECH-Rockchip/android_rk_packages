package com.android.settings;

import android.content.DialogInterface;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;

import java.io.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ProgressBar;

import android.os.PowerManager;
import android.os.SystemProperties;


public class ScreenRotationSettings extends PreferenceActivity {
	private static final String TAG = "ScreenRotationSettings";
//	private Preference backup_preference ;
//	private Preference restore_preference ;
	private Preference rotation_0_preference ;
	private Preference rotation_90_preference ;
	private Preference rotation_180_preference ;
	private Preference rotation_270_preference ;

	private AlertDialog.Builder builder;
	private View  view;
	public TextView Text1 ;
	private ProgressBar bar;

	Handler handler = new Handler(){
		@Override
			public void handleMessage(Message msg){
				int what = msg.what;
				if(what == 0){
					Text1.setText(msg.obj.toString());
					Text1.setVisibility(View.VISIBLE);
					bar.setVisibility(View.GONE);
				}
			}
	};

	//    private static final String BUTTON_DECT_KEY  = "button_dect_module_key";

	@Override
		protected void onCreate(Bundle icicle) {
			super.onCreate(icicle);

			addPreferencesFromResource(R.xml.screen_rotation_settings);
			rotation_0_preference = (Preference) findPreference("rotation_0");
			rotation_90_preference = (Preference) findPreference("rotation_90");
			rotation_180_preference = (Preference) findPreference("rotation_180");
			rotation_270_preference = (Preference) findPreference("rotation_270");
			String strHwRotation = SystemProperties.get("persist.sys.hwrotation", "0");
			switch(Integer.parseInt(strHwRotation)) {
			case 0:
			{
				rotation_0_preference.setEnabled(false);
				break;
			}
			case 180:
			{
				rotation_180_preference.setEnabled(false);
				break;
			}
			case 90:
			{
				rotation_90_preference.setEnabled(false);
				break;
			}
			case 270:
			{
				rotation_270_preference.setEnabled(false);
				break;
			}
			default :
				break;
			}
			builder =new AlertDialog.Builder(this);
			view=(LinearLayout) getLayoutInflater().inflate(R.layout.screen_rotation,null);
			Text1 = (TextView)view.findViewById(R.id.Text1);
			bar = (ProgressBar) view.findViewById(R.id.progressBar1);
		}

	@Override
		public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
			if(preference == rotation_0_preference){
				SystemProperties.set("persist.sys.hwrotation","0");
				PowerManager pManager=(PowerManager) getSystemService(Context.POWER_SERVICE);
				pManager.reboot("");

			} else if(preference == rotation_90_preference){
				SystemProperties.set("persist.sys.hwrotation","90");
				PowerManager pManager=(PowerManager) getSystemService(Context.POWER_SERVICE);
				pManager.reboot("");

			} else if(preference == rotation_180_preference){
				SystemProperties.set("persist.sys.hwrotation","180");
				PowerManager pManager=(PowerManager) getSystemService(Context.POWER_SERVICE);
				pManager.reboot("");

			} else if(preference == rotation_270_preference){
				SystemProperties.set("persist.sys.hwrotation","270");
				PowerManager pManager=(PowerManager) getSystemService(Context.POWER_SERVICE);
				pManager.reboot("");

			}
		return true;
		}

	@Override
		protected void onResume() {
			super.onResume();
		}

	@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();

		}
}