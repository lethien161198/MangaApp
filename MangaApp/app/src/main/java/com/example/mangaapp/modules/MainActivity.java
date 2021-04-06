package com.example.mangaapp.modules;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mangaapp.R;
import com.example.mangaapp.common.AppNavigator;
import com.example.mangaapp.databinding.ActivityMainBinding;
import com.example.mangaapp.models.CustomEvent;
import com.example.mangaapp.models.Manga;
import com.example.mangaapp.modules.adapter.BottomAdapter;
import com.example.mangaapp.modules.database.MangaDatabase;
import com.example.mangaapp.modules.reading.ReadingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private static BottomNavigationView navigation;
    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnCustomEvent(CustomEvent customEvent) {
        if (customEvent.isA()) {
            navigation.setVisibility(View.VISIBLE);
        } else navigation.setVisibility(View.GONE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        init();
        setContentView(view);
    }
    public void init(){

        viewPager2 = binding.viewPager;
        navigation = binding.navigation;
        setupViewPager(viewPager2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //loadFragment( (Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_MANGA) );


    }
    private void setupViewPager(ViewPager2 viewPager) {
        BottomAdapter bottomAdapter = new BottomAdapter(this);
        bottomAdapter.addFragment((Fragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_MANGA));
        bottomAdapter.addFragment(new ReadingFragment());
        viewPager.setAdapter(bottomAdapter);
        viewPager.setUserInputEnabled(false);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.manga:
                viewPager2.setCurrentItem(0);
                return true;
            case R.id.reading:
                viewPager2.setCurrentItem(1);
                return true;
        }
        return false;
    };
    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        navigation.setVisibility(View.VISIBLE);

    }
}