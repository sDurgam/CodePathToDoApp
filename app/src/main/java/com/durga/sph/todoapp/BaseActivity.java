package com.durga.sph.todoapp;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cengalabs.flatui.FlatUI;

/**
 * Created by durga on 12/27/15.
 */
    public class BaseActivity extends AppCompatActivity
    {

        protected SQLiteDBHelper dbHelper;
        protected Toolbar toolbar;

        @Override
        protected void onCreate(Bundle arg0)
        {
            super.onCreate(arg0);
//            ActionBar actionBar = getSupportActionBar();
//            actionBar.setLogo(R.drawable.checkboxchecked);
//            actionBar.setDisplayUseLogoEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
            FlatUI.initDefaultValues(this);
            FlatUI.setDefaultTheme(FlatUI.SEA);
        }

        protected void SetUpToolBar()
        {
            toolbar.setTitle(null);
            toolbar.setLogo(R.drawable.checkboxchecked);
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        protected void SetBackToolbar()
        {
             getSupportActionBar().setHomeButtonEnabled(true);
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
            int id = item.getItemId();
            if(id == android.R.id.home)
            {
                NavUtils.navigateUpFromSameTask(this);
            }
            return super.onOptionsItemSelected(item);
        }


        @Override
        protected void onPause() {
            dbHelper.close();
            super.onPause();
        }

        @Override
        protected void onResume() {
            super.onResume();
            dbHelper = new SQLiteDBHelper(this);
        }

        @Override
        public void onBackPressed()
        {
            super.onBackPressed();
        }
}
