(ns reversi.system
  (:require [reversi.command :as command]
            [reversi.store :as store]
            [reversi.view :as view]))

(defn new-game []
  (store/reset-stones!)
  (let [color :b]
    (println "新しいゲームを開始します。")
    (println "黒の番です。")
    (println (view/render-board @store/stones))))

(defn black-hand [x y]
  (swap! store/stones command/put-stone [x y] :b)
  (println "白の番です。")
  (println (view/render-board @store/stones)))

(defn white-hand [x y]
  (swap! store/stones command/put-stone [x y] :w)
  (println "黒の番です。")
  (println (view/render-board @store/stones)))
