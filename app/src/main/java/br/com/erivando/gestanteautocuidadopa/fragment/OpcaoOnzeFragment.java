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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.camera.CropImageIntentBuilder;

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

    private ImageView imageview;
    private Button btnSelectImage;
    private Bitmap bitmap;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,};

    private Permissoes permissoes;


//    public static ArrayList<Model_images> al_images = new ArrayList<>();
//    boolean boolean_folder;
//    Adapter_PhotosFolder obj_adapter;
//    GridView gv_folder;
    private static final int REQUEST_PERMISSIONS = 100;

    public static final String INTENT_KEY_FINISH_ACTIVITY_ON_SAVE_COMPLETED = "finishActivityOnSaveCompleted";


    public final static int DISPLAYWIDTH = 200;
    public final static int DISPLAYHEIGHT = 200;

    TextView titleTextView;
    ImageButton imageButton;

    Cursor cursor;
    Bitmap bmp;
    String imageFilePath;
    int fileColumn;
    int titleColumn;
    int displayColumn;

    Uri imageFileUri;

    private EditText descricao;

    public OpcaoOnzeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_opcao_onze, container, false);

        presenter = new Presenter(this);

        descricao = (EditText) rootView.findViewById(R.id.txt_descricao_foto);

        fragmentManager = getFragmentManager();

//        String textoOpcaoOnze = getResources().getString(R.string.texto_opcao_11);
//        ProcessaWebView processaWebView = new ProcessaWebView(rootView.getContext());
//        processaWebView.processaHtml((WebView)rootView.findViewById(R.id.txt_opcao_onze), textoOpcaoOnze);

        imageview = (ImageView) rootView.findViewById(imageView);

        Button btFotografia = (Button) rootView.findViewById(R.id.bt_adquirir_foto);
        btFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (ContextCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions((Activity) rootView.getContext(), new String[]{Manifest.permission.CAMERA}, PICK_IMAGE_CAMERA);
//                } else {
//                    selecionarImagem();
//                }

//                if(checkAndRequestPermissions()) {
//                    selecionarImagem();
//                }

//                verificaPermissoes();
//
                if(!hasPermissions(rootView.getContext(), PERMISSIONS)){
                    ActivityCompat.requestPermissions((Activity) rootView.getContext(), PERMISSIONS, PERMISSION_ALL);
                } else {
                    selecionarImagem();
                }

//                if (Build.VERSION.SDK_INT < 23) {
//                    selecionarImagem();
//                } else {
//                    verificaPermissoes();
//                    if(!hasPermissions(rootView.getContext(), PERMISSIONS)){
//                        ActivityCompat.requestPermissions((Activity) rootView.getContext(), PERMISSIONS, PERMISSION_ALL);
//                    } else {
//                        selecionarImagem();
//                    }
//                }

//                if ((ContextCompat.checkSelfPermission(rootView.getContext().getApplicationContext(),
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(rootView.getContext().getApplicationContext(),
//                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//                    if ((ActivityCompat.shouldShowRequestPermissionRationale((Activity) rootView.getContext(),
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale((Activity) rootView.getContext(),
//                            Manifest.permission.READ_EXTERNAL_STORAGE))) {
//                    } else {
//                        ActivityCompat.requestPermissions((Activity) rootView.getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE_GALLERY);
//                    }
//                }else {
//                    selecionarImagem();
//                }

            }
        });

        Button btSalvarFoto = (Button) rootView.findViewById(R.id.bt_salvar_foto);
        btSalvarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validacao_foto = false;
                //Bitmap fotoVazia = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                if (bitmap == null) {
                    Toast.makeText(rootView.getContext(), "Adicione uma fotografia", Toast.LENGTH_LONG).show();
                } else {
                    long status = 0L;
                    status = presenter.cadastrarAlbum(bitmapParaBase64(bitmap), descricao.getText().toString());
                    if (status > 0) {
                        Snackbar.make(v, "Foto inserida com sucesso!", Snackbar.LENGTH_LONG).show();

                        //((MainActivity) getActivity()).nomeGestanteToolbar(nome.getText().toString().toUpperCase());

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

    private void selecionarImagem() {
        try {
            PackageManager pm = getActivity().getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getActivity().getPackageName());
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
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            Log.d("ACTION_GET_CONTENT",String.valueOf(Intent.ACTION_GET_CONTENT));
                            Log.d("intenT",String.valueOf(intent));
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else
                            if (options[item].equals("Selecionar nova foto")) {

//                                Log.d("Selecionar nova foto",String.valueOf(MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
                                //dialog.dismiss();
//                                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                pickPhoto.setType("image/*");
//                                pickPhoto.setAction(Intent.ACTION_PICK);
//                                Log.d("ACTION_GET_CONTENT",String.valueOf(Intent.ACTION_GET_CONTENT));
//                                Log.d("pickPhoto",String.valueOf(pickPhoto));
//                                //startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);

                                // Determine Uri of camera image to save.
//                                final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "gestante" + File.separator);
//                                root.mkdir();
//                                final String fname = "IMG_" + System.currentTimeMillis() + ".jpg";
//                                final File sdImageMainDirectory = new File(root, fname);
//                                imageFileUri = Uri.fromFile(sdImageMainDirectory);
//
//                                Log.d("sdImageMainDirectory",String.valueOf(sdImageMainDirectory));
//                                Log.d("imageFileUri",String.valueOf(imageFileUri));

                                // Camera.
                                final List<Intent> cameraIntents = new ArrayList<Intent>();
                                final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                final PackageManager packageManager = rootView.getContext().getPackageManager();
                                final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
                                for (ResolveInfo res : listCam){
                                    final String packageName = res.activityInfo.packageName;
                                    final Intent intent = new Intent(captureIntent);
                                    intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                                    intent.setPackage(packageName);

                                    final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "gestante" + File.separator);
                                    root.mkdir();
                                    final String fname = "IMG_" + System.currentTimeMillis() + ".jpg";
                                    final File sdImageMainDirectory = new File(root, fname);
                                    imageFileUri = Uri.fromFile(sdImageMainDirectory);

                                    Log.d("sdImageMainDirectory",String.valueOf(sdImageMainDirectory));
                                    Log.d("imageFileUri",String.valueOf(imageFileUri));

                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
                                    cameraIntents.add(intent);
                                }

                                //FileSystem
                                final Intent galleryIntent = new Intent();
                                galleryIntent.setType("image/");
                                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                                // Chooser of filesystem options.
                                final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
                                // Add the camera options.
                                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{imageFileUri}));
                                //startActivityForResult(chooserIntent, PICK_IMAGE_CAMERA);
                                Log.d("Parcelable",String.valueOf(new Parcelable[]{imageFileUri}));

                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 1);


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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("dataDATA", String.valueOf(data));
       // Log.d("dataDATA1", String.valueOf(data.getAction()));
        Log.d("resultCode", String.valueOf(resultCode));
        Log.d("requestCode", String.valueOf(requestCode));

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
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
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

