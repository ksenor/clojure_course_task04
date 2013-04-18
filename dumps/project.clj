(defproject dumps "0.0.1-SNAPSHOT"
  :description "делаем себе дампыыы"
  :url "http://jspcj.com"
  ;; :license {:name "dumps"
  ;;           :url "http://dmpz.ru"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [me.raynes/laser "1.1.1"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [korma "0.3.0-RC5"]
                 [lib-noir "0.4.9"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler dumps.handler/app}
  :profiles {:dependencies [[ring-mock "0.1.3"]] 
             :production {:env {:production true}}}
  :main dumps.handler)