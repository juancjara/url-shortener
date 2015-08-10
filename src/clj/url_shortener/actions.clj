(ns url-shortener.actions
  (:use url-shortener.views))

(def db (atom {}))

(defn create-valid-url [url]
  (if (.contains url "http")
    url
    (str "http://" url)))

(defn look-up [url]
  (println "called")
  (let [key (keyword url)
        elem (get @db key)
        new-url (first elem)
        clicks (inc (second elem))]
     (swap! db (fn[a] (assoc a key [new-url clicks])))
     (println @db)
     (create-valid-url new-url)))

(defn add-elem [elem]
  (swap! db conj elem))

(defn create-short-url [url]
  (str (count @db)))

(defn short-url [url-to-short headers]
  (let [hash-url (create-short-url url-to-short)
        base-url (second (first headers))]
    (add-elem (hash-map (keyword hash-url) [url-to-short 0]))
    (str base-url "/" hash-url)))
