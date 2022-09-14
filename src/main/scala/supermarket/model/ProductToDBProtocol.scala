package supermarket.model

import cats.effect.IO
import doobie.util.transactor.Transactor
import doobie.{Read, Write}

import supermarket.config.Config

trait ProductToDBProtocol {

  //TODO: da cambiare il modo con cui vengono passate username+password
  val xa: Transactor[IO] = Transactor.fromDriverManager[IO](
    Config.db.driver,
    Config.db.url,
    Config.db.user,
    Config.db.password
  )

  //necessari per doobie
  implicit val productRead: Read[Product] =
    Read[(Int, String, String, String)].map {
      case (id, name, brand, brandproducer) =>
        Product(ProductId(id),
          ProductName(name),
          ProductBrand(brand),
          ProductBrandProducer(brandproducer))
    }

  implicit val productWrite: Write[Product] =
    Write[(Int, String, String, String)].contramap {
      case Product(ProductId(id),
      ProductName(name),
      ProductBrand(brand),
      ProductBrandProducer(brandproducer)) => (id, name, brand, brandproducer)
    }
}

