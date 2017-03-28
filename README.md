# s3-crewser
AWS s3 upload, list and delete helper for clojure web apps

Small server-side wrapper for amazonica and s3-beam for uploading, listing and deleting files from your clojure(script) web app.

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
List objects like so:
```clj
(ns server.some.namespace
  (:require
   [server.controllers.aws.s3 :as aws-s3]))
   
(aws-s3/list-objects)
```

Delete an object like so:
```clj
(ns server.some.namespace
  (:require
   [server.controllers.aws.s3 :as aws-s3]))
   
(aws-s3/delete-object-by-url! url)
;; OR
(aws-s3/delete-object-by-key! key)
```

## Client-side usage
Upload an object like so (where `uploaded` is a core.async channel):
```cljs
(ns client.some.namespace
  (:require 
    [s3-beam.client :as s3]))
    
(s3/s3-pipe uploaded)
    
```

For more info on how to use channels with s3-beam, see the s3-beam documentation: https://github.com/martinklepsch/s3-beam#3-integrate-the-upload-pipeline-into-your-frontend



### Obscure name
s3-crewser is a silly name made from s3 CReate dElete for broWSER
