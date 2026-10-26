public class Fase4_Vigilante {
    public static void main(String[] args) {
        System.out.println("--- Inicio del invernadero con hilo vigilante ---");

        Temperatura temp = new Temperatura();
        Humedad hum = new Humedad();
        Luz luz = new Luz();
        ProgramacionRiego riego = new ProgramacionRiego();

        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");
        Thread hiloHum = new Thread(hum, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        hiloTemp.start();
        hiloHum.start();
        hiloLuz.start();
        riego.start();

        while (hiloTemp.isAlive() || hiloHum.isAlive() || hiloLuz.isAlive() || riego.isAlive()) {
            System.out.println("--- Comprobando los estados de los hilos ---");
            System.out.println("Estado " + hiloTemp.getName() + ": " + hiloTemp.getState());
            System.out.println("Estado " + hiloHum.getName() + ": " + hiloHum.getState());
            System.out.println("Estado " + hiloLuz.getName() + ": " + hiloLuz.getState());
            System.out.println("Estado " + riego.getName() + ": " + riego.getState());

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("El hilo vigilante fue parado.");
                Thread.currentThread().interrupt();
                break;
            }
        }

        try {
            hiloTemp.join();
            hiloHum.join();
            hiloLuz.join();
            riego.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- Todos los hilos han acabado. Ultimos informes ---");
        System.out.println(temp.informe());
        System.out.println(hum.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());
    }
}