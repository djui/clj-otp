# clj-otp

[![Build Status](https://secure.travis-ci.org/djui/clj-otp.png)](https://travis-ci.org/djui/clj-otp)

Generate one time passwords, [HOTP](http://en.wikipedia.org/wiki/HOTP)s and
[TOTP](http://en.wikipedia.org/wiki/Time-based_One-time_Password_Algorithm)s for 
your application. Should work with the Google Authenticator.

Clojure API
-----------

Get a HTOP based on a interval number

``` clojure
(get-hotp secret interval)
```

Get a TOTP based on the time

``` clojure
(get-totp secret)
```

The secret needs to be a base32 encoded secret key!

Licence
-------

Check the [LICENCE](https://github.com/djui/clj-otp/raw/master/LICENSE) file.
