;; копируется в lein-repl и выполняется 1 раз
(ns dumps.sqlitedb.creator
  (:require [clojure.java.jdbc :as j]))

;; описываем спецификацию файлика sqlite3 - ne.sqli 
(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "./ne.sqli"
   })

;; механизм создания ne.sqli - Таблица и поля, которые у нас будут
(defn create-db []
  (try (j/with-connection db 
         (j/create-table :dumps
                       [:date :text]
                       [:body :text]))
       (catch Exception e (println e))))

(create-db)