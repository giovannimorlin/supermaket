package supermarket.config

import com.typesafe.config.ConfigFactory
import pureconfig.ConfigSource
import pureconfig.generic.auto._

object Config {
  //TODO:refactoring to pure
  val s = ConfigSource.default.at("server").load[ServerConfig] match {
    case Right(conf) => conf
    case Left(error) =>throw new Exception(error.toString())
  }
  val db = ConfigSource.default.at("database").load[DbConfig] match {
    case Right(conf) => conf
    case Left(error) => throw new Exception(error.toString())
  }
}
