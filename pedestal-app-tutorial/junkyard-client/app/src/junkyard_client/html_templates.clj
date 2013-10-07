(ns junkyard-client.html-templates
  (:use [io.pedestal.app.templates :only [tfn dtfn tnodes]]))

(defmacro junkyard-client-templates
  []
  {:junkyard-client-page (dtfn (tnodes "junkyard-client.html" "hello") #{:id})})
