public class Fase2_Concurrente {
    public static void main(String[] args) {
        System.out.println("--- Inicio del invernadero concurrente ---");

        Temperatura temp = new Temperatura();
        Humedad hum = new Humedad();
        Luz luz = new Luz();
        ProgramacionRiego riego = new ProgramacionRiego();

        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");
        Thread hiloHum = new Thread(hum, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        long tiempoInicio = System.currentTimeMillis();

        hiloTemp.start();
        hiloHum.start();
        hiloLuz.start();
        riego.start();

        try {
            hiloTemp.join();
            hiloHum.join();
            hiloLuz.join();
            riego.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
            Thread.currentThread().interrupt();
        }

        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println("--- Informes finales ---");
        System.out.println(temp.informe());
        System.out.println(hum.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());

        System.out.println("Tiempo total de ejecución: " + tiempoTotal + " ms");
    }
}