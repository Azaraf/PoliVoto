/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import com.polivoto.shared.Opcion;
import com.polivoto.shared.Pregunta;
import com.polivoto.shared.Votacion;
import java.util.Date;

/**
 *
 * @author azaraf
 */
public class TestVotacion {
    private static final String TITULO_VOTACION = "Consulta de prueba 2016";
    private static final String LUGAR_DE_VOTACION = "UPIITA - IPN";
    private static final long INICIO_VOTACION = new Date().getTime();
    private static final long FINAL_VOTACION = new Date().getTime();
    private static final String PREGUNTA_1 = "¿Está usted de acuerdo el nuevo reglamento de tránsito?";
    private static final String PREGUNTA_2 = "¿Es usted usuario del transporte público en la CDMX?";
    private static final String OPCION_TEXTO_1_1 = "De acuerdo";
    private static final String OPCION_TEXTO_1_2 = "En desacuerdo";
    private static final String OPCION_TEXTO_1_3 = "No me interesa";
    private static final String OPCION_TEXTO_2_1 = "Sí";
    private static final String OPCION_TEXTO_2_2 = "No";
    private static final String OPCION_TEXTO_2_3 = "Ocasionalmente";
    public static final int OPCION_CANTIDAD_1_1 = 31;
    public static final int OPCION_CANTIDAD_1_2 = 24;
    public static final int OPCION_CANTIDAD_1_3 = 89;
    public static final int OPCION_CANTIDAD_2_1 = 20;
    public static final int OPCION_CANTIDAD_2_2 = 47;
    public static final int OPCION_CANTIDAD_2_3 = 58;
    
    private static final Votacion votacion;
    private static final Pregunta pregunta1;
    private static final Pregunta pregunta2;
    private static final Opcion opcion11;
    private static final Opcion opcion12;
    private static final Opcion opcion13;
    private static final Opcion opcion21;
    private static final Opcion opcion22;
    private static final Opcion opcion23;
    
    static{
        votacion = new Votacion(TITULO_VOTACION);
        pregunta1 = new Pregunta(PREGUNTA_1);
        pregunta2 = new Pregunta(PREGUNTA_2);
        opcion11 = new Opcion(OPCION_TEXTO_1_1, OPCION_CANTIDAD_1_1);
        opcion12 = new Opcion(OPCION_TEXTO_1_2, OPCION_CANTIDAD_1_2);
        opcion13 = new Opcion(OPCION_TEXTO_1_3, OPCION_CANTIDAD_1_3);
        opcion21 = new Opcion(OPCION_TEXTO_2_1, OPCION_CANTIDAD_2_1);
        opcion22 = new Opcion(OPCION_TEXTO_2_2, OPCION_CANTIDAD_2_2);
        opcion23 = new Opcion(OPCION_TEXTO_2_3, OPCION_CANTIDAD_2_3);
        votacion.setFechaInicio(INICIO_VOTACION);
        votacion.setFechaFin(FINAL_VOTACION);
        votacion.setLugar(LUGAR_DE_VOTACION);
        pregunta1.agregarOpcion(opcion11);
        pregunta1.agregarOpcion(opcion12);
        pregunta1.agregarOpcion(opcion13);
        pregunta2.agregarOpcion(opcion21);
        pregunta2.agregarOpcion(opcion22);
        pregunta2.agregarOpcion(opcion23);
        votacion.agregaPregunta(pregunta1);
        votacion.agregaPregunta(pregunta2);
        
    }

    public static Votacion getVotacion() {
        return votacion;
    }

    public static Pregunta getPregunta1() {
        return pregunta1;
    }

    public static Pregunta getPregunta2() {
        return pregunta2;
    }

    public static Opcion getOpcion11() {
        return opcion11;
    }

    public static Opcion getOpcion12() {
        return opcion12;
    }

    public static Opcion getOpcion13() {
        return opcion13;
    }

    public static Opcion getOpcion21() {
        return opcion21;
    }

    public static Opcion getOpcion22() {
        return opcion22;
    }

    public static Opcion getOpcion23() {
        return opcion23;
    }
    
    
    
}
