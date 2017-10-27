package br.com.erivando.gestanteautocuidadopa.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.entity.Album;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 26 de Outubro de 2017 as 19:02h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class SlideShowArrayAdapter extends ArrayAdapter<Album> {

    Context context;
    ArrayList<Album> fotos;


    public SlideShowArrayAdapter(Context context, ArrayList<Album> fotos){
        super(context, R.layout.activity_slide_show, fotos);
        this.context=context;
        this.fotos=fotos;
    }

    public  class  Holder{
        TextView nameFV;
        ImageView pic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Album data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_slide_show, parent, false);

            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txt_descricao_foto);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.img_pager_item);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }


        viewHolder.nameFV.setText("First Name: "+data.getDescricao());
        viewHolder.pic.setImageBitmap(base64ParaBitmap(data.getFoto()));


        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array

    private Bitmap base64ParaBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    private Bitmap convertToBitmap(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
}
