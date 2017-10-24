package br.com.erivando.gestanteautocuidadopa.util;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import br.com.erivando.gestanteautocuidadopa.R;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 23 de Outubro de 2017 as 23:49h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class ViewPagerPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    int[] mResources = {
            R.drawable.ic_gestante_alimentacao,
            R.drawable.ic_pa_aparelho_digital,
            R.drawable.ic_gestante_exercicio,
            R.drawable.ic_obs_pa_profissional,
            R.drawable.ic_gestante_repouso,
            R.drawable.ic_medidor_pa
    };

    public ViewPagerPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    @Override
//    public Object instantiateItem(ViewGroup collection, int position) {
//        ModelObject modelObject = ModelObject.values()[position];
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);
//        collection.addView(layout);
//        return layout;
//    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView((LinearLayout) object);
        container.removeView((View) object);
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        ModelObject customPagerEnum = ModelObject.values()[position];
//        return mContext.getString(customPagerEnum.getTitleResId());
//    }
}
