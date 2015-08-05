(ns url-shortener.views
  (:use [hiccup core page]))

(defn layout-view [& content]
  (html5
    [:head
      [:title "new page"]
    [:body content ]]))

(defn index-page []
  (layout-view
    [:h1 "Url Shortener"]
      [:form {:method "GET" :action "/short"}
       [:input {:type "text" :name "url"}]
       [:button "Send"]]))

(defn url-page [url]
  (layout-view [:h1  url]))
