;app start
(ns junkyard-client.start
  (:require [io.pedestal.app.protocols :as p]
            [io.pedestal.app :as app]
            [io.pedestal.app.render.push :as push-render]
            [io.pedestal.app.render :as render]
            [io.pedestal.app.messages :as msg]
            [junkyard-client.behavior :as behavior]
            [junkyard-client.rendering :as rendering]
            [junkyard-client.simulated.services :as services]
            [junkyard-client.post-processing :as post]))

(defn create-app [render-config]
  (let [app (app/build (post/add-post-processors behavior/example-app))
        render-fn (push-render/renderer "content" render-config render/log-fn)
        app-model (render/consume-app-model app render-fn)]
    (app/begin app)
    (p/put-message (:input app) {msg/type :inc msg/topic [:my-counter]})
    {:app app :app-model app-model}))

(defn ^:export main []
  (let [app (start/create-app d/data-renderer-config)
        services (services/->MockServices (:app app))]
    (p/start services)
    app))
