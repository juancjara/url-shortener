(ns url-shortener.handler
  (:use url-shortener.views)
  (:use url-shortener.actions)
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn ggwp [& data]
  (println (str data))
  (println data)
  "testerino")

(defroutes app-routes
  (GET "/" [] (index-page))
  ;;(POST "/test" {{url :url} :params headers :headers}  (ggwp url headers))
  (POST "/short" {{url :url} :params headers :headers} (short-url url headers))
  (GET "/:foo" {{foo :foo} :params } (redirect (look-up foo)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
