public class Fase1_Secuencial {
    public static void main(String[] args) {
        System.out.println("--- Inicio del invernadero secuencial (1 hilo) ---");

        Temperatura temp = new Temperatura();
        Humedad hum = new Humedad();
        Luz luz = new Luz();
        ProgramacionRiego riego = new ProgramacionRiego();

        long tiempoInicio = System.currentTimeMillis();

        temp.run();
        hum.run();
        luz.run();
        riego.run();

        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println(" Resultado final");
        System.out.println(temp.informe());
        System.out.println(hum.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());

        System.out.println("Tiempo total: " + tiempoTotal + " ms");
    }
}