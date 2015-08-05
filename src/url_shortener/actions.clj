(ns url-shortener.actions
  (:use url-shortener.views))

(def db (atom {:a "http://www.google.com"}))

(defn create-valid-url [url]
  (if (.contains url "http")
    url
    (str "http://" url)))

(defn look-up [url]
  (create-valid-url (get @db (keyword url))))

(defn add-elem [elem]
  (swap! db conj elem))

(defn create-short-url [url]
  (str (count @db)))

(defn short-url [url-to-short headers]
  (let [hash-url (create-short-url url-to-short)
        base-url (second (first headers))]
    (add-elem (hash-map (keyword hash-url) url-to-short))
    (str base-url "/" hash-url)))

