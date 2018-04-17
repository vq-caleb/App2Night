package com.example.denis.a2night;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.a2night.entidades.AlmacenamientoGlobal;
import com.example.denis.a2night.entidades.Horario;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabInformacion extends Fragment {
    AlmacenamientoGlobal aGlobal = AlmacenamientoGlobal.getInstance();
    TextView tipoEmpresa, costoEntrada, codigoVestimenta, estadoHorario, numTelefonos, correoElectronico, paginaFacebook,
            paginaInstagram, paginaTwitter, diaLunes, diaMartes, diaMiercoles, diaJueves, diaViernes,
            diaSabado, diaDomingo, horaLunesA, horaLunesC, horaMartesA, horaMartesC, horaMiercolesA, horaMiercolesC,
            horaJuevesA, horaJuevesC, horaViernesA, horaViernesC, horaSabadoA, horaSabadoC, horaDomingoA, horaDomingoC;
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

        this.diaLunes.setText(temp.get(0).getDia()+":");
        this.horaLunesA.setText(temp.get(0).getHoraAbierto());
        this.horaLunesC.setText(temp.get(0).getHoraCierre());

        this.diaMartes.setText(temp.get(1).getDia()+":");
        this.horaMartesA.setText(temp.get(1).getHoraAbierto());
        this.horaMartesC.setText(temp.get(1).getHoraCierre());

        this.diaMiercoles.setText(temp.get(2).getDia()+":      ");
        this.horaMiercolesA.setText(temp.get(2).getHoraAbierto());
        this.horaMiercolesC.setText(temp.get(2).getHoraCierre());

        this.diaJueves.setText(temp.get(3).getDia()+":");
        this.horaJuevesA.setText(temp.get(3).getHoraAbierto());
        this.horaJuevesC.setText(temp.get(3).getHoraCierre());

        this.diaViernes.setText(temp.get(4).getDia()+":");
        this.horaViernesA.setText(temp.get(4).getHoraAbierto());
        this.horaViernesC.setText(temp.get(4).getHoraCierre());

        this.diaSabado.setText(temp.get(5).getDia()+":");
        this.horaSabadoA.setText(temp.get(5).getHoraAbierto());
        this.horaSabadoC.setText(temp.get(5).getHoraCierre());

        this.diaDomingo.setText(temp.get(6).getDia()+":");
        this.horaDomingoA.setText(temp.get(6).getHoraAbierto());
        this.horaDomingoC.setText(temp.get(6).getHoraCierre());
    }

    public void verificarEstadoHorario(){
        Date currentTime = Calendar.getInstance().getTime();
        List<Horario> horario = aGlobal.getEmpresa().getHorarioSemanal();
        switch (currentTime.getDay()){
            case 0: cambiarEstadoHorario(horario.get(6).estaAbierto(currentTime.getHours(),currentTime.getMinutes())); break;
            case 1: cambiarEstadoHorario(horario.get(0).estaAbierto(currentTime.getHours(),currentTime.getMinutes())); break;
            case 2: cambiarEstadoHorario(horario.get(1).estaAbierto(currentTime.getHours(),currentTime.getMinutes())); break;
            case 3: cambiarEstadoHorario(horario.get(2).estaAbierto(currentTime.getHours(),currentTime.getMinutes())); break;
            case 4: cambiarEstadoHorario(horario.get(3).estaAbierto(currentTime.getHours(),currentTime.getMinutes())); break;
            case 5: cambiarEstadoHorario(horario.get(4).estaAbierto(currentTime.getHours(),currentTime.getMinutes())); break;
            case 6: cambiarEstadoHorario(horario.get(5).estaAbierto(currentTime.getHours(),currentTime.getMinutes())); break;
        }
    }

    public void cambiarEstadoHorario(boolean estado){
        if (estado){
            this.estadoHorario.setText("ABIERTO");
        } else {
            this.estadoHorario.setText("CERRADO");
        }
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
        this.diaLunes = (TextView) view.findViewById(R.id.diaLunes);
        this.horaLunesA = (TextView) view.findViewById(R.id.horaLunesA);
        this.horaLunesC = (TextView) view.findViewById(R.id.horaLunesC);
        this.diaMartes = (TextView) view.findViewById(R.id.diaMartes);
        this.horaMartesA = (TextView) view.findViewById(R.id.horaMartesA);
        this.horaMartesC = (TextView) view.findViewById(R.id.horaMartesC);
        this.diaMiercoles = (TextView) view.findViewById(R.id.diaMiercoles);
        this.horaMiercolesA = (TextView) view.findViewById(R.id.horaMiercolesA);
        this.horaMiercolesC = (TextView) view.findViewById(R.id.horaMiercolesC);
        this.diaJueves = (TextView) view.findViewById(R.id.diaJueves);
        this.horaJuevesA = (TextView) view.findViewById(R.id.horaJuevesA);
        this.horaJuevesC = (TextView) view.findViewById(R.id.horaJuevesC);
        this.diaViernes = (TextView) view.findViewById(R.id.diaViernes);
        this.horaViernesA = (TextView) view.findViewById(R.id.horaViernesA);
        this.horaViernesC = (TextView) view.findViewById(R.id.horaViernesC);
        this.diaSabado = (TextView) view.findViewById(R.id.diaSabado);
        this.horaSabadoA = (TextView) view.findViewById(R.id.horaSabadoA);
        this.horaSabadoC = (TextView) view.findViewById(R.id.horaSabadoC);
        this.diaDomingo = (TextView) view.findViewById(R.id.diaDomingo);
        this.horaDomingoA = (TextView) view.findViewById(R.id.horaDomingoA);
        this.horaDomingoC = (TextView) view.findViewById(R.id.horaDomingoC);

        this.estrellas = (RatingBar) view.findViewById(R.id.calificacion);
    }

    public void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};
}
