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
                 [lib-noir "0.4.9"]
                 ;; [com.cemerick/piggieback "0.0.4"] ; is not working now
                 [enfocus "1.0.1"]
                 [jayq "2.3.0"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.3"]
            [lein-cljsbuild "0.3.0"]]
  :ring {:handler dumps.handler/app}
  :source-paths ["src/clj" "src/cljs"]
  ;; :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  
  :cljsbuild
  {:builds
   [{:source-paths ["src/cljs"],
     :id "main",
     :compiler
     {:pretty-print true,
      :output-to "resources/public/js/main.js",
      :warnings true,
      ;; :externs ["externs/jquery-1.9.js"],
      :optimizations :whitespace,
      :print-input-delimiter false}}
   
    {:source-paths ["src/clj" "src/cljs"],
     :id "test",
     :compiler
     {:pretty-print true,
      :output-to "resources/public/js/tests.js",
      :optimizations :whitespace,
      :print-input-delimiter false}}],
    :test-commands
    {"unit"
     ["phantomjs"
      "phantomjs/unit-test.js"
      "http://localhost:3000/html/test.html"]}}
   :war {:name "dumps.war"}
  :profiles {:dependencies [[ring-mock "0.1.3"]] 
             :production {:env {:production true}}}
  :main dumps.handler)