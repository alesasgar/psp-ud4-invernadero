# psp-ud4-invernadero

Práctica de la asignatura **Programación de Servicios y Procesos**.

## Descripción del proyecto
Automatización concurrente de un invernadero mediante el uso de:
- **Interfaces `Runnable`** para los sensores.
- **Herencia de `Thread`** para el programador de riego.
- **Hilos vigilantes** para el control de estados.

## Comparacion de Tiempos: Secuencial vs Concurrente

| Modo de Ejecución | Tiempo Total (ms) | Explicación teórica |
| :--- | :--- | :--- |
| **Secuencial** | 9300 ms | El hilo `main` ejecuta las tareas una detrás de otra. El tiempo total es la suma de los tiempos de todos los dispositivos. |
| **Concurrente** | 3500 ms | Los hilos se ejecutan en paralelo. El tiempo total no es la suma, sino el maximo. |

### ¿Por qué ocurre esto?
- En el modo **secuencial**, ningún sensor puede empezar a leer hasta que el anterior termina por completo su bucle de 5 lecturas con sus respectivos `Thread.sleep`. Por tanto, los tiempos se acumulan linealmente.
- En el modo **concurrente**, gracias al uso de `.start()`, todos los dispositivos compiten por la CPU de forma simultánea. El programa principal solo tiene que esperar a que el último hilo termine mediante `.join()`, lo que reduce drásticamente el tiempo total de la operación.

## Resultados de los Experimentos
1. **Experimento A:** Si el hilo principal no espera a los hilos secundarios con `.join()`, los informes se imprimen vacíos o con 0 lecturas porque el hilo `main` es mucho más rápido y finaliza antes de que los sensores tomen los datos.
2. **Experimento B:** Al invocar `.run()` directamente, no se crea un hilo nuevo; el código se ejecuta de forma secuencial dentro del hilo actual, obteniendo estado `TERMINATED` de forma sorda pero sin concurrencia real.
3. **Experimento C:** Lanzar `.start()` dos veces sobre el mismo objeto `Thread` provoca una excepción de tipo `IllegalThreadStateException`, ya que un hilo en Java es de un solo uso y no puede reiniciarse tras haber finalizado su ciclo de vida.
