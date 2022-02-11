import java.io.*;


public class Main {

    static Metrics metriques;

    /**
     * Main dans lequel nous allons calculer les différentes métriques
     * et ou nous allons créer les fichiers csv associé aux métriques des classes
     * et des paquets
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {
        Metrics.parcoursRepertoire("/Users/davejoseph/Desktop/jfreechart-master");
    }
}
