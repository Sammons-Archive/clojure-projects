(ns ^:shared junkyard-client.behavior
    (:require [clojure.string :as string]
              [io.pedestal.app.messages :as msg]
              [io.pedestal.app.dataflow :as d]
              [io.pedestal.app :as app]))


;transform
(defn set-transform [_ message]
	(:value message))
(defn inc-transform [old-state _]
	((fnil inc 0) old-state))
;emit

;initialize transform enables (message creators)
(defn init-main [_])
;dataflow
(def example-app
  {:version 2
    :transform []
    :effect []
    :derive []
    :emit []
    })

(comment
  (defn example-transform [old-state message]
    ;; returns new state
    )
  (defn example-derive [old-state inputs]
    ;; returns new state
    )
  (defn example-emit [inputs]
    ;; returns rendering deltas
    )
  (defn example-effect [inputs]
    ;; returns a vector of messages which effect the outside world
    )
  (defn example-continue [inputs]
    ;; returns a vector of messages which will be processed as part of
    ;; the same dataflow transaction
    )
  ;; dataflow description reference
  {:transform [[:op [:path] example-transform]]
   :derive    #{[#{[:in]} [:path] example-derive]}
   :effect    #{[#{[:in]} example-effect]}
   :continue  #{[#{[:in]} example-continue]}
   :emit      [[#{[:in]} example-emit]]}
  )
