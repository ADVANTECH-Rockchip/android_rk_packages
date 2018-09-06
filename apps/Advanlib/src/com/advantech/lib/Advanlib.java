package com.advantech.lib;

import android.annotation.SdkConstant;
import android.annotation.SdkConstant.SdkConstantType;
import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.WorkSource;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;
import android.os.ServiceManager;
import android.view.Window;
import android.os.IAdvSdkService;
import android.advsdklib.AdvSdklib;
import java.util.List;
import java.util.Set;
import android.net.wifi.ScanResult;
import android.bluetooth.BluetoothDevice;
import android.text.format.Time; 

public class Advanlib {
    private static final String TAG = "com.advantech.Advanlib";
    public static final String[] Cellular_NetworkType_Titles = {
        "4G",
        "3G",
        "2G",
    };
    public static final String[] Cellular_NetworkType_Values = {
        "9",
        "0",
        "1",
    };

    public static final String[] Rotation_Titles = {
	"0 degrees",
        "90 degrees",
        "180 degrees",
        "270 degrees"
    };
    public static final String[] Rotation_Values = {
        "0",
        "1",
        "2",
	"3"
    };

    public static final String[] FontSize_Titles = {
        "Small",
        "Normal",
        "Large",
        "Huge"
    };
    public static final String[] FontSize_Values = {
        "0.85",
        "1.0",
        "1.15",
        "1.3"
    };

    public static final String[] LocationMode_Titles = {
        "High accuracy",
        "Battery saving",
        "Device only"
    };
    public static final String[] LocationMode_Values = {
        "3",
        "2",
        "1"
    };

    public static final String[] Color_Titles = {
        "black",
        "dkgray",
        "gray",
        "ltgray",
        "white",
        "red",
        "green",
        "blue",
        "yellow",
        "cyan",
        "magenta"
    };
    public static final String[] Color_Values = {
        "0xFF000000",
        "0xFF444444",
        "0xFF888888",
        "0xFFCCCCCC",
        "0xFFFFFFFF",
        "0xFFFF0000",
        "0xFF00FF00",
        "0xFF0000FF",
        "0xFFFFFF00",
        "0xFF00FFFF",
        "0xFFFF00FF"
    };

    public static final String[] Screen_Timeout_Titles = {
        "15 seconds",
        "30 seconds",
        "1 minute",
        "2 minutes",
        "5 minutes",
        "10 minutes",
        "30 minutes",
    };
    public static final String[] Screen_Timeout_Values = {
        "15000",
        "30000",
        "60000",
        "120000",
        "300000",
        "600000",
        "1800000",
    };


    public static final String[] Lock_After_Timeout_Titles = {
	"Immediately",
        "5 seconds",
        "15 seconds",
        "30 seconds",
        "1 minute",
        "2 minutes",
        "5 minutes",
        "10 minutes",
        "30 minutes"
    };
    public static final String[] Lock_After_Timeout_Values = {
	"0",
        "5000",
        "15000",
        "30000",
        "60000",
        "120000",
        "300000",
        "600000",
        "1800000"
    };

    public static final String[] Usb_Configuration_Titles = {
	"Charging",
	"MTP (Media Transfer Protocol)",
	"PTP (Picture Transfer Protocol)",
	"RNDIS (USB Ethernet)",
	"Audio Source",
	"MIDI"
    };
    public static final String[] Usb_Configuration_Values = {
        "none",
        "mtp",
        "ptp",
        "rndis",
        "audio_source",
        "midi"
    };

    public static final String[] Animation_Scale_Titles = {
        "Animation off",
        "Animation scale .5x",
        "Animation scale 1x",
        "Animation scale 1.5x",
        "Animation scale 2x",
        "Animation scale 5x",
        "Animation scale 10x"
    };
    public static final String[] Animation_Scale_Values = {
        "0.0",
        "0.5",
        "1.0",
        "1.5",
        "2.0",
        "5.0",
        "10.0"
    };

