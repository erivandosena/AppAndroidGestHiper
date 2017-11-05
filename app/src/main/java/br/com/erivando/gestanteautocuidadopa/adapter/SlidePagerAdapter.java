package br.com.erivando.gestanteautocuidadopa.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.entity.Album;
import br.com.erivando.gestanteautocuidadopa.util.Utilitarios;


/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 25 de Outubro de 2017 as 22:51h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class SlidePagerAdapter extends PagerAdapter {

    private final int delay = 2000;
    private Context mContext;
    private List<Album> fotos;
    private Handler handler;
    private int page = 0;

    public SlidePagerAdapter(Context mContext, List<Album> fotos) {
        this.mContext = mContext;
        this.fotos = fotos;
    }

    @Override
    public int getCount() {
        if (!fotos.isEmpty()) {
            return fotos.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.slide_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.img_pager_item);
        TextView descricaoFoto = itemView.findViewById(R.id.texto_descricao_foto);

        if (!fotos.isEmpty()) {
            imageView.setImageBitmap(Utilitarios.base64ParaBitmap(fotos.get(position).getFoto()));
            if (fotos.get(position).getDescricao() != null)
                descricaoFoto.setText(String.valueOf(fotos.get(position).getDescricao()));
        } else {
            imageView.setImageResource(R.drawable.ic_background_800x1280);
            descricaoFoto.setText(fotos.get(position).getDescricao());
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}