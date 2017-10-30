package br.com.erivando.gestanteautocuidadopa.mvp.presenters;


import br.com.erivando.gestanteautocuidadopa.mvp.common.BaseFragmentPresenter;
import br.com.erivando.gestanteautocuidadopa.mvp.views.IntroView;

public interface IntroPresenter extends BaseFragmentPresenter<IntroView> {
    public void getDetails();
}
