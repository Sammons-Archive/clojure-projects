(ns ^:shared junkyard-client.html-templates
  (:require [io.pedestal.app.templates :only [tfn dtfn tnodes]]))

(defmacro junkyard-client-templates
  []
  {:junkyard-client-page (dtfn (tnodes "junkyard-client.html" "hello") #{:id})
   :other-counter (dtfn (tnodes "tutorial-client.html" "other-counter") #{:id}
  })

 (defn whan [x] (+ x 2))
