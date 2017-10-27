package br.com.erivando.gestanteautocuidadopa.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.entity.Album;


/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 25 de Outubro de 2017 as 22:51h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class SlidePagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mResources;
    List<Album> fotos;

    public SlidePagerAdapter(Context mContext, int[] mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    public SlidePagerAdapter(Context mContext, List<Album> fotos) {
        this.mContext = mContext;
        this.fotos=fotos;
    }

    @Override
    public int getCount() {
        int num = 0;
        int numFotos = fotos.size();
        int numResources = mResources.length;
        if (numFotos == 0 && numResources == 0)
            num = 0;
        else if (numFotos > 0)
            num = fotos.size();
        else if (numResources > 0)
            num = mResources.length;
        return num;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.slide_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        if(mResources.length > 0)
            imageView.setImageResource(mResources[position]);
        else
            imageView.setImageBitmap(base64ParaBitmap(fotos.get(position).getFoto()));
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private Bitmap base64ParaBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

}