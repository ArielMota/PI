package com.example.pi.views;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pi.R;
import com.example.pi.fragments.FragmentCadastrar;
import com.example.pi.fragments.FragmentCarrinho;
import com.example.pi.fragments.FragmentLogin;
import com.example.pi.fragments.FragmentRoupasFemininas;
import com.example.pi.fragments.FragmentRoupasMasculinas;

public class AtividadeLoja extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);


        /*texto = (TextView) findViewById(R.id.token);

        String value = getIntent().getExtras().getString("token");

        texto.setText(value);*/

        tabLayout = (TabLayout) findViewById(R.id.id_tablayot);
        viewPager = (ViewPager)  findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //adicione fragmento aqui e o titulo

        adapter.AddFragment(new FragmentRoupasMasculinas(),"Masculino");
        adapter.AddFragment(new FragmentRoupasFemininas(),"Feminino");
        adapter.AddFragment(new FragmentCarrinho(),"");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
        //tabLayout.getTabAt(1).setIcon(R.drawable.ic_group);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_local_grocery_store_black_24dp);

    }
}
