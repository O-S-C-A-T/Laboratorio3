import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        GenerateData generateData = new GenerateData();
        ArrayList<Game> g1 = generateData.generateGames(100);
        Dataset ds = new Dataset(g1);
        ArrayList<Game> g2 = generateData.generateGames(10000);
        Dataset ds2 = new Dataset(g2);
        ArrayList<Game> g3 = generateData.generateGames(1000000);
        Dataset ds3 = new Dataset(g3);


        /*System.out.println("Pruebas collectionsSort(quality):");
        long inicio = System.nanoTime();
        ds.sortByAlgorithm("collectionsSort", "quality");
        long fin = System.nanoTime();
        long totalTime = (fin - inicio) / 1000000;
        System.out.println("Tiempo total (100): " + totalTime + "ms");
        inicio = System.nanoTime();
        ds2.sortByAlgorithm("collectionsSort", "quality");
        fin = System.nanoTime();
        totalTime = (fin - inicio) / 1000000;
        System.out.println("Tiempo total (10000): " + totalTime + "ms");
        inicio = System.nanoTime();
        ds3.sortByAlgorithm("collectionsSort", "quality");
        fin = System.nanoTime();
        totalTime = (fin - inicio) / 1000000;
        System.out.println("Tiempo total (1000000): " + totalTime + "ms"); */

        /*System.out.println("Prueba getGamesByPrice:");
        Random rand = new Random();
        int price = rand.nextInt(70001);
        long inicio = System.nanoTime();
        ds3.getGamesByPrice(price);
        long fin = System.nanoTime();
        long diff = (fin - inicio) / 1000000;
        System.out.println("Tiempo total (linear): " + diff + "ms");
        ds3.sortByAlgorithm("mergeSort","price");
        inicio = System.nanoTime();
        ds3.getGamesByPrice(price);
        fin = System.nanoTime();
        diff = (fin - inicio) / 1000000;
        System.out.println("Tiempo total (binary): " + diff + "ms");*/


        System.out.println("Pruebas countingSort:");
        long startTime = System.nanoTime();
        ds.countingSortByQuality();
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime) / 1000000;
        System.out.println("Tiempo final (100): " + totalTime + " ms");
        startTime = System.nanoTime();
        ds2.countingSortByQuality();
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        System.out.println("Tiempo final (10000): " + totalTime + " ms");
        startTime = System.nanoTime();
        ds3.countingSortByQuality();
        endTime = System.nanoTime();
        totalTime = (endTime - startTime) / 1000000;
        System.out.println("Tiempo final (1000000): " + totalTime + " ms");

    }
}