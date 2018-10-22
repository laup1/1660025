# debug
#set -x

avec_couleur=0

if [ $avec_couleur -eq 1 ]; then

    rouge=$(tput setaf 1; tput setab 0)
    vert=$(tput setaf 2; tput setab 0)
    defaut=$(tput setaf 7; tput setab 0)

    titre=$(tput setaf 0; tput setab 7)

else

    rouge=""
    vert=""
    defaut=""
    titre=""

fi

num_test=0;
num_section=0;

quitter(){
    echo $defaut
    exit
}

section(){
    description=$1

    #num_test=0

    echo $titre

    printf "SECTION %02d: %s" $num_section "$description"
    num_section=$((num_section+1))

    echo $defaut

}

afficher_grep(){

    echo_grep=$1
    chemins_fichiers=$2

    printf "\ncommande:\t$echo_grep \\"

    for chemin in $chemins_fichiers; do
        printf "\n"
        printf "\t\t\t%s \\" $chemin
    done

    # Effacer le dernier \\
    printf "\b \n"

}

afficher_resultat_attendu(){

    if [ $min_attendu -eq $max_attendu ]; then
        printf "résultat voulu:\t$min_attendu\n"

    else

        printf "résultat voulu:\t>=$min_attendu && <=$max_attendu\n"

    fi

}

tester(){
    options_grep=$1
    options_grep_echo=$2
    patron_grep=$3
    fichiers_grep=$4
    min_attendu=$5
    max_attendu=$6

    echo_options="$options_grep -Hno $options_grep_echo"
    echo_grep="grep $echo_options \"$patron_grep\""

    echo $defaut

    printf "TEST %02d: " $num_test
    num_test=$((num_test+1))

    chemins_fichiers=""

    for nom_fichier in $fichiers_grep; do

        chemin=$(find ./app/src/main/java -name "$nom_fichier")

        if [ -z "$chemin" ]; then

            printf "${rouge}[ECHOUE]\n"
            printf "\t fichier introuvable:\t$nom_fichier\n"

            echo $defaut
            return 1

        fi

        if [ -n "$chemins_fichiers" ]; then
            chemins_fichiers="$chemins_fichiers $chemin"
        else
            chemins_fichiers="$chemin"

        fi

    done


    # Effectuer le test
    grep="grep $options_grep $patron_grep $chemins_fichiers"
    resultat=$($grep | grep -c .)

    if [ $resultat -ge $min_attendu -a $resultat -le $max_attendu ]; then

        printf "${vert}[REUSSI]\n"
        printf "${defaut}"

    else

        printf "${rouge}[ECHOUE]\n"

    fi

    afficher_grep "$echo_grep" "$chemins_fichiers"
    afficher_resultat_attendu $min_attendu $max_attendu
    printf "résultat reçu:\t$resultat\n"

    printf "${defaut}"
}

# SECTION 00
description="MParametres ne stoque plus largeur/hauteur/pourGagner"
section "$description"

options_grep="-R"
options_grep_echo="\b"
fichiers_grep="MParametres.java"
min_attendu=0;
max_attendu=0;

# TEST 00
patron_grep="p[^(]*nt[^(]*hauteur[^(]*;"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST 01
patron_grep="p[^(]*nt[^(]*largeur[^(]*;"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST 02
patron_grep='p[^(]*nt[^(]*pourGagner[^(]*;' 
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu


# SECTION 01
description="MParametresPartie stoque largeur/hauteur/pourGagner"
section "$description"

options_grep="-R"
fichiers_grep="MParametresPartie.java"
min_attendu=1;
max_attendu=1;

# TEST 00
patron_grep="p[^(]*nt[^(]*hauteur[^(]*;"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST 01
patron_grep="p[^(]*nt[^(]*largeur[^(]*;"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST 02
patron_grep="p[^(]*nt[^(]*pourGagner[^(]*;"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# SECTION 03

description="VPartie utilise l'observation"
section "$description"

options_grep="-R"
fichiers_grep="VPartie.java"
min_attendu=1;
max_attendu=1;

# TEST
patron_grep="observerModele(.*$"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu


# SECTION 04
description="MPartie fournit des actions"
section "$description"

options_grep="-R"
options_grep_echo="-A1"
fichiers_grep="MPartie.java"
min_attendu=1;
max_attendu=1;

# TEST
patron_grep="fournirAction(.*$"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu


# SECTION 05
description="Les vues de la partie utilisent des actions"
section "$description"

options_grep="-R"
options_grep_echo="\b"
fichiers_grep="VCase.java VEntete.java VGrille.java VPartie.java Vue.java"
min_attendu=1;
max_attendu=1;

# TEST
patron_grep="demanderAction(.*$"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# SECTION 06
description="Les vues de la partie ne modifie **pas** directement les modèles"
section "$description"

options_grep="-R"
options_grep_echo="\b"
fichiers_grep="VCase.java  VEntete.java  VGrille.java VPartie.java  Vue.java"
min_attendu=0;
max_attendu=0;

# TEST
patron_grep="jouerCoup"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu



#### FIN DE LA VALATION EN CLASSE

quitter

#### BONUS

description="VParametres utilise l'observation"
section "$description"

options_grep="-R"
fichiers_grep="VParametres.java"
min_attendu=1;
max_attendu=1;

# TEST
patron_grep="observerModele(.*$"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# SECTION 06
description="Les vues ne modifie **pas** directement les modèles"
section "$description"

options_grep="-R"
options_grep_echo="\b"
fichiers_grep="VCase.java  VEntete.java  VGrille.java  VMenuPrincipal.java  VParametres.java  VPartie.java  Vue.java"
min_attendu=0;
max_attendu=0;

# TEST
patron_grep="setHauteur"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST
patron_grep="setLargeur"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST
patron_grep="setPourGagner"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST
patron_grep="\.hauteur"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST
patron_grep="\.largeur"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST
patron_grep="\.pourGagner"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# SECTION 02
description="Aucune instance statique de MParametres ou MPartie"
section "$description"

options_grep="-R"
fichiers_grep="*.java"
min_attendu=0;
max_attendu=0;

# TEST 00
patron_grep="static.*MParametres"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# TEST 01
patron_grep="static.*MPartie"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# SECTION 04
description="MParametres fournit des actions"
section "$description"

options_grep="-R"
options_grep_echo="-A1"
fichiers_grep="MParametres.java"
min_attendu=3;
max_attendu=3;

# TEST
patron_grep="fournirAction(.*$"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu


# SECTION 04
description="Les activités fournissent des actions"
section "$description"

options_grep="-R"
options_grep_echo="-A1"
fichiers_grep="Activite.java AMenuPrincipal.java AParametres.java"
min_attendu=2;
max_attendu=3;

# TEST
patron_grep="fournirAction(.*$"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

# SECTION 05
description="Les vues des menus utilisent des actions"
section "$description"

options_grep="-R"
options_grep_echo="\b"
fichiers_grep="VMenuPrincipal.java VParametres.java"
min_attendu=5;
max_attendu=6;

# TEST
patron_grep="demanderAction(.*$"
tester $options_grep $options_grep_echo $patron_grep "$fichiers_grep" $min_attendu $max_attendu

quitter
