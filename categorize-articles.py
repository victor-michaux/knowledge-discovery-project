# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

from sklearn.datasets import fetch_20newsgroups
import pickle

twenty_newsgroup_train_dataset = fetch_20newsgroups(subset='train', shuffle=True)

modelPath = 'finalized_model.sav'

inputFile = "raw-articles.txt"
file = open(inputFile)

lines = [line.rstrip('\n') for line in file]

articleDateArray = []
articleContentArray = []
articleAuthorArray = []

for j in range(0,len(lines),3):
    articleContentArray.append(lines[j])
    articleAuthorArray.append(lines[j+1])
    articleDateArray.append(lines[j+2])
    

loaded_model = pickle.load(open(modelPath, 'rb'))
result = loaded_model.predict(articleContentArray)

outputFile = open("articles.txt","w") 

for i in range(len(result)):
    outputFile.write("article-" + str(i+1) + "\n")
    outputFile.write(articleContentArray[i] + "\n")
    outputFile.write(str(twenty_newsgroup_train_dataset.target_names[result[i]]) + "\n") 
    outputFile.write(articleAuthorArray[i] + "\n")
    outputFile.write(str(articleDateArray[i]) + "\n")
outputFile.close()