    public class CurrentBatteryStatus {
	float voltage;
	float temperature;
	int levelPercent;
	String status;
	String health;
	String plugged;
	public CurrentBatteryStatus() {
		voltage = 0;
		temperature = 0;
		levelPercent = 0;
		status = "";
		health = "";
		plugged = "";
	}
	public float getVoltage() {
	    return voltage;
	}
        public float getTemperature() {
            return temperature;
        }
        public int getLevelPercent() {
            return levelPercent;
        }
        public String getStatus() {
            return status;
        }
        public String getHealth() {
            return health;
        }
        public String getPlugged() {
            return plugged;
        }
    }
	
    public class CurrentMemoryStatus {
        long totalMemory;
        long availMemory;
        
        public CurrentMemoryStatus() {
            totalMemory = 0;
            availMemory = 0;
        }
        public long getTotalMemory() {
            return totalMemory;
	}
        public long getAvailMemory() {
            return availMemory;
	}
    }
	
    
    private Context mContext;
    private IAdvSdkService mAdvSdkService;
    private AdvSdklib mAdvSdklib;

    //Advlib init
    public Advanlib(Context context) {
	mContext = context;
	mAdvSdkService = IAdvSdkService.Stub.asInterface(ServiceManager.getService("advsdk"));
	mAdvSdklib = new AdvSdklib(mContext);
    }

    //Advlib api

/********************************************** UI ************************************************************/

/********************************** UI ---- Navigation Bar ********************************************/

