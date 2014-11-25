(ns clojbot.core
  (:require [irclj.core :as irc]))

(defn handle-privmsg [conn args]
  (let [{:keys [text 
                target 
                nick 
                host 
                command]} args]
    (prn command text target nick host)  
    (irc/message conn target (str "Hi " nick "! You said: " text))))

(defn handle-notice [conn args]
  (let [{:keys [text 
                target 
                nick 
                host 
                command]} args]
    (prn command nick)))

(defn handle-ctcp [conn args]
  (prn args))

(defn -main [& args]
  (let [server "irc.cat.pdx.edu"
        port 6697
        bot-nick "clojbot"
        ssl  true 
        callbacks {:privmsg handle-privmsg :notice handle-notice :ctcp-action handle-ctcp}
        conn (irc/connect server port bot-nick :ssl? ssl :callbacks callbacks)
        chan "#botgrounds"]
    (irc/join conn chan)
    (irc/message conn chan "Hello, World!")
    (irc/ctcp conn chan :action " is a bot!")))
