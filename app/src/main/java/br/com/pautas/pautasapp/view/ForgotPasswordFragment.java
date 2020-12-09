package br.com.pautas.pautasapp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.ILoginView;
import br.com.pautas.pautasapp.presenter.LoginPresenter;

public class ForgotPasswordFragment extends BaseFragment implements View.OnClickListener, ILoginView {
    private LoginPresenter mLoginPresenter;
    private TextInputLayout mTxtInpEmail;

    public ForgotPasswordFragment() {
    }

    static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        mTxtInpEmail = view.findViewById(R.id.txtInpEmail);
        view.findViewById(R.id.btnSend).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSend) {
            mTxtInpEmail.setError(null);
            mLoginPresenter.recoverPassword(
                    mTxtInpEmail.getEditText().getText().toString()
            );
        }
    }

    @Override
    public void onEmailError(int message) {
        mTxtInpEmail.setError(getString(message));
    }

    @Override
    public void showPassword(String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.alert_title))
                .setMessage(String.format(getString(R.string.msg_password_show), password))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onReturn();
                    }
                }).create();

        builder.show();
    }

    @Override
    public void onPasswordError(int message) {
    }

    @Override
    public void onNameError(int message) {
    }

    @Override
    public void onLoginSuccess() {
    }

    @Override
    public void onCreateSuccess() {
    }
}
