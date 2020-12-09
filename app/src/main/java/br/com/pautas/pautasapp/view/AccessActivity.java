package br.com.pautas.pautasapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.IFragmentActions;
import br.com.pautas.pautasapp.presenter.LoginPresenter;

public class AccessActivity extends AppCompatActivity implements IFragmentActions {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoginPresenter mLoginPresenter = new LoginPresenter(null);
        if (mLoginPresenter.userPresent()) {
            showMenu();
        } else {
            replaceFragment(LoginFragment.newInstance());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        String tag = fragment.getClass().getName();

        if (manager.findFragmentByTag(tag) == null) {
            fragment.setEnterTransition(new Slide(Gravity.BOTTOM));
            fragment.setExitTransition(new Slide(Gravity.TOP));

            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onReplaceFragment(Fragment fragment) {
        replaceFragment(fragment);
    }

    @Override
    public void onReturn() {
        //Remove o Fragment da pilha
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onLoginSuccess() {
        showMenu();
    }

    private void showMenu() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
