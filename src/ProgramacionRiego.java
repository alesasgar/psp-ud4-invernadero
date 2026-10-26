public class ProgramacionRiego extends Thread {
    private int ciclosTotales = 5;
    private double litrosPorRiego = 2.5;
    private double totalLitrosRegados = 0;
    private int ciclosRealizados = 0;


    public ProgramacionRiego() {
        super("riego");
    }


    @Override
    public void run() {
        for (int i = 1; i <= ciclosTotales; i++) {
            totalLitrosRegados += litrosPorRiego;
            ciclosRealizados++;


            String nombreHilo = getName();
            System.out.println( nombreHilo + " Ciclo " + i + ": Válvula abierta. Regando " + litrosPorRiego + " litros.");


            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("El programador de riego fue interrumpido.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }


    public String informe() {
        return "Ciclos realizados por el programador de riego: " + ciclosRealizados + ", Agua consumida: " + totalLitrosRegados + " litros.";
    }
}