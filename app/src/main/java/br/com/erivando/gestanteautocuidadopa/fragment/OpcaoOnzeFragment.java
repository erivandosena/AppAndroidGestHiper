package br.com.erivando.gestanteautocuidadopa.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.camera.CropImageIntentBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.erivando.gestanteautocuidadopa.BuildConfig;
import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.activity.SlideShowActivity;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;
import br.com.erivando.gestanteautocuidadopa.util.MediaUtil;
import br.com.erivando.gestanteautocuidadopa.util.Permissoes;

import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;
import static br.com.erivando.gestanteautocuidadopa.R.id.imageView;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 06 de Outubro de 2017 as 10:56h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class OpcaoOnzeFragment extends Fragment implements MainMVP.view {

    public Presenter presenter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Class fragmentClass;
    private ViewGroup rootView;

//    private ImageView imageview;
//    private Button btnSelectImage;
//    private Bitmap bitmap;
//    private File destination = null;
//    private InputStream inputStreamImg;
//    private String imgPath = null;
//    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

//    int PERMISSION_ALL = 1;
//    String[] PERMISSIONS = {
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.CAMERA,};

//    private Permissoes permissoes;
//
//    private static final int REQUEST_PERMISSIONS = 100;
//
//    public static final String INTENT_KEY_FINISH_ACTIVITY_ON_SAVE_COMPLETED = "finishActivityOnSaveCompleted";
//
//
//    public final static int DISPLAYWIDTH = 200;
//    public final static int DISPLAYHEIGHT = 200;
//
//
//    Uri imageFileUri;

    private EditText descricao;

    private static final int REQUEST_IMG_CAMERA = 1;
    private static final int REQUEST_IMG_GALERIA = 2;
    private int TODAS_PERMISSOES = 1;
    private String[] PERMISSOES = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
    };
    private Bitmap imagemBitmapFoto;
    private ImageView imagemViewFoto;
    String localImagem;
    private File arquivoImagem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_opcao_onze, container, false);
        presenter = new Presenter(this);
        descricao = (EditText) rootView.findViewById(R.id.txt_descricao_foto);
        fragmentManager = getFragmentManager();
        imagemViewFoto = (ImageView) rootView.findViewById(R.id.imageView);
        arquivoImagem = new File(rootView.getContext().getCacheDir(), "foto.jpg");

        Button btFotografia = (Button) rootView.findViewById(R.id.bt_adquirir_foto);
        btFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hasPermissions(rootView.getContext(), PERMISSOES)){
                    ActivityCompat.requestPermissions((Activity)getContext(), PERMISSOES, TODAS_PERMISSOES);
                } else {
                    selecionarImagem();
                }
            }
        });

        Button btSalvarFoto = (Button) rootView.findViewById(R.id.bt_salvar_foto);
        btSalvarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!possuiImagem(imagemViewFoto)) {
                    Toast.makeText(rootView.getContext(), "Adicione uma fotografia", Toast.LENGTH_LONG).show();
                } else {
                    long status = 0L;
                    status = presenter.cadastrarAlbum(bitmapParaBase64(imagemBitmapFoto), descricao.getText().toString());
                    if (status > 0) {
                        Snackbar.make(v, "Foto inserida com sucesso!", Snackbar.LENGTH_LONG).show();
                        //((MainActivity) getActivity()).nomeGestanteToolbar(nome.getText().toString().toUpperCase());
                        imagemViewFoto.setImageBitmap(null);
                    } else {
                        Snackbar.make(v, "Problema ao salvar as informações!", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        Button btGaleria = (Button) rootView.findViewById(R.id.bt_galeria);
        btGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), SlideShowActivity.class);
                startActivity(intent);
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

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean possuiImagem(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);
        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }
        return hasImage;
    }

    private void imagemIntentCamera() {
        Intent intentImagem = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            if (intentImagem.resolveActivity(rootView.getContext().getPackageManager()) != null) {
                arquivoImagem = criaArquivoImagem();
                if (arquivoImagem != null) {
                    Uri photoURI = FileProvider.getUriForFile(rootView.getContext(), BuildConfig.APPLICATION_ID + ".provider", criaArquivoImagem());
                    List<ResolveInfo> resInfoList = rootView.getContext().getPackageManager().queryIntentActivities(intentImagem, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        rootView.getContext().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    intentImagem.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                }
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        startActivityForResult(intentImagem, REQUEST_IMG_CAMERA);
    }

    private void selecionarImagem() {
        try {
            PackageManager pm = rootView.getContext().getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, rootView.getContext().getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Tirar nova foto", "Selecionar nova foto", "Cancelar"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(rootView.getContext());
                builder.setTitle("Opção de foto");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        Log.d("DialogInterface dialog",String.valueOf(dialog) + " "+String.valueOf(item));
                        if (options[item].equals("Tirar nova foto")) {
                            Log.d("Tirar nova foto",String.valueOf(MediaStore.ACTION_IMAGE_CAPTURE));
                            dialog.dismiss();

                            imagemIntentCamera();

                        } else
                        if (options[item].equals("Selecionar nova foto")) {
                            Log.d("Selecionar nova foto",String.valueOf(MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
                            dialog.dismiss();

                            imagemIntentGaleria();

                        } else
                        if (options[item].equals("Cancelar")) {
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

    private void imagemIntentGaleria() {
        Intent intentImagem = new Intent();
        if (Build.VERSION.SDK_INT <= 20) {
            intentImagem.setType("image/*");
            intentImagem.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intentImagem = new Intent(Intent.ACTION_PICK);
            intentImagem.setType("image/*");
        }
        List<ResolveInfo> resInfoList = rootView.getContext().getPackageManager().queryIntentActivities(intentImagem, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            rootView.getContext().grantUriPermission(packageName, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        startActivityForResult(intentImagem, REQUEST_IMG_GALERIA);

    }

    private File criaArquivoImagem() throws IOException {
        File localArquivo = new File(Environment.getExternalStorageDirectory(), "Gestante");
        File imagem = null;
        try {
            if (!localArquivo.exists()) {
                if (!localArquivo.mkdirs()) {
                    localArquivo = new File(Environment.getExternalStorageDirectory(), File.separator);
                }
            }
            String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
            String imageFileName = "IMG_" + timeStamp + "_";
            imagem = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    localArquivo /* directory */
            );
            localImagem = "file:" + imagem.getAbsolutePath();
        } catch (Exception ex) {
            ex.getStackTrace();
            return null;
        }
        return imagem;
    }

    private String bitmapParaBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        Log.d("requestCode", String.valueOf(requestCode));
        Log.d("resultCode", String.valueOf(resultCode));
        Log.d("dataDATA", String.valueOf(data));

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMG_CAMERA) {
                try {
                    // Show the thumbnail on ImageView
                    Uri uriImagem = Uri.parse(localImagem);
                    arquivoImagem = new File(uriImagem.getPath());
                    //imagemViewFoto.setImageURI(uriImagem);

                    InputStream ims = new FileInputStream(arquivoImagem);
                    imagemBitmapFoto = BitmapFactory.decodeStream(ims);

                    //if (!possuiImagem(imagemViewFoto))
                    imagemViewFoto.setImageBitmap(imagemBitmapFoto);

                    // ScanFile para que ele apareça na Galeria
                    MediaScannerConnection.scanFile(rootView.getContext(), new String[]{uriImagem.getPath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            if (!possuiImagem(imagemViewFoto))
                                imagemViewFoto.setImageDrawable(Drawable.createFromPath(new File(path).getAbsolutePath()));
                            if (!possuiImagem(imagemViewFoto))
                                imagemViewFoto.setImageURI(uri);
                        }
                    });
                } catch (Exception ex) {
                    ex.getStackTrace();
                }
            } else if (requestCode == REQUEST_IMG_GALERIA) {
                if (data != null) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = rootView.getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    imagemBitmapFoto = BitmapFactory.decodeFile(picturePath);
                    imagemViewFoto.setImageBitmap(imagemBitmapFoto);
                } else
                    Toast.makeText(rootView.getContext(), "Sua versão do Android é incompatível com esta funcionalidade, atualize seu sistema Android.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
