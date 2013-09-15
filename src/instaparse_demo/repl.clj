(ns instaparse-demo.repl
  (:require instaparse-demo.core)
  (:gen-class))

(defn repl []
  (do
    (print "Boo> ")
    (flush))
  (let [input (read-line)]
    (println (instaparse-demo.core/parse input))
    (recur)))


(defn -main [& args]
  (println "Boo yah!")
  (repl))
