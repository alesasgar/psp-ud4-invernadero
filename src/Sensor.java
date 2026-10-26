import java.util.concurrent.ThreadLocalRandom;

public abstract class Sensor implements Runnable {
    String nombre;
    String unidad;
    int periodoMs;
    int numLecturas;
    double umbralMinimo;
    double umbralMaximo;
    int totalLecturasRealizadas = 0;
    double sumaValores = 0;
    double valorMinimoRegistrado = Double.MAX_VALUE;
    double valorMaximoRegistrado = Double.MIN_VALUE;
    int contadorAlertas = 0;

    public Sensor(String nombre, String unidad, int periodoMs, int numLecturas, double umbralMinimo, double umbralMaximo) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodoMs = periodoMs;
        this.numLecturas = numLecturas;
        this.umbralMinimo = umbralMinimo;
        this.umbralMaximo = umbralMaximo;
    }

    public abstract double medir();

    @Override
    public void run() {
        for (int i = 0; i < numLecturas; i++) {
            double valor = medir();
            totalLecturasRealizadas++;
            sumaValores += valor;

            if (valor < valorMinimoRegistrado) {
                valorMinimoRegistrado = valor;
            }
            if (valor > valorMaximoRegistrado) {
                valorMaximoRegistrado = valor;
            }

            boolean esAlerta = (valor < umbralMinimo || valor > umbralMaximo);
            if (esAlerta) {
                contadorAlertas++;
            }

            String nombreHilo = Thread.currentThread().getName();
            String alertaStr = esAlerta ? "Alerta" : "";

            System.out.println("[" + nombreHilo + "] " + nombre + " -> Valor: " + valor + " " + unidad + alertaStr);

            // Esperar el periodo del sensor
            try {
                Thread.sleep(periodoMs);
            } catch (InterruptedException e) {
                System.out.println("El sensor " + nombre + " fue parado.");
                Thread.currentThread().interrupt();
            }
        }
    }

    public String informe() {
        double media = totalLecturasRealizadas > 0 ? sumaValores / totalLecturasRealizadas : 0;

        return "Sensor " + nombre + " [" + unidad + "] " +
                "-> Lectura: " + totalLecturasRealizadas +
                ", Media: " + media +
                ", Min: " + valorMinimoRegistrado +
                ", Max: " + valorMaximoRegistrado +
                ", Alertas: " + contadorAlertas;
    }
}