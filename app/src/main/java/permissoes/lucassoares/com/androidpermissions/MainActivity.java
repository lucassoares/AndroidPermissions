package permissoes.lucassoares.com.androidpermissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import permissoes.lucassoares.com.androidpermissions.helper.Permissions;

public class MainActivity extends AppCompatActivity{

    private String[] permissions = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Permissions.ActivePermissions(this, permissions, 1);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[]grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int result : grantResults){
            if(result == PackageManager.PERMISSION_DENIED){
                activePermissionAlert();
            }
        }
    }

    private void activePermissionAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões negadas");
        builder.setMessage("Para utilizar este app, é necessário aceitar as permissões");

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
