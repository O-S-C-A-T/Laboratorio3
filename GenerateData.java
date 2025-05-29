import java.util.ArrayList;
import java.util.Random;

public class GenerateData {
    private final String[] palabras = {"Dragon","Empire","Quest","Galaxy","Legends","Warrior"};
    private final String[] categorias = {"Acción","Aventura","Estrategia","RPG","Deportes","Simulación"};

    public ArrayList<Game> generateGames(int n) {
        ArrayList<Game> games = new ArrayList<>();
        Random rand = new Random();

        for (int i=0; i<n; i++) {
            String name = palabras[rand.nextInt(palabras.length)] + palabras[rand.nextInt(palabras.length)];
            String category = categorias[rand.nextInt(categorias.length)];
            int price = rand.nextInt(70001);
            int quality = rand.nextInt(101);
            Game game = new Game(name, category, price, quality);
            games.add(game);
        }
        return games;
    }
}