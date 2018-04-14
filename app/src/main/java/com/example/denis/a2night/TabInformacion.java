package com.example.denis.a2night;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Horario;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabInformacion extends Fragment {
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    TextView tipoEmpresa, costoEntrada, codigoVestimenta, estadoHorario, numTelefonos, correoElectronico, paginaFacebook,
            paginaInstagram, paginaTwitter, horarioLunes, horarioMartes, horarioMiercoles, horarioJueves, horarioViernes,
            horarioSabado, horarioDomingo;
    RatingBar estrellas;
    private static final String  TAG = "TabInformacion";

    public TabInformacion() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_informacion, container, false);

        this.alambrarVariables(view);
        this.completarInformacion();

        return view;
    }

    public void completarInformacion(){
        this.tipoEmpresa.setText(this.aGlobal.getEmpresa().getTipoNegocio());
        this.costoEntrada.setText(this.aGlobal.getEmpresa().getEntrada());
        this.codigoVestimenta.setText(this.aGlobal.getEmpresa().getCodigoVestimenta());
        this.numTelefonos.setText(this.aGlobal.getEmpresa().getTelefono1()+"/"+this.aGlobal.getEmpresa().getTelefono2());
        this.correoElectronico.setText(this.aGlobal.getEmpresa().getCorreo());
        this.paginaFacebook.setText(this.aGlobal.getEmpresa().getPaginaFacebook());
        this.paginaInstagram.setText(this.aGlobal.getEmpresa().getPaginaInstagram());
        this.paginaTwitter.setText(this.aGlobal.getEmpresa().getPaginaTwitter());
        this.estrellas.setRating(this.aGlobal.getEmpresa().getCalificacion());
        completarInfoHorarioSemanal();
    }

    public void completarInfoHorarioSemanal(){
        List<Horario> temp = this.aGlobal.getEmpresa().getHorarioSemanal();
        this.horarioLunes.setText(temp.get(0).toString());
        this.horarioMartes.setText(temp.get(1).toString());
        this.horarioMiercoles.setText(temp.get(2).toString());
        this.horarioJueves.setText(temp.get(3).toString());
        this.horarioViernes.setText(temp.get(4).toString());
        this.horarioSabado.setText(temp.get(5).toString());
        this.horarioDomingo.setText(temp.get(6).toString());
    }

    public void alambrarVariables(View view){
        this.tipoEmpresa = (TextView) view.findViewById(R.id.tipoEmpresa);
        this.costoEntrada = (TextView) view.findViewById(R.id.costoEntrada);
        this.codigoVestimenta = (TextView) view.findViewById(R.id.codigoVestimenta);
        this.estadoHorario = (TextView) view.findViewById(R.id.estadoHorario);
        this.numTelefonos = (TextView) view.findViewById(R.id.numTelefonos);
        this.correoElectronico = (TextView) view.findViewById(R.id.correoElectronico);
        this.paginaFacebook = (TextView) view.findViewById(R.id.paginaFacebook);
        this.paginaInstagram = (TextView) view.findViewById(R.id.paginaInstagram);
        this.paginaTwitter = (TextView) view.findViewById(R.id.paginaTwitter);
        this.horarioLunes = (TextView) view.findViewById(R.id.horarioLunes);
        this.horarioMartes = (TextView) view.findViewById(R.id.horarioMartes);
        this.horarioMiercoles = (TextView) view.findViewById(R.id.horarioMiercoles);
        this.horarioJueves = (TextView) view.findViewById(R.id.horarioJueves);
        this.horarioViernes = (TextView) view.findViewById(R.id.horarioViernes);
        this.horarioSabado = (TextView) view.findViewById(R.id.horarioSabado);
        this.horarioDomingo = (TextView) view.findViewById(R.id.horarioDomingo);
        this.estrellas = (RatingBar) view.findViewById(R.id.calificacion);
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}