    /**
     * Show navigation bar.
     * @param win 
     * @param show Show navigation bar
     */
    public void showNavigationBar(Window win, boolean show){
	    try {
            mAdvSdklib.showNavigationBar(win, show);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
   
    public boolean isNavigationBarShow() {
		boolean result = false;
		try {
            result = mAdvSdklib.isNavigationBarShow();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }


    /**
     * Show or hide back button on navigation bar 
     * @param show Flag to show / hide back icon
     */
    public void showBackButton(boolean show){
        try {
            mAdvSdkService.showBackButton(show);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isBackButtonShow() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isBackButtonShow();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Show or hide home button on navigation bar 
     * @param show Flag to show / hide home icon
     */
    public void showHomeButton(boolean show){
        try {
            mAdvSdkService.showHomeButton(show);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isHomeButtonShow() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isHomeButtonShow();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Show or hide recent button on navigation bar 
     * @param show Flag to show / hide recent icon
     */
    public void showRecentButton(boolean show){
        try {
            mAdvSdkService.showRecentButton(show);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isRecentButtonShow() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isRecentButtonShow();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Set navigation bar color
     * @param color navigation bar color
     */
    public void setNavigationBarColor(int color){
        try {
            mAdvSdkService.setNavigationBarColor(color);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getNavigationBarColor(){
      	int result = 0;
		try {
			result = mAdvSdkService.getNavigationBarColor();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }
/******************************************************************************************************/

/************************************** UI ---- Status Bar ********************************************/

    /**
     * Show status bar.
     * @param win 
     * @param show Show status bar
     */
    public void showStatusBar(Window win, boolean show){
	    try {
            mAdvSdklib.showStatusBar(win, show);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isStatusBarShow() {
		boolean result = false;
		try {
            result = mAdvSdklib.isStatusBarShow();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }


    /**
     * Show or hide quick settings menu on status bar 
     * @param show Flag to show / hide quick settings menu
     */
    public void showQuickSettingMenu(boolean show) {
        try {
            mAdvSdkService.showQuickSettingMenu(show);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isQuickSettingMenuShow() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isQuickSettingMenuShow();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Show or hide time icon on status bar 
     * @param show Flag to show / hide time icon
     */
    public void showTimeIcon(boolean show) {
        try {
            mAdvSdkService.showTimeIcon(show);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isTimeIconShow() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isTimeIconShow();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Show or hide battery icon on status bar 
     * @param show Flag to show / hide battery icon
     */
    public void showBatteryIcon(boolean show) {
        try {
            mAdvSdkService.showBatteryIcon(show);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isBatteryIconShow() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isBatteryIconShow();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Set status bar color
     * @param color status bar color
     */
    public void setStatusBarColor(int color) {
        try {
            mAdvSdkService.setStatusBarColor(color);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getStatusBarColor(){
      	int result = 0;
		try {
			result = mAdvSdkService.getStatusBarColor();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

/******************************************************************************************************/

/************************************** UI ---- More **************************************************/

    /**
     * Set system kiosk mode.
     * @param win 
     * @param enabling enter kiosk mode
     */
    public void setKioskMode(Window win, boolean enabling){
	    try {
            mAdvSdklib.setKioskMode(win, enabling);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isKioskModeSet() {
		boolean result = false;
		try {
            result = mAdvSdklib.isKioskModeSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }

/******************************************************************************************************/

/*********************************************** UI End *****************************************************/

/*************************************** Wireless&Networks Start ********************************************/

/***************************** Wireless&Networks ---- Wifi ********************************************/

    /**
     * enable or disable wifi.
     * @param enabling enable or disable wifi
     */
    public void enableWifi(boolean enabling) {
        try {
            mAdvSdkService.enableWifi(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isWifiEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isWifiEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Get all wifi devices.
     * @return list wifi scan results
     */
    public List<ScanResult> getWifiScanResults() {
		try {
            return mAdvSdklib.getWifiScanResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }

/*****************************************************************************************************/

/***************************** Wireless&Networks ---- Bluetooth **************************************/

    /**
     * enable or disable bluetooth.
     * @param enabling enable or disable bluetooth
     */
    public void enableBluetooth(boolean enabling) {
        try {
            mAdvSdkService.enableBluetooth(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isBluetoothEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isBluetoothEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Start discovery bluetooth device nearby.
     */
    public void startBluetoothDiscovery() {
        try {
            mAdvSdkService.startBluetoothDiscovery();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all bonded bluetooth devices.
     * @return list bonded bluetooth devices
     */
    public Set<BluetoothDevice> getBluetoothBondedDevices() {
		try {
            return mAdvSdklib.getBluetoothBondedDevices();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }

/*****************************************************************************************************/

/******************************** Wireless&Networks ---- More ****************************************/

    /**
     * enable or disable airplane mode.
     * @param enabling enable or disable airplane mode
     */
    public void enableAirplaneMode(boolean enabling) {
        try {
            mAdvSdkService.enableAirplaneMode(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isAirplaneModeEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isAirplaneModeEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * enable or disable wifi tethering.
     * @param enabling enable or disable wifi tethering
     */
    public void enableWifiTethering(boolean enabling) {
        try {
            mAdvSdkService.enableWifiTethering(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isWifiTetheringEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isWifiTetheringEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Set default softap account information including user name.
     * and password 
     * @param username softap user name 
     * @param password softap user password 
     */
    public void setSsidDefaultAccount(String username, String password) {
        try {
            mAdvSdkService.setSsidDefaultAccount(username, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /**
     * enable or disable mobile data.
     * @param enabling enable or disable mobile data
     */
    public void enableMobileData(boolean enabling) {
        try {
            mAdvSdkService.enableMobileData(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isMobileDataEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isMobileDataEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * enable or disable data roaming.
     * @param enabling enable or disable data roaming
     */
    public void enableDataRoaming(boolean enabling) {
        try {
            mAdvSdkService.enableDataRoaming(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isDataRoamingEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isDataRoamingEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    
    /**
     * Set mobile network mode.
     * @param mode 4G/3G/2G
     */
    public void setMobileNetworkMode(int mode) {
        try {
            mAdvSdkService.setMobileNetworkMode(mode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getMobileNetworkMode() {
      	int result = 0;
		try {
			result = mAdvSdkService.getMobileNetworkMode();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

/*****************************************************************************************************/

/****************************************** Wireless&Networks End *****************************************/

/****************************************** Device Start ****************************************************/

/****************************************** Device ---- Display ***************************************/

    /**
     * Set brightness.
     * @param brightness 0-255
     */
    public void setBrightness(int brightness) {
        try {
            mAdvSdkService.setBrightness(brightness);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getBrightness() {
      	int result = 0;
		try {
			result = mAdvSdkService.getBrightness();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Set screen timeout.
     * @param timeout screen timeout
     */
    public void setScreenTimeout(int timeout) {
        try {
            mAdvSdkService.setScreenTimeout(timeout);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getScreenTimeout() {
      	int result = 0;
		try {
			result = mAdvSdkService.getScreenTimeout();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Change system font size.
     * @param fontsize 4 level totally(0.85, 1.0, 1.15 and 1.30) 
     */
    public void setFontSize(float fontsize) {
        try {
            mAdvSdkService.setFontSize(fontsize);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public float getFontSize() {
      	float result = 0;
		try {
			result = mAdvSdkService.getFontSize();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

/*****************************************************************************************************/

/************************************** Device ---- Sound&notification *******************************/

    /**
     * Set media volume.
     * @param volume 0-15
     */
    public void setMediaVolume(int volume) {
        try {
            mAdvSdkService.setMediaVolume(volume);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getMediaVolume() {
      	int result = 0;
		try {
			result = mAdvSdkService.getMediaVolume();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Set alarm volume.
     * @param volume 0-7
     */
    public void setAlarmVolume(int volume) {
        try {
            mAdvSdkService.setAlarmVolume(volume);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getAlarmVolume() {
      	int result = 0;
		try {
			result = mAdvSdkService.getAlarmVolume();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Set notification volume.
     * @param volume 0-7
     */
    public void setNotificationVolume(int volume) {
        try {
            mAdvSdkService.setNotificationVolume(volume);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getNotificationVolume() {
      	int result = 0;
		try {
			result = mAdvSdkService.getNotificationVolume();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * enable or disable lock screen notification .
     * @param enabling enable or disable lock screen notification
     */
    public void enableLockScreenNotification(boolean enabling) {
        try {
            mAdvSdkService.enableLockScreenNotification(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isLockScreenNotificationEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isLockScreenNotificationEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * enable or disable application notification.
     * @param pkgname application package name
     * @param enabling enable or disable application notification
     */
    public void enableApplicationNotification(String pkgname, boolean enabling) {
        try {
            mAdvSdkService.enableApplicationNotification(pkgname, enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isApplicationNotificationEnabled(String pkgname) {
      	boolean result = false;
		try {
			result = mAdvSdkService.isApplicationNotificationEnabled(pkgname);
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;

    }

/*****************************************************************************************************/

/***************************************** Device ---- Apps ******************************************/

    /**
     * Enable or disable to enter app info in Settings application.
     * @param enabling enable or disable enter app info 
     */
    public void enableManagedAppInfoPage(boolean enabling) {
        try {
            mAdvSdkService.enableManagedAppInfoPage(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isManagedAppInfoPageEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isManagedAppInfoPageEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Disable application.
     * @param pkgname the application package name.
     */
    public void disableApplication(String pkgname) {
        try {
            mAdvSdkService.disableApplication(pkgname);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enable application.
     * @param pkgname the application package name.
     */
    public void enableApplication(String pkgname) {
        try {
            mAdvSdkService.enableApplication(pkgname);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update or install application from file.
     * @param path the updated file full path.
     */
    public void updateApplication(String path) {
        try {
            mAdvSdkService.updateApplication(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove application.
     * @param pkgname the application package name.
     */
    public void removeApplication(String pkgname) {
        try {
            mAdvSdkService.removeApplication(pkgname);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start application.
     * @param pkgname the application package name.
     */
    public void startApplication(String pkgname) {
        try {
            mAdvSdkService.startApplication(pkgname);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set auto start application when Starting up.
     * @param pkgname the application package name.
     */
    public void setAutoStartApplication(String pkgname) {
        try {
            mAdvSdkService.setAutoStartApplication(pkgname);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getAutoStartApplication() {
        String result = "";
	try {
            result = mAdvSdkService.getAutoStartApplication();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
	return result;
    }

/*****************************************************************************************************/

/***************************************** Device ---- Battery ***************************************/

    /**
     * Start get battery status.
     */
    public void startGetBatteryStatus() {
        try {
            mAdvSdklib.startGetBatteryStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop get battery status.
     */
    public void stopGetBatteryStatus() {
        try {
            mAdvSdklib.stopGetBatteryStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
     * Get battery status.
	 * @return BatteryStatus include battery voltage, temperature, levelPercent, status, health and plugged.
     */
    public CurrentBatteryStatus getBatteryStatus() {
        CurrentBatteryStatus mCurrentBatteryStatus = new CurrentBatteryStatus();
		AdvSdklib.BatteryStatus mBatteryStatus =mAdvSdklib.new BatteryStatus();
		try {
            mBatteryStatus = mAdvSdklib.getBatteryStatus();
			mCurrentBatteryStatus.voltage = mBatteryStatus.getVoltage();
			mCurrentBatteryStatus.temperature = mBatteryStatus.getTemperature();
			mCurrentBatteryStatus.levelPercent = mBatteryStatus.getLevelPercent();
			mCurrentBatteryStatus.status = mBatteryStatus.getStatus();
			mCurrentBatteryStatus.health = mBatteryStatus.getHealth();
			mCurrentBatteryStatus.plugged = mBatteryStatus.getPlugged();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return mCurrentBatteryStatus;
    }

/*****************************************************************************************************/

/***************************************** Device ---- Memory ****************************************/

    /**
     * Get memory status.
     * @return MemoryStatus include total memory size and availible memory size.
     */
    public CurrentMemoryStatus getMemoryStatus() {
		CurrentMemoryStatus mCurrentMemoryStatus = new CurrentMemoryStatus();
		AdvSdklib.MemoryStatus mMemoryStatus =mAdvSdklib.new MemoryStatus();
		try {
            mMemoryStatus = mAdvSdklib.getMemoryStatus();
			mCurrentMemoryStatus.totalMemory = mMemoryStatus.getTotalMemory();
			mCurrentMemoryStatus.availMemory = mMemoryStatus.getAvailMemory();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return mCurrentMemoryStatus;
    }

/*****************************************************************************************************/

/***************************************** Device ---- Users *****************************************/

    /**
     * Enable or disable allow add user.
     * @param enabling enable or disable allow add user
     */
    public void enableAllowAddUser(boolean enabling) {
        try {
            mAdvSdkService.enableAllowAddUser(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isAllowAddUserEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isAllowAddUserEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Enable or disable allow remove user.
     * @param enabling enable or disable allow remove user
     */
    public void enableAllowRemoveUser(boolean enabling) {
        try {
            mAdvSdkService.enableAllowRemoveUser(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isAllowRemoveUserEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isAllowRemoveUserEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Get all users id.
     * @return String[] get all users id
     */
    public String[] getUsersID() {
		try {
            return mAdvSdkService.getUsersID();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Get all users name.
     * @return String[] get all users name
     */
    public String[] getUsersName() {
		try {
            return mAdvSdkService.getUsersName();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * remove user.
     * @param userid user id
     */
    public void removeUser(int userid) {
        try {
            mAdvSdkService.removeUser(userid);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

/*****************************************************************************************************/

/***************************************** Device End *******************************************************/

/******************************************** Personal Start ************************************************/

/*************************************** Personal ---- Location **************************************/


    /**
     * Enable or disable location.
     * @param enabling enable or disable location
     */
    public void enableLocation(boolean enabling) {
        try {
            mAdvSdkService.enableLocation(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isLocationEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isLocationEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;	
    }


    /**
     * Set location mode.
     * @param mode set location mode.
     */
    public void setLocationMode(int mode) {
        try {
            mAdvSdkService.setLocationMode(mode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getLocationMode() {
        int result = 0;
		try {
			result = mAdvSdkService.getLocationMode();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


/*****************************************************************************************************/

/*************************************** Personal ---- Security **************************************/

    /**
     * Enable or disable lock screen.
     * @param enabling enable or disable lock screen
     */  
    public void enableLockScreen(boolean enabling) {
        try {
            mAdvSdkService.enableLockScreen(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isLockScreenEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isLockScreenEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Set screen lock info.
     * @param info set screen lock info.
     */
    public void setScreenLockInfo(String info) {
        try {
            mAdvSdkService.setScreenLockInfo(info);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getScreenLockInfo() {
      	String result = "";
		try {
			result = mAdvSdkService.getScreenLockInfo();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Enable or disable visible pattern when lock screen.
     * @param enabling enable or disable visible pattern
     */
    public void enableVisiblePattern(boolean enabling) {
        try {
            mAdvSdkService.enableVisiblePattern(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isVisiblePatternEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isVisiblePatternEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Set screen lock timeout.
     * @param timeout set screen lock info timeout.
     */
    public void setScreenLockTimeout(int timeout) {
        try {
            mAdvSdkService.setScreenLockTimeout(timeout);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getScreenLockTimeout() {
      	int result = 0;
		try {
			result = mAdvSdkService.getScreenLockTimeout();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Enable or disable power button lock screen instantly.
     * @param enabling enable or disable power button lock screen instantly
     */
    public void enablePowerInstantlyLock(boolean enabling) {
        try {
            mAdvSdkService.enablePowerInstantlyLock(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isPowerInstantlyLockEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isPowerInstantlyLockEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Enable or disable show password when entering password.
     * @param enabling enable or disable show password when entering password
     */
    public void enableShowPassword(boolean enabling) {
        try {
            mAdvSdkService.enableShowPassword(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isShowPasswordEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isShowPasswordEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    
    /**
     * Allow or disallow non-market application install.
     * @param enabling allow or disallow non-marke application install
     */
    public void allowNonMarketAppsInstall(boolean enabling) {
        try {
            mAdvSdkService.allowNonMarketAppsInstall(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isNonMarketAppsInstallAllowed() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isNonMarketAppsInstallAllowed();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

/*****************************************************************************************************/

/*********************************** Personal ---- Language&input ************************************/

    /**
     * Set language.
     * @param languageTag set language.
     */
    public void setLanguage(String languageTag) {
		try {
            mAdvSdkService.setLanguage(languageTag);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getLanguage() {
      	String result = "";
		try {
			result = mAdvSdkService.getLanguage();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Get all languages name.
     * @return String[] all languages name list.
     */
    public String[] getSystemAllLanguageName() {
		try {
            return mAdvSdkService.getSystemAllLanguageName();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Get all languages tag.
     * @return String[] all languages tag list.
     */
    public String[] getSystemAllLanguageTag() {
		try {
            return mAdvSdkService.getSystemAllLanguageTag();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Set pointer speed.
     * @param speed set pointer speed.
     */
    public void setPointerSpeed(int speed) {
        try {
            mAdvSdkService.setPointerSpeed(speed);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
   
    public int getPointerSpeed() {
      	int result = 0;
		try {
			result = mAdvSdkService.getPointerSpeed();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

/*****************************************************************************************************/

/*********************************** Personal ---- Backup&reset **************************************/

    /**
     * Enable or disable factory reset on Settings application.
     * @param enabling enable or disable factory reset
     */
    public void enableFactoryReset(boolean enabling) {
        try {
            mAdvSdkService.enableFactoryReset(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isFactoryResetEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isFactoryResetEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }
/*****************************************************************************************************/

/******************************************Personal End ***************************************************/

/******************************************System Start ***************************************************/

/*************************************** System ---- Date&time ***************************************/

    /**
     * Enable or disable auto time.
     * @param enabling enable or disable auto time
     */
    public void enableAutoTime(boolean enabling) {
        try {
            mAdvSdkService.enableAutoTime(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isAutoTimeEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isAutoTimeEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Enable or disable auto time zone.
     * @param enabling enable or disable auto time zone
     */
    public void enableAutoTimeZone(boolean enabling) {
        try {
            mAdvSdkService.enableAutoTimeZone(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isAutoTimeZoneEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isAutoTimeZoneEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Set system date and time.
     * @param year Date of year
     * @param month Date of month
     * @param day Day of day
     * @param hour Time of hour
     * @param minute Time of minute
     * @param second Time of second
     */
    public void setDateTime(int year, int month, int day, int hour, int minute, int second) {
        try {
            mAdvSdkService.setDateTime(year, month, day, hour, minute, second);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getDateTime() {
      	String result = "";
		try {
			result = mAdvSdkService.getDateTime();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    public Time formatDateTime(String datetime) {
        try {
			return mAdvSdklib.formatDateTime(datetime);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    
    /**
     * Set time zone.
     * @param id time zone id.
     */
    public void setTimeZone(String id) {
        try {
            mAdvSdkService.setTimeZone(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getTimeZone() {
      	String result = "";
		try {
			result = mAdvSdkService.getTimeZone();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Get all time zone ids.
     * @return String[] time zone ids.
     */
    public String[] getAllTimeZoneIDs() {
        try {
            return mAdvSdkService.getAllTimeZoneIDs();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Get all time zone names.
     * @return String[] all time zone names.
     */
    public String[] getAllTimeZoneNames() {
        try {
            return mAdvSdkService.getAllTimeZoneNames();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    
    /**
     * Set 12/24 hour format.
     * @param is24Hour 12/24 hour format.
     */
    public void set24HourFormat(boolean is24Hour) {
        try {
            mAdvSdkService.set24HourFormat(is24Hour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean is24HourFormat() {
      	boolean result = false;
		try {
			result = mAdvSdkService.is24HourFormat();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

/*****************************************************************************************************/

/*********************************** System ---- Accessibility ***************************************/

    /**
     * Enable or disable large text.
     * @param enabling enable or disable large text
     */
    public void enableLargeText(boolean enabling) {
        try {
            mAdvSdkService.enableLargeText(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isLargeTextEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isLargeTextEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Enable or disable high contrast text.
     * @param enabling enable or disable high contrast text
     */
    public void enableTextHighContrast(boolean enabling) {
        try {
            mAdvSdkService.enableTextHighContrast(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isTextHighContrastEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isTextHighContrastEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Enable or disable auto screen rotation.
     * @param enabling enable or disable auto screen rotation
     */
    public void enableAutoScreenRotation(boolean enabling) {
        try {
            mAdvSdkService.enableAutoScreenRotation(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isAutoScreenRotationEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isAutoScreenRotationEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }


    /**
     * Set screen rotation.
     * @param rotation set screen rotation
     */
    public void setScreenRotation(int rotation){
        try {
            mAdvSdkService.setScreenRotation(rotation);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public int getScreenRotation() {
      	int result = 0;
		try {
			result = mAdvSdkService.getScreenRotation();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;

    }

    /**
     * Enable or disable screen color inversion.
     * @param enabling enable or disable screen color inversion
     */
    public void enableColorInversion(boolean enabling) {
        try {
            mAdvSdkService.enableColorInversion(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isColorInversionEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isColorInversionEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

/*****************************************************************************************************/

/******************************** System ---- Developer options **************************************/

    /**
     * Enable or disable developer options.
     * @param enabling enable or disable developer options
     */
    public void enableDeveloperOptions(boolean enabling) {
        try {
            mAdvSdkService.enableDeveloperOptions(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isDeveloperOptionsEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isDeveloperOptionsEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Enable or disable stay awake when charging.
     * @param enabling enable or disable stay awake when charging
     */
    public void enableStayAwakeForCharging(boolean enabling) {
        try {
            mAdvSdkService.enableStayAwakeForCharging(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isStayAwakeForChargingEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isStayAwakeForChargingEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Enable or disable usb debug.
     * @param enabling enable or disable usb debug
     */
    public void enableAdb(boolean enabling) {
        try {
            mAdvSdkService.enableAdb(enabling);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isAdbEnabled() {
      	boolean result = false;
		try {
			result = mAdvSdkService.isAdbEnabled();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Set usb configuration option.
     * @param option set usb configuration option 
     */
    public void setUsbConfigurationOption(String option) {
        try {
            mAdvSdkService.setUsbConfigurationOption(option);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getUsbConfigurationOption() {
      	String result = "";
		try {
			result = mAdvSdkService.getUsbConfigurationOption();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Change system window animation scale.
     * @param scale there are 6 levels (0, 0.5, 1, 1.5, 2, 5, 10)
     */    
    public void setWindowAnimationScale(float scale) {
        try {
            mAdvSdkService.setWindowAnimationScale(scale);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public float getWindowAnimationScale() {
      	float result = 0;
		try {
			result = mAdvSdkService.getWindowAnimationScale();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }
    
    /**
     * Change system transition animation scale.
     * @param scale there are 6 levels (0, 0.5, 1, 1.5, 2, 5, 10)
     */    
    public void setTransitionAnimationScale(float scale) {
        try {
            mAdvSdkService.setTransitionAnimationScale(scale);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public float getTransitionAnimationScale() {
      	float result = 0;
		try {
			result = mAdvSdkService.getTransitionAnimationScale();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

    /**
     * Change system animator duration scale.
     * @param scale there are 6 levels (0, 0.5, 1, 1.5, 2, 5, 10)
     */    
    public void setAnimatorDurationScale(float scale) {
        try {
            mAdvSdkService.setAnimatorDurationScale(scale);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public float getAnimatorDurationScale() {
      	float result = 0;
		try {
			result = mAdvSdkService.getAnimatorDurationScale();
        } catch (RemoteException e) {
           e.printStackTrace();
        }
		return result;
    }

}
