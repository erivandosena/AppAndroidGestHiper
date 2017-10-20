package br.com.erivando.gestanteautocuidadopa.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.erivando.gestanteautocuidadopa.R;
import br.com.erivando.gestanteautocuidadopa.activity.MainActivity;
import br.com.erivando.gestanteautocuidadopa.mvp.MainMVP;
import br.com.erivando.gestanteautocuidadopa.mvp.Presenter;
import br.com.erivando.gestanteautocuidadopa.util.Validador;

/**
 * Projeto: GestanteAutocuidadoPA
 * Criado por Erivando Sena
 * Data/Hora: 13 de Outubro de 2017 as 15:20h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class AfericaoPAFragment extends Fragment implements MainMVP.view {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Class fragmentClass;
    private Presenter presenter;
    private EditText sistolica;
    private EditText diastolica;

    public AfericaoPAFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_afericao_pa, container, false);

        presenter = new Presenter(this);

        sistolica = (EditText) rootView.findViewById(R.id.txt_sistolica_pa);
        diastolica = (EditText) rootView.findViewById(R.id.txt_diastolica_pa);

        fragmentManager = getFragmentManager();

        Button btRegistrar = (Button) rootView.findViewById(R.id.bt_registro_afericao_pa);
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valida_pas = false;
                boolean valida_pad = false;

                valida_pas = Validador.validaNotNull(sistolica, "Informe o valor da sistólica");
                valida_pad = Validador.validaNotNull(diastolica, "Informe o valor da diastólica");

                Log.d("valida_pas", String.valueOf(valida_pas));
                Log.d("valida_pad", String.valueOf(valida_pad));

                if (valida_pas && valida_pad) {
                    long status = 0L;
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
                    String dataHoraAtual=dateFormat.format(new Date());

                    Log.d("data", dateFormat.format(new Date()).toString());

                    status = presenter.cadastrarDiario(sistolica.getText().toString(), diastolica.getText().toString(), dateFormat.format(new Date()).toString());
                    Log.d("status", String.valueOf(status));
                    if (status > 0) {
                        Snackbar.make(v, "Registrado no diário com sucesso!", Snackbar.LENGTH_LONG).show();

                        AlertDialog.Builder aDialogo  = new AlertDialog.Builder(getActivity());
                        aDialogo.setCancelable(false);
                        aDialogo.setTitle("Your Title");
                        aDialogo.setIcon(R.mipmap.ic_launcher);
                        aDialogo.setMessage("R.string.dialog_message").setTitle("R.string.dialog_title");

                        aDialogo.setPositiveButton("R.string.ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button

                                fragmentClass = OpcaoOitoFragment.class;
                                try {
                                    fragment = (Fragment) fragmentClass.newInstance();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

                            }
                        });
                        aDialogo.setNegativeButton("R.string.cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog

                                fragmentClass = MenuFragment.class;
                                try {
                                    fragment = (Fragment) fragmentClass.newInstance();
                                    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    dialog.cancel();
                                }
                            }
                        });

                        AlertDialog dialog = aDialogo.create();
                        dialog.show();
                    }
                }
            }
        });

        Button btOpcaoSeis = (Button) rootView.findViewById(R.id.bt_como_aferir_pressao);
        btOpcaoSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoSeisFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoCinco = (Button) rootView.findViewById(R.id.bt_observar_afericao);
        btOpcaoCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoCincoFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        Button btOpcaoTres = (Button) rootView.findViewById(R.id.bt_deve_saber);
        btOpcaoTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentClass = OpcaoTresFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        ImageButton btAnteriorOpcaoSete = (ImageButton) rootView.findViewById(R.id.bt_ant_opcao_sete);
        btAnteriorOpcaoSete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentClass = OpcaoSeteFragment.class;
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
}
