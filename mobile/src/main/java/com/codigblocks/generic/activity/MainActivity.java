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
package com.codigblocks.generic.activity;

import com.codigblocks.generic.R;
import com.codigblocks.generic.adapter.ViewPagerAdapter;
import com.codigblocks.generic.fragment.GenericFragment;
import com.codigblocks.generic.util.Intents;
import com.codigblocks.generic.util.Preferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    @BindView(R.id.navigation)
    NavigationView mNavigation;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.title)
    TextView mTitle;

    TextView mName;

    TextView mEmail;

    ViewPagerAdapter mAdapter;

    @IdRes
    int currentNavigationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -- Bind view
        ButterKnife.bind(this);

        View headerView = mNavigation.getHeaderView(0);
        mName = ButterKnife.findById(headerView, R.id.text_user_name);
        mEmail = ButterKnife.findById(headerView, R.id.text_email);

        // -- Toolbar
        setSupportActionBar(mToolbar);

        // -- Navigation
        mNavigation.setNavigationItemSelectedListener(this);

        // -- ViewPager && TabLayout

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new GenericFragment(), "Generic 1");
        mAdapter.addFragment(new GenericFragment(), "Generic 2");
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    private void setupDrawer() {
        String name = Preferences.of(getApplicationContext()).firstName().get()+" "
                +Preferences.of(getApplicationContext()).lastName().get();
        mName.setText(name);
        mEmail.setText(Preferences.of(getApplicationContext()).email().get());
    }

    @Override
    protected void onActivityResult(int request, int result, Intent intent) {
        super.onActivityResult(request, result, intent);

    }

    // -- NavigationView.OnNavigationItemSelectedListener -----------------------------------------

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.menu_settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(settingsIntent, Intents.Requests.DEAUTHORIZATION);
                break;

            default:
                break;
        }

        currentNavigationId = menuItem.getItemId();

        mDrawer.closeDrawers();

        return true;

    }

    // -- Navigation ------------------------------------------------------------------------------

    @OnClick(R.id.drawer_menu_icon)
    void openDrawerMenu() {
        mDrawer.openDrawer(GravityCompat.START);
    }

    private void showNavigation(@IdRes int navigationId) {
        mNavigation.getMenu().findItem(navigationId).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
            return;
        }
    }
}
