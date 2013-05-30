(ns cljotp.crypto
  (:import java.security.SecureRandom
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
