import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Metrics {
    
    // Ces 2 hashmap si on veut stocker les elements pour la partie 2 
    static List <String[]>classInfos = new ArrayList<>();
    static List<String[]> packageInfos = new ArrayList<>();

    // contient la liste des dossier de mon chemin
    static ArrayList<String> repositories = new ArrayList<>();

    static Metrics metric ;

    /**
     * Fonction recuperant tous les dossiers d'un chemin donne.
     * Le chemin de chaque dossier est ensuite stocke dans un
     * arrayList
     * @param chemin
     * @return 
     */

    static void parcoursRepertoire(String chemin){

        Path path = Paths.get(chemin);
        try(Stream<Path> subPaths = Files.walk(path) ){
            // Je recupere tous les dossiers de mon repertoire
            subPaths.filter(Files::isDirectory).forEach(a -> repositories.add(a.toString()));
        } catch (IOException e){
           e.printStackTrace();
        }

       for(int i=0; i<repositories.size(); i++){
           System.out.println("-------------------------------------");
           System.out.println("Le dossier est: "+ repositories.get(i));
           metrics(repositories.get(i));
       }
       classeCsv();
       paquetCsv();
    }


    /**
     * Fonction recuperant tous les fichiers d'un dossier
     * specifique, calcul le loc,cloc et dc de chaque fichier.
     * Stocke les resultat dans un ArrayList.
     * @param folder
     */

     // Src: https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
     // https://stackoverflow.com/questions/11373130/how-to-get-foldername-and-filename-from-the-directorypath
    static void metrics(String folder){

        int locPaquet , clocPaquet;
        locPaquet = clocPaquet = 0;
        double dcPaquet; 
        dcPaquet = 0; 

        File myFolder = new File(folder);
        File[] listOfFiles = myFolder.listFiles(); // list de fichiers dans mon dossier

        for(File file : listOfFiles){
            if(file.isFile()){

                System.out.println(file.getPath());

                String path = file.getPath();
                
                // Recuperation du texte a lire

                /*
                Prob: j'utilise le meme bufferedRead alors qd je 
                fs une 1ere lecture de mon doc qd je cherche le loc, qd je 
                vais faire une deuxieme lecture du doc pr le cloc, mon
                bufferedReader est deja a la fin du document. Et donc line == null
                alors ca lit rien
                */

                BufferedReader texte1 =  ClassMetrics.readFiles(path);
                BufferedReader texte2 =  ClassMetrics.readFiles(path);

                // Solution non efficace: creer 2 bufferedReader (a voir comment ameliorer)

                String name = file.getName();

                //test
                System.out.println("Le fichier est: "+name);

                int loc = ClassMetrics.get_classe_LOC(texte1);
                int cloc = ClassMetrics.get_classe_CLOC(texte2);
                double dc = ClassMetrics.get_classe_DC(cloc,loc);

                locPaquet += loc; clocPaquet += cloc; 
                // infos entrees de la facon qu'il vont etre mis dans le fichier csv
                classInfos.add(new String[]
                {path,name,loc+"",cloc+"",dc+""});
            }
        }
        // si pas de fichier dans le dossier on a pas besoin de calculer ca
        if(listOfFiles.length != 0){
            dcPaquet = (double) clocPaquet/ (double) locPaquet;
            String name = myFolder.getName();

            packageInfos.add(new String[]
            {folder,name,locPaquet+"",clocPaquet+"",dcPaquet+""});
        }
    }


    // source:https://www.youtube.com/watch?v=dHZaqMmQNO4
    static void classeCsv(){
        File csvFile = new File("classes.csv");
        try {
            PrintWriter out = new PrintWriter(csvFile);
            out.printf("%s, %s, %s, %s, %s\n",
            "chemin","class","class_LOC","classe_CLOC","classe_DC");

            for(int i = 0; i < classInfos.size(); i++){
                for(int j = 0; j < classInfos.get(i).length; j++){
                    out.printf("%s, ",
                    classInfos.get(i)[j]);
                }
                out.println();
            }
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static void paquetCsv(){
        File csvFile = new File("paquets.csv");
        try {
            PrintWriter out = new PrintWriter(csvFile);
            out.printf("%s, %s, %s, %s, %s\n",
            "chemin","paquet","paquet_LOC","paquet_CLOC","paquet_DC");

            for(int i = 0; i < packageInfos.size(); i++){
                for(int j = 0; j < packageInfos.get(i).length; j++){
                    out.printf("%s, ",
                    packageInfos.get(i)[j]);
                }
                out.println();
            }
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    
}
