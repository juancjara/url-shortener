(ns url-shortener.views
  (:use [ring.util.anti-forgery])
  (:use [hiccup core page form]))

(defn layout-view [& content]
  (html5
    [:head
      [:title "new page"]
    [:body content ]]))

(defn index-page []
  (layout-view
    [:h1 "Url shortener"]
    (form-to {:id "form"} [:post "/short"]
      (anti-forgery-field)
      (text-field "url")
      (submit-button "send"))
    [:div {:id "short-url"} ""]
   (include-js "js/main.js")))

(defn url-page [url]
  (layout-view [:h1  url]))
