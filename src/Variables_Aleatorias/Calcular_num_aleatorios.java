package Variables_Aleatorias;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calcular_num_aleatorios extends JFrame {
    private JTextField entradaSemilla, entradaDigitos, entradaCantidad;
    private JButton botonGenerar;

    public Calcular_num_aleatorios() {
        setTitle("Generador de Números Aleatorios - Cuadrados Medios");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(4, 2));

        panelPrincipal.add(new JLabel("Semilla:"));
        entradaSemilla = new JTextField();
        panelPrincipal.add(entradaSemilla);

        panelPrincipal.add(new JLabel("Dígitos:"));
        entradaDigitos = new JTextField();
        panelPrincipal.add(entradaDigitos);

        panelPrincipal.add(new JLabel("Cantidad:"));
        entradaCantidad = new JTextField();
        panelPrincipal.add(entradaCantidad);

        botonGenerar = new JButton("Generar");
        botonGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarNumerosAleatorios();
            }
        });
        panelPrincipal.add(botonGenerar);

        add(panelPrincipal);
        setVisible(true);
    }

    private void generarNumerosAleatorios() {
        int semilla = Integer.parseInt(entradaSemilla.getText());
        int digitos = Integer.parseInt(entradaDigitos.getText());
        int cantidad = Integer.parseInt(entradaCantidad.getText());
        CuadradosMedios cuadradosMedios = new CuadradosMedios();
        cuadradosMedios.cuadradosMedios(semilla, digitos, cantidad);
    }

    public static void main(String[] args) {
        new Calcular_num_aleatorios();
    }
}

class CuadradosMedios {
    public void cuadradosMedios(int semilla, int digitos, int cantidad) {
        double[] resultados = new double[cantidad];
        int[][] matrizResultados = new int[cantidad][2];
        for (int i = 0; i < cantidad; i++) {
            int semillaCuadrada = semilla * semilla;
            String semillaStr = String.format("%0" + (2 * digitos) + "d", semillaCuadrada);
            int centroInicio = (semillaStr.length() - digitos) / 2;
            int ri = Integer.parseInt(semillaStr.substring(centroInicio, centroInicio + digitos));
            resultados[i] = (double) ri / Math.pow(10, digitos);
            matrizResultados[i][0] = semilla;
            matrizResultados[i][1] = ri;
            semilla = ri;
        }
        mostrarResultados(resultados, matrizResultados);
    }

    private void mostrarResultados(double[] resultados, int[][] matrizResultados) {
        JFrame ventanaResultados = new JFrame("Resultados");
        ventanaResultados.setLayout(new GridLayout(resultados.length + 1, 3));

        ventanaResultados.add(new JLabel("i"));
        ventanaResultados.add(new JLabel("Xi"));
        ventanaResultados.add(new JLabel("Ri"));

        for (int i = 0; i < resultados.length; i++) {
            ventanaResultados.add(new JLabel(String.valueOf(i + 1)));
            ventanaResultados.add(new JLabel(String.valueOf(matrizResultados[i][0])));
            ventanaResultados.add(new JLabel(String.valueOf(resultados[i])));
        }

        ventanaResultados.setSize(300, 200);
        ventanaResultados.setVisible(true);
    }
}
