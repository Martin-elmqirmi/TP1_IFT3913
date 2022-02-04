import java.io.*;

public class Main {

    //------------------- PARTIE 1 -------------------//
    /**
     * Fonction qui prend en paramètre un chemin vers une classe java
     * et calcul le nombre de lignes de code de cette classe
     * @param chemin
     * @return
     */
    private static int get_classe_LOC(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre un chemin vers un paquet java
     * et calcul le nombre de lignes de code du paquet (somme des LOC de ses classes)
     * @param chemin
     * @return
     */
    private static int get_paquet_LOC(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre un chemin vers une classe java
     * et calcul le nombre de lignes de commentaires dans celle-ci
     * @param chemin
     * @return
     */
    private static int get_classe_CLOC(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre un chemin vers un paquet java
     * et calcul le nombre de lignes de commentaires du paquet (somme des CLOC de ses classes)
     * @param chemin
     * @return
     */
    private static int get_paquet_CLOC(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre les métriques classe_CLOC et classe_LOC
     * d'une même classe et calcul la densité de commentaires dans cette classe
     * @param classe_CLOC
     * @param classe_LOC
     * @return
     */
    private static double get_classe_DC(int classe_CLOC, int classe_LOC) {
        return (double) classe_CLOC / (double) classe_LOC;
    }


    /**
     * Fonction qui prend en paramètre les métriques paquet_CLOC et paquet_LOC
     * d'un même paquet et calcul la densité de commentaires dans ce paquet
     * @param paquet_CLOC
     * @param paquet_LOC
     * @return
     */
    private static double get_paquet_DC(int paquet_CLOC, int paquet_LOC) {
        return (double) paquet_CLOC / (double) paquet_LOC;
    }


    //------------------- PARTIE 3 -------------------//

    /**
     * Fonction qui prend en paramètre un chemin vers une classe java
     * et calcul  la somme pondérée des complexités cyclomatiques de McCabe
     * de toutes les méthodes de la classe
     * @param Chemin
     * @return
     */
    private static int WMC(String Chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre un chemin vers un paquet java
     * et calcul  la somme des WMC de toutes les classes d’un paquet
     * et les WCP de ses sous-paquets
     * @param chemin
     * @return
     */
    private static int WCP(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre la densité de commentaires
     * et la somme des complexités cyclomatique de McCabe d'une classe java
     * et retourne le degré selon lequel cette classe est bien commentée
     * @param classe_DC
     * @param WMC
     * @return
     */
    private static double get_classe_BC(double classe_DC, int WMC) {
        return classe_DC / (double) WMC;
    }


    /**
     * Fonction qui prend en paramètre la densité de commentaires
     * et la somme des complexités cyclomatique de McCabe d'un paquet java
     * et retourne le degré selon lequel ce paquet est bien commenté
     * @param paquet_DC
     * @param WCP
     * @return
     */
    private static double get_paquet_BC(double paquet_DC, int WCP) {
        return paquet_DC / (double) WCP;
    }


    /**
     * Main dans lequel nous allons calculer les différentes métriques
     * et ou nous allons créer les fichiers csv associé aux métriques des classes
     * et des paquets
     * @param args
     */
    public static void main(String[] args) {

    }
}
