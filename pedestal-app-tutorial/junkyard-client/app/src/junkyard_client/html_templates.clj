(ns junkyard-client.html-templates
  (:require [io.pedestal.app.render.events :as events])
  (:use [io.pedestal.app.templates :only [tfn dtfn tnodes]]))

(defmacro junkyard-client-templates
  []
  ;; Extract the 'hello' template from the template file junkyard-client.html.
  ;; The 'dtfn' function will create a dynamic template which can be
  ;; updated after it has been attached to the DOM.
  ;;
  ;; To see how this template is used, refer to
  ;;
  ;; app/src/junkyard_client/rendering.cljs
  ;;
  ;; The last argument to 'dtfn' is a set of fields that should be
  ;; treated as static fields (may only be set once). Dynamic templates
  ;; use ids to set values so you cannot dynamically set an id.
  {:junkyard-client-page (dtfn (tnodes "junkyard-client.html" "hello") #{:id})})

(defn button-enable [r [_ path transform-name messages] d]
  (events/send-on-click (dom/by-id "msg-button")
                          d
                          transform-name
                          [{msg/type :set msg/topic [:set-value] :value "Pedestal Rocks!"}]))
;; Note: this file will not be reloaded automatically when it is changed.
