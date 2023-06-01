package com.ucb.tdd;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ascensor {
    private int pisoActual;
    private int direccion;
    private List<Persona> personas;

    public List<Persona> getPersonas() {
        return personas;
    }

    public Ascensor() {
        pisoActual = 1;
        direccion = 1;
        personas = new ArrayList<>();
    }

    public void operar() {
        while (true) {
            mover();
            abrirPuerta();
            cerrarPuerta();
        }
    }

    public void agregarPersona(Persona p) {
        personas.add(p);
        p.presionarBoton();
    }

    private void mover() {
        pisoActual += direccion;
        System.out.println("Ascensor en el piso " + pisoActual);
        verificarPersonas();
        verificarDireccion();
    }

    private void verificarPersonas() {
        List<Persona> personasLlegadas = new ArrayList<>();
        for (Persona p : personas) {
            if (p.getDestino() == pisoActual) {
                personasLlegadas.add(p);
            }
        }
        if (!personasLlegadas.isEmpty()) {
            System.out.println("Personas en el ascensor: " + personas.size());
            System.out.println("Personas que han llegado a su destino: " + personasLlegadas.size());
            personas.removeAll(personasLlegadas);
        }
    }

    private void verificarDireccion() {
        if (pisoActual == 1) {
            direccion = 1;
        } else if (pisoActual == 3) {
            direccion = -1;
        } else {
            boolean tieneSubida = false;
            boolean tieneBajada = false;
            for (Persona p : personas) {
                if (p.getDestino() > pisoActual) {
                    tieneSubida = true;
                } else {
                    tieneBajada = true;
                }
            }
            if (tieneSubida && !tieneBajada) {
                direccion = 1;
            } else if (tieneBajada && !tieneSubida) {
                direccion = -1;
            }
        }
    }

    private void abrirPuerta() {
        System.out.println("Puerta abierta en el piso " + pisoActual);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cerrarPuerta() {
        System.out.println("Puerta cerrada en el piso " + pisoActual);
    }
}

class Persona {
    private int pisoActual;
    private int destino;

    public Persona(int pisoActual) {
        this.pisoActual = pisoActual;
        Random random = new Random();
        do {
            destino = random.nextInt(3) + 1;
        } while (destino == pisoActual);
    }

    public void presionarBoton() {
        System.out.println("Persona en el piso " + pisoActual + " ha presionado el botón para ir al piso " + destino);
    }

    public int getDestino() {
        return destino;
    }
}
