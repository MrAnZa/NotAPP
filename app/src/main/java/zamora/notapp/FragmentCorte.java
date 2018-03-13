package zamora.notapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.text.InputType;
import android.util.TypedValue;
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

public class FragmentCorte extends Fragment {
    RelativeLayout ContenedorCorte;
    EditText etNP;
    EditText etN;
    TextView tvN;
    TextView tvR;
    AlertAbout ad=new AlertAbout();
    protected EditText[] etns;
public FragmentCorte(){

}
public static FragmentCorte newInstance(){
    FragmentCorte fragmentcorte= new FragmentCorte();
    Bundle args=new Bundle();
    fragmentcorte.setArguments(args);
    return fragmentcorte;
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.corte_fragment,container,false);
        etN=(EditText)(vista.findViewById(R.id.etN));
        tvN=(TextView)(vista.findViewById(R.id.tvN));
        tvR=(TextView)(vista.findViewById(R.id.tvRes));
        FloatingActionButton fab = (FloatingActionButton) vista.findViewById(R.id.fab);
        ContenedorCorte=(RelativeLayout) vista.findViewById(R.id.ContenedorCorte);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContenedorCorte.getChildCount()>4) {
                    int x=ContenedorCorte.getChildCount();
                    ContenedorCorte.removeViewsInLayout(4,(x-4));
                    crear(etN);
                }else{
                    crear(etN);
                }
            }
        });
        return vista;
    }

    public void crear(EditText n){
        try {
        etns = new EditText[Integer.parseInt(n.getText().toString())];
        //ArrayList<editText> lista=new ArrayList<editText>();
        float y = (etN.getY())+60f;
        for (int i = 0; i < Integer.parseInt(n.getText().toString()); i++) {
            etns[i] = new EditText(getActivity().getApplicationContext());
            etns[i].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            etns[i].setX(tvN.getX());
            etns[i].setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            //etns[i].setTextSize(etN.getTextSize());
            etns[i].setTextColor(Color.BLUE);
            etns[i].setHintTextColor(Color.GRAY);
            etns[i].setText("");
            etns[i].setHint("nota" + (i + 1));
            etns[i].setY(y);
            //etns[i].setY(etN.getY()*2f+y);
            etns[i].setId(i + 1);
            ContenedorCorte.addView(etns[i]);
            //y = etns[i].getY();
            y+=56f;
        }
        etNP = new EditText(getActivity().getApplicationContext());
        etNP.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etNP.setTextColor(Color.MAGENTA);
        etNP.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        etNP.setText("");
        etNP.setHint("Parcial");
        etNP.setHintTextColor(Color.DKGRAY);
        etNP.setX(tvN.getX());
        etNP.setY(ContenedorCorte.getChildAt(ContenedorCorte.getChildCount() - 1).getY()+56f);
        etNP.setId(etns.length);
        ContenedorCorte.addView(etNP);
        Button btn = new Button(getActivity().getApplicationContext());
        btn.setTextColor(Color.WHITE);
        btn.setText("Calcular");
        btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        btn.setX(etNP.getX());
        btn.setY(etNP.getY()+90f);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    float acum = 0f;
                    float promn=0f;
                    float promp=0f;
                    float notaC=0f;
                    boolean valid=true;

                   for (EditText edi : etns) {
                       if (!(edi.getText().toString().equals(""))&&Float.parseFloat(edi.getText().toString())<=5.0f) {
                           acum=acum+Float.parseFloat(edi.getText().toString());
                           valid=true;
                           //Toast.makeText(getActivity().getApplicationContext(), "Nota "+edi.getId()+" v:"+edi.getText().toString(), Toast.LENGTH_SHORT).show();
                           //Toast.makeText(getActivity().getApplicationContext(), "Acumulado "+acum, Toast.LENGTH_SHORT).show();
                       } else{
                           acum=0f;
                           tvR.setTextColor(Color.RED);
                           tvR.setText("Error Nota: "+edi.getId());
                           FragmentManager fg= getFragmentManager();
                           ad.setMsn("Error en Datos, La Nota: "+edi.getId()+" no debe ser Mayor a 5.0 ni Nulo");
                           ad.show(fg,"Alerta");
                           Toast.makeText(getActivity().getApplicationContext(), "Error en Nota: "+edi.getId(), Toast.LENGTH_SHORT).show();
                           valid=false;
                           break;
                       }
                   }
                //Toast.makeText(getActivity().getApplicationContext(), "Acumulado final "+acum, Toast.LENGTH_SHORT).show();
                try {

                    promn = ((acum) / etns.length) * 0.5f;
                    promp = (Float.parseFloat(etNP.getText().toString())) * 0.5f;
                    notaC = promn + promp;
                    if(Float.parseFloat(etNP.getText().toString())<=5.0f&&valid){
                        tvR.setText(String.valueOf(notaC));
                        Toast.makeText(getActivity().getApplicationContext(), "Nota del Corte: " + notaC, Toast.LENGTH_LONG).show();
                    }else if(Float.parseFloat(etNP.getText().toString())>5.0f||etNP.getText().equals("")){
                        tvR.setTextColor(Color.RED);
                        tvR.setText("Error Nota Parcial");
                        FragmentManager fg= getFragmentManager();
                        ad.setMsn("Error en Datos, La Nota Parcial deben ser Mayor a 5.0");
                        ad.show(fg,"Alerta");

                    }
                   }catch (NumberFormatException nfe){
                    Toast.makeText(getActivity().getApplicationContext(), "Nota Parcial Nula", Toast.LENGTH_LONG).show();
                }
            }});
        ContenedorCorte.addView(btn);

    }catch (NumberFormatException nfe){
        Toast.makeText(getActivity().getApplicationContext(), "Error en Numero de Notas", Toast.LENGTH_LONG).show();
    }
    }
}
