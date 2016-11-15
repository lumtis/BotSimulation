# Ensimag 2A POO - TP 2016/17
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testInvader testLecture

simulateur:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/LancerSimulateur.java


doc:
	javadoc private gui.jar doc src/simulation/*.java


# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:bin/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeInvader
exeMap1:
	java -classpath bin:bin/gui.jar LancerSimulateur cartes/carteSujet.map

exeMap2:
	java -classpath bin:bin/gui.jar LancerSimulateur cartes/desertOfDeath-20x20.map

exeMap3:
	java -classpath bin:bin/gui.jar LancerSimulateur cartes/mushroomOfHell-20x20.map

exeMap4:
	java -classpath bin:bin/gui.jar LancerSimulateur cartes/spiralOfMadness-50x50.map

doc:
	javadoc private gui.jar doc src/simulation/*.java

clean:
	rm -rf bin/*.class
