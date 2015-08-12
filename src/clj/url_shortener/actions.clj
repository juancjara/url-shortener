(ns url-shortener.actions
  (:use url-shortener.views)
  (:require [url-shortener.models.query-defs :as query]))

(defn create-valid-url [url]
  (if (.contains url "http")
    urL
    (str "http://" url)))

(defn look-up [url]
  (let [row (first (query/find-url {:short url}))]
     (query/inc-clicks<! {:id (get row :id)})
     (get row :original)))

(defn create-short-url [url]
  (str (get (first (query/count-urls)) :count)))

(defn short-url [url-to-short headers]
  (let [hash-url (create-short-url url-to-short)
        base-url (second (first headers))]
    (query/insert-url<! {:short hash-url, :original (create-valid-url url-to-short)})
    (str base-url "/s/" hash-url)))
