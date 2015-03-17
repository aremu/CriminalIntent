package com.example.leodavinci360.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;



public class CrimeListActivity extends SingleFragmentActivity {

    private static final String TAG = "CrimeListFragment";

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}

