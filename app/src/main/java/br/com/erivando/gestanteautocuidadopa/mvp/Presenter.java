package br.com.erivando.gestanteautocuidadopa.mvp;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.dao.AlbumDAO;
import br.com.erivando.gestanteautocuidadopa.dao.DiarioDAO;
import br.com.erivando.gestanteautocuidadopa.dao.GestanteDAO;
import br.com.erivando.gestanteautocuidadopa.entity.Album;
import br.com.erivando.gestanteautocuidadopa.entity.Diario;
import br.com.erivando.gestanteautocuidadopa.entity.Gestante;
import br.com.erivando.gestanteautocuidadopa.fragment.OpcaoOnzeFragment;

import static android.app.Activity.RESULT_OK;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 04 de Outubro de 2017 as 12:41h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Presenter implements MainMVP.presenter {
    private MainMVP.view view;
    private GestanteDAO gestanteDAO;
    private DiarioDAO diarioDAO;
    private AlbumDAO albumDAO;

//    private ImageView imageview;
//    private Button btnSelectImage;
//    public Bitmap bitmap;
//    private File destination = null;
//    private InputStream inputStreamImg;
//    private String imgPath = null;
//    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    public Presenter(MainMVP.view view) {
        this.view = view;
        gestanteDAO = new GestanteDAO(view.getContext());
        diarioDAO = new DiarioDAO(view.getContext());
        albumDAO = new AlbumDAO(view.getContext());
    }

    public long cadastrarGestante(String nome, String menstruacao, String ultrasom, int semanas) {
        long codGestante = 0L;
        try{
            Gestante gestante = new Gestante();
            gestante.setNome(nome);
            gestante.setMenstruacao(menstruacao);
            gestante.setUltrasom(ultrasom);
            gestante.setSemanas(semanas);

            codGestante = gestanteDAO.inserir(gestante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codGestante;
    }

    public Gestante getGestante() {
        Gestante dadosGestante = new Gestante();
        try{
            dadosGestante = gestanteDAO.buscar(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosGestante;
    }

    public List getGestantes() {
        List<Gestante> dadosGestante = new ArrayList<>();
        try{
            dadosGestante = gestanteDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosGestante;
    }

    public long cadastrarDiario(String siatolica, String diastolica, String dataHora) {
        long codDiario = 0L;
        try{
            Diario diario = new Diario();
            diario.setPas(siatolica);
            diario.setPad(diastolica);
            diario.setData(dataHora);

            codDiario = diarioDAO.inserir(diario);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codDiario;
    }

    public Diario getDiario(int id) {
        Diario dadosDiario = new Diario();
        try{
            dadosDiario = diarioDAO.buscar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosDiario;
    }

    public List getDiarios() {
        List<Diario> dadosDiario = new ArrayList<>();
        try{
            dadosDiario = diarioDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosDiario;
    }

    public long cadastrarAlbum(String foto, String descricao) {
        long codAlbum = 0L;
        try{
            Album album = new Album();
            album.setFoto(foto);
            album.setDescricao(descricao);

            codAlbum = albumDAO.inserir(album);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codAlbum;
    }

    public Album getAlbum(int id) {
        Album dadosAlbum = new Album();
        try{
            dadosAlbum = albumDAO.buscar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosAlbum;
    }

    @Override
    public List<Album> getAlbuns() {
        List<Album> dadosAlbum = new ArrayList<>();
        try{
            dadosAlbum = albumDAO.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosAlbum;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        inputStreamImg = null;
//        if (data != null) {
//            if (resultCode == RESULT_OK) {
//                if (requestCode == PICK_IMAGE_CAMERA) {
//                    try {
//                        Uri selectedImage = data.getData();
//                        bitmap = (Bitmap) data.getExtras().get("data");
//                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
//
//                        Log.e("Activity", "Pick from Camera::>>> ");
//
//                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
//                        destination = new File(Environment.getExternalStorageDirectory() + "/" + view.getContext().getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
//                        FileOutputStream fo;
//                        try {
//                            destination.createNewFile();
//                            fo = new FileOutputStream(destination);
//                            fo.write(bytes.toByteArray());
//                            fo.close();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        imgPath = destination.getAbsolutePath();
//                        imageview.setImageBitmap(bitmap);
//                        imageview.refreshDrawableState();
//
////                        View view = navigationView.getHeaderView(0);
////                        ImageView fotoGestanteToolbar = (ImageView) view.findViewById(R.id.img_perfil);
////                        fotoGestanteToolbar.setImageBitmap(bitmap);
////                        fotoGestanteToolbar.refreshDrawableState();
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else if (requestCode == PICK_IMAGE_GALLERY) {
//                    Uri selectedImage = data.getData();
//
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(view.getContext().getContentResolver(), selectedImage);
//                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
//                        Log.e("Activity", "Pick from Gallery::>>> ");
//
//                        imgPath = getRealPathFromURI(selectedImage);
//                        destination = new File(imgPath.toString());
//                        imageview.setImageBitmap(bitmap);
//                        imageview.refreshDrawableState();
//
////                        View view = navigationView.getHeaderView(0);
////                        ImageView fotoGestanteToolbar = (ImageView) view.findViewById(R.id.img_perfil);
////                        fotoGestanteToolbar.setImageBitmap(bitmap);
////                        fotoGestanteToolbar.refreshDrawableState();
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void selecionarFotoAlbum() {
//        try {
//            PackageManager pm = view.getContext().getPackageManager();
//            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, view.getContext().getPackageName());
//            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
//                final CharSequence[] options = {"Tirar nova foto", "Selecionar nova foto", "Cancelar"};
//                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());
//                builder.setTitle("Opção de foto");
//                builder.setItems(options, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        if (options[item].equals("Tirar nova foto")) {
//                            dialog.dismiss();
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            ((OpcaoOnzeFragment)view).startActivityForResult(intent, PICK_IMAGE_CAMERA);
//                        } else if (options[item].equals("Selecionar nova foto")) {
//                            dialog.dismiss();
//                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                            pickPhoto.setType("image/*");
//                            pickPhoto.setAction(Intent.ACTION_GET_CONTENT);
//                            ((OpcaoOnzeFragment)view).startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
//                        } else if (options[item].equals("Cancelar")) {
//                            dialog.dismiss();
//                        }
//                    }
//                });
//                builder.show();
//            } else
//                Toast.makeText(view.getContext(), "Erro de permissão da câmera", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(view.getContext(), "Erro de permissão da câmera", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public String getRealPathFromURI(Uri contentUri) {
//        String[] proj = {MediaStore.Audio.Media.DATA};
//        Cursor cursor = view.getContext().getContentResolver().query(contentUri, proj, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }

}
