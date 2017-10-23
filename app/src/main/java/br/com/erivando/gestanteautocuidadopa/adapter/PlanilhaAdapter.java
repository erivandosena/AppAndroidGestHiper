package br.com.erivando.gestanteautocuidadopa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.entity.Diario;


/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 19 de Outubro de 2017 as 19:23h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class PlanilhaAdapter extends BaseAdapter {

    private Context context;
    public LayoutInflater inflater;
    private ArrayList<Diario> lista;

    public PlanilhaAdapter(Context context, ArrayList<Diario> lista) {
        super();
        this.context = context;
        this.lista = lista;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Diario diario = lista.get(position);
        View layout;

        if(convertView == null){
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            layout  = inflater.inflate(R.layout.content_planilha_pa, null);
        }else
        {
            layout = convertView;
        }

        TextView textSistolica = (TextView) layout.findViewById(R.id.text_pas);
        textSistolica.setText(diario.getPas());

        TextView textDiastolica = (TextView) layout.findViewById(R.id.text_pad);
        textDiastolica.setText(diario.getPad());

        TextView textData = (TextView) layout.findViewById(R.id.text_data);
        textData.setText(diario.getData());

        if (position % 2 == 1) {
            layout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            layout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }


        return layout;
    }

}
