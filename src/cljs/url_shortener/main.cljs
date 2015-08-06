(ns url-shortener.main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [cljs.core.async :refer [<! put! chan]]
            [ajax.core :refer [GET POST]])
  (:import [goog Uri]))

(enable-console-print!)

(defn listen [el type]
  (let [out (chan)]
    (events/listen el type
      (fn [e]
        (.preventDefault e)
        (put! out e)))
    out))

(defn log [data]
 (.log js/console data))

(defn extract-value [id]
  (str id "=" (js/encodeURIComponent (.-value (dom/getElement id)))))

(defn error-handler [{:keys [status status-text]}]
  (log (str "something bad happened: " status " " status-text)))


(defn post-request [url data content-type handler]
  (POST url
    {:params data
     :format content-type
     :handler handler
     :error error-handler}))

(defn show-short-url [res]
  (log res)
  (dom/setTextContent (dom/getElement "short-url") res))

(defn submit-data []
  (let [data (str (extract-value "url") "&"
             (extract-value "__anti-forgery-token"))]
    (post-request "/short" data "application/x-www-form-urlencoded" show-short-url)))

(defn init []
  (let [submits (listen (dom/getElement "form") "submit")]
    (go (while true
      (let  [e (<! submits)]
        (submit-data))))))

(init)
