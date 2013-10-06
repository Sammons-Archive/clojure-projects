(ns toast-to-clojure.core
  (:gen-class))
(def vowel? (set "aeiou"))

(defn pig-latin [word] ; defines a function
  ; word is expected to be a string
  ; which can be treated like a sequence of characters.
  (let [first-letter (first word)] ; assigns a local binding
    (if (vowel? first-letter)
      (str word "ay") ; then part of if
      (str (subs word 1) first-letter "ay")))) ; else part of i

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print \tab\l\i\t\e\r\a\l\space"usage"\newline ) 
  (println "normal string")
  (println "I don't understand :keyword usage")
  (println "I also don't understand ::keywords from current namespace")
  (def words "ABC123")
  (println "before regex re-seq application:" words)
  (println ["after applying [0-9A-Z]*", (re-seq #"[0-9A-Z]*" words)])
  (println "commas count as whitespace!")
  (println "'(items) does not evaluate, where (items) does")
  (println (String/map-str String/length ["a " "b"] ))
  (println (pig-latin "what?")))

