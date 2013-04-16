(ns dumps.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" []  "ok")
  (GET "/req" r (str r))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))