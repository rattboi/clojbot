(ns clojbot.core
  (:require [irclj.core :as irc]))

(defn -main [& args]
  (let [conn (irc/connect "irc.cat.pdx.edu" 6697 "clojbot" :ssl? true)
        chan "#botgrounds"]
    (irc/join conn chan)
    (irc/message conn chan "Hello, World!")
    (irc/ctcp conn chan :action " is a bot!")))
