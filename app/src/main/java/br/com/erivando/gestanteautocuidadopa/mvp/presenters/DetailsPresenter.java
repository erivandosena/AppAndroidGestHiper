package br.com.erivando.gestanteautocuidadopa.mvp.presenters;


import br.com.erivando.gestanteautocuidadopa.mvp.common.BaseFragmentPresenter;
import br.com.erivando.gestanteautocuidadopa.mvp.views.DetailsView;

public interface DetailsPresenter extends BaseFragmentPresenter<DetailsView> {

    public void getDetails();
    public void doStuffThenFinish();

}
