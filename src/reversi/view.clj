(ns reversi.view
  (:require [clojure.string :as str]))

(def header
  (str "     "
       (->> (range (int \A) (inc (int \H)))
            (map #(format " %s " (char %)))
            (str/join "|"))))

(def outline
  (format "   +%s+" (str/join "-" (repeat 8 "---"))))

(def line
  (format "---+%s+" (str/join "+" (repeat 8 "---"))))

(defn render-stone [stone]
  (case stone
    :b "●"
    :w "○"
    " "))

(defn render-board [stones]
  (str header \newline
       outline \newline
       (str/join (str \newline line \newline)
                 (for [y (range 8)]
                   (format " %s | %s |"
                           (inc y)
                           (str/join " | "
                                     (for [x (range 8)]
                                       (-> stones
                                           (get [x y])
                                           render-stone))))))
       \newline outline))
