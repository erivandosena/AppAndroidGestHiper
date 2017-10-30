package br.com.erivando.gestanteautocuidadopa.mvp.presenters;


import javax.inject.Inject;

import br.com.erivando.gestanteautocuidadopa.mvp.views.IntroView;

public class IntroPresenterImpl implements IntroPresenter {
    private IntroView view;

    @Inject
    public IntroPresenterImpl() {

    }

    @Override
    public void init(IntroView view) {
        this.view = view;
    }


    @Override
    public void getDetails() {
        // Do stuff to get details
        // then report back to view
        view.loadDetailsFragment();
    }
}
