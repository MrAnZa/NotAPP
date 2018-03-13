package zamora.notapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by AndresZamora on 26/10/2017.
 */

public class SemestreFragment extends Fragment {
    RelativeLayout ContenedorSemestre;
    EditText eTc1,eTc2,eTc3;
    TextView eTsr;
    Button btnS;
    public SemestreFragment(){

    }
    public static SemestreFragment newInstance(){
        SemestreFragment semestrefragment= new SemestreFragment();
        Bundle args=new Bundle();
        semestrefragment.setArguments(args);
        return semestrefragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_semestre,container,false);
        eTc1=(EditText)(vista.findViewById(R.id.etc1));
        eTc2=(EditText)(vista.findViewById(R.id.etc2));
        eTc3=(EditText)(vista.findViewById(R.id.etc3));
        eTsr=(TextView)(vista.findViewById(R.id.etsr));
        btnS=(Button)(vista.findViewById(R.id.btnSemestre));
        ContenedorSemestre=(RelativeLayout)vista.findViewById(R.id.ContenedorSemestre);
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float c1,c2,c3,cf;
                try {
                    if(Float.parseFloat(eTc1.getText().toString())<=5f&&Float.parseFloat(eTc2.getText().toString())<=5f&&Float.parseFloat(eTc3.getText().toString())<=5f){
                    c1 = (Float.parseFloat(eTc1.getText().toString())) * (0.3f);
                    c2 = (Float.parseFloat(eTc2.getText().toString())) * (0.3f);
                    c3 = (Float.parseFloat(eTc3.getText().toString())) * (0.4f);
                    cf = c1 + c2 + c3;
                    eTsr.setText(String.valueOf(cf));
                    Toast.makeText(getActivity().getApplicationContext(), "Nota Final del Semetre: " + cf, Toast.LENGTH_LONG).show();
                    }else{
                        FragmentManager fg= getFragmentManager();
                        AlertAbout ad=new AlertAbout();
                       ad.setMsn("Error la Nota No de ser Mayor que 5");
                       ad.show(fg,"Alerta");
                    }
                }catch (NumberFormatException nfe){
                    Toast.makeText(getActivity().getApplicationContext(), "Error en Notas", Toast.LENGTH_LONG).show();
                }
            }
        });
        return vista;
    }

}
