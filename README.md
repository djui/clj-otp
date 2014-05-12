# clj-otp

Generate one time passwords, [HOTP](http://en.wikipedia.org/wiki/HOTP)s based on [RFC 4226](https://tools.ietf.org/html/rfc4226) and
[TOTP](http://en.wikipedia.org/wiki/Time-based_One-time_Password_Algorithm)s based on [RFC 6238](http://tools.ietf.org/html/rfc6238) for your application.
Works with the Google Authenticator.

Originally implemented by [omarkj](https://github.com/omarkj/cljotp) and forked from [djui](https://github.com/djui/clj-otp).

## Installation

Clojars package name:

    [clj-otp "0.1.3"]

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

## License

Check the [LICENSE](https://github.com/djui/clj-otp/raw/master/LICENSE) file.
