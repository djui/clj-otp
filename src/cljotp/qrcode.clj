(ns clj-otp.qrcode
  (:use [ring.util.codec :only [url-encode]]))

(declare otp-url)
(declare build-url)
(declare generate-url)

(defn hotp-url
  "Generate a valid OTP-Auth HOTP URL to a QR code image given a label, counter
  and Base32 encoded secret."
  [label counter secret] (otp-url "hotp" label {:counter counter, :secret secret}))

(defn totp-url
  "Generate a valid OTP-Auth TOTP URL to a QR code image given a label and
  Base32 encoded secret."
  [label secret] (otp-url "totp" label {:secret secret}))

(defn- otp-url
  "Generate a valid OTP-Auth URL to a QR code image."
  [type label params]
  ;; URL schema: otpauth://TYPE/LABEL?PARAMETERS
  (let [base (format "otpauth://%s/%s" type (url-encode label))
        otpauth-url (build-url base params)]
    (generate-url otpauth-url)))

(defn- make-query-string
  "Encode key-value query pairs to an URL encoded query string."
  [m & [encoding]]
  (let [s #(if (instance? clojure.lang.Named %) (name %) %)
        enc (or encoding "UTF-8")]
    (->> (for [[k v] m]
           (str (url-encode (s k) enc) "=" (url-encode (str v) enc)))
         (interpose "&")
         (apply str))))

(defn- build-url
  "Construct a valid URL given a base and query key-value pairs."
  [url-base query-map & [encoding]]
  (str url-base "?" (make-query-string query-map encoding)))

(defn- generate-url
  "Construct an URL to a QR code generate by the (deprecated) Google
  Infographics developer API."
  [data]
  (let [params {:chs "200x200", :cht "qr", :chl data}]
    (build-url "https://chart.googleapis.com/chart" params)))
