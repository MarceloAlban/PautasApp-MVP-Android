package br.com.pautas.pautasapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.adapter.FragmentPagerAdapterList;
import br.com.pautas.pautasapp.contract.IPauta;
import br.com.pautas.pautasapp.contract.IProfileView;
import br.com.pautas.pautasapp.presenter.ProfilePresenter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IProfileView, ListFragment.IFragmentUpdate {
    private DrawerLayout mDrawerLayout;
    private AppCompatTextView mTxtNameUser;
    private AppCompatTextView mTxtEmailUser;
    private FragmentPagerAdapterList mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadUser();
    }

    private void loadUser() {
        ProfilePresenter profilePresenter = new ProfilePresenter(this);
        profilePresenter.loadUser();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.navigationView);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mTxtNameUser = navigationView.getHeaderView(0).findViewById(R.id.txtNameUser);
        mTxtEmailUser = navigationView.getHeaderView(0).findViewById(R.id.txtEmailUser);

        ViewPager viewPager = findViewById(R.id.viewpager);
        mAdapter = new FragmentPagerAdapterList(this, getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnu_profile) {
            startActivityForResult(new Intent(this, ProfileActivity.class), ProfileActivity.REQUEST_PROFILE);
        } else if (item.getItemId() == R.id.mnu_create_pauta) {
            startActivityForResult(new Intent(this, CreatePautaActivity.class), CreatePautaActivity.REQUEST_CREATE_PAUTA);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == ProfileActivity.REQUEST_PROFILE) {
            startActivity(new Intent(this, AccessActivity.class));
            finish();
        } else if (requestCode == CreatePautaActivity.REQUEST_CREATE_PAUTA) {
            updateFragment(0);
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof ListFragment) {
            ListFragment listFragment = (ListFragment) fragment;
            listFragment.setIFragmentUpdate(this);
        }
    }

    @Override
    public void showNameUser(String name) {
        mTxtNameUser.setText(name);
    }

    @Override
    public void showEmailUser(String email) {
        mTxtEmailUser.setText(email);
    }

    @Override
    public void fragmentUpdateStatus(String status) {
        if (status.equals(IPauta.STATUS_CLOSED)) {
            updateFragment(0);
        } else if (status.equals(IPauta.STATUS_OPEN)) {
            updateFragment(1);
        }
    }

    private void updateFragment(int position) {
        ListFragment fragment = (ListFragment) mAdapter.getItem(position);
        fragment.reloadList();
    }
}
