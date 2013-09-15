(ns instaparse-demo.core
  (:require [instaparse.core :as insta])
  (:gen-class))

(def parser
  (insta/parser
   "
   expr = number | vector | operation
   operation = (number space* operator space*)+ number
   operator = '+' | '-' | '*' | '/'
   vector = snumber+ number
   <snumber> = (number space)*
   <space> = <#'[ ]+'>
   number = #'[0-9]+'
   "))

(def operators
  {"+" +
   "-" -
   "/" /
   "*" *})

(defn eval-op [op1 operator op2 & others]
  (let [result (operator op1 op2)]
    (if (empty? others)
      result
      (apply eval-op result others))))

(def transforms
  {:number read-string
   :vector vector
   :operator operators
   :operation eval-op
   :expr identity})

(defn parse [in]
  (->> (parser in) (insta/transform transforms)))
