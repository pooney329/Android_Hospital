package com.example.administrator.hospital;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Content extends AppCompatActivity {
    private static final int PAGES = 4; // 페이지 수
    private ViewPager pager;
    private PagerAdapter adapter;
    String id;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수

        setContentView(R.layout.content_tab);

         id = getIntent().getExtras().getString("id");
        bundle.putString("id", id); // key , value
//        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();


        pager = findViewById(R.id.pager);
        // PagerAdapter 생성
        adapter = new SlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        // TabLayout 연결
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        // Tab 부분에 아이콘 배치


        int icons[] = {
                R.drawable.res,
                R.drawable.gom,
                R.drawable.mark,
                R.drawable.my,
        };


        for(int i=0; i<PAGES; i++) {
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }

    }

    // PagerAdapter 클래스를 정의(내부클래스)
    private class SlidePagerAdapter extends FragmentStatePagerAdapter {
        private String tabTitles[] = new String[] {"TAB1", "TAB2", "TAB3","TAB4"};

        // 생성자 정의
        public SlidePagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int i) {  // 프레그먼트 생성
            switch(i) {
                case 0: TabFragment tabFragment =new TabFragment();

                    tabFragment.setArguments(bundle);
                        return tabFragment;
                case 1: TabFragment2 tabFragment2 =new TabFragment2();
                    tabFragment2.setArguments(bundle);
                        return tabFragment2;
                case 2: TabFragment3 tabFragment3 =new TabFragment3();
                    tabFragment3.setArguments(bundle);
                        return tabFragment3;
                case 3: TabFragment4 tabFragment4 =new TabFragment4();
                    tabFragment4.setArguments(bundle);
                        return tabFragment4;
                default: return null;
            }

        }

        @Override
        public int getCount() {  // 페이지 수를 반환
            return PAGES;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //return "Page - " + (position+1);
            //return tabTitles[position];
            return null;
        }
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
    }


}

