package br.com.pautas.pautasapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.ILoginView;
import br.com.pautas.pautasapp.presenter.LoginPresenter;

public class LoginFragment extends BaseFragment implements View.OnClickListener, ILoginView {
    private LoginPresenter mLoginPresenter;
    private TextInputLayout mTxtInpEmail;
    private TextInputLayout mTxtInpPassword;
    private AppCompatTextView mTxtCreateUser;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mTxtInpEmail = view.findViewById(R.id.txtInpEmail);
        mTxtInpPassword = view.findViewById(R.id.txtInpPassword);

        view.findViewById(R.id.txtForgotPassword).setOnClickListener(this);
        view.findViewById(R.id.btnLogin).setOnClickListener(this);

        mTxtCreateUser = view.findViewById(R.id.txtCreateUser);
        mTxtCreateUser.setOnClickListener(this);

        /*
        Optei por esconder o cadastro, pois da maneira que implementei, nao deleto o usuario no logoff.
        se deletar o usuario no login, toda vez que ele entrar no app vai ter que se cadastrar.

        entao criei um campo na tabela de usuario para saber se o mesmo fez login ou nao, assim, no logoff,
        somente faço o update para false.

        Achei confuso o documento de visao, entao fiz dessa maneira
         */
        mTxtCreateUser.setVisibility(mLoginPresenter.hasUser() ? View.GONE : View.VISIBLE);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.txtForgotPassword) {
            if (mListener != null) {
                mListener.onReplaceFragment(ForgotPasswordFragment.newInstance());
            }
        } else if (v.getId() == R.id.txtCreateUser) {
            if (mListener != null) {
                mListener.onReplaceFragment(CreateUserFragment.newInstance());
            }
        } else if (v.getId() == R.id.btnLogin) {
            mTxtInpEmail.setError(null);
            mTxtInpPassword.setError(null);
            mLoginPresenter.login(
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
    }

    @Override
    public void onCreateSuccess() {
    }

    @Override
    public void showPassword(String password) {
    }

    @Override
    public void onLoginSuccess() {
        mListener.onLoginSuccess();
    }
}
