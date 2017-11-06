package br.com.erivando.gestanteautocuidadopa.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.erivando.gestanteautocuidadopa.BuildConfig;
import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.activity.SlideShowActivity;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;
import br.com.erivando.gestanteautocuidadopa.util.ConverteBase64Task;
import br.com.erivando.gestanteautocuidadopa.util.Utilitarios;

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

    private static final int REQUEST_IMG_CAMERA = 1;
    private static final int REQUEST_IMG_GALERIA = 2;
    private static final int TODAS_PERMISSOES = 1;
    private String[] PERMISSOES = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    private ImageView imagemViewFoto;
    private File arquivoImagem;
    private Bitmap imagemBitmapFoto;

    public Presenter presenter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Class fragmentClass;
    private ViewGroup rootView;
    private EditText descricao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_opcao_onze, container, false);
        presenter = new Presenter(this);
        descricao = (EditText) rootView.findViewById(R.id.txt_descricao_foto);
        fragmentManager = getFragmentManager();
        imagemViewFoto = (ImageView) rootView.findViewById(imageView);
        imagemViewFoto.setImageResource(R.drawable.ic_launcher_round_splash);
        imagemViewFoto.setAlpha(new Float(0.3));

        Button btFotografia = (Button) rootView.findViewById(R.id.bt_adquirir_foto);
        btFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hasPermissoes(rootView.getContext(), PERMISSOES)){
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
                if (imagemBitmapFoto == null) {
                    Toast.makeText(rootView.getContext(), getResources().getString(R.string.texto_aviso_cadastro_foto), Toast.LENGTH_LONG).show();
                } else {
                    long status = 0L;
                    status = presenter.cadastrarAlbum(Utilitarios.bitmapParaBase64(imagemBitmapFoto), descricao.getText().toString());
                    if (status > 0) {
                        Snackbar.make(v, getResources().getString(R.string.texto_sucesso_cadastro), Snackbar.LENGTH_LONG).show();
                        imagemViewFoto.setImageResource(R.drawable.ic_launcher_round_splash);
                        imagemViewFoto.setAlpha(new Float(0.3));
                        imagemBitmapFoto = null;
                        descricao.setText(null);
                    } else {
                        Snackbar.make(v, getResources().getString(R.string.texto_erro_cadastro), Snackbar.LENGTH_LONG).show();
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
            PackageManager pm = rootView.getContext().getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, rootView.getContext().getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {getResources().getString(R.string.texto_cadastro_opcao_camera), getResources().getString(R.string.texto_cadastro_opcao_imagens), getResources().getString(R.string.texto_botao_dialogo_cancela)};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(rootView.getContext());
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle(getResources().getString(R.string.texto_botao_dialogo_titulo));
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals(getResources().getString(R.string.texto_cadastro_opcao_camera))) {
                            dialog.dismiss();

                            imagemIntentCamera();

                        } else if (options[item].equals(getResources().getString(R.string.texto_cadastro_opcao_imagens))) {
                            dialog.dismiss();

                            imagemIntentGaleria();

                        } else if (options[item].equals(getResources().getString(R.string.texto_botao_dialogo_cancela))) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(rootView.getContext(), getResources().getString(R.string.texto_aviso_permissao_camera), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(rootView.getContext(), getResources().getString(R.string.texto_aviso_permissao_camera), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void imagemIntentCamera() {
        Intent intentImagem = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentImagem.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intentImagem.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (rootView.getContext().getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            if (intentImagem.resolveActivity(rootView.getContext().getPackageManager()) != null) {
                File arquivo = new File(rootView.getContext().getObbDir(), getResources().getString(R.string.texto_nome_local));
                arquivoImagem = criaArquivoImagem(arquivo);
                if (arquivoImagem != null) {
                    Uri photoURI = FileProvider.getUriForFile(rootView.getContext(), BuildConfig.APPLICATION_ID, arquivoImagem);
                    List<ResolveInfo> resolvedIntentActivities = rootView.getContext().getPackageManager().queryIntentActivities(intentImagem, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
                        String packageName = resolvedIntentInfo.activityInfo.packageName;
                        rootView.getContext().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    intentImagem.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                }
                startActivityForResult(intentImagem, REQUEST_IMG_CAMERA);
            }
        } else
            Toast.makeText(rootView.getContext(), getResources().getString(R.string.texto_aviso_camera_ausente), Toast.LENGTH_LONG).show();
    }


    private void imagemIntentGaleria() {
        Intent intentImagem = new Intent(Intent.ACTION_PICK);
        intentImagem.setAction(Intent.ACTION_GET_CONTENT);
        intentImagem.setType("image/*");
        startActivityForResult(intentImagem, REQUEST_IMG_GALERIA);
    }

    private File criaArquivoImagem(File arquivo) {
        File arqImagem = rootView.getContext().getCacheDir();
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        try {
            if (!arquivo.exists()) {
                if (!arquivo.mkdirs()) {
                    arquivo = arqImagem;
                }
            }
            arqImagem = File.createTempFile(
                    imageFileName,  /* prefixo */
                    ".jpg",         /* sufixo */
                    arquivo         /* diretorio */
            );
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return arqImagem;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final int retornoRequestCode = requestCode & 0x0000ffff;
        try {
            if (resultCode == RESULT_OK) {
                if (retornoRequestCode == REQUEST_IMG_CAMERA) {
                    imagemViewFoto.setAlpha(new Float(1.0));
                    Uri uriImagem = Uri.fromFile(arquivoImagem);
                    if (arquivoImagem.length() > 0) {
                        ConverteBase64Task task = new ConverteBase64Task(arquivoImagem);
                        task.setImageCompressiorListner(new ConverteBase64Task.ImageCompressiorListner() {
                            @Override
                            public void onImageCompressed(Bitmap bitmap) {
                                imagemBitmapFoto = bitmap;
                                imagemViewFoto.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onError() {
                                Toast.makeText(rootView.getContext(), getResources().getString(R.string.texto_aviso_erro_processo_imagem), Toast.LENGTH_LONG).show();
                            }
                        });
                        task.execute();
                        /**
                         * ScanFile para que ele apareça na Galeria
                         */
                        MediaScannerConnection.scanFile(rootView.getContext(),
                                new String[]{arquivoImagem.getAbsolutePath()},
                                new String[]{"image/jpg"},
                                new MediaScannerConnection.OnScanCompletedListener() {
                                    public void onScanCompleted(String path, Uri uri) {
                                        try {
                                            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                            //File f = new File(path);
                                            //Uri contentUri = Uri.fromFile(f);
                                            mediaScanIntent.setData(uri);
                                            rootView.getContext().sendBroadcast(mediaScanIntent);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(rootView.getContext(), getResources().getString(R.string.texto_aviso_erro_aquisicao_imagem), Toast.LENGTH_LONG).show();
                    }
                } else if (retornoRequestCode == REQUEST_IMG_GALERIA) {
                    if (data != null) {
                        imagemViewFoto.setAlpha(new Float(1.0));
                        Uri uriImagem = data.getData();
                        imagemViewFoto.setImageURI(uriImagem);
                        imagemBitmapFoto = ((BitmapDrawable)imagemViewFoto.getDrawable()).getBitmap();
                    } else
                        Toast.makeText(rootView.getContext(), getResources().getString(R.string.texto_aviso_erro_aquisicao_imagem), Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception ex) {
            ex.getStackTrace();
            Toast.makeText(rootView.getContext(), getResources().getString(R.string.texto_aviso_erro_sistema_desatualizado), Toast.LENGTH_LONG).show();
        }
    }

    public static boolean hasPermissoes(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
