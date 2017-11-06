package br.com.erivando.gestanteautocuidadopa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.fragment.AfericaoPAFragment;
import br.com.erivando.gestanteautocuidadopa.fragment.CadastroFragment;
import br.com.erivando.gestanteautocuidadopa.fragment.MainFragment;
import br.com.erivando.gestanteautocuidadopa.fragment.OpcaoOnzeFragment;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 25 de Setembro de 2017 as 21:16h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainMVP.view {

    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Class fragmentClass;
    private TextView nomeGestanteToolbar;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        nomeGestanteToolbar = view.findViewById(R.id.txt_nome_gestante);

        presenter = new Presenter(this);

        fragmentManager = getSupportFragmentManager();
        fragmentClass = MainFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
         * Inflar o menu; Isso adiciona itens à barra de ação se estiver presente.
         */
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
         * Acionar o item da barra de ação "clique aqui" A barra de ação manipulará automaticamente
         * os cliques no botão Home/Up, desde que especificado uma atividade pai no AndroidManifest.alpha.
         */
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            if(validaTela(presenter.getGestantes().size())) {
                fragmentClass = CadastroFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        /*
         * Controla o item de exibição de navegação, clique aqui.
         */
        int id = item.getItemId();
        if (id == R.id.nav_camera) {

            if(validaTela(presenter.getGestantes().size())) {
                fragmentClass = OpcaoOnzeFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }

        }  else if (id == R.id.nav_send) {

            if(validaTela(presenter.getGestantes().size())) {
                fragmentClass = AfericaoPAFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }

        } else if (id == R.id.nav_gallery) {

            if(validaTela(presenter.getGestantes().size())) {
                Intent intent = new Intent(this, SlideShowActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_slideshow) {

            if(validaTela(presenter.getGestantes().size())) {
                Intent intent = new Intent(this, SlideShowActivity.class);
                intent.putExtra("slide", "show");
                startActivity(intent);
            }

        } else if (id == R.id.nav_share) {

            ShareCompat.IntentBuilder.from(this)
                    .setType("text/html")
                    .setSubject(getResources().getString(R.string.texto_titulo_splash))
                    .setText(getResources().getString(R.string.texto_introducao))
                    .setChooserTitle(getResources().getString(R.string.texto_share_compat))
                    .setText("https://play.google.com/store/apps/details?id" + this.getPackageName() + "&hl=pt_BR")
                    .startChooser();

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<Fragment> listOfFragments = fragmentManager.getFragments();
        if (listOfFragments.size() >= 1) {
            for (Fragment fragment : listOfFragments) {
                if (fragment instanceof OpcaoOnzeFragment) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void nomeGestanteToolbar(String text) {
        nomeGestanteToolbar.setText(text);
    }

    private boolean validaTela(int valor) {
        if (valor > 0)
            return true;
        else {
            Toast.makeText(this, getResources().getString(R.string.texto_aviso_nao_cadastrado), Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
