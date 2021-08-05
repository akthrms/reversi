(ns reversi.command)

(defn line-of-stones [stones [x-pos y-pos] [x-dir y-dir]]
  (loop [x (+ x-pos x-dir)
         y (+ y-pos y-dir)
         collected []]
    (if-let [stone (get stones [x y])]
      (recur x
             y
             (conj collected [[x y stone]]))
      collected)))

(def directions
  (->> (for [x [-1 0 1]
             y [-1 0 1]]
         [x y])
       (remove (partial every? zero?))))

(defn reverse-targets [stones position color]
  (let [reversed-color? (partial = (if (= color :b) :w :b))]
    (reduce (fn [targets direction]
              (let [[sandwiches opposite] (->> (line-of-stones stones position direction)
                                               (split-with #(reversed-color? (second %))))]
                (cond-> targets
                        (seq opposite)
                        (into (map first sandwiches)))))
            []
            directions)))

(defn put-stone [stones [position] color]
  (reduce (fn [stones position] (assoc stones position color))
          stones
          (conj (reverse-targets stones position color) position)))
