package br.com.erivando.gestanteautocuidadopa.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.erivando.gestanteautocuidadopa.R;

import static android.app.Activity.RESULT_OK;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 06 de Outubro de 2017 as 10:56h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class OpcaoOnzeFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Class fragmentClass;

    private ViewGroup rootView;

    private ImageView imageview;
    private Button btnSelectImage;
    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    public OpcaoOnzeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_opcao_onze, container, false);

        fragmentManager = getFragmentManager();

//        String textoOpcaoOnze = getResources().getString(R.string.texto_opcao_11);
//        ProcessaWebView processaWebView = new ProcessaWebView(rootView.getContext());
//        processaWebView.processaHtml((WebView)rootView.findViewById(R.id.txt_opcao_onze), textoOpcaoOnze);

        imageview = (ImageView) rootView.findViewById(R.id.imageView);

        Button btFotografia = (Button) rootView.findViewById(R.id.bt_adquirir_foto);
        btFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ActivityCompat.checkSelfPermission(rootView.getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?

                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) rootView.getContext(),
                            android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

                        Snackbar.make(getWindow().getDecorView().getRootView(), "We need permissions to do stuff.\n Please allow.", Snackbar.LENGTH_LONG)
                                .setAction("Settings", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        final Intent i = new Intent();
                                        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        i.addCategory(Intent.CATEGORY_DEFAULT);
                                        i.setData(Uri.parse("package:" + rootView.getContext().getPackageName()));
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                        rootView.getContext().startActivity(i);
                                    }
                                }).show();

                        ActivityCompat.requestPermissions((Activity) rootView.getContext(),
                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSIONS_REQUEST_READ);

                    } else {

                        ActivityCompat.requestPermissions((Activity) rootView.getContext(),
                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSIONS_REQUEST_READ);

                    }

                }else {
                    //Do your stuff here
                }





                if (ContextCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) rootView.getContext(), new String[]{Manifest.permission.CAMERA}, PICK_IMAGE_CAMERA);
                } else {
                    selecionarImagem();
                }
            }
        });

        Button btSalvarFoto = (Button) rootView.findViewById(R.id.bt_salvar_foto);
        btSalvarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btGaleria = (Button) rootView.findViewById(R.id.bt_galeria);
        btGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = GaleriaFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btAnteriorOpcaoDez = (ImageButton) rootView.findViewById(R.id.bt_ant_opcao_dez);
        btAnteriorOpcaoDez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = OpcaoDezFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btMenu = (ImageButton) rootView.findViewById(R.id.bt_menu);
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = MenuFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.commit();
            }
        });
		
        return rootView;
    }

    private void selecionarImagem() {
        try {
            PackageManager pm = getActivity().getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getActivity().getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Tirar foto", "Escolher da Galeria", "Cancelar"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(rootView.getContext());
                builder.setTitle("Opção de seleção");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Tirar foto")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (options[item].equals("Escolher da Galeria")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            pickPhoto.setType("image/*");
                            pickPhoto.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancelar")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(rootView.getContext(), "Erro de permissão da câmera", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(rootView.getContext(), "Erro de permissão da câmera", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (data != null) {
            if (resultCode == RESULT_OK) {
                if (requestCode == PICK_IMAGE_CAMERA) {
                    try {
                        Uri selectedImage = data.getData();
                        bitmap = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                        Log.e("Activity", "Pick from Camera::>>> ");

                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        destination = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                        FileOutputStream fo;
                        try {
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        imgPath = destination.getAbsolutePath();
                        imageview.setImageBitmap(bitmap);
                        imageview.refreshDrawableState();

//                        View view = navigationView.getHeaderView(0);
//                        ImageView fotoGestanteToolbar = (ImageView) view.findViewById(R.id.img_perfil);
//                        fotoGestanteToolbar.setImageBitmap(bitmap);
//                        fotoGestanteToolbar.refreshDrawableState();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (requestCode == PICK_IMAGE_GALLERY) {
                    Uri selectedImage = data.getData();

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), selectedImage);
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
                        Log.e("Activity", "Pick from Gallery::>>> ");

                        imgPath = getRealPathFromURI(selectedImage);
                        destination = new File(imgPath.toString());
                        imageview.setImageBitmap(bitmap);
                        imageview.refreshDrawableState();

//                        View view = navigationView.getHeaderView(0);
//                        ImageView fotoGestanteToolbar = (ImageView) view.findViewById(R.id.img_perfil);
//                        fotoGestanteToolbar.setImageBitmap(bitmap);
//                        fotoGestanteToolbar.refreshDrawableState();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private String bitmapParaBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private Bitmap base64ParaBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }


}
