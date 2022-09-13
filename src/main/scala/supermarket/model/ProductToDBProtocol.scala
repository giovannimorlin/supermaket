package supermarket.model
import cats.effect.IO
import doobie.util.transactor.Transactor
import doobie.{Read, Write}

trait ProductToDBProtocol {

  val xa: Transactor[IO] = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql:myproducts",
    "docker",
    "docker"
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

