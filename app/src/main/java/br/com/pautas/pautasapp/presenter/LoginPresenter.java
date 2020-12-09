package br.com.pautas.pautasapp.presenter;

import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.ILoginView;
import br.com.pautas.pautasapp.contract.IUser;
import br.com.pautas.pautasapp.model.User;

public class LoginPresenter {
    private ILoginView mILoginView;
    private IUser mIUser;

    public LoginPresenter(ILoginView mLoginView) {
        this.mILoginView = mLoginView;
        this.mIUser = new User();
    }

    public void login(String email, String password) {
        //feito dessa maneira, para jogar o set error em todos os campos de uma vez
        boolean valEmail = validateEmail(email, true);
        boolean valPassword = validatePassword(password, true);

        if (valEmail && valPassword) {
            User user = mIUser.getUser();
            user.setLogged(true);

            mIUser.update(user);

            mILoginView.onLoginSuccess();
        }
    }

    public void createUser(String name, String email, String password) {
        //feito dessa maneira, para jogar o set error em todos os campos de uma vez
        boolean valEmail = validateEmail(email, false);
        boolean valPassword = validatePassword(password, false);
        boolean valName = validateName(name);

        if (valEmail && valPassword && valName) {
            mIUser.insert(new User(name, email, password));

            mILoginView.onCreateSuccess();
        }
    }

    public void recoverPassword(String email) {
        boolean valEmail = validateEmail(email, true);

        if (valEmail) {
            mILoginView.showPassword(mIUser.getUser().getPassword());
        }
    }

    public boolean userPresent() {
        User user = this.mIUser.getUser();
        return ((user != null) && (user.isLogged()));
    }

    public boolean hasUser() {
        return this.mIUser.getUser() != null;
    }

    public boolean validateName(String name) {
        if (name.trim().equals("")) {
            mILoginView.onNameError(R.string.msg_name_empty);
            return false;
        }

        return true;
    }

    public boolean validateEmail(String email, boolean forLogin) {
        if (email.trim().equals("")) {
            mILoginView.onEmailError(R.string.msg_email_empty);
            return false;
        }

        if (forLogin) {
            User user = mIUser.getUser();
            if (user != null) {
                if (!user.getEmail().equals(email.trim())) {
                    mILoginView.onEmailError(R.string.msg_email_invalid);
                    return false;
                }
            } else {
                mILoginView.onEmailError(R.string.msg_email_invalid);
                return false;
            }
        }

        return true;
    }

    public boolean validatePassword(String password, boolean forLogin) {
        if (password.trim().equals("")) {
            mILoginView.onPasswordError(R.string.msg_password_empty);
            return false;
        }

        if (forLogin) {
            User user = mIUser.getUser();
            if (user != null) {
                if (!user.getPassword().equals(password.trim())) {
                    mILoginView.onPasswordError(R.string.msg_password_invalid);
                    return false;
                }
            } else {
                mILoginView.onPasswordError(R.string.msg_password_invalid);
                return false;
            }
        }

        return true;
    }
}
