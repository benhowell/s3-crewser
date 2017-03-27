(ns server.controllers.aws.s3
  (:require
   [clojure.edn :as edn]
   [s3-beam.handler :as s3b]
   [amazonica.aws.s3 :as s3]
   [clojure.string :as s]
   [clojure.java.io :as io])
  (:use [amazonica.core]))

(def aws-config
  (edn/read-string
   (slurp (.getFile (io/resource "aws-config.edn")))))

(def bucket (get-in aws-config [:config :bucket]))
(def aws-zone (get-in aws-config [:config :zone]))
(def access-key-id (get-in aws-config [:config :access-key-id]))
(def secret-key (get-in aws-config [:config :secret-key]))

;; amazonica specific
(def cred {:access-key access-key-id
           :secret-key secret-key
           :endpoint   aws-zone})

(defn list-objects []
  (s3/list-objects cred :bucket-name bucket))


(defn delete-object-by-key! [key]
  (try
    (s3/delete-object cred bucket key)
    (catch Exception e {:error e})))

(defn delete-object-by-url! [url]
  (let [key (second (s/split url (re-pattern (str bucket "/")) 2))]
    (delete-object-by-key! key)))
;; end amazonica specific


;; s3-beam specific
(defn s3-sign []
  (s3b/s3-sign bucket aws-zone access-key-id secret-key))
;; end s3-beam specific
