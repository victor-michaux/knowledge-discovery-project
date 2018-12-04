#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from sklearn.datasets import fetch_20newsgroups
from sklearn import linear_model
from sklearn.pipeline import Pipeline
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
import pickle

twenty_train = fetch_20newsgroups(subset='train', shuffle=True)

svm_classifier = Pipeline([('vect', CountVectorizer(ngram_range= (1, 2))), ('tfidf', TfidfTransformer(use_idf=True)), ('clf', linear_model.SGDClassifier(loss="hinge", max_iter=100))])
svm_classifier = svm_classifier.fit(twenty_train.data, twenty_train.target)

filename = 'finalized_model.sav'
pickle.dump(svm_classifier, open(filename, 'wb'))