# s3-crewser
AWS s3 upload and delete helper for clojure web apps

Small server-side wrapper for amazonica and s3-beam for uploading and deleting files from your clojure(script) web app.

Requires:
 - https://github.com/martinklepsch/s3-beam
 - https://github.com/mcohen01/amazonica


## Server-side config
Wherever you define your routes, add a path for "/sign" like so:

```clj
(defroutes

  ;; aws s3-beam
   (GET "/sign" {params :params} (aws-s3/s3-sign))
  ;; end aws s3-beam

)
```

## Server-side usage
Delete an object like so:
```clj
(ns server.some.namespace
  (:require
   [server.controllers.aws.s3 :as aws-s3]))
   
(aws-s3/delete-object-by-url! url)
```

