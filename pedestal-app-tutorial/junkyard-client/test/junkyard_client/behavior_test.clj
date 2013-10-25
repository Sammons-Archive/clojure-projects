(ns junkyard-client.behavior-test
  (:require [io.pedestal.app :as app]
            [io.pedestal.app.protocols :as p]
            [io.pedestal.app.tree :as tree]
            [io.pedestal.app.messages :as msg]
            [io.pedestal.app.util.test :as test]
            [junkyard-client.html-templates :as templates])
  (:use clojure.test
        junkyard-client.behavior
        [io.pedestal.app.query :only [q]]))



;; transform tests
(deftest test-transform
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
          1))
  (is (= (set-transform {} {msg/type :set msg/topic [:try-button] :value "YAY"})
          "YAY"))
  (is (= (set-transform {} {msg/type :set msg/topic [:try-button] :value nil})
        nil))
  )

;; App state tests
(defn- data-model [app]
  "deconstructs the app to access states"
  (-> app :state deref :data-model))

(defn- mult-to-vector [item n] 
  "creates a vector of n items"
  (apply vector (concat (repeat n item))))

(deftest test-app-state
  "test the app at a particular state"
  
  (let [app (app/build example-app)
        single-greet [{msg/type :set msg/topic [:greeting] :value "hello"}]
        multi-greet (mult-to-vector {msg/type :set msg/topic [:greeting] :value "hello"} 50)
        try-button [{msg/type :set msg/topic [:set-value] :value "yaaaay"}]
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

;test the template macros

;test rendering

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
