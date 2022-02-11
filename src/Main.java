import java.io.*;
import java.util.Scanner;


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

        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le chemin vers le dossier ou le fichier" +
                " dont vous voulez connaitre les métriques :");

        String path = sc.nextLine();
        Metrics.parcoursRepertoire(path);
    }
}
