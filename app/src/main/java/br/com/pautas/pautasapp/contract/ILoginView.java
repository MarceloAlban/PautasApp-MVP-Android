package br.com.pautas.pautasapp.contract;

public interface ILoginView {
    void onEmailError(int message);
    void onPasswordError(int message);
    void onNameError(int message);
    void onLoginSuccess();
    void onCreateSuccess();
    void showPassword(String password);
}
