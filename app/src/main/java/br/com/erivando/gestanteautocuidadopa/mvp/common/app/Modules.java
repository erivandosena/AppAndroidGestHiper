package br.com.erivando.gestanteautocuidadopa.mvp.common.app;


class Modules {

    private Modules() {
        // No instances
    }


    static Object[] list(App ngswApp) {
        return new Object[]{
                new AppModule(ngswApp),
        };
    }

}
