;;tutorial here
;;http://zaiste.net/2014/02/web_applications_in_clojure_all_the_way_with_compojure_and_om/

(defproject url-shortener "0.1.0-SNAPSHOT"
  :description "Url shortener using clojure and clojurescript"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [org.clojure/clojurescript "0.0-3208"]
                 [cljs-ajax "0.3.13"]
                 [hiccup "1.0.5"]]

  :plugins [[lein-ring "0.8.13"]
            [lein-cljsbuild "1.0.6"]
            [lein-pdo "0.1.1"]]
  :ring {:handler url-shortener.handler/app}

  ;;compile both
  :aliases {"up" ["pdo" "cljsbuild" "auto," "ring" "server-headless"]}

  ;;updated when paths changed
  :source-paths ["src/clj"]

  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}}

  ;; cljsbuild options configuration
  :cljsbuild {:builds
    [{;; CLJS source code path
      :source-paths ["src/cljs"]

      ;; Google Closure (CLS) options configuration
      :compiler {;; CLS generated JS script filename
       :output-to "resources/public/js/main.js"

       ;; minimal JS optimization directive
       :optimizations :whitespace

       ;; generated JS code prettyfication
       :pretty-print true}}]})
