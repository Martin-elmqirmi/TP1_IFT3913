
    /**
     * The Foo class is a silly example to illustrate documentation 
     * comments.
     * 
     * // 
     * 
     * /*
     */
    public class Foo { 

        /**
        * 
        * Test JavaDoc 
        * An integer to keep track of for fun.
        *
        */
        private int count; 

        // Je suis entrain de tester ce type de commentaire

        int A = 55; // Voyons si il detect celui la  

        String C = "Lalalal"; /* hmm, augmentons un peu la difficulte */

        /* Yeahh Super difficile */ double montant = 0.0; /* oopppsss */ int piege = 1 ; // winnnnnnnnnn

        int D = 55; 

        
        /*
        Au debut je voulais 
        utiliser ce texte mais la je veux plus // OMG apprends a indenter :)
        l'utiliser. 
        Il est
        Trop mal ecrit. pffffff /** Nopppepe
        /*
        */

        /**
        * Increment a value by delta and return the new value. 
        *
        * @param  delta   the amount the value should be incremented by
        * @return         the post-incremented value
        */
        int increment(int delta) /* Apprends a commenter  */{
            System.out.println("Resultat: "+ (A+D+piege));
        }
    } 

