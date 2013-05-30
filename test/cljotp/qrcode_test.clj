(ns cljotp.qrcode-test
  (:use clojure.test
        cljotp.base32
        cljotp.qrcode))

(deftest test-otp-url
  (testing "QR Code image url generation"
    (is (= "https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=otpauth%3A%2F%2Fhotp%2Ffoo%2540bar%3Fcounter%3D1%26secret%3DMJQXU%253D%253D%253D"
           (hotp-url "foo@bar" 1 (encode "baz"))))
    (is (= "https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=otpauth%3A%2F%2Ftotp%2Ffoo%2540bar%3Fsecret%3DMJQXU%253D%253D%253D"
           (totp-url "foo@bar" (encode "baz"))))))
