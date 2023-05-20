/************************************************************************************************************************
*     INFORMATIONS SUR LES ETUDIANTS : Lebrima Wail Dhaya Eddine, Section A, Groupe 2.                                  *                                                                                                
*                                      Amer El Khedoud Amir, Section A, Groupe 2.                                       *                                                                                                
*                                                                                                                       *                                                                                                                                                                                                
*************************************************************************************************************************/



// Importation des classes Scanner, List, ArrayList et Collectors
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class testThl {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        // Demander à l'utilisateur la taille maximale k des mots dans le langage L(G)
        System.out.print("Enterez la taille max (k) des mots appartient au langage L(G): ");
        int k = scanner.nextInt();

        System.out.println("");
    
        // Afficher le langage L(G) généré
        System.out.println("le language original avec la fonction :");
        System.out.println(generate(k).toString());
    
        System.out.println("");

        // Afficher le langage miroir de L(G)
        System.out.println("le language mirroire :");
        System.out.println(generateR(k).toString());
    
        System.out.println("");

        Scanner scann = new Scanner(System.in);
    
        // Demander à l'utilisateur la puissance n du langage L(G)
        System.out.print("Enterez la puissance (n) du langage L(G): ");
        int n = scann.nextInt();
        System.out.println("le langage puissance "+n+" :");
    
        // Afficher le langage L(G) élevé à la puissance n
        System.out.println(power(k,n).toString());
    
        System.out.println("");

        Scanner scan = new Scanner(System.in);
    
        // Demander à l'utilisateur un mot à vérifier
        System.out.print("Enterez le mot que vous voulez verifier (w) :");
        String w = scan.nextLine();
    
        // Vérifier si le mot w appartient au langage L(G) et afficher le résultat
        Analyseur(w);
    }
  
    static List<String> generate(int k){ 
        // Crée une nouvelle liste vide de type String appelée "words"
        List<String> words = new ArrayList<String>();
    
        // Boucle de 1 à k inclus
        for (int i = 1; i <= k; i++) {
        
            // Si i = 1, ajoute "c" à la liste "words"
            if (i == 1) {
            words.add("c");
            } 
        
            // Si i est divisible par 3 et a un reste de 1
            else if (i % 3 == 1 ) {
            
                // Crée une copie de la liste "words" appelée "nouveauxwords"
                List<String> nouveauxwords = new ArrayList<>(words);
            
                // Boucle à travers chaque mot dans la liste "nouveauxwords"
                for (String mot : nouveauxwords) {
                
                    // Crée deux nouveaux words en ajoutant "a" et "aa" au début et "bb" et "b" à la fin de chaque mot
                    String mot1 ="a" + mot + "aa";
                    String mot2 ="bb" + mot + "b";

                    // Si le mot1 n'est pas déjà dans la liste "words", l'ajoute à la liste
                    if (!words.contains(mot1)) {
                        words.add(mot1);
                    }

                    // Si le mot2 n'est pas déjà dans la liste "words", l'ajoute à la liste
                    if (!words.contains(mot2)) {
                        words.add(mot2);
                    }
                }            
            }        
        }         
        words.add(0, "epsilon");
        // Retourne la liste de words "words"
        return words;
    }

    static List<String> generateR(int k){ 
        // Initialise une liste de mots vides
        List<String> mots = new ArrayList<String>();
    
        // Itère k fois pour générer de nouveaux mots
        for (int i = 1; i <= k; i++) {
            // Si c'est la première itération, ajoute le mot "c" à la liste de mots
            if (i == 1) {
                mots.add("c");
            } 
            // Si l'itération est un multiple de 3 plus 1
            else if (i % 3 == 1) {
                // Crée une nouvelle liste de mots à partir de la liste existante
                List<String> nouveauxMots = new ArrayList<>(mots);
                // Itère sur chaque mot dans la nouvelle liste de mots
                for (String mot : nouveauxMots) {
                    /* Crée deux nouveaux mots en ajoutant "aa" au début et "a" à la fin du mot actuel
                    et  "b" au début et "bb" à la fin du mot actuel (l'inverse de la grammaire original)*/
                    String nouveauMot1 = "aa" + mot + "a";
                    String nouveauMot2 = "b" + mot + "bb";
                    // Ajoute les deux nouveaux mots à la liste de mots
                    mots.add(nouveauMot1);
                    mots.add(nouveauMot2);
                }            
            }        
        }         
        mots.add(0,"epsilon");
        // Retourne la liste de mots sans redondances
        return mots.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> power(int k, int n) {
        List<String> powerN = new ArrayList<>();
        // Cas de base : n est égal à 0, on retourne une liste contenant une chaîne vide.
        if (n == 0) {
            powerN.add("");
            return powerN;
        }

        // On génère toutes les chaînes de longueur 1 à partir des k caractères et on les ajoute à powerN.
        List<String> generated = generate(k);
        generated.remove(0);
        powerN.add("");
        // On boucle n-1 fois et à chaque itération on construit les chaînes de longueur i+1 à partir des chaînes de longueur i stockées dans powerN.
        for (int i = 0; i < n; i++) {
            List<String> newPowerN = new ArrayList<>();

            /* Pour chaque chaîne w générée et chaque chaîne prevWord dans powerN,
               on concatène w et prevWord et on ajoute la chaîne obtenue à newPowerN si elle n'est pas déjà dedans.*/
            for (String w : powerN) {
                for (String prevWord : generated) {
                    newPowerN.add(w + prevWord);
                }
            }
            // On met à jour powerN avec les chaînes de longueur i+1.
            powerN = newPowerN;
        }
        powerN.addAll(0,generated);
        powerN.add(0,"epsilon");
        // On retourne la liste powerN contenant toutes les chaînes de longueur n.
        return powerN;
    }

    static void Analyseur(String w) {
        //vérification lexical du mot w
        for (int i = 0;i<w.length() ;i++ ) {
            if(w.charAt(i)!='a' && w.charAt(i)!='b' && w.charAt(i)!='c'){
                System.out.println("le mot "+w+" est lexicalement incorrect.");
                return;
           }
        }
            
        if(w==""){
          System.out.println("le mot vide (epsilon) appartient au langage L(G).");
        }else{
            // Initialisation d'une variable booléenne b à true et d'une variable string sauv à w
            boolean b = true;
            String sauv = w;
        
            // Boucle while qui s'exécute tant que w a plus d'un caractère et que b est true
            while(w.length() != 1 && b) {
                // Vérifie si w satisfait une des deux règles du langage L(G)
                if (w.charAt(0) == 'a' && w.charAt(w.length() - 1) == 'a' && w.charAt(w.length() - 2) == 'a') {
                    // Si la première et la dernière lettre de w sont 'a', et que la lettre avant la dernière lettre est également 'a',
                    // on supprime la première lettre et les deux dernières lettres de w à l'aide de la méthode substring
                    w = w.substring(1, w.length() - 2);
                } else if (w.charAt(0) == 'b' && w.charAt(1) == 'b' && w.charAt(w.length() - 1) == 'b') {
                    // Si les deux premières lettres de w sont 'b' et que la dernière lettre est également 'b',
                    // on supprime les deux premières lettres et la dernière lettre de w à l'aide de la méthode substring
                    w = w.substring(2, w.length() - 1);
                } else {
                    // Si w ne satisfait aucune des règles, on met b à false pour sortir de la boucle
                    b = false;
                }  
            }

            // Vérifie la valeur de b après la boucle
            if (b) {
                // Si b est true, cela signifie que w appartient au langage L(G)
                System.out.println("le mot " + sauv + " appartient au langage L(G).");
            } else {
                // Sinon, cela signifie que w n'appartient pas au langage L(G)
                System.out.println("le mot " + sauv + " n'appartient pas au langage L(G).");
            }
        }     
    }
}