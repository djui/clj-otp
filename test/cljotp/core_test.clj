(ns clj-otp.core-test
  (:use clojure.test
        clj-otp.base32
        clj-otp.core))

(deftest test-hotp
  (testing "HOTP code generation"
    (is (= 765705 (hotp (decode-data "MFRGGZDFMZTWQ2LK") 1)))
    (is (= 816065 (hotp (decode-data "MFRGGZDFMZTWQ2LK") 2)))))
