package com.example.eventure.presenter;

import com.example.eventure.model.contract.ExplorerLoginContract;

public class ExplorerLoginPresenter implements ExplorerLoginContract.Presenter {
    private ExplorerLoginContract.View view;

    public ExplorerLoginPresenter(ExplorerLoginContract.View view) {
        this.view = view;
    }

    @Override
    public boolean validateLoginCredentials(String username, String password) {
        if (username.isEmpty() || username.equals("")) {
            view.showLoginError("Please enter your username");
            return false;
        } else if (password.equals("") || password.isEmpty()) {
            view.showLoginError("Please enter your Password");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onDestroy() {

    }
}
