package supermarket

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import supermarket.config
import supermarket.config.Config
import supermarket.service.ProductService


object Main extends App {

  implicit val system = ActorSystem(Behaviors.empty, "supermarket")
  implicit val ec     = system.executionContext

  //TODO: problema! nel caso sbagli a nserire dati nella configurazione (porta db e webserver uguali) l'errore non viene mostrato/gestito!
  Http().newServerAt(Config.s.host, Config.s.port).bind(ProductService.ServerRoute)
}