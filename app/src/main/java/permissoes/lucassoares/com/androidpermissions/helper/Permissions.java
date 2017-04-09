package permissoes.lucassoares.com.androidpermissions.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissions{

    public static boolean ActivePermissions(Activity activity, String[] permissions, int requestCode){

        //Verificando a versão do Android
        if(Build.VERSION.SDK_INT >= 23){

            List<String> permissionsList = new ArrayList<String>();

            //Percorrer todas as permissões passadas, para saber se já está liberada
            for(String permission : permissions){
               Boolean validaPermissao =  ContextCompat.checkSelfPermission(activity,permission) == PackageManager.PERMISSION_GRANTED;
                if(!validaPermissao) permissionsList.add(permission);
            }

            //Caso a lista de permissões esteja vazia, não precisa solicitar
            if(permissionsList.isEmpty()) return true;

            String[] newPermissions = new String[permissionsList.size()];
            permissionsList.toArray(newPermissions);

            //Solicitando a permissão
            ActivityCompat.requestPermissions(activity, newPermissions, requestCode);
        }
        return true;
    }
}
