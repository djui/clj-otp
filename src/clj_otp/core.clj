(ns clj-otp.core
  (:import java.util.Date
           java.nio.ByteBuffer)
  (:require [clj-otp.crypto :as crypto]))


(declare time-steps)

(defn hotp
  "Return a one time token based on a secret and a interval number, as in
   \"HOTP: An HMAC-Based One-Time Password Algorithm\" -
   https://tools.ietf.org/html/rfc4226"
  [secret counter]
  (let [data (.array (.putLong (ByteBuffer/allocate 8) counter))
        digest (crypto/hmac-sha1 secret data)
        offset (bit-and (digest 19) 0xf)
        code (bit-or (bit-shift-left (bit-and (digest offset) 0x7f) 24)
                     (bit-shift-left (bit-and (digest (+ offset 1)) 0xff) 16)
                     (bit-shift-left (bit-and (digest (+ offset 2)) 0xff) 8)
                     (bit-and (digest (+ offset 3)) 0xff))]
      (rem code 1000000)))

(defn totp
  "Return a one time token based on the time, as in \"TOTP: Time-Based
  One-Time Password Algorithm\" - http://tools.ietf.org/html/rfc6238"
  [secret]
  ;; "We RECOMMEND a default time-step size of 30 seconds. This default value
  ;; of 30 seconds is selected as a balance between security and usability."
  (hotp secret (time-steps 30)))

(defn accept?
  "Validate an one-time password from a prover against a secret from a verifier."
  ;; Because of possible clock drifts between a client and a validation server,
  ;; we RECOMMEND that the validator be set with a specific limit to the number
  ;; of time steps a prover can be "out of synch" before being rejected.
  ([otp secret] (= otp (totp secret)))
  ([otp secret counter] (= otp (hotp secret counter))))

(defn secret-key
  "Generate a random secret key."
  []
  ;; "Keys SHOULD be of the length of the HMAC output to facilitate
  ;; interoperability."
  (crypto/random-bytes 10))

(defn- timestamp
  "Convert milliseconds since epoch to a Unix Timestamp."
  [milliseconds]
  (int (/ milliseconds 1000)))

(defn- time-steps
  "Calculate the time steps from now (UTC) since epoch given a time step."
  [time-step]
  (int (/ (timestamp (.getTime (Date.))) time-step)))
