package br.com.pautas.pautasapp.presenter;

import br.com.pautas.pautasapp.contract.IProfileView;
import br.com.pautas.pautasapp.contract.IUser;
import br.com.pautas.pautasapp.model.User;

public class ProfilePresenter {
    private IProfileView mILoginView;
    private IUser mIUser;

    public ProfilePresenter(IProfileView mILoginView) {
        this.mILoginView = mILoginView;
        initUser();
    }

    private void initUser() {
        this.mIUser = new User();
    }

    public void loadUser(){
        User user = mIUser.getUser();

        mILoginView.showEmailUser(user.getEmail());
        mILoginView.showNameUser(user.getName());
    }

    public void logoff() {
        User user = mIUser.getUser();
        user.setLogged(false);
        mIUser.update(user);
    }
}
