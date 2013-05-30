(ns cljotp.base32-test
  (:use clojure.test
        cljotp.base32))

(deftest test-base32-conversion
  (testing "Base32 encoding->decoding"
    (is (= "foo" (decode (encode "foo"))))))

(deftest test-base32-encode
  (testing "Base32 encoding"
    (is (= "MZXW6===" (encode "foo")))))

(deftest test-base32-decode
  (testing "Base32 decoding"
    (is (= "foo" (decode "MZXW6===")))))
