package br.com.erivando.gestanteautocuidadopa.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
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

        sistolica = rootView.findViewById(R.id.txt_sistolica_pa);
        diastolica = rootView.findViewById(R.id.txt_diastolica_pa);

        fragmentManager = getFragmentManager();

        Button btRegistrar = rootView.findViewById(R.id.bt_registro_afericao_pa);
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valida_pas = false;
                boolean valida_pad = false;

                valida_pas = Validador.validaNotNull(sistolica, getResources().getString(R.string.texto_valida_campo_pas));
                valida_pad = Validador.validaNotNull(diastolica, getResources().getString(R.string.texto_valida_campo_pad));

                if (sistolica.length() < 3 || sistolica.length() > 3) {
                    valida_pas = Validador.validaValorPA(sistolica, getResources().getString(R.string.texto_valida_campo_valor_pas), "sistolica");
                } else
                if (diastolica.length() < 2 || diastolica.length() > 2) {
                    valida_pad = Validador.validaValorPA(diastolica, getResources().getString(R.string.texto_valida_campo_valor_pad), "diastolica");
                } else

                if (valida_pas && valida_pad) {
                    long status = 0L;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
                    status = presenter.cadastrarDiario(sistolica.getText().toString(), diastolica.getText().toString(), dateFormat.format(new Date()));
                    if (status > 0) {
                        Snackbar.make(v, getResources().getString(R.string.texto_sucesso_cadastro), Snackbar.LENGTH_LONG).show();
                        AlertDialog.Builder aDBuilder = new AlertDialog.Builder(getActivity());
                        aDBuilder.setCancelable(false);
                        aDBuilder.setIcon(R.mipmap.ic_launcher_round);
                        aDBuilder.setPositiveButton(getResources().getString(R.string.texto_botao_dialogo_planilha), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                fragmentClass = OpcaoOitoFragment.class;
                                try {
                                    fragment = (Fragment) fragmentClass.newInstance();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

                            }
                        });
                        aDBuilder.setNegativeButton(getResources().getString(R.string.texto_botao_dialogo_menu), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

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

                        AlertDialog aDialogo = null;
                        int pas = Integer.valueOf(sistolica.getText().toString());
                        int pad = Integer.valueOf(diastolica.getText().toString());
                        switch (verificaAlertaPA(pas, pad)) {
                            case 1:
                                aDBuilder.setMessage(Html.fromHtml(getResources().getText(R.string.texto_alerta_pa_120_80).toString())).setTitle(getResources().getString(R.string.texto_html_titulo_dialogo_pa_normal));
                                if (aDialogo == null)
                                    aDialogo = aDBuilder.create();
                                if (!aDialogo.isShowing())
                                    aDialogo.show();
                                aDialogo.getWindow().getDecorView().getBackground().setColorFilter(new LightingColorFilter(0xFF000000, 0xA5C388));
                                break;
                            case 2:
                                aDBuilder.setMessage(Html.fromHtml(getResources().getText(R.string.texto_alerta_pa_121_139).toString())).setTitle(getResources().getString(R.string.texto_html_titulo_dialogo_pa_anormal));
                                if (aDialogo == null)
                                    aDialogo = aDBuilder.create();
                                if (!aDialogo.isShowing())
                                    aDialogo.show();
                                aDialogo.getWindow().getDecorView().getBackground().setColorFilter(new LightingColorFilter(0xFF000000, 0xF3E882));
                                break;
                            case 3:
                                aDBuilder.setMessage(Html.fromHtml(getResources().getText(R.string.texto_alerta_pa_140_159).toString())).setTitle(getResources().getString(R.string.texto_html_titulo_dialogo_pa_anormal));
                                if (aDialogo == null)
                                    aDialogo = aDBuilder.create();
                                if (!aDialogo.isShowing())
                                    aDialogo.show();
                                aDialogo.getWindow().getDecorView().getBackground().setColorFilter(new LightingColorFilter(0xFF000000, 0xFFDB84));
                                break;
                            case 4:
                                aDBuilder.setMessage(Html.fromHtml(getResources().getText(R.string.texto_alerta_pa_160_179).toString())).setTitle(getResources().getString(R.string.texto_html_titulo_dialogo_pa_anormal));
                                if (aDialogo == null)
                                    aDialogo = aDBuilder.create();
                                if (!aDialogo.isShowing())
                                    aDialogo.show();
                                aDialogo.getWindow().getDecorView().getBackground().setColorFilter(new LightingColorFilter(0xFF000000, 0xFFB67B));
                                break;
                            case 5:
                                aDBuilder.setMessage(Html.fromHtml(getResources().getText(R.string.texto_alerta_pa_180_110).toString())).setTitle(getResources().getString(R.string.texto_html_titulo_dialogo_pa_anormal));
                                if (aDialogo == null)
                                    aDialogo = aDBuilder.create();
                                if (!aDialogo.isShowing())
                                    aDialogo.show();
                                aDialogo.getWindow().getDecorView().getBackground().setColorFilter(new LightingColorFilter(0xFF000000, 0xFD8B8B));
                                break;
                            default:
                                if ((pas >= 100 && pas < 120) && (pad >= 60 && pad < 80)) {
                                    aDBuilder.setMessage(Html.fromHtml(getResources().getText(R.string.texto_alerta_pa_120_80).toString())).setTitle(getResources().getString(R.string.texto_html_titulo_dialogo_pa_normal));
                                    if (aDialogo == null)
                                        aDialogo = aDBuilder.create();
                                    if (!aDialogo.isShowing())
                                        aDialogo.show();
                                    aDialogo.getWindow().getDecorView().getBackground().setColorFilter(new LightingColorFilter(0xFF000000, 0xA5C388));
                                } else if ((pas >= 0 && pas <= 100) || (pad >= 0 && pad <= 59)) {
                                    aDBuilder.setMessage(Html.fromHtml(getResources().getText(R.string.texto_alerta_pa_baixa).toString())).setTitle(getResources().getString(R.string.texto_html_titulo_dialogo_pa_anormal));
                                    if (aDialogo == null)
                                        aDialogo = aDBuilder.create();
                                    if (!aDialogo.isShowing())
                                        aDialogo.show();
                                    aDialogo.getWindow().getDecorView().getBackground().setColorFilter(new LightingColorFilter(0xFF000000, 0xFD8B8B));
                                }
                        }
                        aDialogo.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#5d353b"));
                        aDialogo.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.parseColor("#5d353b"));
                    }
                }
            }
        });

        Button btOpcaoSeis = rootView.findViewById(R.id.bt_como_aferir_pressao);
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

        Button btOpcaoCinco = rootView.findViewById(R.id.bt_observar_afericao);
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

        Button btOpcaoTres = rootView.findViewById(R.id.bt_deve_saber);
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

        ImageButton btAnteriorOpcaoSete = rootView.findViewById(R.id.bt_ant_opcao_sete);
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

        ImageButton btMenu = rootView.findViewById(R.id.bt_menu);
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

    private int verificaAlertaPA(int pas, int pad) {
        int resultado = 0;

        if ((pas == 120) || (pad == 80)) {
            resultado = 1;
        }
        if ((pas >= 121 && pas <= 139) || (pad >= 81 && pad <= 89)) {
            resultado = 2;
        }
        if ((pas >= 140 && pas <= 159) || (pad >= 90 && pad <= 99)) {
            resultado = 3;
        }
        if ((pas >= 160 && pas <= 179) || (pad >= 100 && pad <= 109)) {
            resultado = 4;
        }
        if ((pas >= 180) || (pad >= 110)) {
            resultado = 5;
        }

        return resultado;
    }

}
