public class Fase3_Experimentos {
    public static void main(String[] args) {
        System.out.println("--- Inicio de experimentos ---");

        System.out.println("--- Experimento A ---");
        Temperatura sensorTemp = new Temperatura();
        Thread hiloA = new Thread(sensorTemp, "sensor-Temp-ExpA");

        hiloA.start();

        System.out.println("Informe impreso al instante (sin esperar):");
        System.out.println(sensorTemp.informe());

        try {
            hiloA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Informe impreso DESPUÉS de hacer join():");
        System.out.println(sensorTemp.informe());


        System.out.println("--- Experimento B ---");
        ProgramacionRiego riegoB = new ProgramacionRiego();

        riegoB.run();

        System.out.println("Estado del hilo/objeto riegoB tras .run(): " + riegoB.getState());


        System.out.println("--- Experimento C ---");
        ProgramacionRiego riegoC = new ProgramacionRiego();

        try {
            riegoC.start();
            riegoC.join();
            riegoC.start();
        } catch (IllegalThreadStateException e) {
            System.out.println("Excepción capturada: " + e.getClass().getName());
            System.out.println("Mensaje de la excepción: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}