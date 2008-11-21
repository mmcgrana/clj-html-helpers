(ns clj-html.helpers
  (:use [clj-html.core       :only (html domap-str)]
        [clojure.contrib.def :only (defvar)]))

; Derived from compojure

(defn escape-html
  "Change special characters into HTML character entities."
  [string]
  (.. (str string)
    (replace "&"  "&amp;")
    (replace "<"  "&lt;")
    (replace ">"  "&gt;")
    (replace "\"" "&quot;")))

(defvar h escape-html
  "Alias for escape-html")

(defvar doctype
  {:html4
    "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\"
    \"http://www.w3.org/TR/html4/strict.dtd\">\n"

   :xhtml-strict
    "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"
    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"

   :xhtml-transitional
    "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"
    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"}
  "Map of doctype symbols to corresponding declaration strings.")

(defn include-js
  "Include a list of external javascript files."
  [& scripts]
    (domap-str [script scripts]
      (html [:script {:type "text/javascript" :src script} ""])))

(defn include-css
  "Include a list of external stylesheet files."
  [& styles]
  (domap-str [style styles]
    (html [:link {:type "text/css" :href style :rel "stylesheet"}])))

