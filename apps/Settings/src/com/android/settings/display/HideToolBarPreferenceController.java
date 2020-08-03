/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.android.settings.display;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.os.SystemProperties;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.view.View;

import com.android.settings.DisplaySettings;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.search.DatabaseIndexingUtils;
import com.android.settings.search.InlineSwitchPayload;
import com.android.settings.search.ResultPayload;
import com.android.settings.R;
import com.android.settingslib.core.AbstractPreferenceController;

public class HideToolBarPreferenceController extends AbstractPreferenceController implements
        PreferenceControllerMixin {

    private final String mHideToolBarKey;

    public HideToolBarPreferenceController(Context context, String key) {
        super(context);
        mHideToolBarKey = key;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getPreferenceKey() {
        return mHideToolBarKey;
    }

    @Override
    public void updateState(Preference preference) {
    	boolean isShow = "true".equals(SystemProperties.get("persist.statusbar")) 
        		&& "true".equals(SystemProperties.get("persist.navbar"));
        ((SwitchPreference) preference).setChecked(isShow);
    }
}