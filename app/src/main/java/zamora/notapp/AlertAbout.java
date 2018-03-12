package zamora.notapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by AndresZamora on 28/10/2017.
 */

public class AlertAbout extends DialogFragment{
    String msn;
    public  AlertAbout(){

    }
    public void setMsn(String msg){
        this.msn=msg;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        //adb.setMessage("Desarrollado Por Andrés Felipe Zamora Luna")
        adb.setMessage(msn)
        .setTitle("Acerca De...")
                .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return adb.create();
    }
}
