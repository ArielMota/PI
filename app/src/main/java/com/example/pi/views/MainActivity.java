package com.example.pi.views;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pi.R;
import com.example.pi.fragments.FragmentCadastrar;
import com.example.pi.fragments.FragmentCarrinho;
import com.example.pi.fragments.FragmentLogin;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.id_tablayot);
        viewPager = (ViewPager)  findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //adicione fragmento aqui e o titulo

        adapter.AddFragment(new FragmentCadastrar(),"Cadastrar");
        adapter.AddFragment(new FragmentLogin(),"Entrar");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
        //tabLayout.getTabAt(1).setIcon(R.drawable.ic_group);
    }
}
