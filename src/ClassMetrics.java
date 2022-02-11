import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ClassMetrics {


    static BufferedReader readFiles(String chemin){
        FileReader fileToParse = null;
        BufferedReader texte  = null;
        try {
            fileToParse = new FileReader(chemin);
            texte = new BufferedReader(fileToParse);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return texte;
    }

    /**
     * Fonction retournant le nb de ligne de code d'un fichier.
     * Les lignes vides ne sont pas prises en compte lors du calcul
     * Ligne code = nb ligne total - ligne vide
     * @param text
     * @return
     */

    static int get_classe_LOC(BufferedReader text){

        int nbLineOfFile = 0;
        int nbEmptyLine = 0;
        String line = null;
        
        try {
            while((line=text.readLine()) != null){
                nbLineOfFile++;
                nbEmptyLine += isLineEmpty(line);    
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // test
        System.out.println("nb de lignes Total: "+ nbLineOfFile);
        System.out.println("nb de lignes vide: "+nbEmptyLine);

        return nbLineOfFile - nbEmptyLine;
    }

    /**
     * Fonction comptant le nombre de ligne qui contient
     * un commentaire dans un fichier texte
     * @param texte
     * @return
     */

    static int get_classe_CLOC(BufferedReader texte){

        int nbLineWithComments = 0;
        String line = null;

        try {
            while((line=texte.readLine()) != null){
                
                line = line.trim();
                
                // Ma ligne commence par /* 
                if( (isLineEmpty(line) == 0) && parseLine(line) == 2){ 
                    nbLineWithComments++;

                    if(line.length()>2 && (line.charAt(line.length()-1)!='/') && 
                      (!line.contains("/**")) ){

                    }else{
                        // je suis dans le cas /* */ ou /** */
                        while((line=texte.readLine()) != null && !(line.contains("*/")) ){
                            if(isLineEmpty(line)==0){ 
                                nbLineWithComments++;
                            }
                        }
                        nbLineWithComments++;
                    }
                }else{
                    // Soit j'ai une ligne de texte ou une ligne de commentaire ou ligne mix commentaire et texte
                    if(isLineEmpty(line) == 0 && parseLine(line) != 0){  
                        //System.out.println(line);
                        nbLineWithComments+= parseLine(line);
                    }
                }   
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("nb lignes de commentaires: "+nbLineWithComments);
        return nbLineWithComments;
    }


    static double get_classe_DC(int classe_CLOC, int classe_LOC) {
        return (double) classe_CLOC / (double) classe_LOC;
    }


    /**
     * Fonction qui decrit si la ligne passee en parametre
     * est vide ou pas
     * @param line
     * @return
     */
    static int isLineEmpty(String line){
    
        String lineToParse = line.trim(); // enleve whitespace si y en a en debut de ligne
        if(lineToParse.length() == 0){ // ligne vide
            return 1;
        }
        
        return 0;
    }

    /**
     * Fonction permettant de dire si la ligne passe en parametre est: 
     * - une ligne de texte: commence par (A-Z) et ne contient pas // /*
     * - mix commentaire et texte: commence par (A-Z) et contient // ou /*
     * - ligne de commentaire: commence par //
     * - bloc commentaire , javadoc: commence par /*
     * @param line
     * @return
     */

    static int parseLine(String line){

        String lineToParse = line.trim(); // enleve whitespace si y en a en debut de ligne
        int answer = 0;

        if(lineToParse.length() != 0 && lineToParse.charAt(0) != '/'){ // ma ligne commence par une lettre (A-Z)
            
            if( (lineToParse.contains("//")) || (lineToParse.contains("/*"))  ){ // cette ligne contient et du texte et des commentaires 
                answer = 1;
            }else{ // ligne texte
                answer = 0;
            }

        }else {
            // Alors "peut-etre" bloc commentaire ou juste une ligne de commentaire

            if(lineToParse.length() != 0 && lineToParse.charAt(0) == '/' && lineToParse.charAt(1) == '/'){
                answer = 1;
            }

            else if(lineToParse.length() != 0 && lineToParse.charAt(0) == '/' && lineToParse.charAt(1) == '*'){
                answer = 2;
            }
        }

        return answer;
    }  


    /**
     * Fonction qui prend en paramètre la densité de commentaires
     * et la somme des complexités cyclomatique de McCabe d'une classe java
     * et retourne le degré selon lequel cette classe est bien commentée
     * @param classe_DC
     * @param WMC
     * @return
     */
    public static double get_classe_BC(double classe_DC, int WMC) {
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
    public static double get_paquet_BC(double paquet_DC, int WCP) {
        return paquet_DC / (double) WCP;
    }


    /**
     * Fonction qui prend une ligne de code et qui retourne cette même ligne
     * sans les commentaires
     * @param line
     * @return
     */
    static String[] reform_line(String[] line) {
        String[] new_line = {"", ""};
        String[] line_words = line[0].split("\\s");

        // Patterns of comments
        String simple_comment = "//.*";
        String complex_comment_start = "/\\*|/\\*\\*";
        String complex_comment_end = "[*]/";
        Pattern simple_pattern = Pattern.compile(simple_comment, Pattern.CASE_INSENSITIVE);
        Pattern complex_pattern_start = Pattern.compile(complex_comment_start, Pattern.CASE_INSENSITIVE);
        Pattern complex_pattern_end = Pattern.compile(complex_comment_end, Pattern.CASE_INSENSITIVE);

        boolean simple_commented = false;
        boolean complex_commented = Boolean.parseBoolean(line[1]);
        for (String line_word : line_words) {
            if (!simple_commented) {
                // Si on tombe sur un commentaire // la ligne est terminé
                if (simple_pattern.matcher(line_word).find()) {
                    simple_commented = true;
                } else if (complex_pattern_start.matcher(line_word).find()) {
                    complex_commented = true;
                } else {
                    if (!complex_commented) {
                        new_line[0] += line_word + " ";
                    } else if (complex_pattern_end.matcher(line_word).find()) {
                        String[] split_comment = line_word.split("\\*/");
                        if (split_comment.length > 1) {
                            new_line[0] += split_comment[1] + " ";
                        }
                        complex_commented = false;
                    }
                }
            }
        }
        new_line[1] = Boolean.toString(complex_commented);

        return new_line;
    }

    /**
     * Fonction qui prend un BufferedReader d'une classe java en entre et retourne la somme pondérée
     * des complexités cyclomatiques de McCabe de toutes les méthodes d'une classe
     * @param texte
     * @return
     */
    static int get_WMC(BufferedReader texte) {
        int mccabe_complexity = 0;
        int number_of_methods = 0;
        String line = null;
        String[] no_comment_line;

        boolean complex_commented = false;

        // Création des patterns pour récupérer le nombre de prédicats et de méthodes
        String condition = "while\\(.*\\)|if\\(.*\\)|for\\(.*\\)" +
                "|\\bwhile\\b|\\bif\\b|\\bfor\\b|\\b[{]?else[}]?\\b|\\bcase\\b|\\bdefault\\b";
        String boolean_condition = "&&|[|]{2}|\\?|and";
        String method = ".+\\s.+\\(\\w+\\s\\w+\\)";
        Pattern condition_pattern = Pattern.compile(condition);
        Pattern boolean_condition_pattern = Pattern.compile(boolean_condition);
        Pattern method_pattern = Pattern.compile(method);

        String[] new_line = {"", "false"};
        try {
            while((line=texte.readLine()) != null){
                new_line[0] = line;
                new_line[1] = Boolean.toString(complex_commented);
                no_comment_line = reform_line(new_line);
                complex_commented = Boolean.parseBoolean(no_comment_line[1]);
                if(condition_pattern.matcher(no_comment_line[0]).find()) {
                    mccabe_complexity += 1;
                }
                if(boolean_condition_pattern.matcher(no_comment_line[0]).find()) {
                    String[] split_line =  no_comment_line[0].split("\\s");
                    for (String s : split_line) {
                        if (boolean_condition_pattern.matcher(s).find()) {
                            mccabe_complexity += 1;
                        }
                    }
                }
                if(method_pattern.matcher(no_comment_line[0]).find()) {
                    mccabe_complexity += 1;
                    number_of_methods += 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(number_of_methods == 0) {
            return 1;
        }
        return mccabe_complexity / number_of_methods;
    }
}



// je suppose si quelqu'un m'ecrit un if direct comme ca apres un commentaire
// c du mauvais code
/*



*/ //if() // pas encore pris en compte

