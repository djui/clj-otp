(ns cljotp.crypto
  (:import org.apache.commons.codec.binary.Base32
           java.security.SecureRandom
           javax.crypto.spec.SecretKeySpec
           javax.crypto.Mac))


(defn hmac-sha1
  "Return the HMAC-SHA-1 digest for a given secret and data."
  [secret, data]
  (let [spec (SecretKeySpec. secret "HmacSHA1")
        sha-mac (let [mac (Mac/getInstance "HmacSHA1")]
                  (.init mac spec) ; mutable op!
                  mac)
        hash (into [] (.doFinal sha-mac data))] ; mutable op!
    hash))

(defn random-bytes
  "Generate a random byte array."
  [size]
  (let [bytes (byte-array size)]
    ;; Native vs SHA1 PRNG?
    ;; (.nextBytes (SecureRandom.) bytes) ; mutable op!
    (.nextBytes (SecureRandom/getInstance "SHA1PRNG") bytes) ; mutable op!
    bytes))

(defn base32-encode-data
  "Encode a binary to base32 string."
  [data]
  (.encodeToString (Base32.) data))

(defn base32-encode
  "Encode a string to base32 string."
  [string]
  (base32-encode-data (.getBytes string)))

(defn base32-decode
  "Decode a base32 string to binary."
  [string]
  (.decode (Base32.) string))
