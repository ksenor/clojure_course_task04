(ns dumps.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defmacro rtr [res_path]
  `(slurp (str
          (-> (java.io.File. ".") .getAbsolutePath)
          ~res_path)))

(defroutes app-routes
  (GET "/" []  (rtr "/resources/i.html"))
  ;; (POST "/" ff)

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))