package br.com.erivando.gestanteautocuidadopa.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.adapter.SlidePagerAdapter;
import br.com.erivando.gestanteautocuidadopa.entity.Album;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;
import br.com.erivando.gestanteautocuidadopa.util.Utilitarios;

import static br.com.erivando.gestanteautocuidadopa.util.Utilitarios.habilitaImmersiveMode;

//import com.anupcowkur.reservoir.Reservoir;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 25 de Outubro de 2017 as 22:59h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class SlideShowActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, MainMVP.view {

    private final int pausa = 2000;
    public FrameLayout container;
    protected View view;
    private Presenter presenter;
    private ImageButton btnNext, btnFinish;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private SlidePagerAdapter mAdapter;
    private Handler handler;
    private int pagina = 0;
    Runnable runnable = new Runnable() {
        public void run() {
            if (mAdapter.getCount() == pagina) {
                pagina = 0;
            } else {
                pagina++;
            }
            intro_images.setCurrentItem(pagina, true);
            handler.postDelayed(this, pausa);
        }
    };
    private String slide_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_slide_show);
        Toolbar toolbar = findViewById(R.id.toolbar);
        container = findViewById(R.id.container);
        presenter = new Presenter(this);
        Intent intent = getIntent();
        if (intent != null) {
            slide_show = intent.getStringExtra("slide");
            if ("show".equals(slide_show)) {
                toolbar.setTitle(getResources().getString(R.string.texto_nav_slideshow));
                handler = new Handler();
                habilitaImmersiveMode(this);
            }
        }
        setSupportActionBar(toolbar);
        setReference();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setReference() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_slide_show, container);

        intro_images = view.findViewById(R.id.pager_introduction);
        btnNext = view.findViewById(R.id.btn_next);
        btnFinish = view.findViewById(R.id.btn_finish);
        pager_indicator = view.findViewById(R.id.viewPagerCountDots);

        btnNext.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        List<Album> fotosAlbum = presenter.getAlbuns();
        if (!fotosAlbum.isEmpty())
            mAdapter = new SlidePagerAdapter(SlideShowActivity.this, fotosAlbum);
        else {
            Album album = new Album(0, Utilitarios.bitmapParaBase64(BitmapFactory.decodeResource(getResources(), R.drawable.ic_gravidez_800x1280)), getResources().getString(R.string.texto_legenda_item_galeria_vazia));
            List<Album> aList = new ArrayList<>();
            aList.add(album);

            mAdapter = new SlidePagerAdapter(SlideShowActivity.this, aList);
        }

        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        setUiPageViewController();
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(SlideShowActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SlideShowActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                intro_images.setCurrentItem((intro_images.getCurrentItem() < dotsCount) ? intro_images.getCurrentItem() + 1 : 0);
                break;
            case R.id.btn_finish:
                finish();
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {
            btnNext.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (slide_show != null)
            handler.postDelayed(runnable, pausa);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (slide_show != null)
            handler.removeCallbacks(runnable);
    }
}