//    private Bitmap base64ParaBitmap(String b64) {
//        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
//    }

    private  boolean checkAndRequestPermissions() {
        int permissionCamera = ContextCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.CAMERA);
        int permissionEscrita = ContextCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionLeitura = ContextCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionEscrita != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionLeitura != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) rootView.getContext(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),PICK_IMAGE_CAMERA);
            return false;
        }
        return true;
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


    private void CheckForCoarseLocationPermission()
    {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            // ANDROID 6.0 AND UP!
            boolean accessCoarseLocationAllowed = false;
            try
            {
                // Invoke checkSelfPermission method from Android 6 (API 23 and UP)
                java.lang.reflect.Method methodCheckPermission = Activity.class.getMethod("checkSelfPermission", java.lang.String.class);
                Object resultObj = methodCheckPermission.invoke(this, Manifest.permission.CAMERA);
                int result = Integer.parseInt(resultObj.toString());
                if (result == PackageManager.PERMISSION_GRANTED)
                {
                    accessCoarseLocationAllowed = true;
                }
            }
            catch (Exception ex)
            {
            }
            if (accessCoarseLocationAllowed)
            {
                return;
            }
            try
            {
                // We have to invoke the method "void requestPermissions (Activity activity, String[] permissions, int requestCode) "
                // from android 6
                java.lang.reflect.Method methodRequestPermission = Activity.class.getMethod("requestPermissions", java.lang.String[].class, int.class);
                methodRequestPermission.invoke(rootView.getContext(), new String[] {Manifest.permission.CAMERA}, 0x12345);
            }
            catch (Exception ex)
            {
            }
        }
    }

    private void verificaPermissoes() {
        permissoes = new Permissoes();
        permissoes.checkAndRequestPermissions((Activity)rootView.getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        permissoes.onRequestPermissionsResult((Activity)rootView.getContext(), requestCode, permissions, grantResults);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        //fn_imagespath();
                        selecionarImagem();
                    } else {
                        Toast.makeText((Activity) rootView.getContext(), "O aplicativo não tinha permissão para ler ou escrever no seu armazenamento. Por isso, não pode funcionar corretamente. Considere conceder-lhe essa permissão", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

//    public ArrayList<Model_images> fn_imagespath() {
//        al_images.clear();
//
//        int int_position = 0;
//        Uri uri;
//        Cursor cursor;
//        int column_index_data, column_index_folder_name;
//
//        String absolutePathOfImage = null;
//        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
//
//        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
//        cursor = rootView.getContext().getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");
//
//        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//        while (cursor.moveToNext()) {
//            absolutePathOfImage = cursor.getString(column_index_data);
//            Log.e("Column", absolutePathOfImage);
//            Log.e("Folder", cursor.getString(column_index_folder_name));
//
//            for (int i = 0; i < al_images.size(); i++) {
//                if (al_images.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
//                    boolean_folder = true;
//                    int_position = i;
//                    break;
//                } else {
//                    boolean_folder = false;
//                }
//            }
//
//
//            if (boolean_folder) {
//
//                ArrayList<String> al_path = new ArrayList<>();
//                al_path.addAll(al_images.get(int_position).getAl_imagepath());
//                al_path.add(absolutePathOfImage);
//                al_images.get(int_position).setAl_imagepath(al_path);
//
//            } else {
//                ArrayList<String> al_path = new ArrayList<>();
//                al_path.add(absolutePathOfImage);
//                Model_images obj_model = new Model_images();
//                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
//                obj_model.setAl_imagepath(al_path);
//
//                al_images.add(obj_model);
//
//
//            }
//
//
//        }
//
//
//        for (int i = 0; i < al_images.size(); i++) {
//            Log.e("FOLDER", al_images.get(i).getStr_folder());
//            for (int j = 0; j < al_images.get(i).getAl_imagepath().size(); j++) {
//                Log.e("FILE", al_images.get(i).getAl_imagepath().get(j));
//            }
//        }
//        obj_adapter = new Adapter_PhotosFolder(getApplicationContext(),al_images);
//        gv_folder.setAdapter(obj_adapter);
//        return al_images;
//    }


//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public static String getPath(Context context, Uri uri) {
//        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
//
//        // DocumentProvider
//        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
//            // ExternalStorageProvider
//            if (isExternalStorageDocument(uri)) {
//                final String docId = DocumentsContract.getDocumentId(uri);
//                final String[] split = docId.split(":");
//                final String type = split[0];
//
//                if ("primary".equalsIgnoreCase(type)) {
//                    return Environment.getExternalStorageDirectory() + "/" + split[1];
//                }
//                // TODO handle non-primary volumes
//            }
//            // DownloadsProvider
//            else if (isDownloadsDocument(uri)) {
//                final String id = DocumentsContract.getDocumentId(uri);
//                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
//                return getDataColumn(context, contentUri, null, null);
//            }
//            // MediaProvider
//            else
//            if (isMediaDocument(uri)) {
//                final String docId = DocumentsContract.getDocumentId(uri);
//                final String[] split = docId.split(":");
//                final String type = split[0];
//                Uri contentUri = null;
//                if ("image".equals(type)) {
//                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                } else if ("video".equals(type)) {
//                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//                } else if ("audio".equals(type)) {
//                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                }
//                final String selection = "_id=?";
//                final String[] selectionArgs = new String[] {split[1]};
//                return getDataColumn(context, contentUri, selection, selectionArgs);
//            }
//        }
//        // MediaStore (and general)
//        else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            // Return the remote address
//            if (isGooglePhotosUri(uri))
//                return uri.getLastPathSegment();
//            return getDataColumn(context, uri, null, null);
//        }
//        // File
//        else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            return uri.getPath();
//        }
//        return null;
//    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public String getPath(Uri photoUri) {
        String filePath = MediaStore.Images.Media.DATA;
        if (photoUri != null){
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = rootView.getContext().getContentResolver().query(photoUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            filePath = cursor.getString(columnIndex);
            cursor.close();
        }
        return filePath;
    }

    private Bitmap getBitmap(String imageFilePath) {
        // Load up the image's dimensions not the image itself
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) DISPLAYHEIGHT);
        int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth / (float) DISPLAYWIDTH);

        Log.v("HEIGHTRATIO", "" + heightRatio);
        Log.v("WIDTHRATIO", "" + widthRatio);

        // If both of the ratios are greater than 1, one of the sides of
        // the image is greater than the screen
        if (heightRatio > 1 && widthRatio > 1) {
            if (heightRatio > widthRatio) {
                // Height ratio is larger, scale according to it
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                // Width ratio is larger, scale according to it
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }

        // Decode it for real
        bmpFactoryOptions.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);

        return bmp;
    }
}
