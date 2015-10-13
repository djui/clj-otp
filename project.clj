(defproject clj-otp "0.1.3"
  :description "Generate one time passwords using HOTP and TOTP."
  :url "http://github.com/ttasterisco/clj-otp"
  :licence "See the LICENSE file"
  :dependencies [
    [org.clojure/clojure "1.6.0"]
    [commons-codec "1.6"]
    [ring/ring-codec "1.0.0"]
  ]
  :deploy-repositories [
    ["clojars" {:sign-releases false}]
  ]
)
