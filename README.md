# clj-otp

[![Build Status](https://secure.travis-ci.org/djui/clj-otp.png)](https://travis-ci.org/djui/clj-otp)

Generate one time passwords, [HOTP](http://en.wikipedia.org/wiki/HOTP)s and
[TOTP](http://en.wikipedia.org/wiki/Time-based_One-time_Password_Algorithm)s for 
your application. Should work with the Google Authenticator.

## Clojure API

Get a HTOP based on a counter:

```clojure
(hotp secret counter)
```

Get a TOTP based on the time:

```clojure
(totp secret)
```

Get a HOTP based QR code image url:

```clojure
(hotp-url label counter secret)
```

Get a TOTP based QR code image url:

```clojure
(totp-url label secret)
```

## Licence

Check the [LICENCE](https://github.com/djui/clj-otp/raw/master/LICENSE) file.
