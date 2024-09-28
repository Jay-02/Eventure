package com.example.eventure.model.contract;

public interface EventOrganizerSignUpContract {
    interface View{
        void signUp(String email, String password);
        void navigateToOrganizerHome();
    }
    interface Presenter{

    }
}
