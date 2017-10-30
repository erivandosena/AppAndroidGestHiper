package br.com.erivando.gestanteautocuidadopa.mvp.common;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import br.com.erivando.gestanteautocuidadopa.mvp.common.app.App;
import dagger.ObjectGraph;


public abstract class BaseActivity extends Activity {
    private ObjectGraph activityGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityGraph = ((App) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    protected abstract List<Object> getModules();

    public void inject(Object object) {
        activityGraph.inject(object);
    }
}
