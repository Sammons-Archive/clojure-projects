(ns ^:shared junkyard-client.behavior
    (:require [clojure.string :as string]
              [io.pedestal.app.messages :as msg]
              [io.pedestal.app.dataflow :as d]
              [io.pedestal.app :as app]))
;; While creating new behavior, write tests to confirm that it is
;; correct. For examples of various kinds of tests, see
;; test/junkyard_client/behavior-test.clj.


(defn set-transform [_ message]
	(:value message))
(defn inc-transform [old-state _]
	((fnil inc 0) old-state))
;(defn game-emitter [inputs]
;  (if (-> (d/added-inputs inputs) (get [:game]))
;    [[:transform-disable [:game] :new-game]]))


(defn init-main [_]
  [[:transform-enable [:counter] :inc [{msg/topic [:counter]}]]
   [:transform-enable [:try-button] :set [{msg/topic :set-value}]]])


;(def example-app
;  {:version 2
;   :transform [[:inc [:my-counter] inc-transform]]
;   :emit [{:init init-main}
;          [#{[:*]} (app/default-emitter [:main])]]})

(def example-app
  {:version 2
    :transform [
			    [:set [:*] set-transform]
			    [:inc [:counter] inc-transform]]
    :emit [
    	{:init init-main}
    	[#{[:*] [:**]} (app/default-emitter [])]
    	]})




;; Once this behavior works, run the Data UI and record
;; rendering data which can be used while working on a custom
;; renderer. Rendering involves making a template:
;;
;; app/templates/junkyard-client.html
;;
;; slicing the template into pieces you can use:
;;
;; app/src/junkyard_client/html_templates.cljs
;;
;; and then writing the rendering code:
;;
;; app/src/junkyard_client/rendering.cljs

(comment
  ;; The examples below show the signature of each type of function
  ;; that is used to build a behavior dataflow.

  ;; transform

  (defn example-transform [old-state message]
    ;; returns new state
    )

  ;; derive

  (defn example-derive [old-state inputs]
    ;; returns new state
    )

  ;; emit

  (defn example-emit [inputs]
    ;; returns rendering deltas
    )

  ;; effect

  (defn example-effect [inputs]
    ;; returns a vector of messages which effect the outside world
    )

  ;; continue

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
