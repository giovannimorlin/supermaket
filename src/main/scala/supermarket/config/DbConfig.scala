package supermarket.config

case class DriverDbConfig(driver: String)
case class UrlDbConfig(url:String)
case class UserDbConfig(user:String)
case class PaswordDbConfig(password: String)

case class DbConfig(driver: String,
                    url: String,
                    user: String,
                    password: String)

// da aggiungere gli impliciti V case class
// (https://pureconfig.github.io/docs/non-automatic-derivation.html)