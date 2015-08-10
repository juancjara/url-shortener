(ns url-shortener.handler
  (:use url-shortener.views)
  (:use url-shortener.actions)
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (index-page))
  (POST "/short" {{url :url} :params headers :headers} (short-url url headers))
  (GET "/s/:foo" {{foo :foo} :params } (redirect (look-up foo)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
