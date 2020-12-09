package br.com.pautas.pautasapp.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.ILoginView;
import br.com.pautas.pautasapp.presenter.LoginPresenter;

public class CreateUserFragment extends BaseFragment implements View.OnClickListener, ILoginView {
    private LoginPresenter mLoginPresenter;
    private TextInputLayout mTxtInpName;
    private TextInputLayout mTxtInpEmail;
    private TextInputLayout mTxtInpPassword;

    public CreateUserFragment() {
    }

    public static CreateUserFragment newInstance() {
        return new CreateUserFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);

        mTxtInpName = view.findViewById(R.id.txtInpName);
        mTxtInpEmail = view.findViewById(R.id.txtInpEmail);
        mTxtInpPassword = view.findViewById(R.id.txtInpPassword);

        view.findViewById(R.id.btnCreate).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCreate) {
            mTxtInpEmail.setError(null);
            mTxtInpPassword.setError(null);
            mTxtInpName.setError(null);

            mLoginPresenter.createUser(
                    mTxtInpName.getEditText().getText().toString(),
                    mTxtInpEmail.getEditText().getText().toString(),
                    mTxtInpPassword.getEditText().getText().toString()
            );
        }
    }

    @Override
    public void onEmailError(int message) {
        mTxtInpEmail.setError(getString(message));
    }

    @Override
    public void onPasswordError(int message) {
        mTxtInpPassword.setError(getString(message));
    }

    @Override
    public void onNameError(int message) {
        mTxtInpName.setError(getString(message));
    }

    @Override
    public void onLoginSuccess() {}

    @Override
    public void showPassword(String password) {}

    @Override
    public void onCreateSuccess() {
        mListener.onReturn();
    }
}
