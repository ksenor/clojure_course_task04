(ns dumps.handler
  (:use compojure.core
        korma.db)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defmacro gpp [headers text_input_name] ;; get post params
  "headers - заголовки запроса вида {key val, ...}
   text_input_name (:key) - <textarea name=...>"
  `(str (-> ~headers :params ~text_input_name)))

(defmacro rtr [res_path] ;; render ro response
  "res_path ожидает путь, до 
   файла шаблона страницы, 
   от корня проекта"
  `(slurp (str
          (-> (java.io.File. ".") .getAbsolutePath)
          ~res_path)))

;; перед тем как работать с бд надо её создать 
;; поможет нам в этом - ../../ne_sqli_creator.clj

(defroutes app-routes
  (GET "/" []  (rtr "/resources/i.html"))
  (POST "/dmp" h (gpp h :our_dump))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))