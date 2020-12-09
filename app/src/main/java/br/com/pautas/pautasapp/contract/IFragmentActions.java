package br.com.pautas.pautasapp.contract;

import androidx.fragment.app.Fragment;

public interface IFragmentActions {
    void onReplaceFragment(Fragment fragment);
    void onReturn();
    void onLoginSuccess();
}
