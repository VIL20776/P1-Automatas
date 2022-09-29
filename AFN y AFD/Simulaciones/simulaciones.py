def eCerradura(dictionary, finalNode, node):
    if not finalNode == node:
        falseStates = []
        if type(node) == list:
            for i in node:
                falseStates.append(i)
        else:
            falseStates.append(node)
        for i in falseStates:
            if not i == finalNode:
                subDict = dictionary[i]
                key = list(subDict.keys())
                if key[0] == "ε":
                    values = list(subDict.values())[0]
                    if type(values) == list:
                        for k in values:
                            if k not in falseStates:
                                falseStates.append(k)
                    else:
                        if values not in falseStates:
                            falseStates.append(values)
        return falseStates

def move(dictionary,finalNode,states,label):
    #print("move con los estados ",states," y la etiqueta ", label)
    result=[]
    for i in states:
        if not i == finalNode:
            subDict = dictionary[i]
            key = list(subDict.keys())[0]
            if key == label:
                values = list(subDict.values())[0]
                if type(values) == list:
                    for k in values:
                        result.append(k)
                else:
                    result.append(values)
    temp = []
    #print("e-cerradura con los estados: ",result)
    for i in result:
        temp.append(eCerradura(dictionary,finalNode,i))
    for i in temp:
        if type(i) == list:
            for j in i:
                result.append(j)
        elif i == None:
            pass
        else:
            result.append(i)
    #print("Los estados resultantes fueron: ", result)
    return list(set(result))

def simulationNFA(dictionary, initial, final, expresion, subsets,alphabet):
    S = []
    S.append(sorted(eCerradura(dictionary,final,initial)))
    #print("Se parte desde el estado ",S[0],"\nla expresion es: ",expresion)
    cont = 0
    for i in expresion:
        if i in alphabet:
            S.append(sorted(move(dictionary,final,S[cont],i)))
            cont = cont +1 
        else: 
            return "No"
    if subsets[len(subsets)-1] in S:
        return "Sí"
    else:
        return "No"

def simulationFDA(subsetsTrans, states, expresion, alphabet):
    S = []
    Actualstate = states[0]
    for i in expresion:
        if i in alphabet:
            flag = False
            for j in subsetsTrans:
                if j[0] == Actualstate and j[2] == i:
                    flag = True
                    S.append(j[1])
                    Actualstate = j[1]
            if not flag:
                #print("No se llego a ningun subconjunto con estado de aceptación")
                return "No"
        else:
            return "No"
    #print(subsetsTrans)
    if states[len(states)-1] in S:
        return "Sí"
    else:
        return "No"