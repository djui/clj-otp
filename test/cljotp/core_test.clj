(ns cljotp.core-test
  (:use clojure.test
        cljotp.base32
        cljotp.core))

(deftest test-hotp
  (testing "HOTP code generation"
    (is (= 765705 (hotp (decode-data "MFRGGZDFMZTWQ2LK") 1)))
    (is (= 816065 (hotp (decode-data "MFRGGZDFMZTWQ2LK") 2)))))
