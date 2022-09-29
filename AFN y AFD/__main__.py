from  PostfixGen.infixtopostfix import *
from AFN.builder import ThompsonAlgorithm
from Graph.graph import graph
from Graph.graphDirect import graphDirect
from Subconjuntos.subsets import eCerradura, subsetsBuilder
from Simulaciones.simulaciones import simulationNFA, simulationFDA
from DirectAFD.builder import buildAFD

if __name__ == "__main__":
    correcta = False
    while not correcta:
        option = input("\nDesea: 1.Crear AFN y luego AFD 2.AFD directo salir 3.Salirn\n>>")
        if not option == "3":
            expresion = input ("\nIngrese la expresión, por favor: ")
            if firstExpresion(expresion):
                if option == "1":
                    print("---------- CREACIÓN AFN Y AFD ----------")
                    nuevaexpresion = computableExpresion(expresion)
                    print("Expresion ingresada: ",expresion)
                    print("Expresion entendible para computadora: ",nuevaexpresion)
                    postfixexp = infixaPostfix(nuevaexpresion)
                    print("Expresion en Postfix:",postfixexp)
                    result = ThompsonAlgorithm(postfixexp)
                    nfaDict = result.getDict()
                    #print("Dict con el NFA resultante:\n",nfaDict)
                    prueba = graph(postfixexp,result)
                    transitions = prueba.createTransitions()
                    prueba.graphic(transitions,"Thompson")
                    s0 = result.getInitial()
                    sf = result.getFinal()
                    states = prueba.getStates()
                    #print("Nodo inicial: ",s0,"\nNodo de aceptación/final: ",sf)
                    alphabet = getAlphabet(expresion)
                    dictTrans = result.getDict()
                    subsets, numberSubsets, subsetsInfo, finalNodeInside = subsetsBuilder(alphabet, states, dictTrans, s0, sf)
                    prueba.graphSubsets(subsets,numberSubsets,"Subconjuntos",finalNodeInside)
                    simulation = True
                    while simulation:
                        segundaExpresion = input ("\n------------- Nueva Simulación -------------\nIngrese la expresión a evaluar, por favor:\n>> ")
                        resultSimNFA = simulationNFA(dictTrans, s0, sf, segundaExpresion, subsetsInfo, alphabet)
                        print("Resultado de la simulación AFN: ", resultSimNFA)
                        resultSimFDA = simulationFDA(subsets, numberSubsets, segundaExpresion, alphabet)
                        print("Resultado de la simulación AFD: ", resultSimNFA)
                        option = input ("¿Desea realizar otra simulacion?\n1.Sí   2.No\n>> ")
                        if option == "2":
                            simulation = False
                elif option == "2":
                    print("---------- CREACIÓN AFD DIRECTO ----------")
                    expresion = convertOperators(expresion)
                    print(expresion)
                    nuevaExpresionComputable = computableExpresion(expresion)
                    postfixexpNueva = infixaPostfix(nuevaExpresionComputable)+["#","_"]
                    print("Expresion que con la que se hará el arbol sintactico ",postfixexpNueva)
                    labelsDstates, acceptance = buildAFD(postfixexpNueva)
                    prueba = graphDirect(acceptance, labelsDstates, "AFD directo")
                else:
                    print("Opcion equivocada")
            else:
                print("La expresion tiene errores")
        else:
            correcta = True
            print("ADIOS")
