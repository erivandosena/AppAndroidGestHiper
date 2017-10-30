package br.com.erivando.gestanteautocuidadopa.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Projeto: gestante-autocuidado-da-pa
 * Criado por Erivando Sena
 * Data/Hora: 28 de Outubro de 2017 as 10:36h
 * Local: Fortaleza/CE
 * E-mail: erivandoramos@bol.com.br
 */

public class Permissoes {
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 100; // any code you want.

    public void checkAndRequestPermissions(Activity activity, String... permissions) {
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();

                for (String permission : permissions) {
                    perms.put(permission, PackageManager.PERMISSION_GRANTED);
                }

                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);

                    boolean allPermissionsGranted = true;
                    for (String permission1 : permissions) {
                        allPermissionsGranted = allPermissionsGranted && (perms.get(permission1) == PackageManager.PERMISSION_GRANTED);
                    }

                    if (allPermissionsGranted) {
                        Log.d(Permissoes.class.getSimpleName(), "onRequestPermissionsResult: all permissions granted");
                    } else {
                        for (String permission2 : perms.keySet())
                            if (perms.get(permission2) == PackageManager.PERMISSION_GRANTED)
                                perms.remove(permission2);

                        StringBuilder message = new StringBuilder("The app has not been granted permissions:\n\n");
                        for (String permission : perms.keySet()) {
                            message.append(permission);
                            message.append("\n");
                        }
                        message.append("\nHence, it cannot function properly." +
                                "\nPlease consider granting it this permission in phone Settings.");

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle("Permissão necessária").setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            }
        }
    }
}
