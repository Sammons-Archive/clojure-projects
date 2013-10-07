(ns junkyard-client.behavior-test
  (:require [io.pedestal.app :as app]
            [io.pedestal.app.protocols :as p]
            [io.pedestal.app.tree :as tree]
            [io.pedestal.app.messages :as msg]
            [io.pedestal.app.render :as render]
            [io.pedestal.app.util.test :as test])
  (:use clojure.test
        junkyard-client.behavior
        [io.pedestal.app.query :only [q]]))

;; Test a transform function
(def inc-message {msg/type :inc msg/topic [:my-counter]})
(def swap-message {msg/type :set-val msg/topic [:val]})

(deftest test-inc-transform
  (is (= (inc-transform nil inc-message)   1))
  (is (= (inc-transform 0   inc-message)   1))
  (is (= (inc-transform 1   inc-message)   2))
  (is (= (inc-transform 1   nil)           2))
)
;; get current values from application
(defn- data-model [app] 
    (-> app :state deref :data-model))
;(deftest test-set-value-transform
;  (is (= (set-value-transform {} {msg/type :set-value msg/topic [:greeting] :value "x"})
;         "x")))

;; Build an application, send a message to a transform and check the transform
;; state

(deftest test-app-state
  (let [app (app/build example-app)]
      (is (test/run-sync! app [inc-message] :begin :default))
      (is (=  (data-model app)
          {:my-counter 1})))
  (let [app (app/build example-app)]
      (is (test/run-sync! app [inc-message inc-message inc-message] :begin :default))
      (is (= (data-model app) {:my-counter 3}))))

;    (app/begin app)
 ;   (is (vector?
 ;        (test/run-sync! app [{msg/type :set-value msg/topic [:greeting] :value "x"}])))
 ;   (is (= (-> app :state deref :data-model :greeting) "x"))))

;; Use io.pedestal.app.query to query the current application model

;(deftest test-query-ui
;  (let [app (app/build example-app)
;        app-model (render/consume-app-model app (constantly nil))]
;    (app/begin app)
;    (is (test/run-sync! app [{msg/topic [:greeting] msg/type :set-value :value "x"}]))
;    (is (= (q '[:find ?v
;                :where
;                [?n :t/path [:greeting]]
;                [?n :t/value ?v]]
;              @app-model)
;           [["x"]]))))

