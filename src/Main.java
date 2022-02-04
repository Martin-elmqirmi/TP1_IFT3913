import java.io.*;

public class Main {

    /**
     * Fonction qui prend en paramètre un chemin vers une classe java
     * et calcul le nombre de lignes de code de cette classe
     * @param chemin
     * @return
     */
    static int get_classe_LOC(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre un chemin vers un paquet java
     * et calcul le nombre de lignes de code du paquet (somme des LOC de ses classes)
     * @param chemin
     * @return
     */
    static int get_paquet_LOC(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre un chemin vers une classe java
     * et calcul le nombre de lignes de commentaires dans celle-ci
     * @param chemin
     * @return
     */
    static int get_classe_CLOC(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre un chemin vers un paquet java
     * et calcul le nombre de lignes de commentaires du paquet (somme des CLOC de ses classes)
     * @param chemin
     * @return
     */
    static int get_paquet_CLOC(String chemin) {
        return 0;
    }


    /**
     * Fonction qui prend en paramètre les métriques classe_CLOC et classe_LOC
     * d'une même classe et calcul la densité de commentaires dans cette classe
     * @param classe_CLOC
     * @param classe_LOC
     * @return
     */
    static double get_classe_DC(int classe_CLOC, int classe_LOC) {
        return (double) classe_CLOC / (double) classe_LOC;
    }


    /**
     * Fonction qui prend en paramètre les métriques paquet_CLOC et paquet_LOC
     * d'un même paquet et calcul la densité de commentaires dans ce paquet
     * @param paquet_CLOC
     * @param paquet_LOC
     * @return
     */
    static double get_paquet_DC(int paquet_CLOC, int paquet_LOC) {
        return (double) paquet_CLOC / (double) paquet_LOC;
    }
}
