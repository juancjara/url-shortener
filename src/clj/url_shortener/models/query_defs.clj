(ns url-shortener.models.query-defs
  (:require [environ.core :refer [env]]
            [yesql.core :refer [defqueries]]))

;;follow this tutorial www.jarrodctaylor.com/posts/Compojure-Address-Book-Part-4/

(defqueries "url_shortener/models/queries.sql" {:connection "postgres://address_book_user:password1@127.0.0.1:5432/address_book"})

