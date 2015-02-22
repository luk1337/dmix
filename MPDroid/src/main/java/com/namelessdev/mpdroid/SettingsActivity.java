/*
 * Copyright (C) 2010-2015 The MPDroid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.namelessdev.mpdroid;

import com.anpmech.mpd.connection.MPDConnectionListener;
import com.anpmech.mpd.event.StatusChangeListener;
import com.anpmech.mpd.subsystem.status.MPDStatus;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class SettingsActivity extends ActionBarActivity implements
        MPDConnectionListener, StatusChangeListener {

    private final MPDApplication mApp = MPDApplication.getInstance();

    private SettingsFragment mSettingsFragment;

    /**
     * Called upon connection.
     */
    @Override
    public void connectionConnected() {
        mSettingsFragment.onConnectionStateChanged();
    }

    /**
     * Called when connecting.
     */
    @Override
    public void connectionConnecting() {
    }

    /**
     * Called upon disconnection.
     *
     * @param reason The reason given for disconnection.
     */
    @Override
    public void connectionDisconnected(final String reason) {
        mSettingsFragment.onConnectionStateChanged();
    }

    @Override
    public void libraryStateChanged(final boolean updating, final boolean dbChanged) {

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        mSettingsFragment = new SettingsFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, mSettingsFragment).commit();
    }

    @Override
    public void onPause() {
        mApp.oMPDAsyncHelper.oMPD.getConnectionStatus().removeListener(this);
        mApp.oMPDAsyncHelper.removeStatusChangeListener(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mApp.oMPDAsyncHelper.addStatusChangeListener(this);
        mApp.oMPDAsyncHelper.oMPD.getConnectionStatus().addListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mApp.setActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mApp.setActivity(this);
    }

    @Override
    public void playlistChanged(final MPDStatus mpdStatus, final int oldPlaylistVersion) {

    }

    @Override
    public void randomChanged(final boolean random) {

    }

    @Override
    public void repeatChanged(final boolean repeating) {

    }

    @Override
    public void stateChanged(final MPDStatus mpdStatus, final int oldState) {

    }

    @Override
    public void stickerChanged(final MPDStatus mpdStatus) {

    }

    @Override
    public void trackChanged(final MPDStatus mpdStatus, final int oldTrack) {

    }

    @Override
    public void volumeChanged(final MPDStatus mpdStatus, final int oldVolume) {

    }
}
