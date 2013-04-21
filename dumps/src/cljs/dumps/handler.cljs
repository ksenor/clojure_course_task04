(ns js.dumps.handler
  (:require [enfocus.core :as efc])
            ;; [clojure.browser.repl :as repl]) // C+s repl
  (:require-macros [enfocus.macros :as efm])
  (:use [jayq.core :only [$ css inner]]))

; is not working :[
;; (repl/connect "http://localhost:9001/repl")

(defn start []
  js/alert "Hi!!!")

(set! (.-onload js/window) start)