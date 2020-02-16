package com.example.test8dbdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.widget.EditText;
import android.widget.Toolbar;
import com.google.android.material.tabs.TabLayout;



public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar supportActionBar;
    public static SearchView searchView;
    public static EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(null);

        toolbar= findViewById(R.id.toolbar);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.myViewPager);
        searchView=findViewById(R.id.search_bar);


        searchEditText=searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.parseColor("#9E9E9E"));
        searchEditText.setHintTextColor(Color.parseColor("#9E9E9E"));
        searchEditText.setTextSize(14);


        tabLayout.setTabTextColors(getResources().getColor(android.R.color.black), Color.parseColor("#64dd17" ));

        setSupportActionBar(toolbar);
        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter =new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Recents(),"Recents");
        viewPagerAdapter.addFragment(new Contacts_List(),"Contacts");
        viewPager.setAdapter(viewPagerAdapter);

    }

}
