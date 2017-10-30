package br.com.erivando.gestanteautocuidadopa.mvp.presenters;


import javax.inject.Inject;

import br.com.erivando.gestanteautocuidadopa.mvp.views.MainView;

public class MainPresenterImpl implements MainPresenter {
    private MainView view;

    @Inject
    public MainPresenterImpl(MainView view) {
        this.view = view;
    }


    @Override
    public void getImaginaryString() {
        // Could connect to our models to get stuff here
        String message = "Hello world, from Presenter";
        view.showToast(message);
    }
}
