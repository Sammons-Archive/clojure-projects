(ns junkyard-client.html-templates
  (:use [io.pedestal.app.templates :only [tfn dtfn tnodes]]))

(defmacro junkyard-client-templates
  []
  {:junkyard-client-page (dtfn (tnodes "junkyard-client.html" "tutorial" [[:#other-counters]]) #{:id})
   :other-counter (dtfn (tnodes "junkyard-client.html" "other-counter") #{:id})})