/**
 * Copyright 2017-2017 Coding Blocks Pvt Ltd. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codigblocks.generic.util;

import com.codigblocks.generic.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import info.metadude.android.typedpreferences.BooleanPreference;
import info.metadude.android.typedpreferences.StringPreference;

/**
 * Preference utilities.
 *
 * Configuration storage for simple objects, such as text and numbers.
 */
public final class Preferences {

    public static final class Keys {
        private Keys() {
        }

        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String EMAIL = "email";
        public static final String TOKEN = "token";
        public static final String AUTHENTICATED = "authenticated";

    }

    public static final class Defaults {
        private Defaults() {
        }
    }

    private final SharedPreferences serverPreferences;

    @NonNull
    public static Preferences of(@NonNull Context context) {
        return new Preferences(context, context.getResources().getString(R.string.app_name));
    }

    private Preferences(Context context, String preferencesLocation) {
        this.serverPreferences = context.getSharedPreferences(preferencesLocation, Context.MODE_PRIVATE);
    }

    @NonNull
    public StringPreference firstName() {
        return new StringPreference(serverPreferences, Keys.FIRST_NAME);
    }

    @NonNull
    public StringPreference lastName() {
        return new StringPreference(serverPreferences, Keys.LAST_NAME);
    }

    @NonNull
    public StringPreference email() {
        return new StringPreference(serverPreferences, Keys.EMAIL);
    }

    @NonNull
    public StringPreference token() {
        return new StringPreference(serverPreferences, Keys.TOKEN);
    }

    @NonNull
    public BooleanPreference authenticated() {
        return new BooleanPreference(serverPreferences, Keys.AUTHENTICATED);
    }

}
