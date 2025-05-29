import java.util.*;

public class Dataset {
    private ArrayList<Game> data;
    private String sortedByAttribute;

    public Dataset(ArrayList<Game> data) {
        this.data = data;
        this.sortedByAttribute = " ";
    }

    public ArrayList<Game> getGamesByPrice(int price) {
        ArrayList<Game> games = new ArrayList<>();
        if (!sortedByAttribute.equals("price")) {
            for(int i=0;i<data.size();i++) {
                if(data.get(i).getPrice() == price) games.add(data.get(i));
            }
        }
        else {
            int l = 0, r = data.size() - 1;

            while(l <= r) {
                int m = (l + r) / 2;
                int mid = data.get(m).getPrice();

                if(price == mid) {
                    for(int i=m; i>=0 && data.get(i).getPrice()==price; i--) games.add(data.get(i));
                    for(int i=m+1; i<data.size() && data.get(i).getPrice()==price; i++) games.add(data.get(i));
                    break;
                }
                else if(price < mid) {
                    l = m + 1;
                }
                else {
                    r = m - 1;
                }
            }
        }
        return games;
    }

    public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice) {
        ArrayList <Game> rango = new ArrayList<>();
        if (!sortedByAttribute.equals("price")) {
            for(int i = 0; i < data.size(); i++ ) {
                if(data.get(i).getPrice() <= higherPrice && data.get(i).getPrice() >= lowerPrice) rango.add(data.get(i));
            }
        }
        else {
            int l = 0, r = data.size() - 1;
            int inicio=-1;
            while(l <= r) {
                int m = (l + r) / 2;
                int mid = data.get(m).getPrice();
                if(mid>=lowerPrice) {
                    inicio=m;
                    r=m-1;
                }
                else {
                    l = m + 1;
                }
            }
            for(int i=inicio; i<data.size(); i++){
                if(data.get(i).getPrice() > higherPrice) {
                    break;
                }
                if(data.get(i).getPrice() >= lowerPrice) {
                    rango.add(data.get(i));
                }
            }
        }
        return rango;
    }

    public ArrayList<Game> getGamesByCategory(String category) {
        ArrayList <Game> categoria = new ArrayList<>();
        if (!sortedByAttribute.equals("category")) {
            for(int i = 0; i < data.size(); i++ ) {
                if(data.get(i).getCategory().equals(category)) categoria.add(data.get(i));
            }
        }
        else {
            int l = 0, r = data.size() - 1;
            while(l <= r) {
                int m = (l + r) / 2;
                String categoriaM = data.get(m).getCategory();
                if(categoriaM.compareTo(category) == 0) {
                    int i=m;
                    while(i>=0 && data.get(i).getCategory().equals(category)){
                        i--;
                    }
                    i++;
                    while(i<data.size() && data.get(i).getCategory().equals(category)){
                        categoria.add(data.get(i));
                        i++;
                    }
                    break;
                }
                else if(categoriaM.compareTo(category) < 0) {
                    l = m + 1;
                }
                else {
                    r = m - 1;
                }
            }
        }
        return categoria;
    }

    public ArrayList<Game> getGamesByQuality(int quality) {
        ArrayList<Game> calidad = new ArrayList<>();
        if (!sortedByAttribute.equals("quality")) {
            for(int i = 0; i < data.size(); i++){
                if(data.get(i).getQuality() == quality) calidad.add(data.get(i));
            }
        } else {
            int l = 0, r = data.size() - 1;

            while (l <= r) {
                int m = (l + r) / 2;
                int valorMedio = data.get(m).getQuality();

                if (valorMedio == quality) {
                    // Agregar juego en la posición media
                    calidad.add(data.get(m));

                    // Buscar a la izquierda
                    int i = m - 1;
                    while (i >= 0 && data.get(i).getQuality() == quality)
                    {
                        calidad.add(data.get(i));
                        i--;
                    }

                    // Buscar a la derecha
                    i = m + 1;
                    while (i < data.size() && data.get(i).getQuality() == quality)
                    {
                        calidad.add(data.get(i));
                        i++;
                    }

                    break;
                } else if (valorMedio < quality)
                {
                    l = m + 1; // buscar en la derecha
                } else
                {
                    r = m - 1; // buscar en la izquierda
                }
            }
        }
        return calidad;
    }

    public ArrayList<Game> sortByAlgorithm(String algorithm, String attribute) {
        Comparator<Game> comparator;
        switch (attribute) {
            case "quality":
                comparator = Comparator.comparingInt(Game::getQuality);
                break;
            case "category":
                comparator = Comparator.comparing(Game::getCategory);
                break;
            case "price":
                comparator = Comparator.comparingInt(Game::getPrice);
                break;
            default:
                comparator = Comparator.comparingInt(Game::getPrice);
                attribute = "price";
                break;
        }

        switch (algorithm) {
            case "bubbleSort":
                bubbleSort(data, comparator);
                break;
            case "insertionSort":
                insertionSort(data, comparator);
                break;
            case "selectionSort":
                selectionSort(data, comparator);
                break;
            case "mergeSort":
                data = mergeSort(data, comparator);
                break;
            case "quickSort":
                quickSort(data, 0, data.size() - 1, comparator);
                break;
            default:
                Collections.sort(data, comparator);
                break;
        }

        sortedByAttribute = attribute;
        return data;
    }

    public void bubbleSort(ArrayList<Game> list, Comparator<Game> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Game temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public void insertionSort(ArrayList<Game> list,  Comparator<Game> comparator) {
        int n = list.size();
        for(int i = 0; i < n; i++) {
            Game key = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public void selectionSort(ArrayList<Game> list,  Comparator<Game> comparator) {
        int n = list.size();
        for(int i = 0; i < n ; i++) {
            int min = i;
            for(int j = i + 1; j < n; j++) {
                if(comparator.compare(list.get(j) , list.get(min)) < 0) {
                    min = j;
                }
            }
            Game temp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);
        }
    }

    public ArrayList<Game> mergeSort(ArrayList<Game> list, Comparator<Game> comparator) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;

        ArrayList<Game> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Game> right = new ArrayList<>(list.subList(mid, list.size()));

        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        return merge(left, right, comparator);
    }

    private ArrayList<Game> merge(ArrayList<Game> left, ArrayList<Game> right, Comparator<Game> comparator) {
        ArrayList<Game> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i));
                i++;
            }
            else {
                result.add(right.get(j));
                j++;
            }
        }
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }
        return result;
    }

    public void quickSort(ArrayList<Game> list, int low, int high, Comparator<Game> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            quickSort(list, low, pi - 1, comparator);
            quickSort(list, pi + 1, high, comparator);
        }
    }

    private int partition(ArrayList<Game> list, int low, int high, Comparator<Game> comparator) {
        int n = list.size(), i = low - 1;
        Game pivote = list.get(high);
        for(int j = low; j <= high - 1; j++) {
            if(comparator.compare(list.get(j), pivote) <= 0) {
                i++;
                Game temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Game temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
    public void countingSortByQuality() {
        List<Game>[] buckets = new ArrayList[101];


        for (int i = 0; i <= 100; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = 0; i < data.size(); i++) {
            Game game = data.get(i);
            int q = game.getQuality();
            buckets[q].add(game);
        }
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                data.add(buckets[i].get(j));
            }
        }
    }
}