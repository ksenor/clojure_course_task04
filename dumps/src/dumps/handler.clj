(ns dumps.handler
  (:use compojure.core
        korma.db
        korma.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clj-time.core :as time]))

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

;; ;; работа с либой korma

;; коннектимся к базе
(defdb sqli {:classname "org.sqlite.JDBC"
             :subprotocol "sqlite"
             :subname "resources/db/ne.sqli"})

;; задаем сущности
(defentity dumps
  (table :dumps)
  (database sqli)
  (entity-fields :date :body))

;; берем POST-запрос с переданным дампом и пишем в Базу
(defn add_dump_to_db [dump_txt] 
  (insert dumps (values 
                 {:date (.toString (time/now)) 
                  :body dump_txt})))

(defroutes app-routes
  (GET "/" []  (rtr "/resources/i.html"))
  (POST "/dmp" h (add_dump_to_db (gpp h :our_dump)))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))