from math import sqrt 

critics={'User1' : {'Movie1' : 2.5, 'Movie2' : 3.5, 'Movie3' : 3.0, 'Movie4' : 3.5, 'Movie5' : 2.5, 'Movie6' : 3.0},
         'User2' : {'Movie1' : 3.0, 'Movie2' : 3.5, 'Movie3' : 1.5, 'Movie4' : 5.0, 'Movie5' : 3.0, 'Movie6' : 3.5}}

def sim_distance(prefs, person1, person2):
    # get the list of shared items
    si={}
    for item in prefs[person1]:
        if item in prefs[person2]:
            si[item]=1
    if(len(si))==0: return 0
    
    sum_of_squares=sum(pow((prefs[person1][item] - prefs[person2][item]),2) for item in si)
    return 1/(1+ sqrt(sum_of_squares))

print(sim_distance(critics, 'User1', 'User2'))