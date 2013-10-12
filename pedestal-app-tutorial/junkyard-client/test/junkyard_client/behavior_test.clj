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



;; transform tests
;(deftest test-set-value-transform
;  (is (= (set-transform {} {msg/type :set-value msg/topic [:new-val] :value "x"})
;         :value "x")))
(deftest test-set-transform
  "tests whether the transform fns work"
  (is (= (set-transform {} {msg/type :set msg/topic [:greeting] :value "x"})
          "x"))
  (is (= (set-transform {} {msg/type :set msg/topic [:greeting] :value nil})
          nil))
  (is (= (inc-transform 0 nil)
          1))
  (is (= (inc-transform 1 nil)
          2))
  (is (= (inc-transform nil nil)
          1)))
;; Build an application, send a message to a transform and check the transform
;; state

;(deftest test-app-state
;  (let [app (app/build example-app)]
;    (app/begin app)
;    (is (vector?
;         (test/run-sync! app [{msg/type :set-value msg/topic [:greeting] :value "x"}])))
;    (is (= (-> app :state deref :data-model :greeting) "x"))))
(defn- data-model [app]
  (-> app :state deref :data-model))

(defn- mult-to-vector [item n] 
  (apply vector (concat (repeat n item))))

(deftest test-app-state
  "test the app at a particular state"
  
  (let [app (app/build example-app)
        single-greet [{msg/type :set msg/topic [:greeting] :value "hello"}]
        multi-greet (mult-to-vector {msg/type :set msg/topic [:greeting] :value "hello"} 50)
        ]
  (is (test/run-sync! app single-greet))
  (is (= (data-model app) {:greeting "hello"}))
  (is (test/run-sync! app multi-greet));must finish before timeout
  (is (= (data-model app) {:greeting "hello"})))

  (let [app (app/build example-app)
        single-inc [{msg/type :inc msg/topic [:counter]}]
        ] ;refresh app
  (is (test/run-sync! app single-inc))
  (is (= (data-model app) {:counter 1}))
  ))

;; Use io.pedestal.app.query to query the current application model

;(deftest test-query-ui
;  (let [app (app/build example-app)
;        app-model (render/consume-app-model app (constantly nil))]
;    (app/begin app)
;    (is (test/run-sync! app [{msg/topic [:greeting] msg/type :set-value :value "x"}]))
;    (is (= (q '[:find ?v
;                :where
;               [?n :t/path [:greeting]]
;                [?n :t/value ?v]]
;              @app-model)
;           [["x"]]))))

