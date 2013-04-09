(ns demo.web
  (:use ring.adapterjetty))

(defn app [req]
  (locking *out*
    (println (name (:request-method req)) (:uri req)))
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<h2>Hello world!</h2>"})

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT"))]
    (run-jetty app {:port port})))