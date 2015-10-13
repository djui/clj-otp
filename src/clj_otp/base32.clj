(ns clj-otp.base32
  (:import org.apache.commons.codec.binary.Base32))


(defn encode-data
  "Encode a binary to base32 string."
  [data]
  (.encodeAsString (Base32.) data))

(defn encode
  "Encode a string to base32 string."
  [string]
  (encode-data (.getBytes string)))

(defn decode-data
  "Decode a base32 string to binary."
  [string]
  (.decode (Base32.) string))

(defn decode
  "Decode a base32 string to string."
  [string]
  (String. (decode-data string)))
