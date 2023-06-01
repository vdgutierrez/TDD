package com.ucb.tdd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AscensorTest {

    private Ascensor ascensor;

    @BeforeEach
    public void setUp() {
        ascensor = new Ascensor();
    }

    @Test
    public void CrearPersona() {
        // PREPARACIÓN DE LA PRUEBA
        Persona p = new Persona(1);

        //LOGICA DE LA PRUBA
        ascensor.agregarPersona(p);

        //VERIFICACION DE LA PRUEBA
        Assertions.assertEquals(1, ascensor.getPersonas().size());
        Assertions.assertEquals(p, ascensor.getPersonas().get(0));
    }

    @Test
    public void OperacionAscensor() {
        // PREPARACIÓN DE LA PRUEBA
        Persona p1 = new Persona(1);
        Persona p2 = new Persona(2);
        Persona p3 = new Persona(3);
        ascensor.agregarPersona(p2);
        ascensor.agregarPersona(p3);
        ascensor.agregarPersona(p1);

        //LOGICA DE LA PRUBA
        ascensor.operar();
        Assertions.assertEquals(2, ascensor.getClass());

        //VERIFICACION DE LA PRUEBA
        Assertions.assertEquals(1, ascensor.getPersonas().size());
        Assertions.assertEquals(p2, ascensor.getPersonas().get(0));
        ascensor.operar();
        Assertions.assertEquals(3, ascensor.getClass());
        Assertions.assertEquals(2, ascensor.getPersonas().size());
        Assertions.assertTrue(ascensor.getPersonas().contains(p2));
        Assertions.assertTrue(ascensor.getPersonas().contains(p3));
        ascensor.operar();
        Assertions.assertEquals(1, ascensor.getClass());
        Assertions.assertEquals(0, ascensor.getPersonas().size());
    }
}
